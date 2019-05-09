package it.polimi.se2019.controller.weapon_managers;

import it.polimi.se2019.Adrenaline;
import it.polimi.se2019.controller.Controller;
import it.polimi.se2019.model.game.Player;

import java.util.ArrayList;

public class MachinegunManager {

    public void action(){
        char damage[] = new char[1];
        damage [0] = Adrenaline.getMainController().getActiveturn().getActivePlayer().getFigure().getColor();
        //choose
        baseEffect(damage);
        secondaryEffect(damage);
        thirdEffect(damage);
    }
    private void baseEffect(char damage[]){
        /** target: 1 or 2 (thet you can see)
         *  damage: 1
         *
         */
        //input da view del target
        //TODO è una toppa momentanea in attesa dell'input
        ArrayList<Player> targetList = new ArrayList<>();
        targetList.add(Adrenaline.getMainController().getActiveturn().getActivePlayer()) ;
        Adrenaline.getMainController().getActiveturn().getActionManager().getShootManager().inflictDamage(targetList, damage);
    }
    private void secondaryEffect(char damage[]){
        /** optional attack
         * target: 1 (choose one of the target of base effect)
         *  damage: 1
         */
        //input da view del target
        //TODO è una toppa momentanea in attesa dell'input
        ArrayList<Player> targetList = new ArrayList<>();
        targetList.add(Adrenaline.getMainController().getActiveturn().getActivePlayer()) ;
        Adrenaline.getMainController().getActiveturn().getActionManager().getShootManager().inflictDamage(targetList, damage);

    }
    private void thirdEffect(char damage[]){
        /** target: 1 or 2 (one of the previous one, another that you can see BUT not one of the previous one)
         *  damage: 1
         *
         */
        //input da view del target, utilizzare come input i player del metodo precedente
        //TODO è una toppa momentanea in attesa dell'input
        ArrayList<Player> targetList = new ArrayList<>();
        targetList.add(Adrenaline.getMainController().getActiveturn().getActivePlayer()) ;
        Adrenaline.getMainController().getActiveturn().getActionManager().getShootManager().inflictDamage(targetList, damage);
    }
}
