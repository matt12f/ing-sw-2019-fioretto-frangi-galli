package it.polimi.se2019.controller.weapon_managers;

import it.polimi.se2019.AdrenalineServer;
import it.polimi.se2019.model.game.Player;

import java.util.ArrayList;

public class LockRifleManager {

    public static void action(){
        //choose
        baseEffect();
        secondaryEffect();
    }
    private static void baseEffect(){
        /** target: 1 (that you can see)
         *  damage: 1
         *  marker: 1
         */
        char damage[] = new char[1];
        damage [0] = AdrenalineServer.getMainController().getActiveturn().getActivePlayer().getFigure().getColor();
        //TODO è una toppa momentanea in attesa dell'input
        ArrayList<Player> targetList = new ArrayList<>();
        targetList.add(AdrenalineServer.getMainController().getActiveturn().getActivePlayer()) ;
        AdrenalineServer.getMainController().getActiveturn().getActionManager().getShootManager().inflictDamage(targetList, damage);
        //toppa
        targetList = AdrenalineServer.getMainController().getActiveturn().getActivePlayer().getFigure().getCell().getPlayers();
        AdrenalineServer.getMainController().getActiveturn().getActionManager().getShootManager().appointMarker(targetList, damage);
    }
    private static void secondaryEffect(){
        /** optional attack
         *  target: 1 (different from the one of base effect)
         *
         *  marker: 1
         */
        char mark[] = new char[1];
        mark [0] = AdrenalineServer.getMainController().getActiveturn().getActivePlayer().getFigure().getColor();
        //TODO è una toppa momentanea in attesa dell'input
        ArrayList<Player> targetList = new ArrayList<>();
        targetList.add(AdrenalineServer.getMainController().getActiveturn().getActivePlayer()) ;
        //toppa
        targetList = AdrenalineServer.getMainController().getActiveturn().getActivePlayer().getFigure().getCell().getPlayers();
        AdrenalineServer.getMainController().getActiveturn().getActionManager().getShootManager().appointMarker(targetList, mark);
    }
}
