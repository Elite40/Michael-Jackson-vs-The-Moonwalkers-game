package nl.han.ica.MichaelJacksonVSTheMoonwalkers.src.models.enemy;

import nl.han.ica.MichaelJacksonVSTheMoonwalkers.src.models.enemy.Zombie;

/**
 * Created by tiesbaltissen on 20-04-17.
 */
public class ZombieBird extends Zombie {

    private float altitude;

    public ZombieBird(ScalingSize size, float speed, int damage, float altitude) {
        super(size, speed, damage);
        this.altitude = altitude;
    }
}
