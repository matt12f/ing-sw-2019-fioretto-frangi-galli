package it.polimi.se2019.controller.weapon_managers;

import it.polimi.se2019.AdrenalineServer;
import it.polimi.se2019.model.game.Player;

import java.util.ArrayList;

public class CyberbladeManager {
    private static ArrayList<Player> targetList = new ArrayList<>();
    private static char [] damage = new char[2];

    public static void action(){
        damage [0] = AdrenalineServer.getMainController().getActiveturn().getActivePlayer().getFigure().getColor();
        damage [1] = AdrenalineServer.getMainController().getActiveturn().getActivePlayer().getFigure().getColor();


        baseEffect();
        secondaryEffect();
        thirdEffect();
    }
    private static void baseEffect(){
        /**
         *  target: 1 (in your cell)
         *  damage: 2
         *
         */
        targetList.clear();


        //input da view del target
        //TODO è una toppa momentanea in attesa dell'input

        targetList.add(AdrenalineServer.getMainController().getActiveturn().getActivePlayer());
        AdrenalineServer.getMainController().getActiveturn().getActionManager().getShootManager().inflictDamage(targetList, damage);
    }
    private static void secondaryEffect(){
        /** optional attack before or after any attack
         *  target: you
         *  move: 1
         *
         */
        targetList.clear();
        targetList.add(AdrenalineServer.getMainController().getActiveturn().getActivePlayer()) ;
        AdrenalineServer.getMainController().getActiveturn().getActionManager().getShootManager().moveOpponent(targetList);
    }
    private static void thirdEffect(){
        /** optional attack
         *  target: 1 (another one, but in your cell)
         *  damage: 2
         *
         */
        targetList.clear();

        //TODO è una toppa momentanea in attesa dell'input

        targetList.add(AdrenalineServer.getMainController().getActiveturn().getActivePlayer()) ;
        AdrenalineServer.getMainController().getActiveturn().getActionManager().getShootManager().inflictDamage(targetList, damage);
    }
}
