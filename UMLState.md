@startuml
title State Pattern - Homework 8

interface HeroState {
+getName() : String
+modifyOutgoingDamage(basePower : int) : int
+modifyIncomingDamage(rawDamage : int) : int
+onTurnStart(hero : Hero) : void
+onTurnEnd(hero : Hero) : void
+canAct() : boolean
}

class Hero {
-name : String
-hp : int
-maxHp : int
-attackPower : int
-defense : int
-state : HeroState
+Hero(name : String, hp : int, attackPower : int, defense : int)
+Hero(name : String, hp : int, attackPower : int, defense : int, initialState : HeroState)
+getName() : String
+getHp() : int
+getMaxHp() : int
+getAttackPower() : int
+getDefense() : int
+isAlive() : boolean
+getState() : HeroState
+setState(newState : HeroState) : void
+onTurnStart() : void
+onTurnEnd() : void
+canAct() : boolean
+getModifiedAttackPower() : int
+attack(monster : Monster) : void
+takeDamage(amount : int) : void
+receiveAttack(rawDamage : int) : void
+heal(amount : int) : void
}

class NormalState {
+getName() : String
+modifyOutgoingDamage(basePower : int) : int
+modifyIncomingDamage(rawDamage : int) : int
+onTurnStart(hero : Hero) : void
+onTurnEnd(hero : Hero) : void
+canAct() : boolean
}

class PoisonedState {
-turnsRemaining : int
+PoisonedState(turnsRemaining : int)
+getName() : String
+modifyOutgoingDamage(basePower : int) : int
+modifyIncomingDamage(rawDamage : int) : int
+onTurnStart(hero : Hero) : void
+onTurnEnd(hero : Hero) : void
+canAct() : boolean
}

class StunnedState {
-turnsRemaining : int
+StunnedState(turnsRemaining : int)
+getName() : String
+modifyOutgoingDamage(basePower : int) : int
+modifyIncomingDamage(rawDamage : int) : int
+onTurnStart(hero : Hero) : void
+onTurnEnd(hero : Hero) : void
+canAct() : boolean
}

class BerserkState {
+getName() : String
+modifyOutgoingDamage(basePower : int) : int
+modifyIncomingDamage(rawDamage : int) : int
+onTurnStart(hero : Hero) : void
+onTurnEnd(hero : Hero) : void
+canAct() : boolean
}

class Monster {
-name : String
-hp : int
-attackPower : int
+Monster(name : String, hp : int, attackPower : int)
+getName() : String
+getHp() : int
+getAttackPower() : int
+isAlive() : boolean
+takeDamage(amount : int) : void
+attack(hero : Hero) : void
}

Hero --> HeroState : current state
Hero --> Monster : attacks
Monster --> Hero : attacks

HeroState <|.. NormalState
HeroState <|.. PoisonedState
HeroState <|.. StunnedState
HeroState <|.. BerserkState
@enduml