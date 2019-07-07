package it.polimi.se2019.model.cards;

import it.polimi.se2019.controller.*;
import it.polimi.se2019.enums.CellEdge;
import it.polimi.se2019.exceptions.OuterWallException;
import it.polimi.se2019.model.game.NewCell;
import it.polimi.se2019.model.game.Player;
import it.polimi.se2019.view.ChosenActions;

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
    /**
     * This applies the base/base+opt2 effect
     * @param currentController it the current controller of the game
     * @param playersChoice are the choices the player wants to apply
     */
    @Override
    void applyBaseEffect(Controller currentController, ChosenActions playersChoice) {
        ActionManager.movePlayer(currentController,currentController.getActiveTurn().getActivePlayer(),playersChoice.getTargetsFromList1().get(0).getFigure().getCell());
        ActionManager.giveDmgandMksToOnePlayer(currentController,playersChoice.getTargetsFromList1().get(0),playersChoice,1,2);
    }
    /**
     * This applies the secondary effect
     * @param currentController it the current controller of the game
     * @param playersChoice are the choices the player wants to apply
     */
    @Override
    void applySecondaryEffect(Controller currentController, ChosenActions playersChoice) {

        if(!playersChoice.isUseExtra())
            ActionManager.movePlayer(currentController,currentController.getActiveTurn().getActivePlayer(),playersChoice.getTargetsFromList1().get(0).getFigure().getCell());
        else {
            ActionManager.movePlayer(currentController,currentController.getActiveTurn().getActivePlayer(),playersChoice.getTargetsFromCell().get(0).getFigure().getCell() );
            ActionManager.giveDmgandMksToOnePlayer(currentController,playersChoice.getTargetsFromCell().get(0),playersChoice,2,0);
        }
        //part of the normal secondary effect (it's important to move first and then hit)
        ActionManager.giveDmgandMksToOnePlayer(currentController,playersChoice.getTargetsFromList1().get(0),playersChoice,2,0);

    }

    /**
     * Choose 1 target on any square exactly 1 move away. Move onto that square and give the target 1 damage and 2 marks.
     *
     * This applies the secondary effect
     * @param currentController
     * @param actions
     * @param player
     */
    @Override
    void targetsOfBaseEffect(Controller currentController, SingleEffectsCombinationActions actions, FictitiousPlayer player) {
        NewCell[][] board = currentController.getMainGameModel().getCurrentMap().getBoardMatrix();
        ArrayList<Player> targets = new ArrayList<>();
        try {
            for (int i = 0; i < 4; i++){
                if(!player.getPosition().getEdge(i).equals(CellEdge.WALL))
                    targets.addAll(Player.duplicateList(MapManager.getCellInDirection(board,player.getPosition(),1,i).getPlayers()));
            }
        }catch (OuterWallException e){
            //Won't ever happen
        }
        actions.addToPlayerTargetList(targets);
        actions.setMaxNumPlayerTargets(1);
        actions.setMinNumPlayerTargets(1);

        if(actions.getPlayersWithTargets().isEmpty())
            actions.setOfferableBase(false);

        //you will then move to that square automatically
    }

    /**
     * Choose a square exactly 1 move away. Move onto that square. You may deal 2 damage to 1 target there.
     *
     * If you want, you may move 1 more square in that same direction (but only if it is a legal move).
     * You may deal 2 damage to 1 target there, as well.
     *
     *
     * @param currentController
     * @param actions
     * @param player
     */
    @Override
    void targetsOfSecondaryEffect(Controller currentController, SingleEffectsCombinationActions actions, FictitiousPlayer player) {
        //choice of a player to damage in one cell away
        targetsOfBaseEffect(currentController, actions, player);

        if(actions.getPlayersWithTargets().isEmpty()){
            actions.setOfferableOpt1(false);
        }else{
        //cells two moves away, with targets to select

        NewCell[][] board = currentController.getMainGameModel().getCurrentMap().getBoardMatrix();
        ArrayList<NewCell> cellsTwoMovesAway=new ArrayList<>();
        try {
            for (int i = 0; i < 4 ; i++) {
                if (!player.getPosition().getEdge(i).equals(CellEdge.WALL))
                    if(!MapManager.getCellInDirection(board,player.getPosition(),1,i).getEdge(i).equals(CellEdge.WALL))
                        cellsTwoMovesAway.add(MapManager.getCellInDirection(board,player.getPosition(),2,i));
            }
        }catch (OuterWallException e){
            //Won't happen
        }

        for(NewCell cell:cellsTwoMovesAway){
            if(!cell.getPlayers().isEmpty())
                actions.addCellsWithTargets(cell, cell.getPlayers(), 1, 1, false, false);
        }
        if(actions.getCellsWithTargets().isEmpty())
            actions.setOfferableExtra(false);
        else
            actions.setOfferableExtra(true);

            //you'll then automatically move in the cell of the player you've hit
        }
    }

    @Override
    public GunCard clone() {
        GunCard gunCard = new PowerGlove();
        gunCard.setLoaded(this.isLoaded());

        return gunCard;
    }
}