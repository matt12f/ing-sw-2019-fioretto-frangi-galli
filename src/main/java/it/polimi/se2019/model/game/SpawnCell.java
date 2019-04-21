package it.polimi.se2019.model.game;

import it.polimi.se2019.model.cards.GunCard;

import static it.polimi.se2019.App.getMainController;

public class SpawnCell extends Cell {
    private GunCard [] weaponCards;

    public SpawnCell(char color, char top, char bottom, char left, char right){
        super(color,top,bottom,left,right);
        this.weaponCards = new GunCard[3];
        weaponCards[0]=getMainController().getLocalGameModel().currentDecks.getGunDeck().draw();
        weaponCards[1]=getMainController().getLocalGameModel().currentDecks.getGunDeck().draw();
        weaponCards[2]=getMainController().getLocalGameModel().currentDecks.getGunDeck().draw();
    }

    public GunCard[] getWeaponCards() {
        return weaponCards;
    }

    /** It fills the empty slots of the weapon cards when a card is picked up
     */
    public void setWeaponCards(){
        try {
            weaponCards[0] = getMainController().getLocalGameModel().currentDecks.getGunDeck().draw();
        }catch (Exception cardsFinished){ //TODO rename the exception
            //No more cards for the rest of the game to be added
        }

        //TODO setWeaponCards
     }
     /**Method that displays the 3 cards that the player can pick in the spawn point and he can pick one up
      **/
    public void pickWeapon(int pick){
        //TODO pickWeapon
     }
}
