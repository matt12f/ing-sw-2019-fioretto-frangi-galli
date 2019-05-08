package it.polimi.se2019.controller.weapon_managers;

import it.polimi.se2019.controller.Controller;
import it.polimi.se2019.model.game.Player;

import java.util.ArrayList;

public class HellionManager {

    public void action(){
        //choose
        baseEffect();
        secondaryEffect();

    }
    private void baseEffect(){
        /** target: 1 (that you can see at  LEAST 1 move from you)
         *  damage: 1
         *  marker: 1 (all target in the square/cell)
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
        /** alternative attack
         *  target: 1 (as basic attack)
         *  damage: 1
         *  marker: 2(all target in the square)
         */
        char damage[] = new char[1];
        damage [0] = Controller.getActiveturn().getActivePlayer().getFigure().getColor();
        //TODO è una toppa momentanea in attesa dell'input
        ArrayList<Player> targetList = new ArrayList<>();
        targetList.add(Controller.getActiveturn().getActivePlayer()) ;
        Controller.getActiveturn().getActionManager().getShootManager().inflictDamage(targetList, damage);
        //toppa
        char mark[] = new char[2];
        mark [0] = Controller.getActiveturn().getActivePlayer().getFigure().getColor();
        mark [1] = Controller.getActiveturn().getActivePlayer().getFigure().getColor();
        targetList = Controller.getActiveturn().getActivePlayer().getFigure().getCell().getPlayers();
        Controller.getActiveturn().getActionManager().getShootManager().appointMarker(targetList, mark);
    }
   
}
