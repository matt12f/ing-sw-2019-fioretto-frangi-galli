package it.polimi.se2019.controller;

import it.polimi.se2019.Adrenaline;
import it.polimi.se2019.model.game.Player;

import java.util.ArrayList;

public class ShootManager {

    public ShootManager(){
        //TODO scrivere costruttore
    }
    public boolean inflictDamage(ArrayList<Player> targets, char[] damage){
        //TODO fare ciclo
       // Controller.getActiveturn().getPlayerManager().damageDealer(targets, damage);
        return (PlayerManager.isAlive(Adrenaline.getMainController().getActiveturn().getActivePlayer()));
    }
    public void appointMarker(ArrayList<Player> targets, char [] markers){
        //TODO scrivere metodo
    }
    public void moveOpponent(ArrayList<Player> targets){
        //TODO scrivere metodo
    }

}
