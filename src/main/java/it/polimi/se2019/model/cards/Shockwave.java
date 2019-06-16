package it.polimi.se2019.model.cards;

import it.polimi.se2019.AdrenalineServer;
import it.polimi.se2019.controller.FictitiousPlayer;
import it.polimi.se2019.controller.MapManager;
import it.polimi.se2019.enums.CellEdge;
import it.polimi.se2019.exceptions.OuterWallException;
import it.polimi.se2019.model.game.NewCell;
import it.polimi.se2019.model.game.Player;
import it.polimi.se2019.view.ChosenActions;
import it.polimi.se2019.controller.SingleEffectsCombinationActions;
import it.polimi.se2019.exceptions.UnavailableEffectCombinationException;

import java.util.ArrayList;

public class Shockwave extends GunCardAltEff {
    /**
     * hard-coded constructor
     */
    public Shockwave() {
        super();
        this.ammoCost = new char[1];
        ammoCost[0]= 'y';
        this.description ="basic mode: Choose up to 3 targets on\n" +
                "different squares, each exactly 1 move away.\n" +
                "Deal 1 damage to each target.\n"+
                "in tsunami mode: Deal 1 damage to all\n" +
                "targets that are exactly 1 move away";

        this.secondaryEffectCost = new char[1];
        secondaryEffectCost[0] = 'y';
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
     * Choose up to 3 targets on different squares, each exactly 1 move away. Deal 1 damage to each target.
     */
    @Override
    void targetsOfBaseEffect(SingleEffectsCombinationActions actions, FictitiousPlayer player) {
        NewCell[][] board = AdrenalineServer.getMainController().getMainGameModel().getCurrentMap().getBoardMatrix();

        ArrayList<Player> targets = new ArrayList<>();
        try {
            for (int i = 0; i < 4; i++){
                if(!player.getPosition().getEdge(i).equals(CellEdge.WALL))
                    targets.addAll(MapManager.getCellInDirection(board,player.getPosition(),1,i).getPlayers());
            }
        }catch (OuterWallException e){
            //Won't ever happen
        }
        actions.addToTargetList1(targets);
        actions.setMaxNumberOfTargetsList1(3);
        //TODO controllare che i target siano ognuno su uno square diverso

    }

    /**
     * Deal 1 damage to all targets that are exactly 1 move away.
     */
    @Override
    void targetsOfSecondaryEffect(SingleEffectsCombinationActions actions, FictitiousPlayer player) {
        //TODO basta scegliere se usarlo o no
    }
}