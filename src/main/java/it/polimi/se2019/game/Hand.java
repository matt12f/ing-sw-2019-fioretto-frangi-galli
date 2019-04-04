package it.polimi.se2019.game;

import it.polimi.se2019.database.GunCard;
import it.polimi.se2019.database.PowerupCard;

public class Hand {
    private PowerupCard powerups [];
    private GunCard guns [];

    public Hand(){
        this.powerups=new PowerupCard[3];
        this.guns=new GunCard[3];
    }

    /**this method draws a weapon card from the player's hand
     * */
    public GunCard[] getGun() {
        return guns;
    }

    /**this method draws a powerup from the player's hand
     * */
    public PowerupCard[] getPowerup() {
        return powerups;
    }
    /**this method puts a gun in the player's hand
     * */
    public void setGun() {
    }
    /**this method puts a powerup in the player's hand
     * */
    public void setPowerup() {
    }
}
