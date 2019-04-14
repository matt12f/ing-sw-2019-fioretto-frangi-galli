package it.polimi.se2019.model.game;

public abstract class ActionTile {
    protected int actionCounter;

    /**This constructor is probably useless. It could be removed
     *
     */

    public ActionTile(){}


    public int getActionCounter() {
       return actionCounter;
    }

    /**
     * This method gets overridden by its heirs, which actually use it.
     *
     *It could include the part that launches the actions chosen by the player, as they're mostly the same
     * (there's a change in number on squares moved for example)
     */
    public void doAction(){ }
}