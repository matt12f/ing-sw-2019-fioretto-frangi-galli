package it.polimi.se2019.controller.weapon_managers;

import it.polimi.se2019.AdrenalineServer;
import it.polimi.se2019.model.game.Player;

import java.util.ArrayList;

public class PowergloveManager {

    public static void action(){

        //choose
        baseEffect();
        secondaryEffect();
    }
    private static void baseEffect(){
        /** target: 1 (1 move away)
         *  damage: 1
         *  move: 1 (yourself)
         *  marker: 2
         */
        char damage[] = new char[1];
        damage [0] = AdrenalineServer.getMainController().getActiveturn().getActivePlayer().getFigure().getColor();
        char marks[] = new char[1];
        marks [0] = AdrenalineServer.getMainController().getActiveturn().getActivePlayer().getFigure().getColor();
        marks [1] = AdrenalineServer.getMainController().getActiveturn().getActivePlayer().getFigure().getColor();
        //input da view del target
        //TODO è una toppa momentanea in attesa dell'input
        ArrayList<Player> targetList = new ArrayList<>();
        targetList.add(AdrenalineServer.getMainController().getActiveturn().getActivePlayer()) ;
        AdrenalineServer.getMainController().getActiveturn().getActionManager().getShootManager().inflictDamage(targetList, damage);
        AdrenalineServer.getMainController().getActiveturn().getActionManager().getShootManager().appointMarker(targetList, marks);
        targetList.remove(0);
        targetList.add(AdrenalineServer.getMainController().getActiveturn().getActivePlayer()) ;
        AdrenalineServer.getMainController().getActiveturn().getActionManager().getShootManager().moveOpponent(targetList);
    }
    private static void secondaryEffect(){
        /** alternative attack
         *  target: 2 (1 target 1 move away from you, 2 target 1 move away from the first and in the same line, but the second is optional)
         *  damage: 2
         *  move: 1 yourself
         */
        char damage[] = new char[2];
        damage [0] = AdrenalineServer.getMainController().getActiveturn().getActivePlayer().getFigure().getColor();
        damage [1] = AdrenalineServer.getMainController().getActiveturn().getActivePlayer().getFigure().getColor();
        ArrayList<Player> targetList = new ArrayList<>();
        targetList.add(AdrenalineServer.getMainController().getActiveturn().getActivePlayer()) ;
        AdrenalineServer.getMainController().getActiveturn().getActionManager().getShootManager().inflictDamage(targetList, damage);
        targetList.remove(0);
        targetList.remove(1);
        targetList.add(AdrenalineServer.getMainController().getActiveturn().getActivePlayer()) ;
        AdrenalineServer.getMainController().getActiveturn().getActionManager().getShootManager().moveOpponent(targetList);

    }

}
