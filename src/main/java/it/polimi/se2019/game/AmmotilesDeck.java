package it.polimi.se2019.game;

public class AmmotilesDeck extends CardDeck{
    private int activeDeck [];
    private int discardedDeck [];

    public AmmotilesDeck() {
        this.activeDeck = new int[21];
        this.discardedDeck = new int[21];
        //TODO riempire il mazzo active
    }

    public int[] getActiveDeck() {
        return activeDeck;
    }

    public int[] getDiscardedDeck() {
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