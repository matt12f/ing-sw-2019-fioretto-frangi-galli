package it.polimi.se2019.controller.weapon_managers;

import it.polimi.se2019.controller.Controller;
import it.polimi.se2019.model.game.Player;

import java.util.ArrayList;

public class LockRifleManager {

    public void action(){
        //choose
        baseEffect();
        secondaryEffect();
    }
    private void baseEffect(){
        /** target: 1 (that you can see)
         *  damage: 1
         *  marker: 1
         */
        char damage[] = new char[1];
        damage [0] = Controller.getActiveturn().getActivePlayer().getFigure().getColor();
        //TODO è una toppa momentanea in attesa dell'input
        ArrayList<Player> targetList = new ArrayList<>();
        targetList.add(Controller.getActiveturn().getActivePlayer()) ;
        Controller.getActiveturn().getActionManager().getShootManager().inflictDamage(targetList, damage);
        //toppa
        targetList = Controller.getActiveturn().getActivePlayer().getFigure().getCell().getPlayers();
        Controller.getActiveturn().getActionManager().getShootManager().appointMarker(targetList, damage);
    }
    private void secondaryEffect(){
        /** optional attack
         *  target: 1 (different from the one of base effect)
         *
         *  marker: 1
         */
        char mark[] = new char[1];
        mark [0] = Controller.getActiveturn().getActivePlayer().getFigure().getColor();
        //TODO è una toppa momentanea in attesa dell'input
        ArrayList<Player> targetList = new ArrayList<>();
        targetList.add(Controller.getActiveturn().getActivePlayer()) ;
        //toppa
        targetList = Controller.getActiveturn().getActivePlayer().getFigure().getCell().getPlayers();
        Controller.getActiveturn().getActionManager().getShootManager().appointMarker(targetList, mark);
    }
}
