package it.polimi.se2019.model.cards;

import it.polimi.se2019.controller.*;
import it.polimi.se2019.enums.CellEdge;
import it.polimi.se2019.exceptions.OuterWallException;
import it.polimi.se2019.model.game.NewCell;
import it.polimi.se2019.model.game.Player;
import it.polimi.se2019.view.ChosenActions;

import java.util.ArrayList;

public class FlameThrower extends GunCardAltEff {
    /**
     * hard-coded constructor
     */
    public FlameThrower() {
        super();
        this.ammoCost = new char[1];
        ammoCost[0]= 'r';
        this.description ="basic mode: Choose a square 1 move away and possibly a second square\n"+
                "1 more move away in the same direction. On each square, you may\nchoose 1 target and give it 1 damage\n"+
                "in barbecue mode: Choose 2 squares as above. Deal 2 damage to\neveryone on the first square and 1 damage to everyone on the second\nsquare.";

        this.secondaryEffectCost = new char[2];
        secondaryEffectCost[0] = 'y';
        secondaryEffectCost[1] = 'y';
    }
    /**
     * This applies the base effect
     * @param currentController it the current controller of the game
     * @param playersChoice are the choices the player wants to apply
     */
    @Override
    void applyBaseEffect(Controller currentController, ChosenActions playersChoice) {
        ActionManager.giveDmgandMksToPlayers(currentController,playersChoice.getTargetsFromCell(),playersChoice,1,0);
    }
    /**
     * This applies the alternative effect
     * @param currentController it the current controller of the game
     * @param playersChoice are the choices the player wants to apply
     */
    @Override
    void applySecondaryEffect(Controller currentController, ChosenActions playersChoice) {
        NewCell[][] board =currentController.getMainGameModel().getCurrentMap().getBoardMatrix();

        NewCell cellOneMoveAway=playersChoice.getCellFromCellWithTrg();
        if(MapManager.distanceBetweenCells(board,playersChoice.getCellFromCellWithTrg(),currentController.getActiveTurn().getActivePlayer().getFigure().getCell())==1)
          ActionManager.giveDmgandMksToPlayers(currentController,playersChoice.getCellFromCellWithTrg().getPlayers(),playersChoice,2,0);
        else {
            ActionManager.giveDmgandMksToPlayers(currentController,playersChoice.getCellFromCellWithTrg().getPlayers(),playersChoice,1,0);
            int dir= getDirection(board,currentController.getActiveTurn().getActivePlayer().getFigure().getCell(),playersChoice.getCellFromCellWithTrg());
            try {
                cellOneMoveAway= MapManager.getCellInDirection(board, currentController.getActiveTurn().getActivePlayer().getFigure().getCell(), 1, dir);
            }catch (OuterWallException e){
                //nothing to see here
            }
            ActionManager.giveDmgandMksToPlayers(currentController,cellOneMoveAway.getPlayers(),playersChoice,2,0);

        }
    }

    /**
     * allow to choose the attack direction
     * @param board map matrix
     * @param originalCell start cell
     * @param otherCell chosencell
     * @return
     */
    private int getDirection(NewCell[][]board,NewCell originalCell, NewCell otherCell){
        int x=MapManager.getLineOrColumnIndex(board,originalCell,true);
        int y=MapManager.getLineOrColumnIndex(board,originalCell,false);
        int i=MapManager.getLineOrColumnIndex(board,otherCell,true);
        int j=MapManager.getLineOrColumnIndex(board,originalCell,false);

        if(x>i &&y==j)//up
            return 0;
        if(x<i &&y==j)//down
            return 1;
        if(x==i &&y>j)//left
            return 2;
        if(x==i &&y<j)//right
            return 3;
        return -1;
    }

    /**
     * Choose a square 1 move away and possibly a second square 1 more move away in the same direction.
     * On each square, you may choose 1 target and give it 1 damage.
     */
    @Override
    void targetsOfBaseEffect(Controller currentController, SingleEffectsCombinationActions actions, FictitiousPlayer player) {
        NewCell[][] board= currentController.getMainGameModel().getCurrentMap().getBoardMatrix();

        NewCell cellOneMoveAway;
        ArrayList<Player> targetsInOneDirection=new ArrayList<>();

        for (int i = 0; i < 4 ; i++){ //selects a direction
            targetsInOneDirection.clear();
            try{
                cellOneMoveAway = MapManager.getCellInDirection(board, player.getPosition(), 1, i);

                    targetsInOneDirection.addAll(Player.duplicateList(cellOneMoveAway.getPlayers()));
                    if(!cellOneMoveAway.getEdge(i).equals(CellEdge.WALL)) {
                        targetsInOneDirection.addAll(Player.duplicateList(MapManager.getCellInDirection(board, player.getPosition(), 2, i).getPlayers()));
                        actions.addCellsWithTargets(cellOneMoveAway,targetsInOneDirection,2,1,false,false);
                    }else
                        actions.addCellsWithTargets(cellOneMoveAway,targetsInOneDirection,1,1,false,false);

            }catch (OuterWallException e2){
                //this happens if you are close to an edge and try to move outside of the board
            }
        }
        actions.setMinCellToSelect(1);
        actions.setMaxCellToSelect(1);
    }

    /**
     *Choose a square 1 move away and possibly a second square 1 more move away in the same direction.
     *Deal 2 damage to everyone on the first square and 1 damage to everyone on the second square.
     */
    @Override
    void targetsOfSecondaryEffect(Controller currentController, SingleEffectsCombinationActions actions, FictitiousPlayer player) {
        targetsOfBaseEffect(currentController,actions,player);
        actions.getCellsWithTargets().forEach(p->{
            p.setMaxTargetsInCell(0);
            p.setMinTargetsInCell(0);
        });
    }

    @Override
    public GunCard clone() {
        GunCard gunCard = new FlameThrower();
        gunCard.setLoaded(this.isLoaded());

        return gunCard;
    }
}