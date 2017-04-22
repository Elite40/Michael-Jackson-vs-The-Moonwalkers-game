package nl.han.ica.MichaelJacksonVSTheMoonwalkers.src.models.player;

import nl.han.ica.MichaelJacksonVSTheMoonwalkers.src.classes.GameSession;
import nl.han.ica.MichaelJacksonVSTheMoonwalkers.src.classes.MichaelJacksonVSTheMoonwalkers;
import nl.han.ica.MichaelJacksonVSTheMoonwalkers.src.models.enemy.Zombie;
import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithGameObjects;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.AnimatedSpriteObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;

import java.util.List;

/**
 * Created by tiesbaltissen on 20-04-17.
 */

enum Direction {
    Left(270),
    Right(90),
    Up(0),
    Down(180);

    private final float value;

    Direction(final float newValue) {
        value = newValue;
    }

    public float getValue() { return value; }
}

public class MJ extends AnimatedSpriteObject implements ICollidableWithGameObjects {

    private int velocity;
    private int health;
    private int damage;
    private List<SpriteGroup> spriteList;
    private final MichaelJacksonVSTheMoonwalkers game;

    private GameSession session = GameSession.sharedInstance();

    public MJ(int health, int damage, int velocity, List<SpriteGroup> spriteList, MichaelJacksonVSTheMoonwalkers game) {
        super(spriteList.get(0).sprites.get(0), spriteList.size());
        this.health = health;
        this.damage = damage;
        this.velocity = velocity;
        this.spriteList = spriteList;
        this.game = game;
    }

    public int getHealth() {
        return 0;
    }

    public void setHealth(int damageTaken) {

    }

    public void damageTaken(int damage) {

    }

    public void attack(Direction direction) {
        if (1==1) {
            session.setScore(session.getScore() + 5);
        }
    }

    public void jump(Direction direction) {

    }

    /**
     *
     * @param direction
     */

    public void move(Direction direction) {
        setDirectionSpeed(direction.getValue(), this.velocity);
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
                move(Direction.Up);
                break;
            case DOWN:
                move(Direction.Down);
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
