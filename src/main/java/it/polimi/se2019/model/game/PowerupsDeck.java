package it.polimi.se2019.model.game;

import it.polimi.se2019.model.cards.PowerupCard;

import java.util.ArrayList;
import java.util.Collections;

public class PowerupsDeck {
    private ArrayList<PowerupCard> activeDeck;
    private ArrayList<PowerupCard> discardedDeck;
    //12 Powerups, one deck has two for each type
    public PowerupsDeck() {
        this.activeDeck = new ArrayList<>();
        this.discardedDeck = new ArrayList<>();
        //powerup creation
        for (int i=0; i<2; i++){
            //Targetting scope
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

    /** This method is called when activeDeck becomes empty. It takes the discardedDeck and
     * shuffles its content back to the activeDeck
     *
     * */
    public void setActiveDeck() {
        this.activeDeck = this.discardedDeck;
        this.discardedDeck=new ArrayList<>();
        this.shuffle();
    }

    /**This method is used to add a card to the discarded pile
     *
     */
    public void setDiscardedDeck(PowerupCard card) {
        this.discardedDeck.add(card);
    }


    public void shuffle(){
        Collections.shuffle(activeDeck);
    }

    /**
     * @return the card on top of the deck without removing it
     */
    public PowerupCard peekCardOnTop(){
        PowerupCard drawn;
        if (this.activeDeck.isEmpty())
            this.setActiveDeck();
        drawn = this.activeDeck.get(0);
        return drawn;
    }

    /**
     * @return the card on top of the deck, removing it
     */
    public PowerupCard draw(){
        PowerupCard drawn = peekCardOnTop();
        this.activeDeck.remove(0);
        return drawn;
    }
}
