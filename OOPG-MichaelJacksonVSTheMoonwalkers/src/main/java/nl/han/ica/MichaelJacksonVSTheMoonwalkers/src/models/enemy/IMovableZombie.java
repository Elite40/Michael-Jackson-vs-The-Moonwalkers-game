package nl.han.ica.MichaelJacksonVSTheMoonwalkers.src.models.enemy;

/**
 * Created by tiesbaltissen on 12-05-17.
 */
public interface IMovableZombie {

    /**
     * Interface that requires every zombietype to implement a move method. This varies from
     * making a bird go up and down and stopping the zombieboss from moving from time to time.
     * @param direction
     */
    void move(Direction direction);
}
