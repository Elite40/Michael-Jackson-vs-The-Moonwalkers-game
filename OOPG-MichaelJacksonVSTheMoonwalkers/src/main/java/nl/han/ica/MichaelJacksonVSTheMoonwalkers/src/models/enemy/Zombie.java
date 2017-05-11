package nl.han.ica.MichaelJacksonVSTheMoonwalkers.src.models.enemy;

import com.sun.istack.internal.Nullable;
import nl.han.ica.MichaelJacksonVSTheMoonwalkers.src.classes.MichaelJacksonVSTheMoonwalkers;
import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithGameObjects;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.AnimatedSpriteObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import processing.core.PGraphics;

import java.util.List;

/**
 * Created by tiesbaltissen on 20-04-17.
 */



public class Zombie extends AnimatedSpriteObject  implements ICollidableWithGameObjects {

    private ScalingSize size;
    private int xPosition;
    private float velocity;
    private int damage;
    private int points;
    private MichaelJacksonVSTheMoonwalkers game;

    public Zombie(ScalingSize size, int xPosition, float velocity, int damage, int points, Sprite sprite, MichaelJacksonVSTheMoonwalkers game) {
        super(sprite, 1);
        this.size = size;
//        this.sprite = new Sprite();
        this.xPosition = xPosition;
        this.velocity = velocity;
        this.damage = damage;
        this.game = game;
        this.points = points;
//        setSprite(getLankySprite(), 1);
//        setCurrentFrameIndex(0);
    }

    public int getDamage() {
        return damage;
    }

    public float getVelocity() {
        return velocity;
    }

    public int getPoints() {
        return points;
    }

    public static String zombieSprites(ZombieType type) {
        switch(type) {
            case ZombieLanky:
                return getLankySprite();
            case ZombieBird:
                return getBirdSprite();
            case ZombieBoss:
                return getBossSprite();
        }
        return getLankySprite();
    }

    private static String getLankySprite() {
        return "src/main/java/nl/han/ica/MichaelJacksonVSTheMoonwalkers/res/drawable/ZombieLanky/zombielanky_movement_left.png";
    }

    private static String getBirdSprite() {
        return "src/main/java/nl/han/ica/MichaelJacksonVSTheMoonwalkers/res/drawable/ZombieBird/zombiebird_movement_left.png";
    }

    private static String getBossSprite() {
        return "src/main/java/nl/han/ica/MichaelJacksonVSTheMoonwalkers/res/drawable/ZombieSuperior/zombiesuperior_movement_left/zombiesuperior_movement1_left.png";
    }

    @Override
    public void update() {
        setX(getX()+1);
        //System.out.println(getX());
    }


    @Override
    public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {

    }
}
