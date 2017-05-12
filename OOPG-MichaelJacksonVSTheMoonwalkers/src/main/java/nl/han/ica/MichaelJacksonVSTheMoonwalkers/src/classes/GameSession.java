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
import nl.han.ica.MichaelJacksonVSTheMoonwalkers.src.helpers.TextObject;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;

public final class GameSession {

    private static GameSession instance = new GameSession();
    private List<Integer> playerScores = new ArrayList<Integer>();
    private final String pathToScoreFile = "src/main/java/nl/han/ica/MichaelJacksonVSTheMoonwalkers/res/mj_score.txt";

    private int score;
    private Timer readyUpTimer = new Timer();
    private GameState gameState;
    private Difficulty difficulty;
    public MJ mj;
    private MichaelJacksonVSTheMoonwalkers game;
    public EnemyFactory enemyFactory;
    private File highscoreFile;

    public boolean isCountingDown = false;

    private IPersistence persistence;

    public Dashboard greenHealthBar;
    private Color healthBarColor = new Color(42, 189, 104);
    public Dashboard healthBarContainer;
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

    public void alterGameState() {
        switch (gameState) {
            case None:
                enemyFactory.stopSpawnTimer();
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
                game.showGameOverView();
                break;
        }
    }

    private void setPlayerScore() {
        playerScores = getPlayerHighscore();
        playerScores.add(getScore());
        String[] scoreArray = new String[playerScores.size()];
        int index = 0;
        for (Integer playerScore : playerScores) {
            scoreArray[index] = String.valueOf(playerScore);
            index++;
        }
        persistence = new FilePersistence("main/java/nl/han/ica/MichaelJacksonVSTheMoonwalkers/res/mj_score.txt");
        if (persistence.fileExists()) {
            persistence.saveData(scoreArray, ",");
        }
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    private void showPauseText() {
        pauseText = HUDCreator.drawTextObject(game.getWorldWidth()/2 - 150, game.getWorldHeight()/2 - 10, "Game Paused", 50);
        game.addGameObject(pauseText);
    }

    public void removePauseText() {
        game.deleteGameObject(pauseText);
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
        persistence = new FilePersistence("main/java/nl/han/ica/MichaelJacksonVSTheMoonwalkers/res/mj_score.txt");
        if (!persistence.loadDataString().isEmpty()) {
            for (String score : persistence.loadDataStringArray(",")) {
                playerScores.add(Integer.parseInt(score));
            }
        }

        playerScores.sort(Comparator.reverseOrder());
        return playerScores;
    }
}
