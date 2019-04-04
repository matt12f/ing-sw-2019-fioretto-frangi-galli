package it.polimi.se2019.game;
import it.polimi.se2019.database.*;


public class GunDeck {
    private GunCard activeDeck [];
    private GunCard discardedDeck [];

    public GunDeck() {
        this.activeDeck = new GunCard[21];
        this.discardedDeck = new GunCard[21];
        /**
         * creation of the 21 guns
         */
        this.activeDeck[0] = new LockRifle();
        this.activeDeck[1] = new MachineGun();
        this.activeDeck[2] = new Thor();
        this.activeDeck[3] = new PlasmaGun();
        this.activeDeck[4] = new Whisper();
        this.activeDeck[5] = new Electroscythe();
        this.activeDeck[6] = new TractorBeam();
        this.activeDeck[7] = new VortexCannon();
        this.activeDeck[8] = new Furnace();
        this.activeDeck[9] = new Heatseeker();
        this.activeDeck[10] = new Hellion();
        this.activeDeck[11] = new FlameThrower();
        this.activeDeck[12] = new GrenadeLauncher();
        this.activeDeck[13] = new RocketLauncher();
        this.activeDeck[14] = new Railgun();
        this.activeDeck[15] = new CyberBlade();
        this.activeDeck[16] = new Zx2();
        this.activeDeck[17] = new Shotgun();
        this.activeDeck[18] = new PowerGlove();
        this.activeDeck[19] = new Shotgun();
        this.activeDeck[20] = new Sledgehammer();
        this.shuffle();


    }

    public GunCard[] getActiveDeck() {
        return activeDeck;
    }

    public GunCard[] getDiscardedDeck() {
        return discardedDeck;
    }

    /** This method is called when activeDeck gets emptied. It takes the discardedDeck and
     * shuffles its content back to the activeDeck
     *
     * */
    public void setActiveDeck() {
        this.activeDeck = this.discardedDeck;
        this.shuffle();
    }
    public void setDiscardedDeck(GunCard card) {
        boolean found = false;
        int i = 0;
        do{
            if(discardedDeck[i] == null) {
                discardedDeck[i] = card;
            }else{
                i ++;
            }
        }while (found == false);
    }
    public void shuffle(){

    }

    public GunCard draw(){
        return this.activeDeck[0];
    }
}
