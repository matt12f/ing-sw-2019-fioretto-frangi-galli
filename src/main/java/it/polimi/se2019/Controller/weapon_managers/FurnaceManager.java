package it.polimi.se2019.controller.weapon_managers;

import it.polimi.se2019.Adrenaline;
import it.polimi.se2019.controller.Controller;
import it.polimi.se2019.model.game.Player;

import java.util.ArrayList;

public class FurnaceManager {

    public void action(){
        //determinare scelta
        baseEffect();
        secondaryEffect();
    }
    private void baseEffect(){
        /** target: room (choose a room you can see, but not yours, inflict damage to all pg inside)
         *  damage: 1
         *
         */
        char damage[] = new char[1];
        damage [0] = Adrenaline.getMainController().getActiveturn().getActivePlayer().getFigure().getColor();
        ArrayList<Player> targetList = new ArrayList<>();
        //TODO è una toppa, in attesa di input
        targetList.add(Adrenaline.getMainController().getActiveturn().getActivePlayer()) ;
        Adrenaline.getMainController().getActiveturn().getActionManager().getShootManager().inflictDamage(targetList, damage);
    }
    private void secondaryEffect(){
        /** alternative attack
         *  target: cell (choose a square 1 move from you)
         *  damage: 1
         *  marker: 1
         */
        char damage[] = new char[1];
        damage [0] = Adrenaline.getMainController().getActiveturn().getActivePlayer().getFigure().getColor();
        ArrayList<Player> targetList = new ArrayList<>();
        //TODO è una toppa, in attesa di input
        targetList.add(Adrenaline.getMainController().getActiveturn().getActivePlayer()) ;
        Adrenaline.getMainController().getActiveturn().getActionManager().getShootManager().inflictDamage(targetList, damage);
        Adrenaline.getMainController().getActiveturn().getActionManager().getShootManager().appointMarker(targetList,damage);
    }
   
}
