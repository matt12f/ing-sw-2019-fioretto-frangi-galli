package it.polimi.se2019.model.cards;

import it.polimi.se2019.controller.*;
import it.polimi.se2019.exceptions.OuterWallException;
import it.polimi.se2019.model.game.NewCell;
import it.polimi.se2019.model.game.Player;
import it.polimi.se2019.view.ChosenActions;

import java.util.ArrayList;

public class Railgun extends GunCardAltEff {
    /**
     * hard-coded constructor
     */
    public Railgun() {
        super();
        this.ammoCost = new char[3];
        ammoCost[0]= 'y';
        ammoCost[1]= 'y';
        ammoCost[2]= 'b';
        this.description ="basic mode: Choose a cardinal direction and 1 target in that direction. Deal 3 damage to it.\n"+
                "in piercing mode: Choose a cardinal direction and 1 or 2 targets in that direction. Deal 2 damage to each.";
        this.secondaryEffectCost = new char[1];
        secondaryEffectCost[0] = 'n';
    }
    /**
     * This applies the base effect
     * @param currentController it the current controller of the game
     * @param playersChoice are the choices the player wants to apply
     */
    @Override
    void applyBaseEffect(Controller currentController, ChosenActions playersChoice) {
        ActionManager.giveDmgandMksToOnePlayer(currentController,playersChoice.getTargetsFromCell().get(0),playersChoice,3,0);
    }
    /**
     * This applies the secondary effect
     * @param currentController it the current controller of the game
     * @param playersChoice are the choices the player wants to apply
     */
    @Override
    void applySecondaryEffect(Controller currentController, ChosenActions playersChoice) {
        ActionManager.giveDmgandMksToOnePlayer(currentController,playersChoice.getTargetsFromCell().get(0),playersChoice,2,0);
        if(playersChoice.getTargetsFromCell().size()==2)
            ActionManager.giveDmgandMksToOnePlayer(currentController,playersChoice.getTargetsFromCell().get(1),playersChoice,2,0);
    }
    /**
     * Choose a cardinal direction and 1 target in that direction. Deal 3 damage to it.
     * Anyone on a square in that direction (including yours) is a valid target.
     */
    @Override
    void targetsOfBaseEffect(Controller currentController, SingleEffectsCombinationActions actions, FictitiousPlayer player) {
        NewCell[][] board= currentController.getMainGameModel().getCurrentMap().getBoardMatrix();

        NewCell cellOneMoveAway;
        ArrayList<Player> targetsInOneDirection=new ArrayList<>();
        boolean inside=true;
        int distance;

            for (int i = 0; i < 4 ; i++){ //selects a direction
                distance=1;
                targetsInOneDirection.clear();
                targetsInOneDirection.addAll(Player.duplicateList(player.getPosition().getPlayers()));
                targetsInOneDirection.remove(player.getCorrespondingPlayer());
                try{
                    cellOneMoveAway=MapManager.getCellInDirection(board, player.getPosition(), distance, i);
                    targetsInOneDirection.addAll(Player.duplicateList(cellOneMoveAway.getPlayers()));

                    while (inside){
                        NewCell cellInDirection;
                        distance++;
                        try{
                            cellInDirection=MapManager.getCellInDirection(board, player.getPosition(), distance, i);
                            targetsInOneDirection.addAll(Player.duplicateList(cellInDirection.getPlayers()));
                        }
                        catch (OuterWallException e){
                            //This happens if you move out of the board while getting cells in one direction
                            inside=false;
                            break;
                        }
                    }
                    actions.addCellsWithTargets(cellOneMoveAway,targetsInOneDirection,1,1,false,false);
                }catch (OuterWallException e2){
                    //this happens if you are close to an edge and try to move outside of the board
                }
            }
            actions.setMinCellToSelect(1);
            actions.setMaxCellToSelect(1);
    }

    /**
     * Choose a cardinal direction and 1 or 2 targets in that direction. Deal 2 damage to each.
     * Anyone on a square in that direction (including yours) is a valid target.
     */
    @Override
    void targetsOfSecondaryEffect(Controller currentController, SingleEffectsCombinationActions actions, FictitiousPlayer player) {
        targetsOfBaseEffect(currentController, actions, player);
        for(CellWithTargets cell: actions.getCellsWithTargets())
            cell.setMaxTargetsInCell(2);
    }

    @Override
    public GunCard clone() {
        GunCard gunCard = new Railgun();
        gunCard.setLoaded(this.isLoaded());

        return gunCard;
    }
}