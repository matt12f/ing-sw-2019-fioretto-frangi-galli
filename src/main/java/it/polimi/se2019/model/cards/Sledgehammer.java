package it.polimi.se2019.model.cards;

import it.polimi.se2019.controller.FictitiousPlayer;
import it.polimi.se2019.controller.MapManager;
import it.polimi.se2019.enums.CellEdge;
import it.polimi.se2019.model.game.Player;
import it.polimi.se2019.view.ChosenActions;
import it.polimi.se2019.controller.SingleEffectsCombinationActions;
import it.polimi.se2019.exceptions.UnavailableEffectCombinationException;

import java.util.ArrayList;

public class Sledgehammer extends GunCardAltEff {
    /**
     * hard-coded constructor
     */
    public Sledgehammer() {
        super();
        this.ammoCost = new char[2];
        ammoCost[0]= 'y';
        this.description ="basic mode: Deal 2 damage to 1 target on\n" +
                "your square.\n"+
                "in pulverize mode: Deal 3 damage to 1 target\n" +
                "on your square, then move that target 0, 1,\n" +
                "or 2 squares in one direction.";

        this.secondaryEffectCost = new char[1];
        secondaryEffectCost[0] = 'r';
    }

    @Override
    void applyBaseEffect(ChosenActions playersChoice) {

    }

    @Override
    void applySecondaryEffect(ChosenActions playersChoice) {

    }

    /**
     * Deal 2 damage to 1 target on your square.
     */
    @Override
    void targetsOfBaseEffect(SingleEffectsCombinationActions actions, FictitiousPlayer player) {
        ArrayList<Player> targets =new ArrayList<>(player.getPosition().getPlayers());
        targets.remove(player.getCorrespondingPlayer());

        actions.addToTargetList1(targets);
        actions.setMaxNumberOfTargetsList1(1);
    }

    /**
     * Deal 3 damage to 1 target on your square, then move that target 0, 1, or 2 squares in one direction.
     */
    @Override
    void targetsOfSecondaryEffect(SingleEffectsCombinationActions actions, FictitiousPlayer player) {
        targetsOfBaseEffect(actions,player);
        actions.setAllowedMovement(true);
        actions.setYourOrTheirMovement(false);
        actions.setMaxDistanceOfMovement(2);

        for (int i = 0; i < 4 ; i++)
            if (!player.getPosition().getEdge(i).equals(CellEdge.WALL))
                actions.addPossibleDirOfMov(MapManager.getDirOfMove(i));
            //TODO dove può essere spostato??
    }
}