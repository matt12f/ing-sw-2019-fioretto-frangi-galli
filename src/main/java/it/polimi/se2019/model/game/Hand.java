package it.polimi.se2019.model.game;
import it.polimi.se2019.controller.Controller;
import it.polimi.se2019.controller.MapManager;
import it.polimi.se2019.model.cards.GunCard;
import it.polimi.se2019.model.cards.PowerupCard;

import it.polimi.se2019.exceptions.CardNotFoundException;
import it.polimi.se2019.exceptions.FullException;
import it.polimi.se2019.model.cards.Zx2;

import java.io.Serializable;

public class Hand implements Serializable {
    private PowerupCard [] powerups;
    private PowerupCard additionalPowerup;
    private GunCard [] guns;
    private static final int MAXCARDS=3;

    public Hand(){
        this.guns = new GunCard[MAXCARDS];
        this.additionalPowerup = null;
        this.powerups = new PowerupCard[MAXCARDS];
    }

    public static int getMaxcards(){
        return Hand.MAXCARDS;
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

    /**
     * this method returns a single powerup given by the parameter
     */

    public PowerupCard getPowerup(int i){
        return this.powerups[i];
    }

    /**this method puts a gun in the player's hand
     * */
    /**
     *
     * @param gun
     * @throws FullException
     */
    public void setGun(GunCard gun) throws FullException {
        int i=0;
        boolean set=false;
        while(!set && i<MAXCARDS){
            if(this.guns[i]==null) {
                this.guns[i]=gun;
                set = true;
            }
            else i++;
        }
        if(!set) throw new FullException("guns");
    }

    /**
     * set the 4th powerup to add to the hand
     * @param additionalPowerup
     */
    public void setAdditionalPowerup(PowerupCard additionalPowerup) {
        this.additionalPowerup = additionalPowerup;
    }

    /**this method puts a powerup in the player's hand
     * */
    public void setPowerup(PowerupCard powerup) throws FullException {
        int i=0;
        boolean set=false;
        while(!set && i<MAXCARDS){
            if(this.powerups[i]==null) {
                this.powerups[i]=powerup;
                set = true;
            }
            else i++;
        }
        if(!set) throw new FullException("powerups");
    }

    /**
     * harmlessly checks if the powerups array is full
     */
    public boolean isPowerUpHandFull(){
        Hand handClone=this.clone();
        try {
            handClone.setPowerup(new PowerupCard("xxx",'x'));
        }catch (FullException e){
            return true;
        }
        return false;
    }

    /**
     * harmlessly checks if the guncards array is full
     */
    public boolean isGunCardHandFull(){
        Hand handClone=this.clone();
        try {
            handClone.setGun(new Zx2());
        }catch (FullException e){
            return true;
        }
        return false;
    }

    /**
     * substitute a powerup in the hand with another powerup
     * @param discarded
     * @param newPowerup
     * @throws CardNotFoundException
     */
    public void substitutionPowerup (PowerupCard discarded,PowerupCard newPowerup) throws CardNotFoundException {
        int i=0;
        boolean substituted=false;
        while(!substituted && i<MAXCARDS){
            if(this.powerups[i].equals(discarded)) {
                this.powerups[i]=newPowerup;
                this.additionalPowerup = null;
                substituted = true;
            }
            else i++;
        }
        if(!substituted) throw new CardNotFoundException("powerup swap");
    }

    /**
     *  substitute a weapon in the hand with another weapon
     * @param spawnPoint cell from the weapon come
     * @param discarded
     * @param newGunCard
     * @throws CardNotFoundException
     */
    public void substitutionGunCard ( NewCell spawnPoint, GunCard discarded, GunCard newGunCard) throws CardNotFoundException{
        int i=0;
        boolean found=false;
        while(!found && i<MAXCARDS){
            if(this.guns[i].equals(discarded)){
                this.guns[i] = newGunCard;
                found = true;
            }
            else i++;
        }


        if(!found)
            throw new CardNotFoundException("gun swap");
        else {//the cards have been swapped in the player's hand, now they must be swapped in the spawn point

            spawnPoint.pickItem(newGunCard);
            try {
                discarded.setLoaded(true);
                spawnPoint.setItem(discarded);
            }catch (FullException e){
                //nothing to see here
            }
        }

    }

    public PowerupCard getAdditionalPowerup() {
        return additionalPowerup;
    }

    public boolean isEmptyPU(){
        boolean empty;
        empty = this.powerups[0] == null && this.powerups[1] == null && this.powerups[2] == null;
        return empty;
    }

    public void removePowerUp(int indexInHand){
        this.powerups[indexInHand]=null;
    }

    @Override
    public Hand clone(){
        Hand hand=new Hand();
        hand.guns=this.guns.clone();
        hand.powerups=this.powerups.clone();
        if(hand.additionalPowerup!=null)
            hand.additionalPowerup=this.additionalPowerup.clone();
        return hand;
    }
}


