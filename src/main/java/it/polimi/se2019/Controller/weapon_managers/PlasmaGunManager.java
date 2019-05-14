package it.polimi.se2019.controller.weapon_managers;

import it.polimi.se2019.AdrenalineServer;
import it.polimi.se2019.model.game.Player;

import java.util.ArrayList;

public class PlasmaGunManager {

    public static void action(){
        //choose
        baseEffect();
        secondaryEffect();
    }
    private static void baseEffect(){
        /** target: 1 (that you can see)
         *  damage: 2
         *
         */
        char damage[] = new char[2];
        damage [0] = AdrenalineServer.getMainController().getActiveturn().getActivePlayer().getFigure().getColor();
        damage [1] = AdrenalineServer.getMainController().getActiveturn().getActivePlayer().getFigure().getColor();
        //input da view del target
        //TODO è una toppa momentanea in attesa dell'input
        ArrayList<Player> targetList = new ArrayList<>();
        targetList.add(AdrenalineServer.getMainController().getActiveturn().getActivePlayer()) ;
        AdrenalineServer.getMainController().getActiveturn().getActionManager().getShootManager().inflictDamage(targetList, damage);
        // da implementare

        thirdEffect(targetList);
    }
    private static void secondaryEffect(){
        /** optional attack (could be used before o after basic attack)
         *  target: yourself
         *  move: 1 or 2
         */
        ArrayList<Player> targetList = new ArrayList<>();
        targetList.add(AdrenalineServer.getMainController().getActiveturn().getActivePlayer()) ;
        AdrenalineServer.getMainController().getActiveturn().getActionManager().getShootManager().moveOpponent(targetList);
        //controllo se vuoi muoverti di una o 2 da fare in move?
    }
    private static void thirdEffect(ArrayList<Player> target){
        /** optional attack
         *  target: 1 (same as basic attack)
         *  damage: 1
         *
         */
        char damage[] = new char[1];
        damage [0] = AdrenalineServer.getMainController().getActiveturn().getActivePlayer().getFigure().getColor();
        AdrenalineServer.getMainController().getActiveturn().getActionManager().getShootManager().inflictDamage(target, damage);
    }
}
