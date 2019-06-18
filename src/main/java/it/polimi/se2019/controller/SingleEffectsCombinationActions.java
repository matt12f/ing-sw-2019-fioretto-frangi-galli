package it.polimi.se2019.controller;

import it.polimi.se2019.exceptions.UnavailableEffectCombinationException;
import it.polimi.se2019.model.game.NewCell;
import it.polimi.se2019.model.game.Player;
import it.polimi.se2019.model.game.Room;

import java.util.ArrayList;

/**
 * This class creates objects that represent the things a player must choose from to use a certain effect
 */
public class SingleEffectsCombinationActions{
    //these variables are for cards that just need the client to select a certain number of targets for an effect
    private ArrayList<Player> playersTargetList; //To choose from
    private int maxNumPlayerTargets; //For the GUI/CLI
    //The minimum is always = 1

    //these variables are for moving an opponent and they inform the client on how they can do that
    private boolean canMoveOpponent; //For the GUI/CLI
    private int maxDistanceOfMovement; //For the GUI/CLI
    //if you can move it will be done automatically when applying the changes

    //these variables are for hitting everyone in a cell/room
    private ArrayList<NewCell> targetCells; //To choose from
    private ArrayList<Room> targetRooms; //To choose from

    private boolean offerableOpt1; //For the GUI/CLI
    private boolean offerableOpt2; //For the GUI/CLI

    private boolean offerableExtra; //For the GUI/CLI; It's used by PowerGlove and MachineGun
    private ArrayList<Player> extraTargets; //To choose from

    private ArrayList<CellWithTargets> cellsWithTargets; //To choose from

    private boolean sameListDifferentTarget; //For the GUI/CLI

    private ArrayList<PlayerWithTargets> playersWithTargets;

    public SingleEffectsCombinationActions() {
        this.playersTargetList =new ArrayList<>();
        this.maxNumPlayerTargets=0;

        this.maxDistanceOfMovement=0;
        this.canMoveOpponent=false;
        this.targetCells=new ArrayList<>();
        this.targetRooms=new ArrayList<>();

        this.offerableOpt1=true;
        this.offerableOpt2=true;

        this.offerableExtra=false;
        this.extraTargets=new ArrayList<>();

        this.cellsWithTargets=new ArrayList<>();

        this.sameListDifferentTarget=false;

        this.playersWithTargets=new ArrayList<>();
    }

    /**
     * this method
     * @throws UnavailableEffectCombinationException when an effect combination has no targets
     */
    //TODO c'è altro da controllare?
    public void validate() throws UnavailableEffectCombinationException{
        if (this.playersTargetList.isEmpty() && this.targetCells.isEmpty() && this.targetRooms.isEmpty() && this.playersWithTargets.isEmpty() && this.cellsWithTargets.isEmpty() &&this.extraTargets.isEmpty())
            throw new UnavailableEffectCombinationException();
    }

    /** ------------- GETTER METHODS ------------- */

    public ArrayList<Player> getPlayersTargetList() {
        return playersTargetList;
    }

    public int getMaxNumPlayerTargets() {
        return maxNumPlayerTargets;
    }

    public int getMaxDistanceOfMovement() {
        return maxDistanceOfMovement;
    }

    public boolean isCanMoveOpponent() {
        return canMoveOpponent;
    }

    public ArrayList<NewCell> getTargetCells() {
        return targetCells;
    }

    public ArrayList<Room> getTargetRooms() {
        return targetRooms;
    }

    public boolean isOfferableOpt1() {
        return offerableOpt1;
    }

    public boolean isOfferableOpt2() {
        return offerableOpt2;
    }

    public boolean isOfferableExtra() {
        return offerableExtra;
    }

    public ArrayList<CellWithTargets> getCellsWithTargets() {
        return cellsWithTargets;
    }

    public boolean isSameListDifferentTarget() {
        return sameListDifferentTarget;
    }

    public ArrayList<PlayerWithTargets> getPlayersWithTargets() {
        return playersWithTargets;
    }

    /** ------------- SETTER METHODS ------------- */

    public void addToPlayerTargetList(ArrayList<Player> targetSubList) {
        this.playersTargetList.addAll(targetSubList);
    }

    public void setMaxNumPlayerTargets(int maxNumberOfTargets) {
        this.maxNumPlayerTargets = maxNumberOfTargets;
    }

    public void addToTargetRooms(ArrayList<Room> targetRooms) {
        this.targetRooms.addAll(targetRooms);
    }

    public void addToTargetCells(ArrayList<NewCell> targetCells) {
        this.targetCells.addAll(targetCells);
    }

    public void setMaxDistanceOfMovement(int maxDistanceOfMovement) {
        this.maxDistanceOfMovement = maxDistanceOfMovement;
    }

    public void setCanMoveOpponent(boolean canMoveOpponent) {
        this.canMoveOpponent = canMoveOpponent;
    }

    public void setOfferableOpt1(boolean offerableOpt1) {
        this.offerableOpt1 = offerableOpt1;
    }

    public void setOfferableOpt2(boolean offerableOpt2) {
        this.offerableOpt2 = offerableOpt2;
    }

    public void setOfferableExtra(boolean offerableExtra) {
        this.offerableExtra = offerableExtra;
    }

    public void addToExtraTarget(ArrayList<Player> extraTargets){
        this.extraTargets.addAll(extraTargets);
    }

    public void setSameListDifferentTarget(boolean sameListDifferentTarget) {
        this.sameListDifferentTarget = sameListDifferentTarget;
    }

    public void addCellsWithTargets(NewCell targetCell, ArrayList<Player> targets, int maxTargets, int minTargets, int minCellToSelect, int maxCellToSelect) {
        this.cellsWithTargets.add(new CellWithTargets(targetCell, targets, maxTargets, minTargets, minCellToSelect, maxCellToSelect));
    }

    public void addPlayersWithTargets(Player basePlayer) {
        this.playersWithTargets.add(new PlayerWithTargets(basePlayer));
    }
}

class CellWithTargets{
    NewCell targetCell;
    ArrayList<Player> targets; //These are targets to choose from
    int maxTargets;
    int minTargets;

    int minCellToSelect;
    int maxCellToSelect;

    public CellWithTargets(NewCell targetCell, ArrayList<Player> targets, int maxTargets, int minTargets, int minCellToSelect, int maxCellToSelect) {
        this.targetCell = targetCell;
        this.targets = targets;
        this.maxTargets = maxTargets;
        this.minTargets = minTargets;
        this.minCellToSelect = minCellToSelect;
        this.maxCellToSelect = maxCellToSelect;
    }
}

//Custom class for T.H.O.R. card
class PlayerWithTargets {
    Player target;
    ArrayList<Player> targetsItCanSee;
    public PlayerWithTargets(Player target) {
        this.target = target;
        this.targetsItCanSee=new ArrayList<>(ActionManager.visibleTargets(new FictitiousPlayer(target,new CellInfo(target.getFigure().getCell(),false,false),false,false)));
    }
}

