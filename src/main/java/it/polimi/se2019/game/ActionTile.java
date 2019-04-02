package it.polimi.se2019.game;

public abstract class ActionTile {
    protected int actionCounter;

    /**This constructor is probably useless. It could be removed
     *
     */

    public ActionTile(){}

    /** The get method is useless here because the actionCounter will only be used inside the class itself.
     * The set method is useless here because actionCounter will be set by the constructor only once.
     */
    //public void setActionCounter() { }

    //public int getActionCounter() { }

    /**
     * This method gets overridden by its heirs, which actually use it.
     *
     *It could include the part that launches the actions chosen by the player, as they're mostly the same
     * (there's a change in number on squares moved for example)
     */
    public void doAction(){ }
}
