package it.polimi.se2019.controller.weapon_managers;

import it.polimi.se2019.controller.Controller;
import it.polimi.se2019.model.game.Player;

import java.util.ArrayList;

public class HeatseekerManager {

    public void action(){
        baseEffect();
    }
    private void baseEffect(){
        /** target: 1 (that you cannot see)
         *  damage: 3
         *
         */
        char damageThirdeffect[] = new char[3];
        damageThirdeffect [0] = Controller.getActiveturn().getActivePlayer().getFigure().getColor();
        damageThirdeffect [1] = Controller.getActiveturn().getActivePlayer().getFigure().getColor();
        damageThirdeffect [2] = Controller.getActiveturn().getActivePlayer().getFigure().getColor();
        //TODO è una toppa momentanea in attesa dell'input
        ArrayList<Player> targetList = new ArrayList<>();
        targetList.add(Controller.getActiveturn().getActivePlayer()) ;
        Controller.getActiveturn().getActionManager().getShootManager().inflictDamage(targetList, damageThirdeffect);
    }

   
}
