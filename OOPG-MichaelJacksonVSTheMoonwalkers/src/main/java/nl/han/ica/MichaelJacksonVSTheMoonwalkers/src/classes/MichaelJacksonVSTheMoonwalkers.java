package nl.han.ica.MichaelJacksonVSTheMoonwalkers.src.classes;

import nl.han.ica.OOPDProcessingEngineHAN.Dashboard.Dashboard;
import nl.han.ica.OOPDProcessingEngineHAN.Engine.GameEngine;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import nl.han.ica.OOPDProcessingEngineHAN.Persistence.FilePersistence;
import nl.han.ica.OOPDProcessingEngineHAN.Persistence.IPersistence;
import nl.han.ica.OOPDProcessingEngineHAN.Sound.Sound;
import nl.han.ica.OOPDProcessingEngineHAN.View.View;
import nl.han.ica.waterworld.TextObject;
import nl.han.ica.MichaelJacksonVSTheMoonwalkers.src.helpers.ButtonCreator;
import processing.core.PApplet;

import javax.xml.soap.Text;
import java.awt.*;
import java.util.List;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Created by tiesbaltissen on 21-04-17.
 */
public class MichaelJacksonVSTheMoonwalkers extends GameEngine {

    private final int worldWidth = 1000;
    private final int worldHeight = 518;
    private int xPositionOfButton = this.worldWidth/2-50;
    private int spaceBetweenTexts = 80;

    private Dashboard dashboard;
    private Sound backgroundMusic;
    private Boolean backButtonIsViewed = false;

    List<TextObject> howToPlayTexts = new ArrayList<TextObject>();
    List<TextObject> highScoreTexts = new ArrayList<TextObject>();

    public static void main(String[] args) {
        PApplet.main(new String[]{"nl.han.ica.MichaelJacksonVSTheMoonwalkers.src.classes.MichaelJacksonVSTheMoonwalkers"});}
    @Override
    public void setupGame() {
        this.dashboard = new Dashboard(0,0, this.worldWidth, this.worldHeight);

        loadCustomFont();
        startGameMusic();

        try {
            showMainMenu();

            createViewWithoutViewport(worldWidth, worldHeight);
            GameSession.sharedInstance().setupGameSession(this);

        } catch (Exception e) {
            println(e);
        }
    }

    private void loadCustomFont() {
        try {
            GraphicsEnvironment ge =
                    GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("src/main/java/nl/han/ica/MichaelJacksonVSTheMoonwalkers/res/fonts/ARCADECLASSIC.TTF")));
        } catch (IOException |FontFormatException e) {
            //Handle exception
            System.out.println("Error loading custom font...");
        }
    }

    @Override
    public void update() {

    }

    public int[] getScreenSize() {
        return new int[]{ worldWidth, worldHeight };
    }

    private void createViewWithoutViewport(int screenWidth, int screenHeight) {
        View view = new View(screenWidth,screenHeight);
        view.setBackground(loadImage("src/main/java/nl/han/ica/MichaelJacksonVSTheMoonwalkers/res/drawable/Backgrounds/sprite-bg.png"));

        setView(view);
        size(screenWidth, screenHeight);
    }

    private void createDashboard(int dashboardWidth,int dashboardHeight) {
//        Dashboard dashboard = new Dashboard(0,0, dashboardWidth, dashboardHeight);
//        TextObject dashboardText=new TextObject("");
//        dashboard.addGameObject(dashboardText);
//        addDashboard(dashboard);
    }

    private void showMainMenu(){
        backButtonIsViewed = false;
        showGameTitle();
        addDashboard(dashboard);
        placeMenuButtons();
    }

    private void startGameMusic(){
        backgroundMusic = new Sound(this, "src/main/java/nl/han/ica/MichaelJacksonVSTheMoonwalkers/res/music/game.mp3");
        backgroundMusic.loop(-1);
        backgroundMusic.play();
    }

    private void showGameTitle(){

        TextObject mjText = new TextObject("Michael Jackson");
        mjText.setCustomFont("ArcadeClassic");
        mjText.setX((this.worldWidth/2) - 170);

        TextObject vsText = new TextObject("VS");
        vsText.setX((this.worldWidth/2));
        vsText.setY(50);

        TextObject theMW = new TextObject("the moonwalkers");
        theMW.setX((this.worldWidth/2) - 170);
        theMW.setY(100);

        this.dashboard.addGameObject(mjText);
        this.dashboard.addGameObject(vsText);
        this.dashboard.addGameObject(theMW);
    }

    private void placeMenuButtons() {

        ButtonCreator playButton = new ButtonCreator("Play", this.xPositionOfButton, 190);
        playButton.setButtonFontSize(25);
        playButton.setBackgroundColor(Color.pink);
        playButton.setButtonTextColor(Color.white);

        ButtonCreator highScoreButton = new ButtonCreator("Highscore", this.xPositionOfButton, 280);
        highScoreButton.setButtonFontSize(25);
        highScoreButton.setBackgroundColor(Color.pink);
        highScoreButton.setButtonTextColor(Color.white);

        ButtonCreator howToPlayButton = new ButtonCreator("How to play", xPositionOfButton, 370);
        howToPlayButton.setButtonFontSize(25);
        howToPlayButton.setBackgroundColor(Color.pink);
        howToPlayButton.setButtonTextColor(Color.WHITE);

        this.dashboard.addGameObject(howToPlayButton);
        this.dashboard.addGameObject(playButton);
        this.dashboard.addGameObject(highScoreButton);
    }

    @Override
    public void mouseClicked() {
        super.mouseClicked();

        int yPositionOfPlayButton = 190;
        int yPositionOfHigscoreButton = 280;
        int yPositionOfHowToPlayButton = 370;
        int yPositionOfBackButton = 418;
        int buttonWidth = 150;
        int buttonHeight = 50;

        if (super.mouseX > this.xPositionOfButton && super.mouseX < this.xPositionOfButton + buttonWidth) {

            //Clicked on the play button
            if (super.mouseY > yPositionOfPlayButton && super.mouseY < yPositionOfPlayButton + buttonHeight) {
                //Start the game
                //1. Count down first
                cleanView();

                TextObject startingText = new TextObject("Starting in");
                startingText.setX(this.worldWidth/2 - 110);
                startingText.setY(this.worldHeight/3);
                this.dashboard.addGameObject(startingText);

                TextObject countDownText = new TextObject("3");
                countDownText.setX(this.worldWidth/2);
                countDownText.setY(this.worldHeight/2);

                this.dashboard.addGameObject(countDownText);

                countDownFrom(3, countDownText);

            }else if (super.mouseY > yPositionOfHigscoreButton && super.mouseY < yPositionOfHigscoreButton + buttonHeight) {
                //Clicked on the highscore button
                System.out.println("Highscore button is clicked");
                showHighscoreView();
            }else if (super.mouseY > yPositionOfHowToPlayButton && super.mouseY < yPositionOfHowToPlayButton + buttonHeight) {
                //How to play button is clicked
                cleanView();

                showGameTitle();

                showHowToPlayView();
            }

            if (backButtonIsViewed){
                if (super.mouseY > yPositionOfBackButton && super.mouseY < yPositionOfBackButton + buttonHeight) {
                    //Pressed on go back button
                    //Viewing the main menu
                    cleanView();
                    showMainMenu();
                }
            }
        }
    }

    public void showHighscoreView() {
        cleanView();
        showGameTitle();

        List<Integer> playerScores = GameSession.sharedInstance().getPlayerHighscore();
        int xPositionOfHeadingText = 330;

        highScoreTexts.add(new TextObject("Rank"));
        highScoreTexts.add(new TextObject("Score"));

        for (int x = 0; x < highScoreTexts.size(); x++) {
            int yPositionOfText = 100 + this.spaceBetweenTexts;

            highScoreTexts.get(x).setX(xPositionOfHeadingText);
            highScoreTexts.get(x).setY(yPositionOfText);
            highScoreTexts.get(x).setFontSize(23);

            xPositionOfHeadingText += 250;
            this.dashboard.addGameObject(highScoreTexts.get(x));
        }

        if (playerScores.size() == 0) {
            TextObject noScoresFoundText = new TextObject("Geen score gevonden");
            noScoresFoundText.setY(this.worldHeight/2);
            noScoresFoundText.setX(this.worldWidth/2 - 150);
            noScoresFoundText.setFontSize(30);
            noScoresFoundText.setTextColor(Color.yellow);

            this.dashboard.addGameObject(noScoresFoundText);
        }else {
            int yPositionOfText = 125 + this.spaceBetweenTexts;
            for (int y = 0; y < playerScores.size(); y++) {

                TextObject rankText = new TextObject(String.valueOf(y+1));
                rankText.setX(330);
                rankText.setY(yPositionOfText);
                rankText.setFontSize(30);

                TextObject playerScore = new TextObject(String.valueOf(playerScores.get(y)));
                playerScore.setX(590);
                playerScore.setY(yPositionOfText);
                playerScore.setFontSize(30);

                yPositionOfText += 50;
                this.dashboard.addGameObject(playerScore);
                this.dashboard.addGameObject(rankText);
            }
        }

        this.addBackButton();
    }

    private void resetVariables() {
        this.spaceBetweenTexts = 80;
    }

    private void showHowToPlayView() {

        Boolean reset = false;

        howToPlayTexts.add(new TextObject("Move Left"));
        howToPlayTexts.add(new TextObject("Move Right"));
        howToPlayTexts.add(new TextObject("Jump"));
        howToPlayTexts.add(new TextObject("Hit enemy"));
        howToPlayTexts.add(new TextObject("Pause"));

        howToPlayTexts.add(new TextObject("Key   Left "));
        howToPlayTexts.add(new TextObject("Key   Right"));
        howToPlayTexts.add(new TextObject("Key   Up "));
        howToPlayTexts.add(new TextObject("Key   Space"));
        howToPlayTexts.add(new TextObject("Key   P "));

        for (int i = 0; i < howToPlayTexts.size(); i ++) {

            int yPositionOfText = 100 + this.spaceBetweenTexts;

            howToPlayTexts.get(i).setX(330);
            howToPlayTexts.get(i).setY(yPositionOfText);
            howToPlayTexts.get(i).setFontSize(23);
            this.dashboard.addGameObject(howToPlayTexts.get(i));

            if (i > 4) {
                if (!reset) {reset = true; this.spaceBetweenTexts = 80;}
                yPositionOfText = 100 + this.spaceBetweenTexts;

                howToPlayTexts.get(i).setX(600);
                howToPlayTexts.get(i).setY(yPositionOfText);
                howToPlayTexts.get(i).setFontSize(23);
                howToPlayTexts.get(i).setTextColor(Color.green);
                this.dashboard.addGameObject(howToPlayTexts.get(i));
            }

            this.spaceBetweenTexts += 30;
        }

        this.addBackButton();

    }

    private void cleanView(){
        this.dashboard.deleteAllDashboardObjects();
        this.resetVariables();
    }

    private void addBackButton() {
        ButtonCreator backButton = new ButtonCreator("Go Back", this.xPositionOfButton, this.worldHeight-100);
        backButton.setBackgroundColor(Color.PINK);
        backButton.setButtonFontSize(24);
        backButtonIsViewed = true;

        this.dashboard.addGameObject(backButton);
    }

    private void countDownFrom(int seconds, TextObject countDownText){

        Timer timer = new Timer();

        timer.scheduleAtFixedRate(new TimerTask() {
            int i = seconds;

            public void run() {
                //Updating the countdown text
                countDownText.setText(String.valueOf(i--));

                if (i < 0){
                    timer.cancel();
                    cleanView();
                    GameSession.sharedInstance().setGameState(GameState.ReadyUp);
                    GameSession.sharedInstance().alterGameState();
                }
            }

        }, 0, 1000);
    }
}