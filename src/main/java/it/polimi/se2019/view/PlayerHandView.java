package it.polimi.se2019.view;


import it.polimi.se2019.model.cards.GunCard;
import it.polimi.se2019.model.cards.PowerupCard;

import java.io.Serializable;

public  class PlayerHandView implements Serializable {
    private GunCard[] guns;
    private boolean[] loadedGuns;
    private PowerupCard[] powerups;
    private PowerupCard additionalPowerup;

    /**
     * create a view representation of the player's hand
     */
    public PlayerHandView(){
        this.powerups = new PowerupCard[3];
        this.loadedGuns = new boolean[3];
        this.guns = new GunCard[3];
        for (int i = 0; i <3 ; i++) {
            this.guns[i] = null;
            this.powerups[i] = null;
        }
        this.additionalPowerup = null;
    }

    /**
     *
     * @return if the weapon hand is full or not (=3)
     */
    public boolean isGunHandFull(){
        for (int i = 0; i <3 ; i++) {
            if (guns[i]==null)
                return false;
        }
        return true;
    }

    /**
     *
     * @return the weapons hand
     */
    public GunCard[] getGuns() {
        return guns;
    }

    /**
     * update weapons hand
     * @param guns
     */
    public void setGuns(GunCard[] guns) {
        this.guns = guns;
    }

    /**
     *
     * @return the weapon that are loaded
     */
    public boolean[] getLoadedGuns() {
        return loadedGuns;
    }

    /**
     * update of the loaded weaons
     * @param loadedGuns
     */
    public void setLoadedGuns(boolean[] loadedGuns) {
        this.loadedGuns = loadedGuns;
    }

    /**
     *
     * @return pw hand
     */
    public PowerupCard[] getPowerups() {
        return powerups;
    }

    /**
     * set pw hand
     * @param powerups
     */
    public void setPowerups(PowerupCard[] powerups) {
        for (int i = 0; i < this.powerups.length; i++) {
            this.powerups[i] = powerups[i];
        }
    }

    /**
     *
     * @return the addictional powerup
     */
    public PowerupCard getAdditionalPowerup() {
        return additionalPowerup;
    }

    /**
     * update the addictional powerup
     * @param additionalPowerup
     */
    public void setAdditionalPowerup(PowerupCard additionalPowerup) {
        this.additionalPowerup = additionalPowerup;
    }
}
