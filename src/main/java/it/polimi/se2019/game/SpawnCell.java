package it.polimi.se2019.game;

import it.polimi.se2019.cards.GunCard;

public class SpawnCell extends Cell {
    private GunCard [] weaponCards;

    public SpawnCell(char color, char top, char bottom, char left, char right){
        this.color = color;
        this.top = top;
        this.bottom = bottom;
        this.left = left;
        this.right = right;
        this.weaponCards = new GunCard[3];
    }

    public GunCard[] getWeaponCards() {

        return weaponCards;

    }

    /** setWeaponCards fills the empty slots of the weapon cards when a card is picked up
     *
     */
    public void setWeaponCards(GunCard weapon){
        //TODO setWeaponCards


     }
     /**Method that displays the 3 cards that the player can pick in the spawn point and he can pick up one*
      **/
    public void pickWeapon(int pick){
        //TODO pickWeapon

     }
}
