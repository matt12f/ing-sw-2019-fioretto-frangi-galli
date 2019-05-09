package it.polimi.se2019.controller.weapon_managers;

import it.polimi.se2019.Adrenaline;
import it.polimi.se2019.controller.Controller;
import it.polimi.se2019.model.game.Player;

import java.util.ArrayList;

public class GrenadeLauncherManager {

    public void action(){
        //determinare scelta
        baseEffect();
        secondaryEffect();
    }
    private void baseEffect(){
        /** target: 1 (that you can see, after damage move target)
         *  damage: 1
         *  move: 1
         */
        char damage[] = new char[1];
        damage [0] = Adrenaline.getMainController().getActiveturn().getActivePlayer().getFigure().getColor();
        ArrayList<Player> targetList = new ArrayList<>();
        //TODO è una toppa, in attesa di input
        targetList.add(Adrenaline.getMainController().getActiveturn().getActivePlayer()) ;
        Adrenaline.getMainController().getActiveturn().getActionManager().getShootManager().inflictDamage(targetList, damage);
        Adrenaline.getMainController().getActiveturn().getActionManager().getShootManager().moveOpponent(targetList);
    }
    private void secondaryEffect(){
        /** optional attack (before or after basic attack)
         *  target: cell (that you can see)
         *  damage: 1
         *
         */
        char damage[] = new char[1];
        damage [0] = Adrenaline.getMainController().getActiveturn().getActivePlayer().getFigure().getColor();
        ArrayList<Player> targetList = new ArrayList<>();
        //TODO è una toppa, in attesa di input
        targetList = Adrenaline.getMainController().getActiveturn().getActivePlayer().getFigure().getCell().getPlayers();
        Adrenaline.getMainController().getActiveturn().getActionManager().getShootManager().inflictDamage(targetList, damage);
    }
   
}
