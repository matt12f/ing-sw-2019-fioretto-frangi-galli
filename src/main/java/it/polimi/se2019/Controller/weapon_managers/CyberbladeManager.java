package it.polimi.se2019.controller.weapon_managers;

import it.polimi.se2019.controller.Controller;
import it.polimi.se2019.model.game.Player;

import java.util.ArrayList;

public class CyberbladeManager {

    public void action(){

        baseEffect();
        secondaryEffect();
        thirdEffect();
    }
    private void baseEffect(){
        /**
         *  target: 1 (in your cell)
         *  damage: 2
         *
         */
        char damage[] = new char[2];
        damage [0] = Controller.getActiveturn().getActivePlayer().getFigure().getColor();
        damage [1] = Controller.getActiveturn().getActivePlayer().getFigure().getColor();
        //input da view del target
        //TODO è una toppa momentanea in attesa dell'input
         ArrayList<Player> targetList = new ArrayList<>();
        targetList.add(Controller.getActiveturn().getActivePlayer()) ;
        Controller.getActiveturn().getActionManager().getShootManager().inflictDamage(targetList, damage);
    }
    private void secondaryEffect(){
        /** optional attack before or after any attack
         *  target: you
         *  move: 1
         *
         */
        ArrayList<Player> targetList = new ArrayList<>();
        targetList.add(Controller.getActiveturn().getActivePlayer()) ;
        Controller.getActiveturn().getActionManager().getShootManager().moveOpponent(targetList);

    }
    private void thirdEffect(){
        /** optional attack
         *  target: 1 (another one, but in your cell)
         *  damage: 2
         *
         */
        char damageThirdeffect[] = new char[2];
        damageThirdeffect [0] = Controller.getActiveturn().getActivePlayer().getFigure().getColor();
        damageThirdeffect [1] = Controller.getActiveturn().getActivePlayer().getFigure().getColor();
        //TODO è una toppa momentanea in attesa dell'input
        ArrayList<Player> targetList = new ArrayList<>();
        targetList.add(Controller.getActiveturn().getActivePlayer()) ;
        Controller.getActiveturn().getActionManager().getShootManager().inflictDamage(targetList, damageThirdeffect);
    }
}
