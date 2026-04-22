@startuml
title Template Method Pattern - Homework 8

class Hero

class FloorResult {
-cleared : boolean
-damageTaken : int
-summary : String
+FloorResult(cleared : boolean, damageTaken : int, summary : String)
+isCleared() : boolean
+getDamageTaken() : int
+getSummary() : String
}

abstract class TowerFloor {
+explore(party : List<Hero>) : FloorResult
#announce() : void
#setup(party : List<Hero>) : void
#resolveChallenge(party : List<Hero>) : FloorResult
#shouldAwardLoot(result : FloorResult) : boolean
#awardLoot(party : List<Hero>, result : FloorResult) : void
#cleanup(party : List<Hero>) : void
#getFloorName() : String
}

class BattleFloor {
-monster : Monster
#getFloorName() : String
#setup(party : List<Hero>) : void
#resolveChallenge(party : List<Hero>) : FloorResult
#awardLoot(party : List<Hero>, result : FloorResult) : void
#cleanup(party : List<Hero>) : void
}

class TrapFloor {
#getFloorName() : String
#setup(party : List<Hero>) : void
#resolveChallenge(party : List<Hero>) : FloorResult
#awardLoot(party : List<Hero>, result : FloorResult) : void
}

class RestFloor {
#getFloorName() : String
#announce() : void
#setup(party : List<Hero>) : void
#resolveChallenge(party : List<Hero>) : FloorResult
#shouldAwardLoot(result : FloorResult) : boolean
#awardLoot(party : List<Hero>, result : FloorResult) : void
#cleanup(party : List<Hero>) : void
}

class BossFloor {
-boss : Monster
#getFloorName() : String
#announce() : void
#setup(party : List<Hero>) : void
#resolveChallenge(party : List<Hero>) : FloorResult
#awardLoot(party : List<Hero>, result : FloorResult) : void
#cleanup(party : List<Hero>) : void
}

class Monster

class TowerRunner {
-floors : List<TowerFloor>
+TowerRunner(floors : List<TowerFloor>)
+run(party : List<Hero>) : TowerRunResult
}

class TowerRunResult {
-floorsCleared : int
-heroesSurviving : int
-towerCleared : boolean
+TowerRunResult(floorsCleared : int, heroesSurviving : int, towerCleared : boolean)
+getFloorsCleared() : int
+getHeroesSurviving() : int
+isTowerCleared() : boolean
}

TowerFloor <|-- BattleFloor
TowerFloor <|-- TrapFloor
TowerFloor <|-- RestFloor
TowerFloor <|-- BossFloor

TowerFloor --> FloorResult : returns
BattleFloor --> Monster
BossFloor --> Monster
TowerRunner --> TowerFloor : executes
TowerRunner --> TowerRunResult : returns
TowerFloor ..> Hero : uses
@enduml