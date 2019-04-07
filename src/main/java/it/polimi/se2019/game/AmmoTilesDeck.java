package it.polimi.se2019.game;

import it.polimi.se2019.database.AmmoTileCard;
import java.util.*;

public class AmmoTilesDeck {
    private ArrayList<AmmoTileCard> activeDeck;
    private ArrayList<AmmoTileCard> discardedDeck;

    public AmmoTilesDeck() {
        this.activeDeck = new ArrayList<AmmoTileCard>();
        this.discardedDeck = new ArrayList<AmmoTileCard>();
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
        ArrayList<AmmoTileCard> Temp1 = new ArrayList<AmmoTileCard>();
        ArrayList<AmmoTileCard> Temp2 = new ArrayList<AmmoTileCard>();
        AmmoTileCard swipe;
        int random2 = (int)(Math.random()*(10))+1;
        random= ((int)(Math.random()*(4))+1)*((int)(Math.random()*(10))+1);
        for(AmmoTileCard card: this.activeDeck)
            Temp2.add(card);
        for(i=0; i<random; i++){
            while (i<this.activeDeck.size()/2){
                Temp1.add(Temp2.get(i));
                Temp1.add(Temp2.get(Temp2.size()-i));
            }
            swipe=Temp1.get(random2);
            Temp1.set(random2, Temp1.get(0));
            Temp1.set(0, swipe);
            while (i<this.activeDeck.size()/2){
                Temp2.add(Temp1.get(i));
                Temp2.add(Temp1.get(Temp1.size()-i));
            }
            swipe=Temp2.get(random2);
            Temp2.set(random2, Temp2.get(0));
            Temp2.set(0, swipe);
        }
        this.activeDeck=Temp2; //FORSE QUESTO NON BASTA
    }

    public AmmoTileCard draw(){
        AmmoTileCard drawn;
        if(activeDeck.size()==0)
           this.setActiveDeck();
        drawn=activeDeck.get(0);
        activeDeck.remove(0);
        return drawn;
    }

    
}

