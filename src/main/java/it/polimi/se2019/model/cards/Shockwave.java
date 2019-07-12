package it.polimi.se2019.model.cards;

import it.polimi.se2019.controller.*;
import it.polimi.se2019.model.game.NewCell;
import it.polimi.se2019.model.game.Player;
import it.polimi.se2019.view.ChosenActions;

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
     * This applies the secondary effect
     * @param currentController it the current controller of the game
     * @param playersChoice are the choices the player wants to apply
     */
    @Override
    void applySecondaryEffect(Controller currentController, ChosenActions playersChoice) {
        ArrayList<Player> targets = ActionManager.targetsOneMoveAway(currentController,playersChoice.getFictitiousPlayer().getPosition());
        ActionManager.giveDmgandMksToPlayers(currentController,targets,playersChoice,1,0);
    }

    /**
     * Choose up to 3 targets on different squares, each exactly 1 move away. Deal 1 damage to each target.
     *
     *
     * @param currentController it the current controller of the game
     */
    @Override
    void targetsOfBaseEffect(Controller currentController, SingleEffectsCombinationActions actions, FictitiousPlayer player) {
        //These are the players that you can move to end up in squares that you can see
        for(NewCell visibleSquare:ActionManager.cellsOneMoveAway(currentController,player.getPosition())){
           if(!visibleSquare.getPlayers().isEmpty())
               actions.addCellsWithTargets(visibleSquare, Player.duplicateList(visibleSquare.getPlayers()),1,1,false,false);
        }
        actions.setMaxCellToSelect(3);
        actions.setMinCellToSelect(1);
    }

    /**
     * Deal 1 damage to all targets that are exactly 1 move away.
     *
     * find targets
     * @param currentController it the current controller of the game
     */
    @Override
    void targetsOfSecondaryEffect(Controller currentController, SingleEffectsCombinationActions actions, FictitiousPlayer player) {
        //here we must create a fictitious cell that will look like the cell of the player, but once selected
        // will harm all of the players on
        ArrayList<NewCell> oneCell=new ArrayList<>();
        oneCell.add(player.getPosition());
        actions.addToTargetCells(oneCell);

        if(ActionManager.targetsOneMoveAway(currentController,player.getPosition()).isEmpty())
            actions.setOfferableOpt1(false);
    }

    @Override
    public GunCard clone() {
        GunCard gunCard = new Shockwave();
        gunCard.setLoaded(this.isLoaded());

        return gunCard;
    }
}