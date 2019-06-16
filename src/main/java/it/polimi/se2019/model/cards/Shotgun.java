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

import java.util.ArrayList;

public class Shotgun extends GunCardAltEff {
    /**
     * hard-coded constructor
     */
    public Shotgun() {
        super();
        this.ammoCost = new char[2];
        ammoCost[0]= 'y';
        ammoCost[1]= 'y';
        this.description ="basic mode: Deal 3 damage to 1 target on\n" +
                "your square. If you want, you may then move\n" +
                "the target 1 square.\n"+
                "in long barrel mode: Deal 2 damage to\n" +
                "1 target on any square exactly one move\n" +
                "away.";
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
     * Deal 3 damage to 1 target on your square. If you want, you may then move the target 1 square.
     */
    @Override
    void targetsOfBaseEffect(SingleEffectsCombinationActions actions, FictitiousPlayer player) {
        ArrayList<Player> targets = new ArrayList<>(player.getPosition().getPlayers());
        targets.remove(player.getCorrespondingPlayer());

        actions.addToTargetList1(targets);
        actions.setMaxNumberOfTargetsList1(1);

        actions.setAllowedMovement(true);
        actions.setYourOrTheirMovement(false);
        actions.setMaxDistanceOfMovement(1);
    }

    /**
     * Deal 2 damage to 1 target on any square exactly one move away.
     */
    @Override
    void targetsOfSecondaryEffect(SingleEffectsCombinationActions actions, FictitiousPlayer player) {
        NewCell[][] board= AdrenalineServer.getMainController().getMainGameModel().getCurrentMap().getBoardMatrix();
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
        actions.setMaxNumberOfTargetsList1(1);
    }
}