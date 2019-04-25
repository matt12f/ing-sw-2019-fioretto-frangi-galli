package it.polimi.se2019.model.game;

import it.polimi.se2019.model.cards.GunCard;


import static it.polimi.se2019.Adrenaline.getMainController;

public class SpawnCell extends Cell {
    private GunCard [] weaponCards;

    public SpawnCell(char color, char top, char bottom, char left, char right){
        super(color,top,bottom,left,right);
        this.weaponCards = new GunCard[3];
        weaponCards[0]=getMainController().getMainGameModel().currentDecks.getGunDeck().draw();
        weaponCards[1]=getMainController().getMainGameModel().currentDecks.getGunDeck().draw();
        weaponCards[2]=getMainController().getMainGameModel().currentDecks.getGunDeck().draw();
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
     /**Method that allow the player to take a weapon from the 3 slots
      **/
    public GunCard pickWeapon(int pick){
        //TODO controllare questo metodo nel caso in cui ci sia uno switch
        GunCard picked ;
        picked = weaponCards[pick];
        setWeaponCards(picked);
        return picked;
     }
}
