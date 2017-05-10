package nl.han.ica.MichaelJacksonVSTheMoonwalkers.src.models.enemy;

import com.sun.istack.internal.Nullable;
import nl.han.ica.MichaelJacksonVSTheMoonwalkers.src.classes.MichaelJacksonVSTheMoonwalkers;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;

import java.util.List;

/**
 * Created by tiesbaltissen on 20-04-17.
 */
public class ZombieBoss extends Zombie {

    private int health;

    public ZombieBoss(ScalingSize size, int xPosition, float velocity, int damage, Sprite sprite, int health) {
        super(size, xPosition, velocity, damage, sprite);
        this.health = health;
    }
}
