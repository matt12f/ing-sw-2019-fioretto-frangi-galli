package it.polimi.se2019.controller.weapon_managers;

import it.polimi.se2019.Adrenaline;
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
        damage [0] = Adrenaline.getMainController().getActiveturn().getActivePlayer().getFigure().getColor();
        //TODO è una toppa momentanea in attesa dell'input
        ArrayList<Player> targetList = new ArrayList<>();
        targetList.add(Adrenaline.getMainController().getActiveturn().getActivePlayer()) ;
        Adrenaline.getMainController().getActiveturn().getActionManager().getShootManager().inflictDamage(targetList, damage);
        //toppa
        targetList.remove(0);
        targetList = Adrenaline.getMainController().getActiveturn().getActivePlayer().getFigure().getCell().getPlayers();
        Adrenaline.getMainController().getActiveturn().getActionManager().getShootManager().appointMarker(targetList, damage);
    }
    private void secondaryEffect(){
        /** alternative attack
         *  target: 1 (as basic attack)
         *  damage: 1
         *  marker: 2(all target in the square)
         */
        char damage[] = new char[1];
        damage [0] = Adrenaline.getMainController().getActiveturn().getActivePlayer().getFigure().getColor();
        //TODO è una toppa momentanea in attesa dell'input
        ArrayList<Player> targetList = new ArrayList<>();
        targetList.add(Adrenaline.getMainController().getActiveturn().getActivePlayer()) ;
        Adrenaline.getMainController().getActiveturn().getActionManager().getShootManager().inflictDamage(targetList, damage);
        //toppa
        char mark[] = new char[2];
        mark [0] = Adrenaline.getMainController().getActiveturn().getActivePlayer().getFigure().getColor();
        mark [1] = Adrenaline.getMainController().getActiveturn().getActivePlayer().getFigure().getColor();
        targetList = Adrenaline.getMainController().getActiveturn().getActivePlayer().getFigure().getCell().getPlayers();
        Adrenaline.getMainController().getActiveturn().getActionManager().getShootManager().appointMarker(targetList, mark);
    }
   
}
