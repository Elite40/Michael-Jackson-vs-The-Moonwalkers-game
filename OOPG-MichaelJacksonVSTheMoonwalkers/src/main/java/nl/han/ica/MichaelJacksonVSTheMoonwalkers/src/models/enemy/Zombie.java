package nl.han.ica.MichaelJacksonVSTheMoonwalkers.src.models.enemy;

/**
 * Created by tiesbaltissen on 20-04-17.
 */

enum ScalingSize {
    Default,
    Small,
    Large
}

enum ZombieType {
    ZombieLanky,
    ZombieSuperior,
    ZombieBird,
    ZombieBoss
}

public class Zombie {

    private ScalingSize size;
    private float velocity;
    private int damage;

    public Zombie(ScalingSize size, float velocity, int damage) {
        this.size = size;
        this.velocity = velocity;
        this.damage = damage;
    }
}
