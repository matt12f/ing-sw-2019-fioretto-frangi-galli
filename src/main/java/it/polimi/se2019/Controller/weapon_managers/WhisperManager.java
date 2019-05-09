package it.polimi.se2019.controller.weapon_managers;

import it.polimi.se2019.controller.Controller;
import it.polimi.se2019.model.game.Player;

import java.util.ArrayList;

public class WhisperManager {

    public void action(){
        baseEffect();
    }
    private void baseEffect(){
        /** target: 1 (thet you can see, but at LEAST 2 moves away from you)
         *  damage: 3
         *  marker: 1
         */

        char damage[] = new char[3];
        damage [0] = Controller.getActiveturn().getActivePlayer().getFigure().getColor();
        damage [1] = Controller.getActiveturn().getActivePlayer().getFigure().getColor();
        damage [2] = Controller.getActiveturn().getActivePlayer().getFigure().getColor();
        //TODO è una toppa momentanea in attesa dell'input
        ArrayList<Player> targetList = new ArrayList<>();
        targetList.add(Controller.getActiveturn().getActivePlayer()) ;
        Controller.getActiveturn().getActionManager().getShootManager().inflictDamage(targetList, damage);
        char mark[] = new char[2];
        mark [0] = Controller.getActiveturn().getActivePlayer().getFigure().getColor();
        mark [1] = Controller.getActiveturn().getActivePlayer().getFigure().getColor();
        Controller.getActiveturn().getActionManager().getShootManager().appointMarker(targetList, mark);
    }
   
}
