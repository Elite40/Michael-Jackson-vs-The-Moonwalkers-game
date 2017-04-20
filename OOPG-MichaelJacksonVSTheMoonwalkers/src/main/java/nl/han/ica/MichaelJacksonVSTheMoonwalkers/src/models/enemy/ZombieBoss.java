package nl.han.ica.MichaelJacksonVSTheMoonwalkers.src.models.enemy;

/**
 * Created by tiesbaltissen on 20-04-17.
 */
public class ZombieBoss extends Zombie {

    private int health;

    public ZombieBoss(ScalingSize size, float speed, int damage, int health) {
        super(size, speed, damage);
        this.health = health;
    }
}
