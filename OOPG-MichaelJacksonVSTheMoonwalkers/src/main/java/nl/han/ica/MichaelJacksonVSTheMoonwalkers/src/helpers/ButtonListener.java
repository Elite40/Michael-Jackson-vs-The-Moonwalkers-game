package nl.han.ica.MichaelJacksonVSTheMoonwalkers.src.helpers;

import nl.han.ica.MichaelJacksonVSTheMoonwalkers.src.classes.GameSession;
import nl.han.ica.MichaelJacksonVSTheMoonwalkers.src.classes.MichaelJacksonVSTheMoonwalkers;

/**
 * Created by tiesbaltissen on 12-05-17.
 */
public class ButtonListener {

    private static int buttonWidth = 150;
    private static int buttonHeight = 50;
    private static int xPositionOfButton = (MichaelJacksonVSTheMoonwalkers.worldWidth / 2) - (buttonWidth / 2);

    public static boolean clickedPlayButton(int mouseY) {
        int yPositionOfPlayButton = 190;
        return (mouseY > yPositionOfPlayButton && mouseY < yPositionOfPlayButton + buttonHeight);
    }

    public static boolean clickedHighscoreButton(int mouseY) {
        int yPositionOfHigscoreButton = 280;
        return (mouseY > yPositionOfHigscoreButton && mouseY < yPositionOfHigscoreButton + buttonHeight);
    }

    public static boolean clickedHowToPlayButton(int mouseY) {
        int yPositionOfHowToPlayButton = 370;
        return (mouseY > yPositionOfHowToPlayButton && mouseY < yPositionOfHowToPlayButton + buttonHeight);
    }

    public static boolean clickedEasyButton(int mouseY) {
        int yPositionOfEasyButton = 100;
        return (mouseY > yPositionOfEasyButton && mouseY < yPositionOfEasyButton + buttonHeight);
    }

    public static boolean clickedIntermediateButton(int mouseY) {
        int yPositionOfIntermediateButton = 190;
        return (mouseY > yPositionOfIntermediateButton && mouseY < yPositionOfIntermediateButton + buttonHeight);
    }

    public static boolean clickedNormalButton(int mouseY) {
        int yPositionOfNormalButton = 280;
        return (mouseY > yPositionOfNormalButton && mouseY < yPositionOfNormalButton + buttonHeight);
    }

    public static boolean clickedHardButton(int mouseY) {
        int yPositionOfHardButton = 370;
        return (mouseY > yPositionOfHardButton && mouseY < yPositionOfHardButton + buttonHeight);
    }

    public static boolean clickedBackButton(int mouseY) {
        int yPositionOfBackButton = 418;
        return (mouseY > yPositionOfBackButton && mouseY < yPositionOfBackButton + buttonHeight);
    }

    public static boolean clickedMusicButton(int mouseX, int mouseY, int width, int height) {
        return ((mouseX > 4 && mouseX < 4 + width) && mouseY > 4 && mouseY < 4 + height);
    }

    public static boolean clickedInWidthBounds(int mouseX) {
        return (mouseX > xPositionOfButton && mouseX < xPositionOfButton + buttonWidth);

    }
}
