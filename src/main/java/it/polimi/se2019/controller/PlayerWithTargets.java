package it.polimi.se2019.controller;

import it.polimi.se2019.model.game.Player;

import java.util.ArrayList;

/**
 * Custom class for T.H.O.R. card
 *
 * returns the list of every player on the board with a list of players it can see
 */
public class PlayerWithTargets {
    private Player target;
    private ArrayList<Player> targetsItCanSee;
    PlayerWithTargets(Controller currentController, Player target) {
        this.target = target;
        this.targetsItCanSee=new ArrayList<>(ActionManager.visibleTargets(currentController,new FictitiousPlayer(currentController, target,new CellInfo(target.getFigure().getCell(),false,false),false,false)));
        this.targetsItCanSee.remove(currentController.getActiveTurn().getActivePlayer());
    }

    public Player getTarget() {
        return target;
    }

    public ArrayList<Player> getTargetsItCanSee() {
        return targetsItCanSee;
    }
}
