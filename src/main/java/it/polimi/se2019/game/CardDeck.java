package it.polimi.se2019.game;

/**This class could be re-engineered with an int vector containing the numbers of the active shuffled cards
 * and a boolean vector of the used cards (could save computing time)
 * */
public abstract class CardDeck {
    private int activeDeck [];
    private int discardedDeck [];

    public CardDeck(){}

    public void shuffle(){

    }

    public int draw(){
        return 0;
    }
}
