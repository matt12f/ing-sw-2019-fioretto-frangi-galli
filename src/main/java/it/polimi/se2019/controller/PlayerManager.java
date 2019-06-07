package it.polimi.se2019.controller;

import it.polimi.se2019.AdrenalineServer;
import it.polimi.se2019.model.cards.PowerupCard;
import it.polimi.se2019.model.game.Player;


public class PlayerManager {

    public void damageDealer(Player player, char[] damage){
        int markNumber = player.getPlayerBoard().getDamageTrack().checkMarks(damage[0]);
        for (char dam:damage){
            if (isAlive(player))
                player.getPlayerBoard().getDamageTrack().addDamage(dam);
            else{
                //TODO setkill
                //TODO throw eccezione morte
            }

        }
        for (int k=0;k<markNumber;k++)
            if (isAlive(player))
                player.getPlayerBoard().getDamageTrack().addDamage(damage[0]);
    }

    public static boolean isAlive(Player player){
        return (player.getPlayerBoard().getDamageTrack().getDamage().length<12);
    }

   public static void markerManager(Player player, char[] add) {
        for (char mark: add)
            player.getPlayerBoard().getDamageTrack().addMark(mark);
    }

    public static void adrenalineManager(Player player){
        if(player.getPlayerBoard().getDamageTrack().getDamage().length >= 3){
            player.getPlayerBoard().getActionTileNormal().setAdrenalineMode1(true);
        }else if(player.getPlayerBoard().getDamageTrack().getDamage().length >= 6){
            player.getPlayerBoard().getActionTileNormal().setAdrenalineMode1(false);
        }
    }

    public void payGunCardCost(boolean fullOrReload){

    }

    public void frenzyManager(){
        //TODO scrivere metodo
        AdrenalineServer.getMainController().getMainGameModel().activateFinalFrenzy(AdrenalineServer.getMainController().getActiveturn().getActivePlayer().getId());
    }
}
