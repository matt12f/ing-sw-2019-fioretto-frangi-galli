package it.polimi.se2019.controller;

import it.polimi.se2019.Adrenaline;
import it.polimi.se2019.model.game.Player;

import java.util.ArrayList;

public class ShootManager {

    public ShootManager(){
        //TODO scrivere costruttore
    }
    public boolean inflictDamage(ArrayList<Player> targets, char[] damage){

        Player target;

        for(int i= 0; i<targets.size(); i++){
            target = targets.get(i);
            Adrenaline.getMainController().getActiveturn().getPlayerManager().damageDealer(target, damage);
        }
        //TODO IL RITORNO NON VA BENE! SI RIFERISCE A UN SOLO PLAYER QUANDO GLI PASSIAMO UN ARRAYLIST!!
        return (PlayerManager.isAlive(Adrenaline.getMainController().getActiveturn().getActivePlayer()));
    }
    public void appointMarker(ArrayList<Player> targets, char [] markers){
        //TODO scrivere metodo
        Player target;

        for(int i= 0; i < targets.size(); i++){
            target = targets.get(i);
            Adrenaline.getMainController().getActiveturn().getPlayerManager().markerManager(target, markers);
        }
    }
    public void moveOpponent(ArrayList<Player> targets){
        //TODO scrivere metodo
    }

}
