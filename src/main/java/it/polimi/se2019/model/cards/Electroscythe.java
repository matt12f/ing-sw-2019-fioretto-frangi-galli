package it.polimi.se2019.model.cards;

import it.polimi.se2019.controller.ActionManager;
import it.polimi.se2019.controller.Controller;
import it.polimi.se2019.controller.FictitiousPlayer;
import it.polimi.se2019.model.game.NewCell;
import it.polimi.se2019.model.game.Player;
import it.polimi.se2019.view.ChosenActions;
import it.polimi.se2019.controller.SingleEffectsCombinationActions;

import java.util.ArrayList;

public class Electroscythe extends GunCardAltEff {
    /**
     * hard-coded constructor
     */
    public Electroscythe() {
        super();
        this.ammoCost = new char[1];
        ammoCost[0]= 'b';
        this.description =  "basic mode: Deal 1 damage to every other player on your square.\n" +
                "in reaper mode: Deal 2 damage to every other player on your square.";

        this.secondaryEffectCost = new char[2];
        secondaryEffectCost[0] = 'b';
        secondaryEffectCost[1] = 'r';
    }
    /**
     * This applies the base effect
     * @param currentController it the current controller of the game
     * @param playersChoice are the choices the player wants to apply
     */
    @Override
    void applyBaseEffect(Controller currentController, ChosenActions playersChoice) {
        ArrayList<Player> targets=new ArrayList<>(Player.duplicateList(playersChoice.getTargetCell().getPlayers()));
        targets.remove(playersChoice.getFictitiousPlayer().getCorrespondingPlayer());
        ActionManager.giveDmgandMksToPlayers(currentController,targets,playersChoice,1,0);
    }
    /**
     * This applies the alternative effect
     * @param currentController it the current controller of the game
     * @param playersChoice are the choices the player wants to apply
     */
    @Override
    void applySecondaryEffect(Controller currentController, ChosenActions playersChoice) {
        ArrayList<Player> targets=new ArrayList<>(Player.duplicateList(playersChoice.getTargetCell().getPlayers()));
        targets.remove(currentController.getActiveTurn().getActivePlayer());
        ActionManager.giveDmgandMksToPlayers(currentController,targets,playersChoice,2,0);
    }

    /**
     * Deal 1 damage to every other player on your square.
     */
    @Override
    void targetsOfBaseEffect(Controller currentController, SingleEffectsCombinationActions actions, FictitiousPlayer player) {
        ArrayList<NewCell> oneCell=new ArrayList<>();
        oneCell.add(player.getPosition());
        actions.addToTargetCells(oneCell);
        if(player.getPosition().getPlayers().size()==1)
            actions.setOfferableBase(false);
    }

    /**
     * Deal 2 damage to every other player on your square.
     */
    @Override
    void targetsOfSecondaryEffect(Controller currentController, SingleEffectsCombinationActions actions, FictitiousPlayer player){
        targetsOfBaseEffect(currentController,actions,player);

        if(player.getPosition().getPlayers().size()==1)
            actions.setOfferableOpt1(false);
    }


    @Override
    public GunCard clone() {
        GunCard gunCard = new Electroscythe();
        gunCard.setLoaded(this.isLoaded());

        return gunCard;
    }
}