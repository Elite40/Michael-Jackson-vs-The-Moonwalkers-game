package nl.han.ica.MichaelJacksonVSTheMoonwalkers.src.classes;

import com.sun.istack.internal.Nullable;
import nl.han.ica.MichaelJacksonVSTheMoonwalkers.src.models.enemy.*;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;

import java.util.List;
import java.util.Random;

/**
 * Created by tiesbaltissen on 20-04-17.
 */

public class EnemyFactory {

    private final int BossLower = 0;
    private final int BossUpper = 10;
    private final int LankyLower = 11;
    private final int LankyUpper = 40;
    private final int BirdLower = 41;
    private final int BirdUpper = 70;
    private MichaelJacksonVSTheMoonwalkers game;

    public EnemyFactory(MichaelJacksonVSTheMoonwalkers game) {
        this.game = game;
    }

    private ZombieType randomZombieType() {
        Random random = new Random(70);
        int seed = random.nextInt();
        if (isBetween(seed, BossLower, BossUpper)) {
            return ZombieType.ZombieBoss;
        }
        if (isBetween(seed, LankyLower, LankyUpper)) {
            return ZombieType.ZombieLanky;
        }
        if (isBetween(seed, BirdLower, BirdUpper)) {
            return ZombieType.ZombieBird;
        }
        return ZombieType.ZombieLanky;
    }

    public Zombie spawnZombie() {
        switch (ZombieType.ZombieLanky) {
            case ZombieBoss:
                return spawnBossZombie();
            case ZombieLanky:
                return spawnLankyZombie();
            case ZombieBird:
                return spawnBirdZombie();
        }
        return null;
    }

    private boolean isBetween(int x, int lower, int upper) {
        return (lower <= x && x <= upper);
    }

    private Zombie spawnLankyZombie() {
        Sprite zombieSprite = new Sprite(Zombie.zombieSprites(ZombieType.ZombieLanky));
        return new Zombie(ScalingSize.Small, 0, 0.2f, 5, 10,  zombieSprite, this.game);
    }

    private Zombie spawnBirdZombie() {
        Sprite zombieSprite = new Sprite(Zombie.zombieSprites(ZombieType.ZombieBird));
        return new ZombieBird(ScalingSize.Small, 20, 0.5f, 5, 5, zombieSprite, 0.7f, this.game);
    }

    private Zombie spawnBossZombie() {
        Sprite zombieSprite = new Sprite(Zombie.zombieSprites(ZombieType.ZombieBoss));
        return new ZombieBoss(ScalingSize.Large, 40, 0.4f, 10, 20, zombieSprite, 200, this.game);
    }
}
