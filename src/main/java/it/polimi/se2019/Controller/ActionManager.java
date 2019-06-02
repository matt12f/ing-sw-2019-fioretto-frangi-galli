package it.polimi.se2019.controller;


import it.polimi.se2019.model.cards.*;

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
}
