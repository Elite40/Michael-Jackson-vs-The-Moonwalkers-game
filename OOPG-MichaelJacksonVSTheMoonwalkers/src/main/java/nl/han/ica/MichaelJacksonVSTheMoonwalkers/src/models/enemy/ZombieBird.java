package nl.han.ica.MichaelJacksonVSTheMoonwalkers.src.models.enemy;

import com.sun.istack.internal.Nullable;
import nl.han.ica.MichaelJacksonVSTheMoonwalkers.src.classes.MichaelJacksonVSTheMoonwalkers;
import nl.han.ica.MichaelJacksonVSTheMoonwalkers.src.models.enemy.Zombie;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;

import java.util.List;

/**
 * Created by tiesbaltissen on 20-04-17.
 */
public class ZombieBird extends Zombie {

    public float altitude;

    public ZombieBird(ScalingSize size, Direction direction, int xPosition, float velocity, int damage, int points, Sprite sprite, float altitude, MichaelJacksonVSTheMoonwalkers game) {
        super(size, direction, xPosition, velocity, damage, points, sprite, game);
        this.altitude = altitude;
    }
}
