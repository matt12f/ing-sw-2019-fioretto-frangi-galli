package it.polimi.se2019.Controller;

public class ShootManager {

    public ShootManager(){

    }
    public boolean inflictDamage(){
        return (PlayerManager.isAlive(TurnManager.getActivePlayer()));
    }
    public void appointMarker(){}
    public void moveOpponent(){}

}
