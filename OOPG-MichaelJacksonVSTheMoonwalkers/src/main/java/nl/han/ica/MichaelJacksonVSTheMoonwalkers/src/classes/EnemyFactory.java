package nl.han.ica.MichaelJacksonVSTheMoonwalkers.src.classes;

import com.sun.istack.internal.Nullable;
import nl.han.ica.MichaelJacksonVSTheMoonwalkers.src.models.enemy.*;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;

import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by tiesbaltissen on 20-04-17.
 */


public class EnemyFactory {

    private MichaelJacksonVSTheMoonwalkers game;
    private Random random = new Random();
    private Timer spawnTimer;

    public EnemyFactory(MichaelJacksonVSTheMoonwalkers game) {
        this.game = game;
        spawnTimer = new Timer();
        startSpawnTimer();
    }

    /**
     * @return A random ZombieType that will be used to spawn a zombie.
     */
    private ZombieType randomZombieType() {
        switch (random.nextInt(3)) {
            case 0:
                return ZombieType.ZombieBoss;
            case 1:
                return ZombieType.ZombieLanky;
            case 2:
                return ZombieType.ZombieBird;
            default:
                return ZombieType.ZombieLanky;
        }
    }

    /**
     * A random zombie will be determined based on a few randoms.
     * The type will be gerenrated from the method aboven, the size is also a randomized factor.
     * The direction the zombie will come from is also random.
     */
    public void randomZombie() {
        int next = random.nextInt(2);
        Direction direction = (next == 0) ? Direction.Left : Direction.Right;
        int scaling = random.nextInt(3);
        ScalingSize scalingSize;
        switch (scaling) {
            case 0:
                scalingSize = ScalingSize.Small;
                break;
            case 1:
                scalingSize = ScalingSize.Default;
                break;
            case 2:
                scalingSize = ScalingSize.Large;
                break;
            default:
                scalingSize = ScalingSize.Default;
                break;
        }
        spawnZombie(direction, randomZombieType(), scalingSize);
    }

    /**
     * After the randomZombie method is called, the zombie will be spawned based on the parameters below.
     * @param direction
     * @param type
     * @param scalingSize
     */
    private void spawnZombie(Direction direction, ZombieType type, ScalingSize scalingSize) {
        Sprite zombieSprite = new Sprite(Zombie.zombieSprites(type));
        zombieSprite.resize(Math.round(zombieSprite.getWidth() * scalingSize.getValue()), Math.round(zombieSprite.getHeight() * scalingSize.getValue()));
        float yPos = game.getWorldHeight() - zombieSprite.getHeight() - 60;
        int xPos = (direction == Direction.Left) ? -40 : game.getWorldWidth();
        Zombie zombie = null;
        switch (type) {
            case ZombieBoss:
                zombie = new ZombieBoss(direction, xPos, random.nextFloat() + 0.5f, 10, 20, zombieSprite, this.game);
                break;
            case ZombieLanky:
                zombie = new Zombie(direction, xPos, random.nextFloat() + 0.5f, 5, 10, zombieSprite, this.game);
                break;
            case ZombieBird:
                yPos = game.getWorldHeight() - game.getWorldHeight() * 0.7f;
                zombie = new ZombieBird(direction, xPos, random.nextFloat() + 0.5f, 5, 10, zombieSprite, 0.7f, this.game);
                break;
        }
        game.addGameObject(zombie, (direction == Direction.Right) ? -40 : game.getWorldWidth(), yPos);
    }

    /**
     * The spawnTimer is canceled here.
     */
    public void stopSpawnTimer() {
        spawnTimer.cancel();
    }

    /**
     * The spawner will start spawning zombies, the frequency is based on the difficulty.
     */
    public void startSpawnTimer() {
        spawnTimer = new Timer();
        spawnTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                randomZombie();
            }
        }, 0, GameSession.sharedInstance().getDifficulty().getValue());
    }
}
