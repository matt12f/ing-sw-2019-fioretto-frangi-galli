package it.polimi.se2019.model.game;
import it.polimi.se2019.model.cards.*;

import java.util.ArrayList;
import java.util.Collections;


public class GunDeck {
    private ArrayList<GunCard> activeDeck;

    /**This method create a shuffled deck of GunCards
     *
     */
    public GunDeck() {
        this.activeDeck= new ArrayList<>();
        this.activeDeck.add(new LockRifle());
        this.activeDeck.add(new Electroscythe());
        this.activeDeck.add(new MachineGun());
        this.activeDeck.add(new TractorBeam());
        this.activeDeck.add(new Thor());
        this.activeDeck.add(new VortexCannon());
        this.activeDeck.add(new Furnace());
        this.activeDeck.add(new PlasmaGun());
        this.activeDeck.add(new Heatseeker());
        this.activeDeck.add(new Whisper());
        this.activeDeck.add(new Hellion());
        this.activeDeck.add(new FlameThrower());
        this.activeDeck.add(new Zx2());
        this.activeDeck.add(new GrenadeLauncher());
        this.activeDeck.add(new Shotgun());
        this.activeDeck.add(new RocketLauncher());
        this.activeDeck.add(new PowerGlove());
        this.activeDeck.add(new Railgun());
        this.activeDeck.add(new Shockwave());
        this.activeDeck.add(new CyberBlade());
        this.activeDeck.add(new Sledgehammer());
        Collections.shuffle(this.activeDeck);
    }

    public ArrayList<GunCard> getActiveDeck() {
        return activeDeck;
    }

    /**
     *
     * @return the first card
     */
    public GunCard draw(){
        GunCard drawn;
        drawn =  activeDeck.get(0);
        activeDeck.remove(0);
        return drawn;
    }
}
