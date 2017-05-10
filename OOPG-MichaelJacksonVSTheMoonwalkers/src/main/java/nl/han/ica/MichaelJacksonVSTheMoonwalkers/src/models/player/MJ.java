package nl.han.ica.MichaelJacksonVSTheMoonwalkers.src.models.player;

import com.sun.istack.internal.Nullable;
import nl.han.ica.MichaelJacksonVSTheMoonwalkers.src.classes.GameSession;
import nl.han.ica.MichaelJacksonVSTheMoonwalkers.src.classes.MichaelJacksonVSTheMoonwalkers;
import nl.han.ica.MichaelJacksonVSTheMoonwalkers.src.models.enemy.Zombie;
import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithGameObjects;
import nl.han.ica.OOPDProcessingEngineHAN.Dashboard.Dashboard;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.AnimatedSpriteObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.waterworld.TextObject;
import sun.rmi.runtime.Log;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Logger;

/**
 * Created by tiesbaltissen on 20-04-17.
 */

enum SpriteFrameIndex {
    MovementLeft(5),
    MovementRight(11),
    AttackLeft(3),
    AttackRight(7),
    JumpLeft(2),
    JumpRight(5);

    private final int value;

    SpriteFrameIndex(final int newValue) { value = newValue; }
    public int getValue() { return value; }

}

enum Direction {
    Left(270),
    Right(90),
    Up(0);

    private final float value;

    Direction(final float newValue) {
        value = newValue;
    }

    public float getValue() { return value; }
}

public class MJ extends AnimatedSpriteObject implements ICollidableWithGameObjects {

    private Direction direction;
    private int velocity;
    private float health;
    private int damage;
    private boolean inTheAir;
    private final MichaelJacksonVSTheMoonwalkers game;
    private Timer animationTimer;
    private boolean isJumping = false;
    private boolean isAttacking = false;

    private Dashboard greenHealthBar;
    private Dashboard healthBarContainer;
    private final int healthBarWidth = 200;
    private float yPositionHealthBar = 5;
    private float xPositionHealthBar;

    private GameSession session = GameSession.sharedInstance();

    public MJ(int damage, int velocity, Sprite sprite, MichaelJacksonVSTheMoonwalkers game) {
        super(sprite, 12);
        this.health = 100;
        this.damage = damage;
        this.velocity = velocity;
        this.game = game;
        this.inTheAir = false;
        this.direction = Direction.Left;
        setCurrentFrameIndex(0);
        this.drawHealthBar();
    }

    /**
     * Draws the health bar objects. One is the real health bar. The other one
     * is the container, which has a red background color.
     * And the health text.
     */
    private void drawHealthBar() {
        xPositionHealthBar = (this.game.getWorldWidth()/2) - this.healthBarWidth/2;

        this.healthBarContainer = new Dashboard(xPositionHealthBar, yPositionHealthBar, 200, 20);
        this.healthBarContainer.setBackground(	231, 76, 60);

        this.greenHealthBar = new Dashboard(xPositionHealthBar, 5, 180, 20);
        this.greenHealthBar.setBackground(42, 189, 104);

        TextObject healthText = new TextObject("Health:");
        healthText.setFontSize(14);
        healthText.setX(xPositionHealthBar - 60);
        healthText.setY(6);

        //Temporary call to test the updateHealthBar() function.
        this.updateHealthBar();

        this.game.addDashboard(this.healthBarContainer);
        this.game.addDashboard(this.greenHealthBar);
        this.game.addGameObject(healthText);
    }

    /**
     * This method has to be called every time when the player get hit.
     */
    private void updateHealthBar() {
        //Calculating the width of the health bar.
        float width = this.health/100 * this.healthBarWidth;

        // Used math round because setWidth only wants an integer as parameter.
        this.greenHealthBar.setWidth(Math.round(width));
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
        health -= damageTaken;
    }

    public void damageTaken(int damage) {
        setHealth(damage);
    };

    public void attack(Direction direction) {
        setSprite(getMJAttackSprite(), 8);
        if (direction == Direction.Left) {
            setCurrentFrameIndex(SpriteFrameIndex.AttackLeft.getValue());
        } else if (direction == Direction.Right) {
            setCurrentFrameIndex(SpriteFrameIndex.AttackRight.getValue());
        }
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
            setDirection(direction.getValue());
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
            setDirection(direction.getValue());
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
                    if (getCurrentFrameIndex() > (totalFrames / 2) - 1 && getCurrentFrameIndex() < totalFrames - 1) {
                        setCurrentFrameIndex(getCurrentFrameIndex() + 1);
                        if (getX() < game.getScreenSize()[0] - 40) {
                            setX(getX() + distanceTravelled);
                        }
                    } else {
                        setCurrentFrameIndex(totalFrames / 2);
                    }
                } else {
                    if (getCurrentFrameIndex() < (totalFrames / 2) - 1) {

                        setCurrentFrameIndex(getCurrentFrameIndex() + 1);
                        if (getX() > 0) {
                            setX(getX() - distanceTravelled);
                        }
                    } else {
                        setCurrentFrameIndex(0);
                    }
                }
                if (repeats <= timesRepeated[0]) {
                    animationTimer.cancel();
                    isJumping = false;
                }
            }
        }, 0, 100);
    }

    private void setSprite(String newSprite, int frames) {
        sprite.setSprite(newSprite);
        setTotalFrames(frames);
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
    public void keyPressed(int keyCode, char key) {
        switch(keyCode) {
            case LEFT:
                move(Direction.Left);
                break;
            case RIGHT:
                move(Direction.Right);
                break;
            case UP:
                jump(this.direction);
                break;
            case BEVEL:
                attack(this.direction);
                break;
        }
    }

    /**
     *
     * @param collidedGameObjects
     */

    @Override
    public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
        for (GameObject g : collidedGameObjects) {
            if (g instanceof Zombie) {

            }
        }
    }
}
