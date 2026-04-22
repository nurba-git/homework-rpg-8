package com.narxoz.rpg;

import com.narxoz.rpg.combatant.Hero;
import com.narxoz.rpg.floor.BattleFloor;
import com.narxoz.rpg.floor.BossFloor;
import com.narxoz.rpg.floor.RestFloor;
import com.narxoz.rpg.floor.TowerFloor;
import com.narxoz.rpg.floor.TrapFloor;
import com.narxoz.rpg.state.NormalState;
import com.narxoz.rpg.state.PoisonedState;
import com.narxoz.rpg.tower.TowerRunResult;
import com.narxoz.rpg.tower.TowerRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Entry point for Homework 8 — The Haunted Tower: Ascending the Floors.
 */
public class Main {

    public static void main(String[] args) {
        Hero knight = new Hero("Levi", 40, 10, 3, new NormalState());
        Hero rogue = new Hero("Arina", 32, 11, 2, new PoisonedState(2));

        List<Hero> party = new ArrayList<>();
        party.add(knight);
        party.add(rogue);

        List<TowerFloor> floors = Arrays.asList(
                new BattleFloor(),
                new TrapFloor(),
                new RestFloor(),
                new BossFloor()
        );

        TowerRunner runner = new TowerRunner(floors);
        TowerRunResult result = runner.run(party);

        System.out.println("\n===== FINAL TOWER RESULT =====");
        System.out.println("Floors cleared: " + result.getFloorsCleared());
        System.out.println("Heroes surviving: " + result.getHeroesSurviving());
        System.out.println("Tower cleared: " + result.isTowerCleared());
    }
}