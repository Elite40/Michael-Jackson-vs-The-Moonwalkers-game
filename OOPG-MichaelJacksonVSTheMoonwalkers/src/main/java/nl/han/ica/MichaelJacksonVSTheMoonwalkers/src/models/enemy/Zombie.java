package nl.han.ica.MichaelJacksonVSTheMoonwalkers.src.models.enemy;

import com.sun.istack.internal.Nullable;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;

import java.util.List;

/**
 * Created by tiesbaltissen on 20-04-17.
 */



public class Zombie {

    private ScalingSize size;
    private int xPosition;
    private float velocity;
    private int damage;
    private List<Sprite> spriteList;

    public Zombie(ScalingSize size, int xPosition, float velocity, int damage, @Nullable List<Sprite> spriteList) {
        this.size = size;
        this.xPosition = xPosition;
        this.velocity = velocity;
        this.damage = damage;
        this.spriteList = spriteList;
    }

    public static List<Sprite> zombieSprites(ZombieType type) {
        switch(type) {
            case ZombieLanky:
                return lankySprites();
            case ZombieSuperior:
                return superiorSprites();
            case ZombieBird:
                return birdSprites();
            case ZombieBoss:
                return bossSprites();
        }
        return lankySprites();
    }

    private static List<Sprite> lankySprites() {
        return null;
    }

    private static List<Sprite> superiorSprites() {
        return null;
    }

    private static List<Sprite> birdSprites() {
        return null;
    }

    private static List<Sprite> bossSprites() {
        return null;
    }
}
