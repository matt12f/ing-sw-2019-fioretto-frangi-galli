package it.polimi.se2019.game;

import it.polimi.se2019.database.PowerupCard;

public class PowerupsDeck extends CardDeck{
    private PowerupCard activeDeck [];
    private PowerupCard discardedDeck [];

    public PowerupsDeck() {
        this.activeDeck = new PowerupCard[24];
        this.discardedDeck = new PowerupCard[24];
        //TODO riempire il mazzo active
    }

    public PowerupCard[] getActiveDeck() {
        return activeDeck;
    }

    public PowerupCard[] getDiscardedDeck() {
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
