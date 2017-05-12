package nl.han.ica.MichaelJacksonVSTheMoonwalkers.src.models.enemy;

/**
 * Created by tiesbaltissen on 20-04-17.
 */


public enum Direction {
    Left(270),
    Right(90);

    private final float value;

    Direction(final float newValue) {
        value = newValue;
    }

    public float getValue() { return value; }
}
