package it.polimi.se2019.view;


import it.polimi.se2019.model.cards.GunCard;
import it.polimi.se2019.model.cards.PowerupCard;

public class PlayerHandView{
    private GunCard[] guns;
    private boolean[] loadedGuns;
    private PowerupCard[] powerups;

    public boolean isGunHandFull(){
        for (int i = 0; i <3 ; i++) {
            if (guns[i]==null)
                return false;
        }
        return true;
    }

    public GunCard[] getGuns() {
        return guns;
    }

    public void setGuns(GunCard[] guns) {
        this.guns = guns;
    }

    public boolean[] getLoadedGuns() {
        return loadedGuns;
    }

    public void setLoadedGuns(boolean[] loadedGuns) {
        this.loadedGuns = loadedGuns;
    }

    public PowerupCard[] getPowerups() {
        return powerups;
    }

    public void setPowerups(PowerupCard[] powerups) {
        this.powerups = powerups;
    }
}
