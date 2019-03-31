package it.polimi.se2019.game;

public abstract class ActionTile {
    private int actionCounter;
    public ActionTile(){}
    public void setActionCounter(int actionCounter) {
        this.actionCounter = actionCounter;
    }
    public int getActionCounter() {
        return actionCounter;
    }
    public void doAction(){}
}
