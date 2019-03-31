package it.polimi.se2019.game;

public class Decks {
    public GunDeck gunDeck;
    public PowerupsDeck powerupsDeck;
    public AmmotilesDeck ammotilesDeck;

    public Decks() {
        this.gunDeck= new GunDeck();
        this.powerupsDeck = new PowerupsDeck();
        this.ammotilesDeck = new AmmotilesDeck();
    }
}
