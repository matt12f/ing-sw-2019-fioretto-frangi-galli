package it.polimi.se2019.model.cards;

public class Heatseeker extends GunCard {

    private int numberOfOptional;
    private boolean hasAlternativeEffect;


   /**
     * constructor
     */
    public Heatseeker() {

        this.numberOfOptional = 0;
        this.hasAlternativeEffect = false;

        this.ammoCost = new char[3];
        ammoCost[0] = 'r';
        ammoCost[1]= 'r';
        ammoCost[2]= 'y';
        this.description ="effect: Choose 1 target you cannot see and deal 3 damage\n" +
                "to it.";
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