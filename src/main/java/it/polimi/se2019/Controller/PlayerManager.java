package it.polimi.se2019.controller;

import it.polimi.se2019.model.game.Player;

public class PlayerManager {

    public void damageDealer(Player player, char[] damage){

        int markNumber = player.getPlayerBoard().getDamageTrack().checkMarks(damage[0]);

        for (int i=0; i < damage.length; i++){
            if (isAlive(player)){
                player.getPlayerBoard().getDamageTrack().addDamage(damage[i]);
            }else{
                //setkill
                //return eccezione morte;
            }

        }
        for (int k=0; k < markNumber; k++ ){
            if (isAlive(player)){
                player.getPlayerBoard().getDamageTrack().addDamage(damage[0]);
            }

        }
    }



    public static boolean isAlive(Player player){
        return (player.getPlayerBoard().getDamageTrack().getDamage().length<12);
    }



    private void markerManager(Player player, char[] add){

        for(int i=0; i<add.length; i++ ){
            player.getPlayerBoard().getDamageTrack().addMarks(add[i]);
        }

    }



    private void adrenalineManager(Player player){
        //TODO scrivere metodo
        if(player.getPlayerBoard().getDamageTrack().getDamage().length >= 3){
            player.getPlayerBoard().getActionTile().setAdrenalineMode1(true);
        }else if(player.getPlayerBoard().getDamageTrack().getDamage().length >= 6){
            player.getPlayerBoard().getActionTile().setAdrenalineMode1(false);
        }
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
