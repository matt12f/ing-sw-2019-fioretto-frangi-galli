package it.polimi.se2019.model.cards;

import it.polimi.se2019.AdrenalineServer;
import it.polimi.se2019.controller.Controller;
import it.polimi.se2019.controller.FictitiousPlayer;
import it.polimi.se2019.controller.MapManager;
import it.polimi.se2019.enums.CellEdge;
import it.polimi.se2019.exceptions.OuterWallException;
import it.polimi.se2019.model.game.NewCell;
import it.polimi.se2019.model.game.Player;
import it.polimi.se2019.view.ChosenActions;
import it.polimi.se2019.controller.SingleEffectsCombinationActions;

import java.util.ArrayList;

public class PowerGlove extends GunCardAltEff {
    /**
     * hard-coded constructor
     */
    public PowerGlove() {
        super();
        this.ammoCost = new char[2];
        ammoCost[0]= 'y';
        ammoCost[1]= 'b';
        this.description ="basic mode: Choose 1 target on any square\n" +
                "exactly 1 move away. Move onto that square\n" +
                "and give the target 1 damage and 2 marks.\n"+
                "in rocket fist mode: Choose a square\n" +
                "exactly 1 move away. Move onto that square.\n" +
                "You may deal 2 damage to 1 target there.\n" +
                "If you want, you may move 1 more square in\n" +
                "that same direction (but only if it is a legal\n" +
                "move). You may deal 2 damage to 1 target\n" +
                "there, as well.";
        this.secondaryEffectCost = new char[1];
        secondaryEffectCost[0] = 'b';
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
     * Choose 1 target on any square exactly 1 move away. Move onto that square and give the target 1 damage and 2 marks.
     */
    @Override
    void targetsOfBaseEffect(Controller currentController, SingleEffectsCombinationActions actions, FictitiousPlayer player) {
        NewCell[][] board = currentController.getMainGameModel().getCurrentMap().getBoardMatrix();
        ArrayList<Player> targets = new ArrayList<>();
        try {
            for (int i = 0; i < 4; i++){
                if(!player.getPosition().getEdge(i).equals(CellEdge.WALL))
                    targets.addAll(MapManager.getCellInDirection(board,player.getPosition(),1,i).getPlayers());
            }
        }catch (OuterWallException e){
            //Won't ever happen
        }
        actions.addToPlayerTargetList(targets);
        actions.setMaxNumPlayerTargets(1);

        //you will then move to that square automatically
    }

    /**
     * Choose a square exactly 1 move away. Move onto that square. You may deal 2 damage to 1 target there.
     *
     * If you want, you may move 1 more square in that same direction (but only if it is a legal move).
     * You may deal 2 damage to 1 target there, as well.
     */
    @Override
    void targetsOfSecondaryEffect(Controller currentController, SingleEffectsCombinationActions actions, FictitiousPlayer player) {
        //choice of a player to damage in one cell away
        targetsOfBaseEffect(currentController, actions, player);

        actions.setOfferableExtra(true); //to be then evaluated in view
    }
}