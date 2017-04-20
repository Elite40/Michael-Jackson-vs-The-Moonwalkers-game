package nl.han.ica.MichaelJacksonVSTheMoonwalkers.src.models.enemy;

/**
 * Created by tiesbaltissen on 20-04-17.
 */
public class ZombieBoss extends Zombie {

    private int health;

    public ZombieBoss(ScalingSize size, float velocity, int damage, int health) {
        super(size, velocity, damage);
        this.health = health;
    }
}
