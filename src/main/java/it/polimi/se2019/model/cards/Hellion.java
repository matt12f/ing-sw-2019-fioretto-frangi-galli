package it.polimi.se2019.model.cards;

import it.polimi.se2019.controller.ActionManager;
import it.polimi.se2019.controller.Controller;
import it.polimi.se2019.controller.FictitiousPlayer;
import it.polimi.se2019.model.game.Player;
import it.polimi.se2019.view.ChosenActions;
import it.polimi.se2019.controller.SingleEffectsCombinationActions;

import java.util.ArrayList;

public class Hellion extends GunCardAltEff{
    /**
     * hard-coded constructor
     */
    public Hellion() {
        super();
        this.ammoCost = new char[2];
        ammoCost[0] = 'r';
        ammoCost[1] = 'y';
        this.description = "basic mode: Deal 1 damage to 1 target you can see at least"+"\n" +
                "1 move away. Then give 1 mark to that target and everyone"+"\n" +
                "else on that square."+"\n" +
                "in nano-tracer mode: Deal 1 damage to 1 target you can"+"\n" +
                "see at least 1 move away. Then give 2 marks to that target"+"\n" +
                "and everyone else on that square.";
        this.secondaryEffectCost = new char[1];
        secondaryEffectCost[0] = 'r';
    }
    /**
     * This applies the base effect
     * @param currentController it the current controller of the game
     * @param playersChoice are the choices the player wants to apply
     */
    @Override
    void applyBaseEffect(Controller currentController, ChosenActions playersChoice) {
        ActionManager.giveDmgandMksToOnePlayer(currentController,playersChoice.getTargetsFromList1().get(0),playersChoice,1,1);
        ActionManager.giveDmgandMksToPlayers(currentController,playersChoice.getTargetsFromList1().get(0).getFigure().getCell().getPlayers(),playersChoice,0,1);
    }
    /**
     * This applies the second effect
     * @param currentController it the current controller of the game
     * @param playersChoice are the choices the player wants to apply
     */
    @Override
    void applySecondaryEffect(Controller currentController, ChosenActions playersChoice) {
        ActionManager.giveDmgandMksToOnePlayer(currentController,playersChoice.getTargetsFromList1().get(0),playersChoice,1,2);
        ActionManager.giveDmgandMksToPlayers(currentController,playersChoice.getTargetsFromList1().get(0).getFigure().getCell().getPlayers(),playersChoice,0,2);
    }

    /**
     * Deal 1 damage to 1 target you can see at least 1 move away. Then give 1 mark to that target and everyone else on that square.
     *
     * @param currentController
     * @param actions
     * @param player
     */
    @Override
    void targetsOfBaseEffect(Controller currentController, SingleEffectsCombinationActions actions, FictitiousPlayer player) {
        ArrayList<Player> targets=new ArrayList<>(ActionManager.visibleTargets(currentController,player));
        targets.removeAll(player.getPosition().getPlayers()); //it's certainly more than one move away if it's not on your cell

        actions.addToPlayerTargetList(targets);
        actions.setMaxNumPlayerTargets(1);
        actions.setMinNumPlayerTargets(1);

    }
    /**
     * Deal 1 damage to 1 target you can see at least 1 move away. Then give 2 marks to that target and everyone else on that square.
     *
     * @param currentController
     * @param actions
     * @param player
     */
    @Override
    void targetsOfSecondaryEffect(Controller currentController, SingleEffectsCombinationActions actions, FictitiousPlayer player) {
        targetsOfBaseEffect(currentController, actions, player);
    }

    @Override
    public GunCard clone() {
        GunCard gunCard = new Hellion();
        gunCard.setLoaded(this.isLoaded());

        return gunCard;
    }
}