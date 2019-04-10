package it.polimi.se2019.model.game;

import it.polimi.se2019.model.cards.AmmoTileCard;
import java.util.*;

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

    }

    public ArrayList<AmmoTileCard> getActiveDeck() {
        return activeDeck;
    }

    public ArrayList<AmmoTileCard> getDiscardedDeck() {
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
        this.discardedDeck.add(card);
    }

    public void shuffle(){
        int i= 0;
        int random = 0;
        ArrayList<AmmoTileCard> temp1 = new ArrayList<>();
        ArrayList<AmmoTileCard> temp2 = new ArrayList<>();
        AmmoTileCard swipe;
        int random2 = (int)(Math.random()*(10))+1;
        random= ((int)(Math.random()*(4))+1)*((int)(Math.random()*(10))+1);
        for(AmmoTileCard card: this.activeDeck)
            temp2.add(card);
        for(i=0; i<random; i++){
            while (i<this.activeDeck.size()/2){
                temp1.add(temp2.get(i));
                temp1.add(temp2.get(temp2.size()-i));
            }
            swipe=temp1.get(random2);
            temp1.set(random2, temp1.get(0));
            temp1.set(0, swipe);
            while (i<this.activeDeck.size()/2){
                temp2.add(temp1.get(i));
                temp2.add(temp1.get(temp1.size()-i));
            }
            swipe=temp2.get(random2);
            temp2.set(random2, temp2.get(0));
            temp2.set(0, swipe);
        }
        this.activeDeck=temp2; //FORSE QUESTO NON BASTA
    }

    public AmmoTileCard draw(){
        AmmoTileCard drawn;
        if(activeDeck.isEmpty())
           this.setActiveDeck();
        drawn=activeDeck.get(0);
        activeDeck.remove(0);
        return drawn;
    }

    
}

