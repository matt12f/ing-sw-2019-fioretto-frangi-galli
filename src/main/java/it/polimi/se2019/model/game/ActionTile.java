package it.polimi.se2019.model.game;

import java.io.Serializable;

public abstract class ActionTile implements Serializable {
    protected int actionCounter;

    public int getActionCounter() {
       return actionCounter;
    }

}
