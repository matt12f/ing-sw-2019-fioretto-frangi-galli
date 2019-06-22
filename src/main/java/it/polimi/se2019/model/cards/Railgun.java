package it.polimi.se2019.model.cards;

import it.polimi.se2019.AdrenalineServer;
import it.polimi.se2019.controller.*;
import it.polimi.se2019.enums.CellEdge;
import it.polimi.se2019.exceptions.OuterWallException;
import it.polimi.se2019.model.game.NewCell;
import it.polimi.se2019.model.game.Player;
import it.polimi.se2019.view.ChosenActions;
import it.polimi.se2019.exceptions.UnavailableEffectCombinationException;

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
        this.description ="basic mode: Choose a cardinal direction and 1 target in that direction.\n" +
                "Deal 3 damage to it.\n"+
                "in piercing mode: Choose a cardinal direction and 1 or 2 targets in that\n" +
                "direction. Deal 2 damage to each.";
        this.secondaryEffectCost = new char[1];
        secondaryEffectCost[0] = 'n';
    }

    @Override
    void applyBaseEffect(ChosenActions playersChoice) {
        //TODO scrivere metodo
    }

    @Override
    void applySecondaryEffect(ChosenActions playersChoice) {
        //TODO scrivere metodo
    }
    /**
     * Choose a cardinal direction and 1 target in that direction. Deal 3 damage to it.
     * Anyone on a square in that direction (including yours) is a valid target.
     */
    @Override
    void targetsOfBaseEffect(SingleEffectsCombinationActions actions, FictitiousPlayer player) {
        NewCell[][] board= AdrenalineServer.getMainController().getMainGameModel().getCurrentMap().getBoardMatrix();

        NewCell cellOneMoveAway;
        ArrayList<Player> targetsInOneDirection=new ArrayList<>();
        boolean inside=true;
        int distance;

            for (int i = 0; i < 4 ; i++){ //selects a direction
                distance=1;
                targetsInOneDirection.clear();
                try{
                    cellOneMoveAway=MapManager.getCellInDirection(board, player.getPosition(), 1, i);

                    NewCell temp=cellOneMoveAway;

                    while (inside){
                        distance++;
                        try{
                        targetsInOneDirection.addAll(temp.getPlayers());
                        temp=MapManager.getCellInDirection(board, player.getPosition(), distance, i);
                        }
                        catch (OuterWallException e){
                            //This happens if you move out of the board while getting cells in one direction
                            inside=false;
                            break;
                        }
                    }
                    actions.addCellsWithTargets(cellOneMoveAway,targetsInOneDirection,1,1);
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
    void targetsOfSecondaryEffect(SingleEffectsCombinationActions actions, FictitiousPlayer player) {
        targetsOfBaseEffect(actions,player);
        for(CellWithTargets cell: actions.getCellsWithTargets())
            cell.setMaxTargetsInCell(2);
    }

}