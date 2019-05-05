package it.polimi.se2019.model.game;

public class Decks {
    private GunDeck gunDeck;
    private PowerupsDeck powerupsDeck;
    private AmmoTilesDeck ammoTilesDeck;

    public Decks() {
        this.gunDeck= new GunDeck();
        this.powerupsDeck = new PowerupsDeck();
        this.ammoTilesDeck = new AmmoTilesDeck();
    }

    public GunDeck getGunDeck() {
        return gunDeck;
    }

    public PowerupsDeck getPowerupsDeck() {
        return powerupsDeck;
    }

    public AmmoTilesDeck getAmmotilesDeck() {
        return ammoTilesDeck;
    }
}
