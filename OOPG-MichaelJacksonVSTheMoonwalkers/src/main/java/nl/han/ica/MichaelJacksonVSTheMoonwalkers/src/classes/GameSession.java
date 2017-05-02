package nl.han.ica.MichaelJacksonVSTheMoonwalkers.src.classes;

import nl.han.ica.MichaelJacksonVSTheMoonwalkers.src.models.enemy.Zombie;
import nl.han.ica.MichaelJacksonVSTheMoonwalkers.src.models.player.MJ;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.OOPDProcessingEngineHAN.Persistence.FilePersistence;
import nl.han.ica.OOPDProcessingEngineHAN.Persistence.IPersistence;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by tiesbaltissen on 20-04-17.
 */

enum Difficulty {
    Easy,
    Normal,
    Intermediate,
    Hard
}

enum GameState {
    None,
    ReadyUp,
    Paused,
    Playing,
    GameOver
}

public final class GameSession {

    private static GameSession instance = new GameSession();


    private int score;
    private Timer readyUpTimer = new Timer();
    public String countDownText = "Test";
    private GameState gameState;
    private Difficulty difficulty;
    private MJ mj;
    private List<Zombie> zombies;
    private MichaelJacksonVSTheMoonwalkers game;
    private File highscoreFile;

    public static GameSession sharedInstance() {
        if (instance == null) {
            instance = new GameSession();
        }
        return instance;
    }

    public void setupGameSession(MichaelJacksonVSTheMoonwalkers game) throws IOException, SAXException, ParserConfigurationException {
        this.game = game;
        this.gameState = GameState.None;

        try {
            highscoreFile = new File("src/main/java/nl/han/ica/MichaelJacksonVSTheMoonwalkers/res/mj_score.txt");
        }catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }

        this.alterGameState();
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
                gameState = GameState.Playing;
                try{
                    startGame();
                }catch(Exception e) {
                    //Handle exception
                }
                break;
            case Paused:
                gameState = GameState.ReadyUp;
                startReadyUpTimer();
                break;
            case Playing:
                gameState = GameState.Paused;
                break;
            case GameOver:
                gameState = GameState.None;
                break;
        }
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public void startGame() throws ParserConfigurationException, SAXException, IOException {
        setupGameSession(game);

        Sprite mjSprite = new Sprite(MJ.getMJSprite());
        mj = new MJ(50, 10, 5, mjSprite, game);
        game.addGameObject(mj, game.getScreenSize()[0] / 2, game.getScreenSize()[1] - mjSprite.getHeight() - 60);
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public List getPlayerHighscore() {
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
