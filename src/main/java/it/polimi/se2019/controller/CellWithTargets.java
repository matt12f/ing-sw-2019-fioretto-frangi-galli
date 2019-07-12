package it.polimi.se2019.controller;

import it.polimi.se2019.model.game.NewCell;
import it.polimi.se2019.model.game.Player;

import java.io.Serializable;
import java.util.ArrayList;

public class CellWithTargets implements Serializable {
    private NewCell targetCell;
    private ArrayList<Player> targets; //These are targets to choose from
    private int maxTargetsInCell;
    private int minTargetsInCell;
    private boolean canMoveYourselfHere; //to indicate if this is a cell where you could move yourself
    private boolean canMoveOthersHere; //to indicate if this is a cell where you could move another player in

    /**
     * class used to control for the target system
     * @param targetCell cell in exam
     * @param targets players that are targets
     * @param maxTargetsInCell max  possible target
     * @param minTargetsInCell min possible targets
     * @param canMoveYourselfHere says if it's possible to move in targetCell
     * @param canMoveOthersHere says if it's possible to move other players in targetCell
     */
    public CellWithTargets(NewCell targetCell, ArrayList<Player> targets, int maxTargetsInCell, int minTargetsInCell, boolean canMoveYourselfHere, boolean canMoveOthersHere) {
        this.targetCell = targetCell;
        this.targets = targets;
        this.maxTargetsInCell = maxTargetsInCell;
        this.minTargetsInCell = minTargetsInCell;
        this.canMoveYourselfHere=canMoveYourselfHere;
        this.canMoveOthersHere=canMoveOthersHere;
    }

    public void setMaxTargetsInCell(int maxTargetsInCell) {
        this.maxTargetsInCell = maxTargetsInCell;
    }

    public void setMinTargetsInCell(int minTargetsInCell) {
        this.minTargetsInCell = minTargetsInCell;
    }

    public NewCell getTargetCell() {
        return targetCell;
    }

    public ArrayList<Player> getTargets() {
        return this.targets;
    }

    public int getMaxTargetsInCell() {
        return maxTargetsInCell;
    }

    public int getMinTargetsInCell() {
        return minTargetsInCell;
    }

    public boolean isCanMoveYourselfHere() {
        return canMoveYourselfHere;
    }

    public boolean isCanMoveOthersHere() {
        return canMoveOthersHere;
    }
}
