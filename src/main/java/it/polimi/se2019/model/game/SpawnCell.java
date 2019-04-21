package it.polimi.se2019.model.game;

import it.polimi.se2019.model.cards.GunCard;

public class SpawnCell extends Cell {
    private GunCard [] weaponCards;

    public SpawnCell(char color, char top, char bottom, char left, char right){
        super(color,top,bottom,left,right);
        this.weaponCards = new GunCard[3];
    }
    public GunCard[] getWeaponCards() {
        return weaponCards;
    }

    /** It fills the empty slots of the weapon cards when a card is picked up
     */
    public void setWeaponCards(GunCard gunCard){
        boolean filled=false;
        int i=0;
        while (!filled){
            if (weaponCards[i]==null){
                this.weaponCards[i]=gunCard;
                filled=true;
            }
            else i++;
     }
    }
     /**Method that returns the card in the pick position
      **/
    public GunCard pickWeapon(int pick){
        return weaponCards[pick];
     }
}
