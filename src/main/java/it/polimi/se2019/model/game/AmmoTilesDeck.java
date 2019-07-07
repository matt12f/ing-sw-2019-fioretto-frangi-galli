package it.polimi.se2019.model.game;

import it.polimi.se2019.model.cards.AmmoTileCard;
import java.util.ArrayList;
import java.util.Collections;

public class AmmoTilesDeck {
    private ArrayList<AmmoTileCard> activeDeck;
    private ArrayList<AmmoTileCard> discardedDeck;

    public AmmoTilesDeck() {
        this.activeDeck = new ArrayList<>();
        this.discardedDeck = new ArrayList<>();
        activeDeck.add(new AmmoTileCard("ybb")); //PRIMA VOLTA
        activeDeck.add(new AmmoTileCard("yrr"));
        activeDeck.add(new AmmoTileCard("rbb"));
        activeDeck.add(new AmmoTileCard("ryy"));
        activeDeck.add(new AmmoTileCard("byy"));
        activeDeck.add(new AmmoTileCard("brr"));
        activeDeck.add(new AmmoTileCard("ybb")); //SECONDA VOLTA
        activeDeck.add(new AmmoTileCard("yrr"));
        activeDeck.add(new AmmoTileCard("rbb"));
        activeDeck.add(new AmmoTileCard("ryy"));
        activeDeck.add(new AmmoTileCard("byy"));
        activeDeck.add(new AmmoTileCard("brr"));
        activeDeck.add(new AmmoTileCard("ybb")); // Terza VOLTA
        activeDeck.add(new AmmoTileCard("yrr"));
        activeDeck.add(new AmmoTileCard("rbb"));
        activeDeck.add(new AmmoTileCard("ryy"));
        activeDeck.add(new AmmoTileCard("byy"));
        activeDeck.add(new AmmoTileCard("brr"));
        activeDeck.add(new AmmoTileCard("pyy")); //SI RIPETE 3 VOLTE LA COSA SOPRA
        activeDeck.add(new AmmoTileCard("prr"));
        activeDeck.add(new AmmoTileCard("pbb"));
        activeDeck.add(new AmmoTileCard("pyr")); // SI RIPETE ANCHE QUI, PRIMA
        activeDeck.add(new AmmoTileCard("pyb"));
        activeDeck.add(new AmmoTileCard("prb"));
        activeDeck.add(new AmmoTileCard("pyr")); // SECONDA
        activeDeck.add(new AmmoTileCard("pyb"));
        activeDeck.add(new AmmoTileCard("prb"));
        activeDeck.add(new AmmoTileCard("pyy")); //PAUSA
        activeDeck.add(new AmmoTileCard("prr")); //PAUSA
        activeDeck.add(new AmmoTileCard("pbb"));//PAUSA
        activeDeck.add(new AmmoTileCard("pyr")); // TERZA
        activeDeck.add(new AmmoTileCard("pyb"));
        activeDeck.add(new AmmoTileCard("prb"));
        activeDeck.add(new AmmoTileCard("pyr")); //quarta
        activeDeck.add(new AmmoTileCard("pyb"));
        activeDeck.add(new AmmoTileCard("prb"));

        this.shuffle();
    }

    public ArrayList<AmmoTileCard> getActiveDeck() {
        return activeDeck;
    }

    public ArrayList<AmmoTileCard> getDiscardedDeck() {
        return discardedDeck;
    }

    /** This method is called when activeDeck becomes empty. It takes the discardedDeck and
     * shuffles its content back to the activeDeck
     *
     * */
    public void setActiveDeck() {
        this.activeDeck = this.discardedDeck;
        this.discardedDeck=new ArrayList<>();
        this.shuffle();
    }

    /**
     *
     * @param card card to add at the discarded deck
     */
    public void setDiscardedDeck(AmmoTileCard card) {
        this.discardedDeck.add(card);
    }

    /**
     * shuffle the deck
     */
    public void shuffle(){
        Collections.shuffle(activeDeck);
    }

    /**
     * draw a card from the deck
     * @return the picked card
     */
    public AmmoTileCard draw(){
        AmmoTileCard drawn;

        if(activeDeck.isEmpty())
           this.setActiveDeck();

        drawn=activeDeck.get(0);
        activeDeck.remove(0);
        return drawn;
    }

    
}

