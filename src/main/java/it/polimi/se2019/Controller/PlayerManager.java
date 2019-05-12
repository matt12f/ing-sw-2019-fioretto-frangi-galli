package it.polimi.se2019.controller;

import it.polimi.se2019.model.game.GameModel;
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

   public void markerManager(Player player, char[] add) {
        for (int i = 0; i < add.length; i++) {
            player.getPlayerBoard().getDamageTrack().addMark(add[i]);
        }
    }


    private void adrenalineManager(Player player){

        if(player.getPlayerBoard().getDamageTrack().getDamage().length >= 3){
            player.getPlayerBoard().getActionTileNormal().setAdrenalineMode1(true);
        }else if(player.getPlayerBoard().getDamageTrack().getDamage().length >= 6){
            player.getPlayerBoard().getActionTileNormal().setAdrenalineMode1(false);
        }
    }


    public void gunHandManager(){
        //TODO scrivere metodo
    }


    public void powerupHandManager(){
        //TODO scrivere metodo

    }



    public boolean ammoManager(Player player, int [] ammo ){
        boolean enoughAmmo = true;
        /**
         * viene inviato un vettore di int , l'ordine è blue - red - yellow
         */

            if (ammo [0] != player.getPlayerBoard().getAmmo().getBlue()){
                enoughAmmo = false;
            }else if (ammo [1] != player.getPlayerBoard().getAmmo().getRed()){
                enoughAmmo = false;
            }else if (ammo [2] != player.getPlayerBoard().getAmmo().getYellow()){
            enoughAmmo = false;
            }else{
                return enoughAmmo;
            }

        //TODO altrimenti verifica se può pagare con un powerup

        return enoughAmmo;
    }



    public void frenzyManager(GameModel game, Player player){
        //TODO scrivere metodo
        game.activateFinalFrenzy(player.getId());
    }
}
