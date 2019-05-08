package it.polimi.se2019.controller.weapon_managers;

import it.polimi.se2019.controller.Controller;
import it.polimi.se2019.model.game.Player;

import java.util.ArrayList;

public class RailgunManager {

    public void action(){
        //choose
        baseEffect();
        secondaryEffect();
    }
    private void baseEffect(){
        /** target: 1 (in a cardinal direction) i could be in your square, ignore walls
         *  damage: 3
         *
         */
        char damage[] = new char[3];
        damage [0] = Controller.getActiveturn().getActivePlayer().getFigure().getColor();
        damage [1] = Controller.getActiveturn().getActivePlayer().getFigure().getColor();
        damage [2] = Controller.getActiveturn().getActivePlayer().getFigure().getColor();
        //input da view del target
        //TODO è una toppa momentanea in attesa dell'input
        ArrayList<Player> targetList = new ArrayList<>();
        targetList.add(Controller.getActiveturn().getActivePlayer()) ;
        Controller.getActiveturn().getActionManager().getShootManager().inflictDamage(targetList, damage);
    }
    private void secondaryEffect(){
        /** target: 1,2 (in a cardinal direction, they could be in different cells)same as before
         *  damage: 2
         *
         */
        char damage[] = new char[2];
        damage [0] = Controller.getActiveturn().getActivePlayer().getFigure().getColor();
        damage [1] = Controller.getActiveturn().getActivePlayer().getFigure().getColor();
        //input da view del target
        //TODO è una toppa momentanea in attesa dell'input
        ArrayList<Player> targetList = new ArrayList<>();
        targetList.add(Controller.getActiveturn().getActivePlayer()) ;
        Controller.getActiveturn().getActionManager().getShootManager().inflictDamage(targetList, damage);
    }

}
