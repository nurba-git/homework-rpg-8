package com.narxoz.rpg.state;

import com.narxoz.rpg.combatant.Hero;

public class BerserkState implements HeroState {

    @Override
    public String getName() {
        return "Berserk";
    }

    @Override
    public int modifyOutgoingDamage(int basePower) {
        return Math.max(1, (int) Math.round(basePower * 1.5));
    }

    @Override
    public int modifyIncomingDamage(int rawDamage) {
        return Math.max(1, (int) Math.round(rawDamage * 1.25));
    }

    @Override
    public void onTurnStart(Hero hero) {
        System.out.println(hero.getName() + " is raging!");
    }

    @Override
    public void onTurnEnd(Hero hero) {
        if (hero.getHp() > hero.getMaxHp() / 2) {
            hero.setState(new NormalState());
        }
    }

    @Override
    public boolean canAct() {
        return true;
    }
}