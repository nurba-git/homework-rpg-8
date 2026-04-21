package com.narxoz.rpg.combatant;

import com.narxoz.rpg.state.HeroState;
import com.narxoz.rpg.state.NormalState;


public class Hero {

    private final String name;
    private int hp;
    private final int maxHp;
    private final int attackPower;
    private final int defense;

    private HeroState state;

    public Hero(String name, int hp, int attackPower, int defense) {
        this(name, hp, attackPower, defense, new NormalState());
    }

    public Hero(String name, int hp, int attackPower, int defense, HeroState initialState) {
        this.name = name;
        this.hp = hp;
        this.maxHp = hp;
        this.attackPower = attackPower;
        this.defense = defense;
        this.state = (initialState == null) ? new NormalState() : initialState;
    }

    public String getName()        { return name; }
    public int getHp()             { return hp; }
    public int getMaxHp()          { return maxHp; }
    public int getAttackPower()    { return attackPower; }
    public int getDefense()        { return defense; }
    public boolean isAlive()       { return hp > 0; }
    public HeroState getState()    { return state; }

    public void setState(HeroState newState) {
        if (newState == null) return;
        if (this.state == null || !this.state.getName().equals(newState.getName())) {
            System.out.println(name + " changes state: " +
                    (this.state == null ? "None" : this.state.getName()) +
                    " -> " + newState.getName());
        }
        this.state = newState;
    }

    public void onTurnStart() {
        if (isAlive()) {
            state.onTurnStart(this);
        }
    }

    public void onTurnEnd() {
        if (isAlive()) {
            state.onTurnEnd(this);
        }
    }

    public boolean canAct() {
        return isAlive() && state.canAct();
    }

    public int getModifiedAttackPower() {
        return state.modifyOutgoingDamage(attackPower);
    }

    public void attack(Monster monster) {
        if (!isAlive()) return;

        if (!canAct()) {
            System.out.println(name + " is " + state.getName() + " and cannot act!");
            return;
        }

        int damage = Math.max(1, getModifiedAttackPower());
        monster.takeDamage(damage);
        System.out.println(name + " [" + state.getName() + "] attacks " +
                monster.getName() + " for " + damage + " damage.");
    }


    public void takeDamage(int amount) {
        hp = Math.max(0, hp - amount);
    }

    public void receiveAttack(int rawDamage) {
        int reduced = Math.max(1, rawDamage - defense);
        int finalDamage = Math.max(1, state.modifyIncomingDamage(reduced));
        takeDamage(finalDamage);
        System.out.println(name + " [" + state.getName() + "] receives " +
                finalDamage + " damage. HP: " + hp + "/" + maxHp);

        if (isAlive() && hp <= maxHp / 3 && !(state instanceof com.narxoz.rpg.state.BerserkState)) {
            setState(new com.narxoz.rpg.state.BerserkState());
        }
    }

    public void heal(int amount) {
        hp = Math.min(maxHp, hp + amount);
        System.out.println(name + " heals for " + amount + ". HP: " + hp + "/" + maxHp);
    }
}