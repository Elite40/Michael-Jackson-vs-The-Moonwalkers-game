package nl.han.ica.MichaelJacksonVSTheMoonwalkers.src.models.player;

import nl.han.ica.MichaelJacksonVSTheMoonwalkers.src.classes.GameSession;
import nl.han.ica.MichaelJacksonVSTheMoonwalkers.src.classes.MichaelJacksonVSTheMoonwalkers;
import nl.han.ica.MichaelJacksonVSTheMoonwalkers.src.models.enemy.Zombie;
import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithGameObjects;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.AnimatedSpriteObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import sun.rmi.runtime.Log;

import java.util.List;
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
    private int health;
    private int damage;
    private Sprite sprite;
    private boolean inTheAir;
    private final MichaelJacksonVSTheMoonwalkers game;

    private GameSession session = GameSession.sharedInstance();

    public MJ(int health, int damage, int velocity, Sprite sprite, MichaelJacksonVSTheMoonwalkers game) {
        super(sprite, 12);
        this.health = health;
        this.damage = damage;
        this.velocity = velocity;
        this.sprite = sprite;
        this.game = game;
        this.inTheAir = false;
        this.direction = Direction.Left;
        setCurrentFrameIndex(0);
    }

    public static Sprite getMJSprite() {
        return new Sprite("src/main/java/nl/han/ica/MichaelJacksonVSTheMoonwalkers/res/drawable/MJ/mj_movement.png");
    }

    public static Sprite getMJDeathSprite() {
        return new Sprite("src/main/java/nl/han/ica/MichaelJacksonVSTheMoonwalkers/res/drawable/MJ/mj_death.png");
    }

    public static Sprite getMJAttackSprite() {
        return new Sprite("src/main/java/nl/han/ica/MichaelJacksonVSTheMoonwalkers/res/drawable/MJ/mj_attack.png");
    }

    public static Sprite  getMJJumpSprite() {
        return new Sprite("src/main/java/nl/han/ica/MichaelJacksonVSTheMoonwalkers/res/drawable/MJ/mj_jump.png");
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int damageTaken) {
        health -= damageTaken;
    }

    public void damageTaken(int damage) {
        setHealth(damage);
    }

    public void attack(Direction direction) {
        setSprite(getMJAttackSprite());
        if (1==1) {
            session.setScore(session.getScore() + 5);
        }
    }

    public void jump(Direction direction) {
        setSprite(getMJJumpSprite());
        if (direction == Direction.Left) {
            setCurrentFrameIndex(SpriteFrameIndex.JumpLeft.getValue());
        } else if (direction == Direction.Right) {
            setCurrentFrameIndex(SpriteFrameIndex.JumpRight.getValue());
        }
        setDirection(direction.getValue());
    }



    /**
     *
     * @param direction
     */

    public void move(Direction direction) {
        this.direction = direction;
        setSprite(getMJSprite());
        if (direction == Direction.Left) {
            setCurrentFrameIndex(SpriteFrameIndex.MovementLeft.getValue());
        } else if (direction == Direction.Right) {
            setCurrentFrameIndex(SpriteFrameIndex.MovementRight.getValue());
        }
        setDirection(direction.getValue());
    }

    private void setSprite(Sprite sprite) {
        this.sprite = sprite;
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
