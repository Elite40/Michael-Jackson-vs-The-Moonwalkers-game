package nl.han.ica.MichaelJacksonVSTheMoonwalkers.src.classes;

import nl.han.ica.OOPDProcessingEngineHAN.Dashboard.Dashboard;
import nl.han.ica.OOPDProcessingEngineHAN.Engine.GameEngine;
import nl.han.ica.OOPDProcessingEngineHAN.View.View;
import nl.han.ica.waterworld.TextObject;
import nl.han.ica.MichaelJacksonVSTheMoonwalkers.src.helpers.ButtonCreator;
import processing.core.PApplet;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by tiesbaltissen on 21-04-17.
 */
public class MichaelJacksonVSTheMoonwalkers extends GameEngine {

    private final int worldWidth = 1000;
    private final int worldHeight = 518;

    private Dashboard dashboard;


    public static void main(String[] args) {
        PApplet.main(new String[]{"nl.han.ica.MichaelJacksonVSTheMoonwalkers.src.classes.MichaelJacksonVSTheMoonwalkers"});}
    @Override
    public void setupGame() {

        loadCustomFont();

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
        this.dashboard = new Dashboard(0,0, this.worldWidth, this.worldHeight);
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

        addDashboard(dashboard);

        placeButtons();
    }

    private void placeButtons() {
        ButtonCreator playButton = new ButtonCreator("Play", (this.worldWidth/2)-50, 170);
        playButton.setButtonFontSize(25);
        playButton.setBackgroundColor(Color.pink);
        playButton.setButtonTextColor(Color.white);

        ButtonCreator highScoreButton = new ButtonCreator("Highscore", (this.worldWidth/2)-50, 250);
        highScoreButton.setButtonFontSize(25);
        highScoreButton.setBackgroundColor(Color.pink);
        highScoreButton.setButtonTextColor(Color.white);

        this.dashboard.addGameObject(playButton);
        this.dashboard.addGameObject(highScoreButton);
    }

    @Override
    public void mouseClicked() {
        super.mouseClicked();

        int yPositionOfPlayButton = 170;
        int yPositionOfHigscoreButton = 250;
        int xPositionOfButton = (this.worldWidth/2) - 50;
        int buttonWidth = 150;
        int buttonHeight = 50;

        if (super.mouseX > xPositionOfButton && super.mouseX < xPositionOfButton + buttonWidth) {

            if (super.mouseY > yPositionOfPlayButton && super.mouseY < yPositionOfPlayButton + buttonHeight) {
                //Start the game
                //1. Count down first
                cleanView();

                TextObject startingText = new TextObject("Starting in");
                startingText.setX(this.worldWidth/2 - 110);
                startingText.setY(this.worldHeight/3);
                this.dashboard.addGameObject(startingText);

                countDown();

            }else if (super.mouseY > yPositionOfHigscoreButton && super.mouseY < yPositionOfHigscoreButton + buttonHeight) {
                System.out.println("Highscore button is clicked");
            }

        }
    }

    private void cleanView(){
        this.dashboard.deleteAllDashboardObjects();
    }

    private void countDown() {
        TextObject countDownText = new TextObject("3");
        countDownText.setX(this.worldWidth/2);
        countDownText.setY(this.worldHeight/2);

        this.dashboard.addGameObject(countDownText);

        countDown(3, countDownText);

    }

    public void countDown(int seconds, TextObject countDownText){
        Timer timer = new Timer();

        timer.scheduleAtFixedRate(new TimerTask() {
            int i = seconds;

            public void run() {
                countDownText.setText(String.valueOf(i));
                System.out.println(i--);

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