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
    private float maxAltitude = 0.9f;
    private float minAltitude = 0.2f;
    private float step = 0.005f;
    private MichaelJacksonVSTheMoonwalkers game;

    public ZombieBird(Direction direction, int xPosition, float velocity, int damage, int points, Sprite sprite, float altitude, MichaelJacksonVSTheMoonwalkers game) {
        super(direction, xPosition, velocity, damage, points, sprite, game);
        this.game = game;
        this.altitude = altitude;
    }

    @Override
    public void move(Direction direction) {
        setX((direction == Direction.Left) ? getX() - (2 * getVelocity()) : getX() + (2 * getVelocity()));
        setY(game.getWorldHeight() - game.getWorldHeight() * altitude);

        altitude += step;
        if (altitude > maxAltitude || altitude < minAltitude) {
            step = step * -1;
            altitude += step;
        }
    }
}
