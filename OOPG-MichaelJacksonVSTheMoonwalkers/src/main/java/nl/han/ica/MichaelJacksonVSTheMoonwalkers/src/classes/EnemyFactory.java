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

    private List<Zombie> zombies;

    private final int BossLower = 0;
    private final int BossUpper = 10;
    private final int LankyLower = 11;
    private final int LankyUpper = 40;
    private final int SuperiorLower = 41;
    private final int SuperiorUpper = 70;
    private final int BirdLower = 71;
    private final int BirdUpper = 100;

    public EnemyFactory(@Nullable List<Zombie> zombies) {
    }

    public void testZombies() {
        Zombie zombie1 = new Zombie(ScalingSize.Default, 0, 0.3f, 5, null);
        Zombie zombie2 = new ZombieBird(ScalingSize.Small, 0, 0.5f, 4, null, 0.7f);
        Zombie zombie3 = new ZombieBoss(ScalingSize.Large, 0, 0.2f, 10, null, 50);
        zombies.add(zombie1);
        zombies.add(zombie2);
        zombies.add(zombie3);
    }

    private ZombieType randomZombieType() {
        Random random = new Random(100);
        int seed = random.nextInt();
        if (isBetween(seed, BossLower, BossUpper)) {
            return ZombieType.ZombieBoss;
        }
        if (isBetween(seed, LankyLower, LankyUpper)) {
            return ZombieType.ZombieLanky;
        }
        if (isBetween(seed, SuperiorLower, SuperiorUpper)) {
            return ZombieType.ZombieSuperior;
        }
        if (isBetween(seed, BirdLower, BirdUpper)) {
            return ZombieType.ZombieBird;
        }
        return ZombieType.ZombieLanky;
    }

    private void spawnZombie() {
        switch (randomZombieType()) {
            case ZombieBoss:
                spawnBossZombie();
                break;
            case ZombieLanky:
                spawnLankyZombie();
                break;
            case ZombieSuperior:
                spawnSuperiorZombie();
                break;
            case ZombieBird:
                spawnBirdZombie();
                break;
        }
    }

    private boolean isBetween(int x, int lower, int upper) {
        return (lower <= x && x <= upper);
    }

    private void spawnLankyZombie() {
        zombies.add(new Zombie(ScalingSize.Small, 0, 0.2f, 5, Zombie.zombieSprites(ZombieType.ZombieLanky)));
    }

    private void spawnSuperiorZombie() {
        zombies.add(new Zombie(ScalingSize.Default, 0, 0.4f, 7, Zombie.zombieSprites(ZombieType.ZombieSuperior)));
    }

    private void spawnBirdZombie() {
        zombies.add(new ZombieBird(ScalingSize.Small, 0, 0.5f, 5, Zombie.zombieSprites(ZombieType.ZombieBird), 0.7f));
    }

    private void spawnBossZombie() {
        zombies.add(new ZombieBoss(ScalingSize.Large, 0, 0.4f, 10, Zombie.zombieSprites(ZombieType.ZombieBoss), 200));
    }
}
