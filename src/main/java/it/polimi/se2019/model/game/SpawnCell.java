package it.polimi.se2019.model.game;

import it.polimi.se2019.Adrenaline;
import it.polimi.se2019.exceptions.FullException;
import it.polimi.se2019.model.cards.GunCard;
import java.util.ArrayList;

public class SpawnCell extends Cell{
    private ArrayList<GunCard> weaponCards;

    public SpawnCell(char color, char top, char bottom, char left, char right){
        super(color,top,bottom,left,right);
        this.weaponCards = new ArrayList<>();
    }

    @Override
    public ArrayList<GunCard> getItem() {
        return weaponCards;
    }


    /** It fills the first empty slot in weaponCards with the card it receives
     */
    @Override
    public void setItem(Object card)throws FullException{
        if(this.weaponCards.size()<3)
            this.weaponCards.add((GunCard)card);
        else
            throw new FullException("gun hand already full");
     }

    @Override
    public boolean needsRefill(boolean deckOk) {
        if (this.weaponCards.size()<3&&deckOk)
            return true;
        return false;
    }

    /** Method that returns the weapon from the pick-numbered slot
      **/
     @Override
    public GunCard pickItem(int pick){
        GunCard picked= this.weaponCards.get(pick);
        this.weaponCards.remove(pick);
        return picked;
     }

}
