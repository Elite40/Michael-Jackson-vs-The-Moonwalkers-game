package nl.han.ica.MichaelJacksonVSTheMoonwalkers.src.classes;

/**
 * Created by tiesbaltissen on 20-04-17.
 */

public enum Difficulty {
    Easy(2500),
    Normal(2000),
    Intermediate(1500),
    Hard(500);

    private final int value;

    Difficulty(final int newValue) { value = newValue; }

    public int getValue() { return value; }
}
