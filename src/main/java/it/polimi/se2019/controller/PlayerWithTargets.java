package it.polimi.se2019.controller;

import it.polimi.se2019.model.game.Player;

import java.util.ArrayList;

//Custom class for T.H.O.R. card
public class PlayerWithTargets {
    private Player target;
    private ArrayList<Player> targetsItCanSee;
    public PlayerWithTargets(Controller currentController, Player target) {
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
