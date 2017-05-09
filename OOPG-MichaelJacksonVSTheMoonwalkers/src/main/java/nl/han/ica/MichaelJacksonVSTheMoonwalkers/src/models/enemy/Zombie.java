package nl.han.ica.MichaelJacksonVSTheMoonwalkers.src.models.enemy;

import com.sun.istack.internal.Nullable;
import nl.han.ica.MichaelJacksonVSTheMoonwalkers.src.classes.MichaelJacksonVSTheMoonwalkers;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.AnimatedSpriteObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import processing.core.PGraphics;

import java.util.List;

/**
 * Created by tiesbaltissen on 20-04-17.
 */



public class Zombie extends AnimatedSpriteObject {

    private ScalingSize size;
    private int xPosition;
    private float velocity;
    private int damage;
    private Sprite sprite;

    public Zombie(ScalingSize size, int xPosition, float velocity, int damage, Sprite sprite) {
        super(sprite, 12);
        this.size = size;
        this.xPosition = xPosition;
        this.velocity = velocity;
        this.damage = damage;
        this.sprite = sprite;
        setCurrentFrameIndex(0);
    }

    public static Sprite zombieSprites(ZombieType type) {
        switch(type) {
            case ZombieLanky:
                return new Sprite(getLankySprite());
            case ZombieBird:
                return new Sprite(getBirdSprite());
            case ZombieBoss:
                return new Sprite(getBossSprite());
        }
        return new Sprite(getLankySprite());
    }

    private static String getLankySprite() {
        return "src/main/java/nl/han/ica/MichaelJacksonVSTheMoonwalkers/res/drawable/MJ/mj_movement.png";
    }

    private static String getBirdSprite() {
        return "src/main/java/nl/han/ica/MichaelJacksonVSTheMoonwalkers/res/drawable/MJ/mj_movement.png";
    }

    private static String getBossSprite() {
        return "src/main/java/nl/han/ica/MichaelJacksonVSTheMoonwalkers/res/drawable/MJ/mj_movement.png";
    }

    public int getxPosition() {
        return xPosition;
    }

    public Sprite getSprite() {
        return sprite;
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(PGraphics g) {

    }
}
