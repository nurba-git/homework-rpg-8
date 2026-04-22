package com.narxoz.rpg.floor;

import com.narxoz.rpg.combatant.Hero;
import com.narxoz.rpg.state.NormalState;

import java.util.List;

public class RestFloor extends TowerFloor {

    @Override
    protected String getFloorName() {
        return "Sanctuary of Echoes";
    }

    @Override
    protected void announce() {
        System.out.println("\n--- Entering Sanctuary of Echoes ---");
        System.out.println("A calm light fills the room. The heroes may rest here.");
    }

    @Override
    protected void setup(List<Hero> party) {
        System.out.println("[setup] Ancient spirits prepare to heal the party.");
    }

    @Override
    protected FloorResult resolveChallenge(List<Hero> party) {
        System.out.println("[challenge] No enemies. The floor restores the heroes.");
        for (Hero hero : party) {
            if (hero.isAlive()) {
                hero.heal(6);
                hero.setState(new NormalState());
            }
        }
        return new FloorResult(true, 0, "The party recovers in the sanctuary.");
    }

    @Override
    protected boolean shouldAwardLoot(FloorResult result) {
        return false;
    }

    @Override
    protected void awardLoot(List<Hero> party, FloorResult result) {
    }

    @Override
    protected void cleanup(List<Hero> party) {
        System.out.println("[cleanup] The sanctuary fades behind the party.");
    }
}