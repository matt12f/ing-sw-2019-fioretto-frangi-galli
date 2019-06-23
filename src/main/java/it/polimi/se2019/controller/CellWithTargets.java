package it.polimi.se2019.controller;

import it.polimi.se2019.model.game.NewCell;
import it.polimi.se2019.model.game.Player;

import java.util.ArrayList;

public class CellWithTargets{
    NewCell targetCell;
    ArrayList<Player> targets; //These are targets to choose from
    int maxTargetsInCell;
    int minTargetsInCell;

    public CellWithTargets(NewCell targetCell, ArrayList<Player> targets, int maxTargetsInCell, int minTargetsInCell) {
        this.targetCell = targetCell;
        this.targets = targets;
        this.maxTargetsInCell = maxTargetsInCell;
        this.minTargetsInCell = minTargetsInCell;
    }

    public void setMaxTargetsInCell(int maxTargetsInCell) {
        this.maxTargetsInCell = maxTargetsInCell;
    }

    public void setMinTargetsInCell(int minTargetsInCell) {
        this.minTargetsInCell = minTargetsInCell;
    }
}
