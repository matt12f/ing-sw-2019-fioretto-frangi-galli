package it.polimi.se2019.game;
import it.polimi.se2019.database.*;

import java.util.*;


public class GunDeck {
    private ArrayList<GunCard> activeDeck;
    private ArrayList<GunCard> discardedDeck;

    /**This method create a shuffled deck, the array "used" says if a card (1 slot per card) is already in the deck, then a random
     * int is created with "random", and with a switch the integer is translated into a gunCard, then putted into the deck
     *
     */
    public GunDeck() {
        int used[];
        int i;
        int temp;
        this.activeDeck = new ArrayList<GunCard>();
        this.discardedDeck = new ArrayList<GunCard>();
        used= new int[21];
        Random random = new Random();
        for(i=0; i<21; i++){
            used[i]=0;
        }
        while (this.activeDeck.size()<21){
            temp = random.nextInt(21);
            if (used[temp]==0) {
                used[temp] = 1;
                switch (temp) {
                    case 0:
                        activeDeck.add(new LockRifle());
                        break;
                    case 1:
                        activeDeck.add(new Electroscythe());
                        break;
                    case 2:
                        activeDeck.add(new MachineGun());
                        break;
                    case 3:
                        activeDeck.add(new TractorBeam());
                        break;
                    case 4:
                        activeDeck.add(new Thor());
                        break;
                    case 5:
                        activeDeck.add(new VortexCannon());
                        break;
                    case 6:
                        activeDeck.add(new Furnace());
                        break;
                    case 7:
                        activeDeck.add(new PlasmaGun());
                        break;
                    case 8:
                        activeDeck.add(new Heatseeker());
                        break;
                    case 9:
                        activeDeck.add(new Whisper());
                        break;
                    case 10:
                        activeDeck.add(new Hellion());
                        break;
                    case 11:
                        activeDeck.add(new FlameThrower());
                        break;
                    case 12:
                        activeDeck.add(new Zx2());
                        break;
                    case 13:
                        activeDeck.add(new GrenadeLauncher());
                        break;
                    case 14:
                        activeDeck.add(new Shotgun());
                        break;
                    case 15:
                        activeDeck.add(new RocketLauncher());
                        break;
                    case 16:
                        activeDeck.add(new PowerGlove());
                        break;
                    case 17:
                        activeDeck.add(new Railgun());
                        break;
                    case 18:
                        activeDeck.add(new Shockwave());
                        break;
                    case 19:
                        activeDeck.add(new CyberBlade());
                        break;
                    case 20:
                        activeDeck.add(new Sledgehammer());
                        break;
                }
            }
        }
    }

    public ArrayList<GunCard> getActiveDeck() {
        return activeDeck;
    }


    public GunCard draw(){
        GunCard drawn;
        drawn =  activeDeck.get(0);
        activeDeck.remove(0);
        return drawn;
    }
}
