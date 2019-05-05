package it.polimi.se2019.model.game;
import it.polimi.se2019.model.cards.GunCard;
import it.polimi.se2019.model.cards.PowerupCard;

import it.polimi.se2019.exceptions.CardNotFoundException;
import it.polimi.se2019.exceptions.HandFullException;

public class Hand {
    private PowerupCard [] powerups;
    private GunCard [] guns;
    private static final int MAXCARDS=3;

    public Hand(){
        this.guns = new GunCard[MAXCARDS];
        this.powerups = new PowerupCard[MAXCARDS];
    }

    /**this method returns the guns array from the player's hand
     * */
    public GunCard[] getGuns() {
        return guns;
    }

    /**this method returns the powerups array from the player's hand
     * */
    public PowerupCard[] getPowerups() {
        return powerups;
    }

    /**this method puts a gun in the player's hand
     * */
    public void setGun(GunCard gun) throws HandFullException{
        int i=0;
        boolean set=false;
        while(!set && i<MAXCARDS){
            if(this.guns[i]==null) {
                this.guns[i]=gun;
                set = true;
            }
            else i++;
        }
        if(!set) throw new HandFullException("guns");
    }

    /**this method puts a powerup in the player's hand
     * */
    public void setPowerup(PowerupCard powerup) throws HandFullException {
        int i=0;
        boolean set=false;
        while(!set && i<MAXCARDS){
            if(this.powerups[i]==null) {
                this.powerups[i]=powerup;
                set = true;
            }
            else i++;
        }
        if(!set) throw new HandFullException("powerups");
    }

    public void substitutionPowerup (PowerupCard discarded,PowerupCard newPowerup) throws CardNotFoundException {
        int i=0;
        boolean substituted=false;
        while(!substituted && i<MAXCARDS){
            if(this.powerups[i]==discarded) {
                this.powerups[i]=newPowerup;
                substituted = true;
            }
            else i++;
        }
        if(!substituted) throw new CardNotFoundException("powerups");
    }

    public void substitutionGunCard (GunCard discarded, GunCard newGunCard) throws CardNotFoundException{
        int i=0;
        boolean substituted=false;
        while(!substituted && i<MAXCARDS){
            if(this.guns[i]==discarded) {
                this.guns[i]=newGunCard;
                substituted = true;
            }
            else i++;
        }
        if(!substituted) throw new CardNotFoundException("guns");
    }
}


