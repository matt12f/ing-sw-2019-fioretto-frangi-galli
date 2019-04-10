package it.polimi.se2019.model.game;

public class ActionTileNormal extends ActionTile {
    private boolean adrenalineMode1;
    private boolean adrenalineMode2;

    /**
     * The constructor sets actionCounter = 2 for every tile
     * the variables adrenalineMode 1 and 2 set the freedom to do the special attacks that the player can do after have 3 or 6 damage points,
     * so setting to false at the beginning of the game
     */
    public ActionTileNormal() {
        this.actionCounter = 2;
        this.adrenalineMode1 = false;
        this.adrenalineMode2 = false;

    }

    public boolean getAdrenalineMode1() {
        return adrenalineMode1;
    }

    public boolean getAdrenalineMode2() {
        return adrenalineMode2;
    }

    /**
     * This set method enables/disable adrenaline mode 1
     *
     */
    public void setAdrenalineMode1() {
        if(!this.adrenalineMode1) {
            this.adrenalineMode1 = true;
        }else {
            this.adrenalineMode1 = false;
        }
    }

    /**
     * This set method enables/disable adrenaline mode 2
     */
    public void setAdrenalineMode2() {
        if(!this.adrenalineMode2) {
            this.adrenalineMode2 = true;
        }else {
            this.adrenalineMode2 = false;
        }
    }

    /**
     * This method:
     * asks the player what moves he wants to perform (twice) depending on how many actions it's allowed to.
     * then launches the code that elaborates the action
     */
    @Override
    public void doAction() {
        //TODO scrivere codice gestione azioni
        super.doAction();
    }
}
