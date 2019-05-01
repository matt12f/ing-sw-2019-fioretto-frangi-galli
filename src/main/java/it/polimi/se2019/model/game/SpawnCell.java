package it.polimi.se2019.model.game;

import it.polimi.se2019.model.cards.GunCard;


import static it.polimi.se2019.Adrenaline.getMainController;

public class SpawnCell extends Cell {
    private GunCard [] weaponCards;

    public SpawnCell(char color, char top, char bottom, char left, char right){
        super(color,top,bottom,left,right);
        this.weaponCards = new GunCard[3];
    }

    public GunCard[] getWeaponCards() {
        return weaponCards;
    }

    /** It fills the first empty slot in weaponCards with the card it receives
     */
    public void setWeaponCard(GunCard gunCard){
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

     /**Method that returns the weapon from the pick-numbered slot
      **/
    public GunCard pickWeapon(int pick){
        //TODO controllare questo metodo nel caso in cui ci sia uno switch
        GunCard picked= weaponCards[pick];
        //TODO aggiungere svuotamento di this.drop
        return picked;
     }
}
