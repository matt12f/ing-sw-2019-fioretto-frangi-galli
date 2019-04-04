package it.polimi.se2019.game;

import it.polimi.se2019.database.GunCard;

public class SpawnCell extends Cell {
    private GunCard weaponCards[];

    public SpawnCell(int id, char color, char top, char bottom, char left, char right){

    }

    public GunCard[] getWeaponCards() {

        return weaponCards;

    }

    /** setWeaponCards fills the empty slots of the weapon cards when a card is picked up
     *
     */
    public void setWeaponCards(){
        //TODO setWeaponCards scrivere codice


     }
     /**Method that displays the 3 cards that the player can pick in the spawn point and he can pick up one*
      **/
    public void pickWeapon(){
        //TODO pickWeapon scrivere codice

     }
}
