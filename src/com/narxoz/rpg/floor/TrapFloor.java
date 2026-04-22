package com.narxoz.rpg.floor;

import com.narxoz.rpg.combatant.Hero;
import com.narxoz.rpg.state.StunnedState;

import java.util.List;

public class TrapFloor extends TowerFloor {

    @Override
    protected String getFloorName() {
        return "Lightning Trap Chamber";
    }

    @Override
    protected void setup(List<Hero> party) {
        System.out.println("[setup] The floor is covered in glowing runes.");
    }

    @Override
    protected FloorResult resolveChallenge(List<Hero> party) {
        System.out.println("[challenge] The trap activates!");
        int totalDamage = 0;

        for (Hero hero : party) {
            if (!hero.isAlive()) continue;

            int before = hero.getHp();
            hero.receiveAttack(8);
            totalDamage += Math.max(0, before - hero.getHp());

            if (hero.isAlive()) {
                System.out.println(hero.getName() + " is shocked and stunned!");
                hero.setState(new StunnedState(1));
            }
        }

        boolean cleared = hasAliveHeroes(party);
        return new FloorResult(cleared, totalDamage,
                cleared ? "The party survives the trap chamber." : "The tower claims all heroes.");
    }

    @Override
    protected void awardLoot(List<Hero> party, FloorResult result) {
        System.out.println("[loot] A hidden chest contains protective charms.");
    }

    private boolean hasAliveHeroes(List<Hero> party) {
        for (Hero hero : party) {
            if (hero.isAlive()) return true;
        }
        return false;
    }
}