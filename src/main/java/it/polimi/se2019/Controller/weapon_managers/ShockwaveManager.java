package it.polimi.se2019.controller.weapon_managers;

import it.polimi.se2019.Adrenaline;
import it.polimi.se2019.controller.Controller;
import it.polimi.se2019.model.game.Player;

import java.util.ArrayList;

public class ShockwaveManager {

    public void action(){
        //choose
        baseEffect();
        secondaryEffect();
    }
    private void baseEffect(){
        /** target: 3 (1 move away, every target in a different cell)
         *  damage: 1
         *
         */
        char damage[] = new char[1];
        damage [0] = Adrenaline.getMainController().getActiveturn().getActivePlayer().getFigure().getColor();

        //input da view del target
        //TODO è una toppa momentanea in attesa dell'input
        ArrayList<Player> targetList = new ArrayList<>();
        targetList.add(Adrenaline.getMainController().getActiveturn().getActivePlayer()) ;
        Adrenaline.getMainController().getActiveturn().getActionManager().getShootManager().inflictDamage(targetList, damage);
    }
    private void secondaryEffect(){
        /** alternative attack
         * target: cell (every cell 1 move away)
         *  damage: 1
         *
         */
        char damage[] = new char[1];
        damage [0] = Adrenaline.getMainController().getActiveturn().getActivePlayer().getFigure().getColor();

        //input da view del target
        //TODO è una toppa momentanea in attesa dell'input
        ArrayList<Player> targetList = new ArrayList<>();
        targetList.add(Adrenaline.getMainController().getActiveturn().getActivePlayer()) ;
        Adrenaline.getMainController().getActiveturn().getActionManager().getShootManager().inflictDamage(targetList, damage);
    }
    
}
