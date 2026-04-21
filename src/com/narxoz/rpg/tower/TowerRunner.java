package com.narxoz.rpg.tower;

import com.narxoz.rpg.combatant.Hero;
import com.narxoz.rpg.floor.FloorResult;
import com.narxoz.rpg.floor.TowerFloor;

import java.util.List;

public class TowerRunner {

    private final List<TowerFloor> floors;

    public TowerRunner(List<TowerFloor> floors) {
        this.floors = floors;
    }

    public TowerRunResult run(List<Hero> party) {
        int floorsCleared = 0;

        for (TowerFloor floor : floors) {
            if (!hasAliveHeroes(party)) {
                break;
            }

            FloorResult result = floor.explore(party);
            System.out.println("Result: " + result.getSummary());
            System.out.println("Damage taken on floor: " + result.getDamageTaken());

            if (result.isCleared()) {
                floorsCleared++;
            } else {
                break;
            }

            printPartyStatus(party);
        }

        int survivors = countAliveHeroes(party);
        boolean towerCleared = floorsCleared == floors.size() && survivors > 0;

        return new TowerRunResult(floorsCleared, survivors, towerCleared);
    }

    private boolean hasAliveHeroes(List<Hero> party) {
        for (Hero hero : party) {
            if (hero.isAlive()) return true;
        }
        return false;
    }

    private int countAliveHeroes(List<Hero> party) {
        int count = 0;
        for (Hero hero : party) {
            if (hero.isAlive()) count++;
        }
        return count;
    }

    private void printPartyStatus(List<Hero> party) {
        System.out.println("Party status:");
        for (Hero hero : party) {
            System.out.println("- " + hero.getName() +
                    " HP=" + hero.getHp() + "/" + hero.getMaxHp() +
                    " State=" + hero.getState().getName());
        }
    }
}