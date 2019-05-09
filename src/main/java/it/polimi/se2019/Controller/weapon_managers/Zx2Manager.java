package it.polimi.se2019.controller.weapon_managers;

import it.polimi.se2019.controller.Controller;
import it.polimi.se2019.model.game.Player;

import java.util.ArrayList;

public class Zx2Manager {

    public void action(){
        baseEffect();
        secondaryEffect();
    }
    private void baseEffect(){
        /** target: 1 (that you can see)
         *  damage: 1
         *  marker: 2
         */
        char damage[] = new char[1];
        damage [0] = Controller.getActiveturn().getActivePlayer().getFigure().getColor();

        //TODO è una toppa momentanea in attesa dell'input
        ArrayList<Player> targetList = new ArrayList<>();
        targetList.add(Controller.getActiveturn().getActivePlayer()) ;
        Controller.getActiveturn().getActionManager().getShootManager().inflictDamage(targetList, damage);
        char mark[] = new char[2];
        mark [0] = Controller.getActiveturn().getActivePlayer().getFigure().getColor();
        mark [1] = Controller.getActiveturn().getActivePlayer().getFigure().getColor();
        Controller.getActiveturn().getActionManager().getShootManager().appointMarker(targetList, mark);
    }
    private void secondaryEffect(){
        /** target: 3 (that you can see)
         *  marker: 1
         */
        char mark[] = new char[1];
        mark [0] = Controller.getActiveturn().getActivePlayer().getFigure().getColor();
        //TODO è una toppa momentanea in attesa dell'input
        ArrayList<Player> targetList = new ArrayList<>();
        targetList.add(Controller.getActiveturn().getActivePlayer()) ;
        Controller.getActiveturn().getActionManager().getShootManager().appointMarker(targetList, mark);
    }

}
