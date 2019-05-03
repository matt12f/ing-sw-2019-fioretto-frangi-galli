package it.polimi.se2019.controller;

public class ShootManager {

    public ShootManager(){
        //TODO scrivere costruttore
    }
    public boolean inflictDamage(){
        return (PlayerManager.isAlive(TurnManager.getActivePlayer()));
    }
    public void appointMarker(){
        //TODO scrivere metodo
    }
    public void moveOpponent(){
        //TODO scrivere metodo
    }

}
