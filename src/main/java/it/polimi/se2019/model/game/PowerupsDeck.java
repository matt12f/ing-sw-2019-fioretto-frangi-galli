package it.polimi.se2019.model.game;

import it.polimi.se2019.model.cards.PowerupCard;

import java.util.*;

public class PowerupsDeck {
    private  ArrayList<PowerupCard> activeDeck ;
    private ArrayList<PowerupCard> discardedDeck;
    //12 POWER UP, IN UN MAZZO DUE PER TIPO
    public PowerupsDeck() {
        this.activeDeck = new ArrayList<>();
        this.discardedDeck = new ArrayList<>();
        //INIZIALIZZO I POWER UP
        for (int i=0; i<2; i++){
            //Targeting scope
            this.activeDeck.add(new PowerupCard("TargettingScope", 'r'));
            this.activeDeck.add(new PowerupCard("TargettingScope", 'b'));
            this.activeDeck.add(new PowerupCard("TargettingScope", 'y'));
            //NEWTON
            this.activeDeck.add(new PowerupCard("Newton", 'r'));
            this.activeDeck.add(new PowerupCard("Newton", 'b'));
            this.activeDeck.add(new PowerupCard("Newton", 'y'));
            //TELEPORTER
            this.activeDeck.add(new PowerupCard("Teleporter", 'r'));
            this.activeDeck.add(new PowerupCard("Teleporter", 'b'));
            this.activeDeck.add(new PowerupCard("Teleporter", 'y'));
            //TAGBACK GRENADE
            this.activeDeck.add(new PowerupCard("TagbackGrenade", 'r'));
            this.activeDeck.add(new PowerupCard("TagbackGrenade", 'b'));
            this.activeDeck.add(new PowerupCard("TagbackGrenade", 'y'));
        }
        this.shuffle();
    }

    public ArrayList<PowerupCard> getActiveDeck() {
        return activeDeck;
    }

    public ArrayList<PowerupCard> getDiscardedDeck() {
        return discardedDeck;
    }

    /** This method is called when activeDeck gets emptied. It takes the discardedDeck and
     * shuffles its content back to the activeDeck
     *
     * */
    public void setActiveDeck() {
        this.activeDeck = this.discardedDeck;
        this.discardedDeck = new ArrayList<>();
        this.shuffle();
    }

    /**This method is used to update the deck of discarded cards
     *
     */
    public void setDiscardedDeck(PowerupCard card) {
        //TODO setDiscardedDeck check code
        this.discardedDeck.add(card);
    }

    /**
     *
     */
    public void shuffle(){
        int i= 0;
        int random = 0;
        ArrayList<PowerupCard> temp1 = new ArrayList<>();
        ArrayList<PowerupCard> temp2 = new ArrayList<>();
        PowerupCard swipe;
        int random2 = (int)(Math.random()*(22))+1;
        random= ((int)(Math.random()*(9))+1)*((int)(Math.random()*(9))+1);
        //using temp2 to copy active
        for(PowerupCard card: activeDeck){
            temp2.add(card);
        }
        //Shuffles all the cards
        for(i=0; i<random; i++){
            while (i<this.activeDeck.size()/2){
                temp1.add(temp2.get(i));
                temp1.add(temp2.get(temp2.size()-i));
            }
            swipe=temp1.get((int)random2);
            temp1.set((int)random2, temp1.get(0));
            temp1.set(0, swipe);
            while (i<this.activeDeck.size()/2){
                temp2.add(temp1.get(i));
                temp2.add(temp1.get(temp1.size()-i));
            }
            swipe=temp2.get((int)random2);
            temp2.set((int)random2, temp2.get(0));
            temp2.set(0, swipe);
        }

        this.activeDeck=temp2;
    }

    /**
     *
     * @return
     */
    public PowerupCard draw(){
        PowerupCard drawn;
        if (this.activeDeck.isEmpty())
            this.setActiveDeck();
        drawn= this.activeDeck.get(0);
        this.activeDeck.remove(0);
        return drawn;
    }
}
