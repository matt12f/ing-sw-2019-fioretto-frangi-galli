package it.polimi.se2019.model.cards;

import it.polimi.se2019.AdrenalineServer;
import it.polimi.se2019.controller.Controller;
import it.polimi.se2019.controller.FictitiousPlayer;
import it.polimi.se2019.controller.MapManager;
import it.polimi.se2019.exceptions.OuterWallException;
import it.polimi.se2019.model.game.NewCell;
import it.polimi.se2019.model.game.Player;
import it.polimi.se2019.view.ChosenActions;
import it.polimi.se2019.controller.SingleEffectsCombinationActions;

import java.util.ArrayList;

public class FlameThrower extends GunCardAltEff {
    /**
     * hard-coded constructor
     */
    public FlameThrower() {
        super();
        this.ammoCost = new char[1];
        ammoCost[0]= 'r';
        this.description ="basic mode: Choose a square 1 move away and possibly a second square\n" +
                "1 more move away in the same direction. On each square, you may\n" +
                "choose 1 target and give it 1 damage\n"+
                "in barbecue mode: Choose 2 squares as above. Deal 2 damage to\n" +
                "everyone on the first square and 1 damage to everyone on the second\n" +
                "square.";

        this.secondaryEffectCost = new char[2];
        secondaryEffectCost[0] = 'y';
        secondaryEffectCost[1] = 'y';
    }

    @Override
    void applyBaseEffect(ChosenActions playersChoice) {
        //TODO scrivere metodo
        //TODO verifica in view che i target siano su celle diverse
    }

    @Override
    void applySecondaryEffect(ChosenActions playersChoice) {
        //TODO scrivere metodo
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
                cellOneMoveAway= MapManager.getCellInDirection(board, player.getPosition(), 1, i);

                try{
                    targetsInOneDirection.addAll(cellOneMoveAway.getPlayers());
                    targetsInOneDirection.addAll(MapManager.getCellInDirection(board, player.getPosition(), 2, i).getPlayers());
                    }
                    catch (OuterWallException e){
                    actions.addCellsWithTargets(cellOneMoveAway,targetsInOneDirection,1,1,false,false);
                        //This happens if you move out of the board while getting the second cell
                    }

            actions.addCellsWithTargets(cellOneMoveAway,targetsInOneDirection,2,1,false,false);
            }catch (OuterWallException e2){
                //this happens if you are close to an edge and try to move outside of the board
            }
        }
        actions.setMinCellToSelect(1);
        actions.setMaxCellToSelect(1);
    }

    /**
     *Choose 2 squares as above.
     *Deal 2 damage to everyone on the first square and 1 damage to everyone on the second square.
     */
    @Override
    void targetsOfSecondaryEffect(Controller currentController, SingleEffectsCombinationActions actions, FictitiousPlayer player) {
        targetsOfBaseEffect(currentController,actions,player);
        //TODO check se è offerable
    }
}