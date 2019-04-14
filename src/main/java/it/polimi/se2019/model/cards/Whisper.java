package it.polimi.se2019.model.cards;

public class Whisper extends GunCard {

    private int numberOfOptional;
    private boolean hasAlternativeEffect;


   /**
     * constructor
     */
    public Whisper() {
        this.numberOfOptional = 0;
        this.hasAlternativeEffect = false;

        this.ammoCost = new char[3];
        ammoCost[0] = 'b';
        ammoCost[1]= 'b';
        ammoCost[2]= 'y';
        this.description ="effect: Deal 3 damage and 1 mark to 1 target you can see.\n" +
                "Your target must be at least 2 moves away from you.";
    }
    /**
     * @return
     */
    public boolean getHasAlternativeEffect(){
        return hasAlternativeEffect;
    }
    /**
     * @return
     */
    public int getNumberOfOptional() {

        return numberOfOptional;
    }




}