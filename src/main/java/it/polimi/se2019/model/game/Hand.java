package it.polimi.se2019.model.game;
import it.polimi.se2019.model.cards.GunCard;
import it.polimi.se2019.model.cards.PowerupCard;

import java.util.NoSuchElementException;

//TODO fare eccezioni custom o cambiarle, così danno code smell

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
    public void setGun(GunCard gun) throws ArrayIndexOutOfBoundsException{
        int index= -1;
        for (int i=0; i<MAXCARDS; i++){
            if(this.guns[i] == null){
                //TODO correggere, così mette la carta in fondo (togli for e metti while con boolean found)
                index = i;
            }
        }
        if(index != -1){
            this.guns[index]=gun;
        }else{
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    /**this method puts a powerup in the player's hand
     * */
    public void setPowerup(PowerupCard powerup) throws ArrayIndexOutOfBoundsException {
        int  index= -1;
        for (int i=0; i<MAXCARDS; i++){
            if(this.powerups[i] == null){
                //TODO correggere, così mette la carta in fondo (togli for e metti while con boolean found)
                index = i;
            }
        }
        if(index != -1){
            this.powerups[index]=powerup;
        }else{
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    public void substitutionPowerup (PowerupCard discarded,PowerupCard newPowerup) throws NoSuchElementException {
        int index= -1;
        for (int i=0; i<MAXCARDS; i++){
            if(this.powerups[i] == discarded){
                index = i;
            }
        }
        if(index != -1){
            this.powerups[index]=newPowerup;
        }else{
            throw new NoSuchElementException();
        }
    }

    public void substitutionGunCard (GunCard discarded, GunCard newGunCard) throws NoSuchElementException{
        int index= -1;
        for (int i=0; i<MAXCARDS; i++){
            if(this.guns[i] == discarded){
                index = i;
            }
        }
        if(index != -1){
            this.guns[index]=newGunCard;
        }else{
            throw new NoSuchElementException();
        }
    }
}


