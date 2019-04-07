package it.polimi.se2019.game;

import it.polimi.se2019.database.GunCard;
import it.polimi.se2019.database.PowerupCard;

import java.util.ArrayList;

public class Hand {
    private ArrayList<PowerupCard>  powerups;
    private GunCard [] guns;

    public Hand(){
        this.guns = new GunCard[3];
    }

    /**this method draws a weapon card from the player's hand
     * */
    public GunCard[] getGun() {
        return guns;
    }

    /**this method draws a powerup from the player's hand
     * */
    public ArrayList<PowerupCard> getPowerup() {
        return powerups;
    }
    /**this method puts a gun in the player's hand
     * */
    public void setGun(GunCard gun) {
       // TODO scrivere metodo
    }

    /**this method puts a powerup in the player's hand
     * */
    public void setPowerup(PowerupCard powerup) {
        // TODO scrivere metodo
    }
}
