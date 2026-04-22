package com.narxoz.rpg.floor;

import com.narxoz.rpg.combatant.Hero;
import com.narxoz.rpg.combatant.Monster;
import com.narxoz.rpg.state.PoisonedState;

import java.util.List;

public class BossFloor extends TowerFloor {

    private Monster boss;

    @Override
    protected String getFloorName() {
        return "Phantom Lord's Throne";
    }

    @Override
    protected void announce() {
        System.out.println("\n=== BOSS FLOOR: Phantom Lord's Throne ===");
        System.out.println("A chilling laugh echoes through the tower...");
    }

    @Override
    protected void setup(List<Hero> party) {
        System.out.println("[setup] The Phantom Lord descends from the shadows.");
        boss = new Monster("Phantom Lord", 55, 12);
    }

    @Override
    protected FloorResult resolveChallenge(List<Hero> party) {
        System.out.println("[challenge] Final battle begins!");
        int damageTaken = 0;
        int round = 1;

        while (boss.isAlive() && hasAliveHeroes(party)) {
            System.out.println("Boss Round " + round);

            for (Hero hero : party) {
                if (!hero.isAlive()) continue;

                hero.onTurnStart();

                if (hero.isAlive() && boss.isAlive()) {
                    hero.attack(boss);
                }

                hero.onTurnEnd();

                if (!boss.isAlive()) {
                    System.out.println("The Phantom Lord is defeated!");
                    break;
                }
            }

            if (boss.isAlive()) {
                for (Hero hero : party) {
                    if (!hero.isAlive()) continue;

                    int before = hero.getHp();
                    boss.attack(hero);
                    damageTaken += Math.max(0, before - hero.getHp());

                    if (hero.isAlive() && round == 2) {
                        System.out.println(hero.getName() + " is cursed with poison!");
                        hero.setState(new PoisonedState(2));
                    }
                    break;
                }
            }

            round++;
        }

        boolean cleared = !boss.isAlive();
        return new FloorResult(cleared, damageTaken,
                cleared ? "The tower boss has fallen." : "The party has fallen to the Phantom Lord.");
    }

    @Override
    protected void awardLoot(List<Hero> party, FloorResult result) {
        System.out.println("[loot] The party claims the Haunted Crown.");
    }

    @Override
    protected void cleanup(List<Hero> party) {
        System.out.println("[cleanup] The curse over the tower begins to break.");
    }

    private boolean hasAliveHeroes(List<Hero> party) {
        for (Hero hero : party) {
            if (hero.isAlive()) return true;
        }
        return false;
    }
}