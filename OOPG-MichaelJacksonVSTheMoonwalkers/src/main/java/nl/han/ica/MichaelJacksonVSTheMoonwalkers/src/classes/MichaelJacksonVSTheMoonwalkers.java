package nl.han.ica.MichaelJacksonVSTheMoonwalkers.src.classes;

import nl.han.ica.MichaelJacksonVSTheMoonwalkers.src.helpers.*;
import nl.han.ica.OOPDProcessingEngineHAN.Dashboard.Dashboard;
import nl.han.ica.OOPDProcessingEngineHAN.Engine.GameEngine;
import nl.han.ica.OOPDProcessingEngineHAN.Sound.Sound;
import nl.han.ica.OOPDProcessingEngineHAN.View.View;
import nl.han.ica.waterworld.TextObject;
import processing.core.PApplet;
import processing.core.PImage;

import javax.xml.soap.Text;
import java.awt.*;
import java.io.*;
import java.util.List;
import java.util.*;
import java.util.Timer;

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

    private PImage musicPlayingIcon;
    private PImage musicMutedIcon;

    private Boolean muted = false;

    private List<String> howToPlayTexts = new ArrayList<String>();
    private List<TextObject> highScoreTexts = new ArrayList<TextObject>();

    private boolean startPressed = false;
    private File howToPlayFile;

    public static void main(String[] args) {
        PApplet.main(new String[]{"nl.han.ica.MichaelJacksonVSTheMoonwalkers.src.classes.MichaelJacksonVSTheMoonwalkers"});}
    @Override
    public void setupGame() {
        this.dashboard = new Dashboard(0,0, this.worldWidth, this.worldHeight);

        loadCustomFont();
//        startGameMusic();

        //Loading the speaker image
        musicPlayingIcon = loadImage("src/main/java/nl/han/ica/MichaelJacksonVSTheMoonwalkers/res/drawable/others/volume-on.png");
        musicMutedIcon = loadImage("src/main/java/nl/han/ica/MichaelJacksonVSTheMoonwalkers/res/drawable/others/mute.png");

        try {
            howToPlayFile = new File("src/main/java/nl/han/ica/MichaelJacksonVSTheMoonwalkers/res/mj_how_to_play.txt");
        }catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
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
        if (!muted) {
            view.setIcon(musicPlayingIcon, 5, 5);
        }else {
            view.setIcon(musicMutedIcon, 5, 5);
        }
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
        this.backgroundMusic = new Sound(this, "src/main/java/nl/han/ica/MichaelJacksonVSTheMoonwalkers/res/music/game.mp3");
        this.backgroundMusic.loop(100);
        this.backgroundMusic.play();
    }

    private void toggleMusic() {
        if (!muted) {
            muted = true;
            createViewWithoutViewport(worldWidth, worldHeight);
            this.backgroundMusic.pause();
            return;
        }

        muted = false;
        createViewWithoutViewport(worldWidth, worldHeight);
        this.backgroundMusic.play();
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
                if (startPressed) return;
                startPressed = true;
                cleanView();
                dashboard.addGameObject(HUDCreator.drawTextObject(worldWidth / 2 - 110, worldHeight / 3, "Starting in", 46));
                TextObject countDownText = HUDCreator.drawTextObject(worldWidth / 2, worldHeight / 2, "3", 23);

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


        if ( (super.mouseX > 4 && super.mouseX < 4 + musicPlayingIcon.width) && super.mouseY > 4 && super.mouseY < 4 + musicPlayingIcon.height) {
            toggleMusic();
        }
    }

    public void showHighscoreView() {
        cleanView();
        showGameTitle();

        List<Integer> playerScores = GameSession.sharedInstance().getPlayerHighscore();
        int xPositionText = 350;

        highScoreTexts.add(HUDCreator.drawTextObject(0, 0, "Rank", 23));
        highScoreTexts.add(HUDCreator.drawTextObject(0, 0, "Score", 23));

        for (int x = 0; x < highScoreTexts.size(); x++) {
            int yPositionOfText = 100 + this.spaceBetweenTexts;
            highScoreTexts.get(x).setX(xPositionText);
            highScoreTexts.get(x).setY(yPositionOfText);

            xPositionText += 250;
            this.dashboard.addGameObject(highScoreTexts.get(x));
        }

        if (playerScores.size() == 0) {
            TextObject noScoresFoundText = HUDCreator.drawTextObject(worldHeight / 2, worldWidth / 2 - 150, "Geen score gevonden", 30);
            this.dashboard.addGameObject(noScoresFoundText);
        }else {
            int yPositionOfText = 125 + this.spaceBetweenTexts;
            for (int y = 0; y < playerScores.size(); y++) {

                TextObject rankText = HUDCreator.drawTextObject(360, yPositionOfText, String.valueOf(y+1), 30);
                TextObject playerScore = HUDCreator.drawTextObject(610, yPositionOfText, String.valueOf(playerScores.get(y)), 30);
                yPositionOfText += 50;
                this.dashboard.addGameObject(playerScore);
                this.dashboard.addGameObject(rankText);
            }
        }

        this.addBackButton();
    }

    public List<String> getHowToPlayTexts() {
        List<String> howToPlay = new ArrayList<>();
        //Read score
        try {
            BufferedReader reader = new BufferedReader(new FileReader(howToPlayFile));
            String line;

            while( (line = reader.readLine() ) != null) {
                howToPlay.add(line.trim());
            }

        }catch(FileNotFoundException e) {
            System.out.println("File not found!" + e.getMessage());
        }catch(IOException e ) {
            System.out.println("Failed to read the file" + e.getMessage());
        }

        return howToPlay;
    }

    private void resetVariables() {
        this.spaceBetweenTexts = 80;
        this.highScoreTexts.clear();
        this.howToPlayTexts.clear();
    }

    private void showHowToPlayView() {

        Boolean reset = false;

        howToPlayTexts = getHowToPlayTexts();

        for (int i = 0; i < howToPlayTexts.size(); i ++) {

            int yPositionOfText = 100 + this.spaceBetweenTexts;
            if (i < 5) {
                this.dashboard.addGameObject(HUDCreator.drawTextObject(330, yPositionOfText, howToPlayTexts.get(i), 23));
            } else {
                if (!reset) {reset = true; this.spaceBetweenTexts = 80;}
                yPositionOfText = 100 + this.spaceBetweenTexts;

                this.dashboard.addGameObject(HUDCreator.drawTextObject(600, yPositionOfText, howToPlayTexts.get(i), 23, Color.green));
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

    public int getWorldWidth() {
        return worldWidth;
    }
}
