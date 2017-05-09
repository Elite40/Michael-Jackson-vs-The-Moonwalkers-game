package nl.han.ica.MichaelJacksonVSTheMoonwalkers.src.models.enemy;

import com.sun.istack.internal.Nullable;
import nl.han.ica.MichaelJacksonVSTheMoonwalkers.src.models.enemy.Zombie;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;

import java.util.List;

/**
 * Created by tiesbaltissen on 20-04-17.
 */
public class ZombieBird extends Zombie {

    private float altitude;

    public ZombieBird(ScalingSize size, int xPosition, float velocity, int damage, Sprite sprite, float altitude) {
        super(size, xPosition, velocity, damage, sprite);
        this.altitude = altitude;
    }
}
