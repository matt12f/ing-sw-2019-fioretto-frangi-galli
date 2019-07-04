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
    private final ArrayList<String> effectsCombination;

    //these variables are for cards that just need the client to select a certain number of targets for an effect
    private ArrayList<Player> playersTargetList; //To choose from
    private int maxNumPlayerTargets; //For the GUI/CLI
    private int minNumPlayerTargets; //For the GUI/CLI
    //The minimum is always = 1 except in MachineGun

    //these variables are for moving an opponent and they inform the client on how they can do that
    private boolean canMoveOpponent; //For the GUI/CLI
    private boolean canMoveYourself; //For the GUI/CLI

    //these variables are for hitting everyone in a cell/room
    private ArrayList<NewCell> targetCells; //To choose from
    private ArrayList<Room> targetRooms; //To choose from

    private boolean offerableBase; //For the GUI/CLI
    private boolean offerableOpt1; //For the GUI/CLI
    private boolean offerableOpt2; //For the GUI/CLI

    private boolean offerableExtra; //For the GUI/CLI; It's used by PowerGlove and MachineGun

    private ArrayList<CellWithTargets> cellsWithTargets; //To choose from
    private int minCellToSelect; //For the GUI/CLI //always 1, could be removed
    private int maxCellToSelect; //For the GUI/CLI

    private ArrayList<PlayerWithTargets> playersWithTargets; //for T.H.O.R use only

    public SingleEffectsCombinationActions(ArrayList<String> effectsCombination) {
        this.effectsCombination=effectsCombination;

        this.playersTargetList =new ArrayList<>();
        this.maxNumPlayerTargets=0;
        this.minNumPlayerTargets=0;

        this.canMoveOpponent=false;
        this.targetCells=new ArrayList<>();
        this.targetRooms=new ArrayList<>();

        this.offerableBase=true;
        this.offerableOpt1=true;
        this.offerableOpt2=true;

        this.offerableExtra=false;

        this.cellsWithTargets=new ArrayList<>();

        this.playersWithTargets=new ArrayList<>();
    }

    /**
     * this method checks if the targets calculated are ok by:
     *  - checking if there are targets
     *  - checking if the combination selected if fully offerable
     * @throws UnavailableEffectCombinationException when an effect combination is not ok
     */
    public void validate(ArrayList<String> effectsCombination) throws UnavailableEffectCombinationException{
        //if the combination selected contains an effect that is not offerable
        if(effectsCombination.contains("Base") && !this.offerableBase)
            throw new UnavailableEffectCombinationException("Base");
        else if (effectsCombination.contains("Optional1") && !this.offerableOpt1)
            throw new UnavailableEffectCombinationException("Optional1");
        else if (effectsCombination.contains("Optional2") && !this.offerableOpt2)
            throw new UnavailableEffectCombinationException("Optional2");

        //there are no targets
        if (this.playersTargetList.isEmpty() && this.targetCells.isEmpty() && this.targetRooms.isEmpty())
            throw new UnavailableEffectCombinationException("No targets");

        //apposite validation for CellsWithTargets
        if(!this.cellsWithTargets.isEmpty() && validateCellsWithTargets())
            throw new UnavailableEffectCombinationException("No targets in cells with targets");
    }

    /**
     * if there are no targets on every cell there's something wrong -> it will return true
     * returns false otherwise
     */
    private boolean validateCellsWithTargets(){
        for(CellWithTargets cellWithTargets: this.cellsWithTargets){
            //case where the cell is meant to have targets on it
            if(cellWithTargets.getMinTargetsInCell()!=0 && cellWithTargets.getMaxTargetsInCell()!=0)
                if(!cellWithTargets.getTargets().isEmpty()) //means there's a list with targets in it
                    return false;
        }
        return true;

    }

    /** ------------- GETTER METHODS ------------- */

    public ArrayList<String> getEffectsCombination() {
        return effectsCombination;
    }

    public ArrayList<Player> getPlayersTargetList() {
        return playersTargetList;
    }

    public int getMaxNumPlayerTargets() {
        return maxNumPlayerTargets;
    }

    public int getMinNumPlayerTargets() {
        return minNumPlayerTargets;
    }

    public boolean isCanMoveOpponent() {
        return canMoveOpponent;
    }

    public boolean isCanMoveYourself() {
        return canMoveYourself;
    }

    public ArrayList<NewCell> getTargetCells() {
        return targetCells;
    }

    public ArrayList<Room> getTargetRooms() {
        return targetRooms;
    }

    public boolean isOfferableExtra() {
        return offerableExtra;
    }

    public ArrayList<CellWithTargets> getCellsWithTargets() {
        return cellsWithTargets;
    }

    public int getMaxCellToSelect() {
        return maxCellToSelect;
    }

    public ArrayList<PlayerWithTargets> getPlayersWithTargets() {
        return playersWithTargets;
    }

    public boolean isOfferableOpt1() {
        return offerableOpt1;
    }

    /** ------------- SETTER METHODS ------------- */

    public void addToPlayerTargetList(ArrayList<Player> targetSubList) {
        this.playersTargetList.addAll(targetSubList);
    }

    public void setMaxNumPlayerTargets(int maxNumberOfTargets) {
        this.maxNumPlayerTargets = maxNumberOfTargets;
    }

    public void setMinNumPlayerTargets(int minNumPlayerTargets) {
        this.minNumPlayerTargets = minNumPlayerTargets;
    }

    public void addToTargetRooms(ArrayList<Room> targetRooms) {
        this.targetRooms.addAll(targetRooms);
    }

    public void addToTargetCells(ArrayList<NewCell> targetCells) {
        this.targetCells.addAll(targetCells);
    }

    public void setCanMoveOpponent(boolean canMoveOpponent) {
        this.canMoveOpponent = canMoveOpponent;
    }

    public void setCanMoveYourself(boolean canMoveYourself) {
        this.canMoveYourself = canMoveYourself;
    }

    public void setOfferableBase(boolean offerableBase) {
        this.offerableBase = offerableBase;
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

    public void addCellsWithTargets(NewCell targetCell, ArrayList<Player> targets, int maxTargets, int minTargets,boolean canMoveYourSelfHere, boolean canMoveOthersHere) {
        this.cellsWithTargets.add(new CellWithTargets(targetCell, targets, maxTargets, minTargets,canMoveYourSelfHere,canMoveOthersHere));
    }

    public void setMinCellToSelect(int minCellToSelect) {
        this.minCellToSelect = minCellToSelect;
    }

    public void setMaxCellToSelect(int maxCellToSelect) {
        this.maxCellToSelect = maxCellToSelect;
    }

    public void addPlayersWithTargets(Controller currentController, Player basePlayer) {
        this.playersWithTargets.add(new PlayerWithTargets(currentController, basePlayer));
        //if a player can't see any targets it must not be enlisted
        if(this.playersWithTargets.get(this.playersWithTargets.size()-1).getTargetsItCanSee().isEmpty())
            this.playersWithTargets.remove(this.playersWithTargets.size()-1);
    }
}