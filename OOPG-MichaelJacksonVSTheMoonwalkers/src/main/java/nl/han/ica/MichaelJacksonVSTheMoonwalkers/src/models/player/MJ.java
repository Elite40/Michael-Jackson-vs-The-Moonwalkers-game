package nl.han.ica.MichaelJacksonVSTheMoonwalkers.src.models.player;

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

    private int health;
    private int damage;
    private float velocity;
    private int game;

    public MJ(int health, int damage, float velocity, int game) {
        this.health = health;
        this.damage = damage;
        this.velocity = velocity;
        this.game = game;
    }

    public int getScore() {

        //TODO make singleton for game sessions
        return 0;
    }

    public void setScore(int score) {
    }

    public int getHealth() {
        return 0;
    }

    public void setHealth(int damageTaken) {

    }

    public void damageTaken(int damage) {

    }

    public void attack(Direction direction) {

    }

    public void jump(Direction direction) {

    }

    public void move(Direction direction) {

    }

}
