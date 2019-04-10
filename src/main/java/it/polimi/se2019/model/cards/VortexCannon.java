package it.polimi.se2019.model.cards;

public class VortexCannon extends GunCard {

    private int numberOfOptional;
    private boolean hasAlternativeEffect;
    private String basicEffectDescription;
    private char [] secondaryEffectCost ;
    private String secondaryEffectDescription;

    /**
     * constructor
     */
    public VortexCannon() {
        this.numberOfOptional = 1;
        this.hasAlternativeEffect = false;

        this.ammoCost = new char[2];
        ammoCost[0]= 'b';
        ammoCost[1]= 'b';
        this.basicEffectDescription ="basic effect: Choose a square you can see, but not your\n" +
                "square. Call it \"the vortex\". Choose a target on the vortex\n" +
                "or 1 move away from it. Move it onto the vortex and give it\n" +
                "2 damage.";
        this.secondaryEffectDescription ="with black hole: Choose up to 2 other targets on the\n" +
                "vortex or 1 move away from it. Move them onto the vortex\n" +
                "and give them each 1 damage.";
        this.secondaryEffectCost = new char[1];
        secondaryEffectCost[0] = 'r';
    }
    /**
     * @return
     */
    public boolean getHasAlternativeEffect() {
        return hasAlternativeEffect;
    }
    /**
     * @return
     */
    public int getNumberOfOptional() {
        return numberOfOptional;
    }
    /**
     * @return
     */
    public String getBasicEffectDescription() {
        return basicEffectDescription;
    }

    public void doBasicEffect() {

    }
    /**
     * @return
     */
    public char[] getSecondaryEffectCost() {
        return  secondaryEffectCost;
    }

    /**
     * @return
     */
    public String getSecondaryEffectDescription() {
        return secondaryEffectDescription;
    }
    public void doSecondaryEffect() {

    }

}