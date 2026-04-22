package com.narxoz.rpg.floor;

import com.narxoz.rpg.combatant.Hero;
import com.narxoz.rpg.combatant.Monster;
import com.narxoz.rpg.state.PoisonedState;

import java.util.List;

public class BattleFloor extends TowerFloor {

    private Monster monster;

    @Override
    protected String getFloorName() {
        return "Skeleton Hall";
    }

    @Override
    protected void setup(List<Hero> party) {
        System.out.println("[setup] A skeleton warrior appears.");
        monster = new Monster("Skeleton Warrior", 30, 9);
    }

    @Override
    protected FloorResult resolveChallenge(List<Hero> party) {
        System.out.println("[challenge] Combat begins.");
        int damageTaken = 0;
        int round = 1;

        while (monster.isAlive() && hasAliveHeroes(party)) {
            System.out.println("Round " + round);

            for (Hero hero : party) {
                if (!hero.isAlive()) continue;

                hero.onTurnStart();

                if (hero.isAlive() && monster.isAlive()) {
                    hero.attack(monster);
                }

                hero.onTurnEnd();

                if (!monster.isAlive()) {
                    System.out.println(monster.getName() + " has been defeated.");
                    break;
                }
            }

            if (monster.isAlive()) {
                Hero target = firstAliveHero(party);
                if (target != null) {
                    int before = target.getHp();
                    monster.attack(target);
                    damageTaken += Math.max(0, before - target.getHp());

                    if (target.isAlive() && round == 1) {
                        System.out.println(target.getName() + " has been poisoned by the skeleton blade!");
                        target.setState(new PoisonedState(2));
                    }
                }
            }

            round++;
        }

        boolean cleared = !monster.isAlive();
        return new FloorResult(cleared, damageTaken,
                cleared ? "The skeleton hall is cleared." : "The party was defeated in battle.");
    }

    @Override
    protected void awardLoot(List<Hero> party, FloorResult result) {
        System.out.println("[loot] The party finds small healing herbs.");
        if (result.isCleared()) {
            for (Hero hero : party) {
                if (hero.isAlive()) {
                    hero.heal(4);
                }
            }
        }
    }

    @Override
    protected void cleanup(List<Hero> party) {
        System.out.println("[cleanup] Broken bones are scattered across the floor.");
    }

    protected boolean hasAliveHeroes(List<Hero> party) {
        for (Hero hero : party) {
            if (hero.isAlive()) return true;
        }
        return false;
    }

    protected Hero firstAliveHero(List<Hero> party) {
        for (Hero hero : party) {
            if (hero.isAlive()) return hero;
        }
        return null;
    }
}