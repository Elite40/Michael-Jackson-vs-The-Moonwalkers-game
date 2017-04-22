package nl.han.ica.MichaelJacksonVSTheMoonwalkers.src.classes;

import nl.han.ica.MichaelJacksonVSTheMoonwalkers.src.helpers.DocumentReader;
import nl.han.ica.MichaelJacksonVSTheMoonwalkers.src.helpers.SpriteLoader;
import nl.han.ica.MichaelJacksonVSTheMoonwalkers.src.models.enemy.Zombie;
import nl.han.ica.MichaelJacksonVSTheMoonwalkers.src.models.player.MJ;
import nl.han.ica.MichaelJacksonVSTheMoonwalkers.src.models.player.SpriteGroup;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import org.xml.sax.SAXException;
import sun.security.provider.ConfigFile;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
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
    private Timer readyUpTimer;
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

    public void setupGameSession() throws IOException, SAXException, ParserConfigurationException {
        List<SpriteGroup> mjSprites = Collections.emptyList();

        File mjDir = new File(String.format("%sMJ/", SpriteLoader.directory));
        for (File fileDir : mjDir.listFiles()) {
            List<Sprite> sprites = Collections.emptyList();
            for (File image : fileDir.listFiles()) {
                sprites.add(new Sprite(image.getAbsolutePath()));
            }
            mjSprites.add(new SpriteGroup(fileDir.getName(), sprites));
        }
        mj = new MJ(50, 10, 5, mjSprites, game);
        game.addGameObject(mj, 0, 0);
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
        setupGameSession();
    }
}
