package nl.han.ica.MichaelJacksonVSTheMoonwalkers.src.models.enemy;

import com.sun.istack.internal.Nullable;
import nl.han.ica.MichaelJacksonVSTheMoonwalkers.src.classes.MichaelJacksonVSTheMoonwalkers;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;

import java.util.List;
import java.util.Random;

/**
 * Created by tiesbaltissen on 20-04-17.
 */
public class ZombieBoss extends Zombie {
    private float originalVelocity;

    public ZombieBoss(Direction direction, int xPosition, float velocity, int damage, int points, Sprite sprite, MichaelJacksonVSTheMoonwalkers game) {
        super(direction, xPosition, velocity, damage, points, sprite, game);
        this.originalVelocity = velocity;
    }

    @Override
    public void move(Direction direction) {
        Random random = new Random();
        boolean stopped = (random.nextInt(5) < 1);
        setX((direction == Direction.Left) ? getX() - (2 * ((stopped) ? 0 : getVelocity())) : getX() + (2 * getVelocity()));
    }
}
