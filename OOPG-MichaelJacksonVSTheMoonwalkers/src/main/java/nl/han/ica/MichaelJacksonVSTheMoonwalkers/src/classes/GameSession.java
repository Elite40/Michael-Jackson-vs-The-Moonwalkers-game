package nl.han.ica.MichaelJacksonVSTheMoonwalkers.src.classes;

import nl.han.ica.MichaelJacksonVSTheMoonwalkers.src.models.enemy.Zombie;
import nl.han.ica.MichaelJacksonVSTheMoonwalkers.src.models.player.MJ;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
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

    public static GameSession sharedInstance() {
        if (instance == null) {
            instance = new GameSession();
        }
        return instance;
    }

    public void setupGameSession(MichaelJacksonVSTheMoonwalkers game) throws IOException, SAXException, ParserConfigurationException {
        this.game = game;
        this.gameState = GameState.None;

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
}
