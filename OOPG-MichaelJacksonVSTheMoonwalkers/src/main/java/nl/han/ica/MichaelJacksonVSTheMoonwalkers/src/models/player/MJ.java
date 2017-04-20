package nl.han.ica.MichaelJacksonVSTheMoonwalkers.src.models.player;

import nl.han.ica.MichaelJacksonVSTheMoonwalkers.src.classes.GameSession;

/**
 * Created by tiesbaltissen on 20-04-17.
 */

enum Direction {
    Left,
    Right,
    Up,
    Down
}

public class MJ {

    private float velocity;
    private int health;
    private int damage;
    private int game;
    private GameSession session = GameSession.sharedInstance();

    public MJ(int health, int damage, float velocity, int game) {
        this.health = health;
        this.damage = damage;
        this.velocity = velocity;
        this.game = game;
    }

    public int getHealth() {
        return 0;
    }

    public void setHealth(int damageTaken) {

    }

    public void damageTaken(int damage) {

    }

    public void attack(Direction direction) {
        if (1==1) {
            session.setScore(session.getScore() + 5);
        }
    }

    public void jump(Direction direction) {

    }

    public void move(Direction direction) {

    }

}
