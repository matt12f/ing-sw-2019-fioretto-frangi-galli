package it.polimi.se2019.model.game;

public class Decks {
    public GunDeck gunDeck;
    public PowerupsDeck powerupsDeck;
    public AmmoTilesDeck ammotilesDeck;

    public Decks() {
        this.gunDeck= new GunDeck();
        this.powerupsDeck = new PowerupsDeck();
        this.ammotilesDeck = new AmmoTilesDeck();
    }
    //TODO siamo sicuri di volere i mazzi pubblici? da valutare un approccio con metodi accessori
}
