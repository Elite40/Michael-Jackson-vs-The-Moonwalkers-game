package nl.han.ica.MichaelJacksonVSTheMoonwalkers.src.helpers;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;

import java.util.List;
import processing.core.PApplet;

/**
 * Created by tiesbaltissen on 22-04-17.
 */
public class SpriteLoader extends PApplet {

    public static final String directory = "src/main/java/nl/han/ica/MichaelJacksonVSTheMoonwalkers/res/drawable/";
    public static final String attackLeft = "attack_left";
    public static final String attackRight = "attack_right";
    public static final String deathLeft = "death_left";
    public static final String deathRight = "death_right";
    public static final String jumpLeft = "jump_left";
    public static final String jumpRight = "jump_right";
    public static final String movementLeft = "movement_left";
    public static final String movementRight = "movement_right";


    public static Sprite getMichaelJacksonSprites(String action, String frame, String direction) {
        return new Sprite(String.format("%sMJ/mj_%s_%s/mj_%s%s_%s", directory, action, direction, action, frame, direction));
    }

    public static Sprite getZombieBirdSprites(String direction) {
        return null;
    }
}
