package it.polimi.se2019.controller;

import it.polimi.se2019.model.game.Player;

public class PlayerManager {

    public void damageDealer(Player player){
        //TODO scrivere metodo

    }
    public static boolean isAlive(Player player){
        return (player.getPlayerBoard().getDamageTrack().getDamage().length<12);
    }
    private void markerManager(){
        //TODO scrivere metodo

    }
    private void adrenalineManager(){
        //TODO scrivere metodo
    }
    public void gunHandManager(){
        //TODO scrivere metodo
    }
    public void powerupHandManager(){
        //TODO scrivere metodo

    }
    public boolean ammoManager(){
        boolean enoughAmmo;
        //TODO verificare se il player può pagare l'arma
        //TODO altrimenti verifica se può pagare con un powerup
        enoughAmmo=false;
        return enoughAmmo;
    }
    public void frenzyManager(){
        //TODO scrivere metodo

    }
}
