package it.polimi.se2019.controller.weapon_managers;

import it.polimi.se2019.AdrenalineServer;
import it.polimi.se2019.model.game.Player;

import java.util.ArrayList;

public class FlamethrowerManager {

    public static void action(){
        //determinare scelta
        baseEffect();
        secondaryEffect();
    }
    private static void baseEffect(){
        /** target: 1, 2(first target 1 move away, second has to be in in a second square 1 move from the first in same direction)
         *  damage: 1
         *
         */
        char damage[] = new char[1];
        damage [0] = AdrenalineServer.getMainController().getActiveturn().getActivePlayer().getFigure().getColor();
        //TODO è una toppa momentanea in attesa dell'input
        ArrayList<Player> targetList = new ArrayList<>();
        targetList.add(AdrenalineServer.getMainController().getActiveturn().getActivePlayer()) ;
        AdrenalineServer.getMainController().getActiveturn().getActionManager().getShootManager().inflictDamage(targetList, damage);
    }
    private static void secondaryEffect(){
        /** alternative attack
         *  target: cell (as the basic attack)
         *  damage: 2 n first square, 1 in second square
         *
         */
        char damage[] = new char[2];
        damage [0] = AdrenalineServer.getMainController().getActiveturn().getActivePlayer().getFigure().getColor();
        damage [1] = AdrenalineServer.getMainController().getActiveturn().getActivePlayer().getFigure().getColor();
        //TODO è una toppa momentanea in attesa dell'input, check alla fine
        ArrayList<Player> targetList = new ArrayList<>();
        targetList.add(AdrenalineServer.getMainController().getActiveturn().getActivePlayer()) ;
        AdrenalineServer.getMainController().getActiveturn().getActionManager().getShootManager().inflictDamage(targetList, damage);
        targetList.remove(0);
        //input 2 target
        targetList.add(AdrenalineServer.getMainController().getActiveturn().getActivePlayer()) ;
        damage [1] = ' ';
        AdrenalineServer.getMainController().getActiveturn().getActionManager().getShootManager().inflictDamage(targetList, damage);
    }
   
}
