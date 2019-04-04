package it.polimi.se2019.game;

import it.polimi.se2019.database.PowerupCard;

public class PowerupsDeck {
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
        //TODO setActiveDecks check  code
        this.activeDeck = this.discardedDeck;
        this.shuffle();
    }

    /**This method is used to update the deck of discarded cards
     *
     */
    public void setDiscardedDeck(PowerupCard card) { ;
        //TODO setDiscardedDeck check code
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
        //TODO shuffle
        //creare vettore di comodo e vettore nteger random,
        //trasferire nell'ordine del random da acive al vettore di comodo
        // ricaricare tutto nell'active deck
    }
    public PowerupCard draw(){
        //TODO ritornare un oggetto Powerupcard e scalare il mazzo
        //caricare primo oggetto su un variabile
        //scalare il vettore di uno
        //ritornare l'oggetto di comodo
        return this.activeDeck[0];
    }
}
