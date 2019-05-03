package it.polimi.se2019.model.game;

import it.polimi.se2019.model.cards.GunCard;
import java.util.ArrayList;

//TODO cambiare il tipo di eccezione lanciata
public class SpawnCell extends Cell {
    private ArrayList<GunCard> weaponCards;

    public SpawnCell(char color, char top, char bottom, char left, char right){
        super(color,top,bottom,left,right);
        this.weaponCards = new ArrayList<>();
    }

    public ArrayList<GunCard> getWeaponCards() {
        return weaponCards;
    }

    /** It fills the first empty slot in weaponCards with the card it receives
     */
    public void setWeaponCard(GunCard gunCard)throws IndexOutOfBoundsException{
        if(this.weaponCards.size()<3)
            this.weaponCards.add(gunCard);
        else
            throw new IndexOutOfBoundsException();
     }

     /** Method that returns the weapon from the pick-numbered slot
      **/
    public GunCard pickWeapon(int pick){
        GunCard picked= this.weaponCards.get(pick);
        this.weaponCards.remove(pick);
        return picked;
     }
}
