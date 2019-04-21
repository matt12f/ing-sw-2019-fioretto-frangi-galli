package it.polimi.se2019.model.game;
// TODO sostituire il 3 con una constante, più elegante, tranquilli fo io successivamente, sono Frangi obv
import it.polimi.se2019.model.cards.GunCard;
import it.polimi.se2019.model.cards.PowerupCard;
import org.omg.CosNaming.NamingContextPackage.NotFound;

public class Hand {
    private PowerupCard []  powerups;
    private GunCard [] guns;

    public Hand(){
        this.guns = new GunCard[3];
        this.powerups = new PowerupCard[3];
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
    public void setGun(GunCard gun) throws ArrayIndexOutOfBoundsException {
        int i, index= -1;
        for (i=0; i<3; i++){
            if(this.guns[i] == null){
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
        int i, index= -1;
        for (i=0; i<3; i++){
            if(this.powerups[i] == null){
                index = i;
            }
        }
        if(index != -1){
            this.powerups[index]=powerup;
        }else{
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    public void substitutionPowerUp (PowerupCard discarded,PowerupCard newPowerup) throws NotFound {
        int i, index= -1;
        for (i=0; i<3; i++){
            if(this.powerups[i] == discarded){
                index = i;
            }
        }
        if(index != -1){
            this.powerups[index]=newPowerup;
        }else{
            throw new NotFound();
        }
    }

    public void substitutionGunCard (GunCard discarded, GunCard newGunCard) throws NotFound{
        int i, index= -1;
        for (i=0; i<3; i++){
            if(this.guns[i] == discarded){
                index = i;
            }
        }
        if(index != -1){
            this.guns[index]=newGunCard;
        }else{
            throw new NotFound();
        }
    }
}


