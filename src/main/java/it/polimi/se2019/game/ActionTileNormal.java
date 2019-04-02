package it.polimi.se2019.game;

public class ActionTileNormal extends ActionTile {
    private boolean adrenalineMode1;
    private boolean adrenalineMode2;

    /**
     * The constructor sets actionCounter = 2 for every tile
     */
    public ActionTileNormal() {
        this.actionCounter=2;

    }

    public boolean getAdrenalineMode1() {
        return adrenalineMode1;
    }

    public boolean getAdrenalineMode2() {
        return adrenalineMode2;
    }

    /**
     * This set method enables adrenaline mode 1
     */
    public void setAdrenalineMode1() {
        this.adrenalineMode1 = true;
    }

    /**
     * This set method enables adrenaline mode 2
     */
    public void setAdrenalineMode2() {
        this.adrenalineMode2 = true;
    }

    /**
     * This method:
     * asks the player what moves he wants to perform (twice) depending on how many actions it's allowed to.
     * then launches the code that elaborates the action
     */
    @Override
    public void doAction() {
        super.doAction();
    }
}
