package nl.han.ica.MichaelJacksonVSTheMoonwalkers.src.classes;

import nl.han.ica.MichaelJacksonVSTheMoonwalkers.src.helpers.ButtonCreator;
import nl.han.ica.MichaelJacksonVSTheMoonwalkers.src.helpers.HUDCreator;
import nl.han.ica.MichaelJacksonVSTheMoonwalkers.src.models.enemy.Zombie;
import nl.han.ica.MichaelJacksonVSTheMoonwalkers.src.models.player.MJ;
import nl.han.ica.OOPDProcessingEngineHAN.Dashboard.Dashboard;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.OOPDProcessingEngineHAN.Persistence.FilePersistence;
import nl.han.ica.OOPDProcessingEngineHAN.Persistence.IPersistence;
import nl.han.ica.waterworld.TextObject;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;

/**
 * Created by tiesbaltissen on 20-04-17.
 */

enum Difficulty {
    Easy(2500),
    Normal(2000),
    Intermediate(1500),
    Hard(500);

    private final float value;

    Difficulty(final float newValue) { value = newValue; }

    public float getValue() { return value; }
}

public final class GameSession {

    private static GameSession instance = new GameSession();
    private List<Integer> playerScores = new ArrayList<Integer>();
    private final String pathToScoreFile = "src/main/java/nl/han/ica/MichaelJacksonVSTheMoonwalkers/res/mj_score.txt";

    private int score;
    private Timer readyUpTimer = new Timer();
    public String countDownText = "Test";
    private GameState gameState;
    private Difficulty difficulty;
    public MJ mj;
    private List<Zombie> zombies;
    private MichaelJacksonVSTheMoonwalkers game;
    private EnemyFactory enemyFactory;
    private File highscoreFile;

    public boolean isCountingDown = false;

    private IPersistence persistence;

    public Dashboard greenHealthBar;
    private Color healthBarColor = new Color(42, 189, 104);
    private Dashboard healthBarContainer;
    private Color healthBarContainerColor = new Color(231, 76, 60);
    private TextObject healthText;
    public TextObject scoreText;
    public TextObject pauseText;
    private final int healthBarWidth = 200;
    private final int healthBarHeight = 20;
    private int yPositionHealthBar = 5;
    private int xPositionHealthBar;

    public static GameSession sharedInstance() {
        if (instance == null) {
            instance = new GameSession();
        }
        return instance;
    }

    public void setupGameSession(MichaelJacksonVSTheMoonwalkers game) throws IOException, SAXException, ParserConfigurationException {
        this.game = game;
        this.gameState = GameState.Started;

        try {
            highscoreFile = new File(pathToScoreFile);
        }catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }

        this.alterGameState();
    }

    public void setupHUD() {
        xPositionHealthBar = (game.getWorldWidth()/2) - healthBarWidth/2;

        healthBarContainer = HUDCreator.drawHealthBarContainer(xPositionHealthBar, yPositionHealthBar, healthBarWidth, healthBarHeight, healthBarContainerColor);
        greenHealthBar = HUDCreator.drawHealthBar(xPositionHealthBar, yPositionHealthBar, healthBarWidth, healthBarHeight, healthBarColor);
        healthText = HUDCreator.drawTextObject(xPositionHealthBar - 60, 6, "Health:", 14);
        scoreText = HUDCreator.drawTextObject(game.getWorldWidth() - 100, 6, "Score: " + score, 14);

        game.addDashboard(healthBarContainer);
        game.addDashboard(greenHealthBar);
        game.addGameObject(healthText);
        game.addGameObject(scoreText);
    }

    private void startReadyUpTimer() {
        readyUpTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                alterGameState();
                readyUpTimer.cancel();
            }
        }, 3000, 0);
    }

    public void alterGameState() {
        switch (gameState) {
            case None:
                break;
            case ReadyUp:
                gameState = GameState.Started;
                try{
                    startGame();
                }catch(Exception e) {
                    //Handle exception
                }
                break;
            case Paused:
                gameState = GameState.Playing;
                enemyFactory.startSpawnTimer();
                removePauseText();
                game.resumeGame();
                break;
            case Playing:
                gameState = GameState.Paused;
                enemyFactory.stopSpawnTimer();
                showPauseText();
                game.pauseGame();
                break;
            case Started:
                break;
            case GameOver:
                gameState = GameState.None;
                setPlayerScore();
                showGameOverView();
                break;
        }
    }

    private void setPlayerScore() {
        this.playerScores.add(this.getScore());

        persistence = new FilePersistence("main/java/nl/han/ica/waterworld/media/bubblesPopped.txt");
        if (persistence.fileExists()) {
            score = Integer.parseInt(persistence.loadDataString());
        }

//        Boolean isDone = true;
//        Scanner scan = new Scanner(System.in);
//        File f = new File(pathToScoreFile);
//
//        try {
//            FileWriter fr = new FileWriter(f);
//            BufferedWriter br  = new BufferedWriter(fr);
//
//            br.write(score);
//
//        }catch(IOException e) {
//            System.out.println("Couldnt save the score to the file: " + e.getMessage());
//        }
    }

    private void showPauseText() {
        pauseText = HUDCreator.drawTextObject(game.getWorldWidth()/2 - 150, game.getWorldHeight()/2 - 10, "Game Paused", 50);
        game.addGameObject(pauseText);
    }

    public void removePauseText() {
        game.deleteGameObject(pauseText);
    }

    private void showGameOverView() {
        game.deleteAllGameOBjects();
        game.deleteDashboard(healthBarContainer);
        game.deleteDashboard(greenHealthBar);

        String score = "Score: " + getScore();

        ButtonCreator menuButton = new ButtonCreator("Go to menu", game.getWorldWidth()/2 - 80, 400);

        game.addGameObject(menuButton);
        game.addGameObject(HUDCreator.drawTextObject(game.getWorldWidth()/2 - 100, 100, "Game Over", 50));
        game.addGameObject(HUDCreator.drawTextObject(game.getWorldWidth()/2 - 100, 250, score, 50));
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public void startGame() throws ParserConfigurationException, SAXException, IOException {
        game.deleteAllGameOBjects();
        setupGameSession(game);
        setupHUD();
        enemyFactory = new EnemyFactory(game);
        Sprite mjSprite = new Sprite(MJ.getMJSprite());
        mj = new MJ(mjSprite, game);
        game.addGameObject(mj, game.getScreenSize()[0] / 2, game.getScreenSize()[1] - mjSprite.getHeight() - 60);
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public GameState getGameState() { return gameState; }

    public List<Integer> getPlayerHighscore() {
        List<Integer> playerScores = new ArrayList<Integer>();
        //Read score
        try {
            BufferedReader reader = new BufferedReader(new FileReader(highscoreFile));
            String line;

            while( (line = reader.readLine() ) != null) {
                playerScores.add(Integer.parseInt(line.trim()));
            }

        }catch(FileNotFoundException e) {
            System.out.println("File not found!" + e.getMessage());
        }catch(IOException e ) {
            System.out.println("Failed to read the file" + e.getMessage());
        }

        return playerScores;
    }
}
