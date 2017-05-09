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

    public List<Zombie> zombies;

    private final int BossLower = 0;
    private final int BossUpper = 10;
    private final int LankyLower = 11;
    private final int LankyUpper = 40;
    private final int BirdLower = 41;
    private final int BirdUpper = 70;

    public EnemyFactory(@Nullable List<Zombie> zombies) {
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
        switch (randomZombieType()) {
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
        return new Zombie(ScalingSize.Small, 0, 0.2f, 5, Zombie.zombieSprites(ZombieType.ZombieLanky));
    }

    private Zombie spawnBirdZombie() {
        return new ZombieBird(ScalingSize.Small, 20, 0.5f, 5, Zombie.zombieSprites(ZombieType.ZombieBird), 0.7f);
    }

    private Zombie spawnBossZombie() {
        return new ZombieBoss(ScalingSize.Large, 40, 0.4f, 10, Zombie.zombieSprites(ZombieType.ZombieBoss), 200);
    }
}
