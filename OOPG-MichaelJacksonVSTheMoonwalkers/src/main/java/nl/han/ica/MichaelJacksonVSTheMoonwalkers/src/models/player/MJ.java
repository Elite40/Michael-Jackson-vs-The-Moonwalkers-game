package nl.han.ica.MichaelJacksonVSTheMoonwalkers.src.models.player;

import com.sun.istack.internal.Nullable;
import nl.han.ica.MichaelJacksonVSTheMoonwalkers.src.classes.GameSession;
import nl.han.ica.MichaelJacksonVSTheMoonwalkers.src.classes.GameState;
import nl.han.ica.MichaelJacksonVSTheMoonwalkers.src.classes.MichaelJacksonVSTheMoonwalkers;
import nl.han.ica.MichaelJacksonVSTheMoonwalkers.src.helpers.HUDCreator;
import nl.han.ica.MichaelJacksonVSTheMoonwalkers.src.models.enemy.Direction;
import nl.han.ica.MichaelJacksonVSTheMoonwalkers.src.models.enemy.Zombie;
import nl.han.ica.MichaelJacksonVSTheMoonwalkers.src.models.enemy.ZombieBoss;
import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithGameObjects;
import nl.han.ica.OOPDProcessingEngineHAN.Dashboard.Dashboard;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.AnimatedSpriteObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.waterworld.TextObject;
import sun.rmi.runtime.Log;

import java.awt.*;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Logger;

/**
 * Created by tiesbaltissen on 20-04-17.
 */


public class MJ extends AnimatedSpriteObject implements ICollidableWithGameObjects {

    private Direction direction;
    private float health;
    private final MichaelJacksonVSTheMoonwalkers game;
    private Timer animationTimer;
    private boolean isJumping = false;
    private boolean isAttacking = false;

    private Dashboard greenHealthBar;
    private Color healthBarColor = new Color(42, 189, 104);
    private Dashboard healthBarContainer;
    private Color healthBarContainerColor = new Color(231, 76, 60);
    private TextObject healthText;
    private TextObject scoreText;
    private final int healthBarWidth = 200;
    private final int healthBarHeight = 20;
    private int yPositionHealthBar = 5;
    private int xPositionHealthBar;

    private GameSession session = GameSession.sharedInstance();

    public MJ(Sprite sprite, MichaelJacksonVSTheMoonwalkers game) {
        super(sprite, 12);
        this.health = 100;
        this.game = game;
        this.direction = Direction.Left;
        setCurrentFrameIndex(0);
    }

    /**
     * This method has to be called every time when the player get hit.
     */
    private void updateHealthBar() {
        //Calculating the width of the health bar.
        float width = getHealth()/100 * this.healthBarWidth;

        if (width >= 0) {
            // Used math round because setWidth only wants an integer as parameter.
            session.greenHealthBar.setWidth(Math.round(width));
        }
    }

    private void updateScoreText(int score) {
        session.setScore(session.getScore()+score);
        session.scoreText.setText("Score: " + session.getScore());
    }

    public static String getMJSprite() {
        return "src/main/java/nl/han/ica/MichaelJacksonVSTheMoonwalkers/res/drawable/MJ/mj_movement.png";
    }

    public static String getMJDeathSprite() {
        return "src/main/java/nl/han/ica/MichaelJacksonVSTheMoonwalkers/res/drawable/MJ/mj_death.png";
    }

    public static String getMJAttackSprite() {
        return "src/main/java/nl/han/ica/MichaelJacksonVSTheMoonwalkers/res/drawable/MJ/mj_attack.png";
    }

    public static String  getMJJumpSprite() {
        return "src/main/java/nl/han/ica/MichaelJacksonVSTheMoonwalkers/res/drawable/MJ/mj_jump.png";
    }

    public float getHealth() {
        return health;
    }

    public void setHealth(int damageTaken) {
        health = (health - damageTaken >= 1) ? health - damageTaken : 1;

        if (health == 1) {
            //Show game over view
            session.setGameState(GameState.GameOver);
            session.alterGameState();
        }
        updateHealthBar();
    }

    public void damageTaken(int damage) {
        setHealth(damage);
    }


    public void attack(Direction direction) {
        this.direction = direction;
        this.setSprite(getMJAttackSprite(), 12);
        this.sprite.resize(75*12, this.sprite.getHeight());
        if (animationTimer != null) {
            animationTimer.cancel();
            animationTimer = null;
        }
        isAttacking = true;
        startAnimationTimer(6, 12, 0);
    }

    public void jump(Direction direction) {
        if (!isJumping) {
            this.direction = direction;
            this.setSprite(getMJJumpSprite(), 8);
            if (animationTimer != null) {
                animationTimer.cancel();
                animationTimer = null;
            }
            isJumping = true;
            startAnimationTimer(4, 8, 30);
        }
    }


    /**
     *
     * @param direction
     */

    public void move(Direction direction) {
        if (!isJumping) {
            this.direction = direction;
            setSprite(getMJSprite(), 12);
            if (animationTimer != null) {
                animationTimer.cancel();
                animationTimer = null;
            }
            startAnimationTimer(6, 12, 10);
        }
    }


    private void startAnimationTimer(int repeats, int totalFrames, @Nullable int distanceTravelled) {
        animationTimer = new Timer();
        int[] timesRepeated = {0};
        animationTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                timesRepeated[0]++;
                if (direction == Direction.Right) {
                    if (session.mj.getCurrentFrameIndex() > (totalFrames / 2) - 1 && session.mj.getCurrentFrameIndex() < totalFrames - 1) {
                        session.mj.setCurrentFrameIndex(session.mj.getCurrentFrameIndex() + 1);
                        if (session.mj.getX() < game.getScreenSize()[0] - 40) {
                            session.mj.setX(session.mj.getX() + distanceTravelled);
                        }
                    } else {
                        session.mj.setCurrentFrameIndex(totalFrames / 2);
                    }
                } else {
                    if (session.mj.getCurrentFrameIndex() < (totalFrames / 2) - 1) {

                        session.mj.setCurrentFrameIndex(session.mj.getCurrentFrameIndex() + 1);
                        if (session.mj.getX() > 0) {
                            session.mj.setX(session.mj.getX() - distanceTravelled);
                        }
                    } else {
                        session.mj.setCurrentFrameIndex(0);
                    }
                }
                if (repeats <= timesRepeated[0]) {
                    animationTimer.cancel();
                    isJumping = false;
                    isAttacking = false;
                }
            }
        }, 0, 100);
    }

    private void setSprite(String newSprite, int frames) {
        session.mj.sprite.setSprite(newSprite);
        session.mj.setTotalFrames(frames);
    }


    @Override
    public void update() {

    }

    /**
     *
     * @param keyCode
     * @param key
     */

    @Override
    public void keyReleased(int keyCode, char key) {
        switch(keyCode) {
            case LEFT:
                move(Direction.Left);
                break;
            case RIGHT:
                move(Direction.Right);
                break;
            case UP:
                jump(session.mj.direction);
                break;
            case BEVEL:
                attack(session.mj.direction);
                break;
            case ENTER:
                if (game.getThreadState()) {
                    session.removePauseText();
                }
                game.countDownFrom(3, (game.getThreadState()) ? GameState.Paused : GameState.Playing);
                break;
        }
    }

    /**
     *
     * @param collidedGameObjects
     */

    @Override
    public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
        if (session.getGameState() != GameState.GameOver) {
            for (GameObject g : collidedGameObjects) {
                if (g instanceof Zombie) {

                    if (isAttacking && ((Zombie)g).getIncomingDirection() != this.direction) {
                        game.deleteGameObject(g);
                        updateScoreText(((Zombie) g).getPoints());
                    } else {
                        if (g instanceof ZombieBoss) {
                            damageTaken(((ZombieBoss) g).getDamage());
                        } else {
                            damageTaken(10);
                        }
                        game.deleteGameObject(g);
                    }
                }
            }
        }
    }
}
