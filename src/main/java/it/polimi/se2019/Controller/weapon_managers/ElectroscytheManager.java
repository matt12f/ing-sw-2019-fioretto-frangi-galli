package it.polimi.se2019.controller.weapon_managers;

import it.polimi.se2019.AdrenalineServer;
import it.polimi.se2019.model.game.Player;

import java.util.ArrayList;

public class ElectroscytheManager {
    private static ArrayList<Player> targetList = new ArrayList<>();
    private static char damage[] = new char[1];
    public static void action(){
        //determinare scelta
        damage [0] = AdrenalineServer.getMainController().getActiveturn().getActivePlayer().getFigure().getColor();

        baseEffect();
        secondaryEffect();
    }
    private static void baseEffect(){
        /** target: every target in your square/cell
         *  damage: 1
         *
         */


        //TODO check, togliere il player

        targetList = AdrenalineServer.getMainController().getActiveturn().getActivePlayer().getFigure().getCell().getPlayers();
        AdrenalineServer.getMainController().getActiveturn().getActionManager().getShootManager().inflictDamage(targetList, damage);
    }
    private static void secondaryEffect(){
        /** alternative attack
         * target: every target in your cell
         *  damage: 2
         *
         */
        char damage[] = new char[2];
        damage [0] = AdrenalineServer.getMainController().getActiveturn().getActivePlayer().getFigure().getColor();
        damage [1] = AdrenalineServer.getMainController().getActiveturn().getActivePlayer().getFigure().getColor();
        //TODO check
        ArrayList<Player> targetList = new ArrayList<>();
        for (int i = 0; i < AdrenalineServer.getMainController().getActiveturn().getActivePlayer().getFigure().getCell().getPlayers().size(); i++ ){
            targetList = AdrenalineServer.getMainController().getActiveturn().getActivePlayer().getFigure().getCell().getPlayers() ;
        }

        AdrenalineServer.getMainController().getActiveturn().getActionManager().getShootManager().inflictDamage(targetList, damage);
    }
   
}
