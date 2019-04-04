package it.polimi.se2019.game;
import it.polimi.se2019.database.GunCard;


public class GunDeck extends CardDeck{
    private GunCard activeDeck [];
    private GunCard discardedDeck [];

    public GunDeck() {
        this.activeDeck = new GunCard[21];
        this.discardedDeck = new GunCard[21];
        //TODO riempire il mazzo active
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
    }

    public void setDiscardedDeck() { ;
    }
}
