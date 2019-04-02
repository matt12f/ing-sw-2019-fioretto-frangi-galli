package it.polimi.se2019.game;

public class PowerupsDeck extends CardDeck{
    private int activeDeck [];
    private int discardedDeck [];

    public PowerupsDeck() {
        this.activeDeck = new int[12];
        this.discardedDeck = new int[12];
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
        //TODO setActiveDecks scrivere codice
    }

    /**This method is used to update the deck of discarded cards
     *
     */
    public void setDiscardedDeck() { ;
        //TODO setDiscardedDeck scrivere codice
    }
}
