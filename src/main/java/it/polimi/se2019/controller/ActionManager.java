package it.polimi.se2019.controller;


import it.polimi.se2019.AdrenalineServer;
import it.polimi.se2019.enums.CardName;
import it.polimi.se2019.model.cards.*;
import it.polimi.se2019.model.game.Ammo;

import java.util.ArrayList;

public class ActionManager {
    private ShootManager shootManager;

    public ActionManager(){
        this.shootManager=new ShootManager();
    }

    public ShootManager getShootManager(){
            return shootManager;
    }
    /**
     * this method puts new ammos in the player's ammo vectors and, if there is one, a powerup card,
     * controlling if it's possible
     * @param drop
     */
    public void dropManager(AmmoTileCard drop){
    }

    /**
     * this method allows to put a weapon from the spawn slot into the player's hand,
     * including the controlling cycle about the player's weapon hand
     */
    public void spawnDropManager(GunCard weapon){
    }

    /**
     * this method evaluates if a player can pay the cost to grab a GunCard from a SpawnCell
     * @param fullOrReload: if true it evaluates the full cost of reloading, if false it evaluates only the buying cost
     */
    public static boolean canAffordCost(Ammo availableAmmo, char[] ammoCost, boolean fullOrReload) {
        int blue=0;
        int red=0;
        int yellow=0;
        int start;
        if (fullOrReload)
            start=0;
        else
            start=1;
        for(int i=start;i<ammoCost.length;i++){
            switch (ammoCost[i]) {
                case 'b':blue++;break;
                case 'y':yellow++;break;
                case 'r':red++;break;
            }
        }
        int availableBlue=availableAmmo.getBlue();
        int availableRed=availableAmmo.getRed();
        int availableYellow=availableAmmo.getYellow();

        for(PowerupCard powerupCard: AdrenalineServer.getMainController().getActiveturn().getActivePlayer().getPlayerBoard().getHand().getPowerups())
            switch (powerupCard.getCubeColor()){
                case 'b':availableBlue++;break;
                case 'y':availableYellow++;break;
                case 'r':availableRed++;break;
            }
        return availableBlue>=blue&&availableRed>=red&&availableYellow>=yellow;
    }
}
