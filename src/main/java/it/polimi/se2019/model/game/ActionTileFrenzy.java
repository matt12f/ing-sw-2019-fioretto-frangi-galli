package it.polimi.se2019.model.game;

public class ActionTileFrenzy extends ActionTile{
    private boolean redSkull;
    public ActionTileFrenzy(){
        //TODO scrivere costruttore (simile alla ActionTileNormal)
    }

    public boolean getRedSkull() {
        return redSkull;
    }

    /**
     * This method:
     * asks the player what move(s) he wants to perform depending on how many actions it's allowed to.
     * then launches the code that elaborates the action
     */
    @Override
    public void doAction(){
        super.doAction();
    }
}
