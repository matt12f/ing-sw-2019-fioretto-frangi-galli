package it.polimi.se2019.game;

import it.polimi.se2019.database.AmmoTileCard;

public class AmmoTilesDeck {
    private AmmoTileCard [] activeDeck;
    private AmmoTileCard [] discardedDeck;

    public AmmoTilesDeck() {
        this.activeDeck = new AmmoTileCard[36];
        this.discardedDeck = new AmmoTileCard[36];
        //TODO riempire il mazzo active
    }

    public AmmoTileCard[] getActiveDeck() {
        return activeDeck;
    }

    public AmmoTileCard[] getDiscardedDeck() {
        return discardedDeck;
    }

    /** This method is called when activeDeck gets emptied. It takes the discardedDeck and
     * shuffles its content back to the activeDeck
     *
     * */
    public void setActiveDeck() {
        this.activeDeck = this.discardedDeck;
        this.shuffle();
    }
    //TODO Check metodo
    public void setDiscardedDeck(AmmoTileCard card) {
        boolean found = false;
        int i = 0;
        do{
            if(discardedDeck[i] == null) {
                discardedDeck[i] = card;
            }else{
                i ++;
            }
        }while (!found);
    }

    public void shuffle(){
    //TODO Scrivere codice che mischi le carte
    }
    public AmmoTileCard draw(){

        return this.activeDeck[0];
    }
}
