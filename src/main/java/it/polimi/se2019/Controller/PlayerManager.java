package it.polimi.se2019.controller;

import it.polimi.se2019.model.game.Player;

public class PlayerManager {

    public void damageDealer(Player player){

    }
    public static boolean isAlive(Player player){
        return (player.playerBoard.damage.getDamage().length<12);
    }
    private void markerManager(){

    }
    private void adrenalineManager(){

    }
    public void gunHandManager(){

    }
    public void powerupHandManager(){

    }
    public boolean ammoManager(){
        boolean enoughAmmo;
        //TODO verificare se il player può pagare l'arma
        //TODO altrimenti verifica se può pagare con un powerup
        enoughAmmo=false;
        return enoughAmmo;
    }
    public void frenzyManager(){

    }
}
