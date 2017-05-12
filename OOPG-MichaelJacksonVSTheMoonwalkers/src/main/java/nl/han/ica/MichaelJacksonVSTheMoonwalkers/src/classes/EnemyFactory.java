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

    public void spawnZombie() {
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
        switch (randomZombieType()) {
            case ZombieBoss:
                spawnBossZombie(direction, scalingSize);
                break;
            case ZombieLanky:
                spawnLankyZombie(direction, scalingSize);
                break;
            case ZombieBird:
                spawnBirdZombie(direction, scalingSize);
                break;
        }
    }

    private void spawnLankyZombie(Direction direction, ScalingSize scalingSize) {
        Sprite zombieSprite = new Sprite(Zombie.zombieSprites(ZombieType.ZombieLanky));
        zombieSprite.resize(Math.round(zombieSprite.getWidth() * scalingSize.getValue()), Math.round(zombieSprite.getHeight() * scalingSize.getValue()));
        Zombie lanky = new Zombie(ScalingSize.Small, direction, 0, random.nextFloat() + 0.5f, 5, 10,  zombieSprite, this.game);
        if (direction == Direction.Right) {
            game.addGameObject(lanky, 0, game.getScreenSize()[1] - zombieSprite.getHeight() - 60);
        } else {
            game.addGameObject(lanky, game.getScreenSize()[0], game.getScreenSize()[1] - zombieSprite.getHeight() - 60);
        }
    }

    private void spawnBirdZombie(Direction direction, ScalingSize scalingSize) {
        Sprite zombieSprite = new Sprite(Zombie.zombieSprites(ZombieType.ZombieBird));
        zombieSprite.resize(Math.round(zombieSprite.getWidth() * scalingSize.getValue()), Math.round(zombieSprite.getHeight() * scalingSize.getValue()));
        ZombieBird bird = new ZombieBird(ScalingSize.Small, direction, 20, random.nextFloat() + 0.5f, 5, 5, zombieSprite, 0.7f, this.game);
        if (direction == Direction.Right) {
            game.addGameObject(bird, 0, game.getScreenSize()[1] - game.getScreenSize()[1] * bird.altitude);
        } else {
            game.addGameObject(bird, game.getScreenSize()[0], game.getScreenSize()[1] - game.getScreenSize()[1] * bird.altitude);
        }
    }

    private void spawnBossZombie(Direction direction, ScalingSize scalingSize) {
        Sprite zombieSprite = new Sprite(Zombie.zombieSprites(ZombieType.ZombieBoss));
        zombieSprite.resize(Math.round(zombieSprite.getWidth() * scalingSize.getValue()), Math.round(zombieSprite.getHeight() * scalingSize.getValue()));
        ZombieBoss boss = new ZombieBoss(ScalingSize.Large, direction,40, random.nextFloat() + 0.5f, 10, 20, zombieSprite, 200, this.game);
        if (direction == Direction.Right) {
            game.addGameObject(boss, 0, game.getScreenSize()[1] - zombieSprite.getHeight() - 60);
        } else {
            game.addGameObject(boss, game.getScreenSize()[0], game.getScreenSize()[1] - zombieSprite.getHeight() - 60);
        }
    }

    public void stopSpawnTimer() {
        spawnTimer.cancel();
    }

    public void startSpawnTimer() {
        spawnTimer = new Timer();
        spawnTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                spawnZombie();
            }
        }, 0, 1000);
    }
}
