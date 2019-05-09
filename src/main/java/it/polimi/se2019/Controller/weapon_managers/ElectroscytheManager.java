package it.polimi.se2019.controller.weapon_managers;

import it.polimi.se2019.Adrenaline;
import it.polimi.se2019.controller.Controller;
import it.polimi.se2019.model.game.Player;

import java.util.ArrayList;

public class ElectroscytheManager {

    public void action(){
        //determinare scelta
        baseEffect();
        secondaryEffect();
    }
    private void baseEffect(){
        /** target: every target in your square/cell
         *  damage: 1
         *
         */
        char damage[] = new char[1];
        damage [0] = Adrenaline.getMainController().getActiveturn().getActivePlayer().getFigure().getColor();
        //TODO check, togliere il player
        ArrayList<Player> targetList = new ArrayList<>();
        targetList = Adrenaline.getMainController().getActiveturn().getActivePlayer().getFigure().getCell().getPlayers();
        Adrenaline.getMainController().getActiveturn().getActionManager().getShootManager().inflictDamage(targetList, damage);
    }
    private void secondaryEffect(){
        /** alternative attack
         * target: every target in your cell
         *  damage: 2
         *
         */
        char damage[] = new char[2];
        damage [0] = Adrenaline.getMainController().getActiveturn().getActivePlayer().getFigure().getColor();
        damage [1] = Adrenaline.getMainController().getActiveturn().getActivePlayer().getFigure().getColor();
        //TODO check
        ArrayList<Player> targetList = new ArrayList<>();
        for (int i=0; i < Adrenaline.getMainController().getActiveturn().getActivePlayer().getFigure().getCell().getPlayers().size(); i++ ){
            targetList = Adrenaline.getMainController().getActiveturn().getActivePlayer().getFigure().getCell().getPlayers() ;
        }

        Adrenaline.getMainController().getActiveturn().getActionManager().getShootManager().inflictDamage(targetList, damage);
    }
   
}
