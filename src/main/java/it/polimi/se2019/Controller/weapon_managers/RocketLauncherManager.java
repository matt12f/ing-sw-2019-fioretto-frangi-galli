package it.polimi.se2019.controller.weapon_managers;

import it.polimi.se2019.controller.Controller;
import it.polimi.se2019.model.game.Player;

import java.util.ArrayList;

public class RocketLauncherManager {

    public void action(){
        //choose
        baseEffect();
        secondaryEffect();

    }
    private void baseEffect(){
        /** target: 1 (that you can see but NOT in your square)
         *  damage: 2
         *  move: 1 (if you want)
         */
        char damage[] = new char[2];
        damage [0] = Controller.getActiveturn().getActivePlayer().getFigure().getColor();
        damage [1] = Controller.getActiveturn().getActivePlayer().getFigure().getColor();
        //input da view del target
        //TODO è una toppa momentanea in attesa dell'input
        ArrayList<Player> targetList = new ArrayList<>();
        targetList.add(Controller.getActiveturn().getActivePlayer()) ;
        Controller.getActiveturn().getActionManager().getShootManager().inflictDamage(targetList, damage);
        targetList.remove(0);
        targetList.add(Controller.getActiveturn().getActivePlayer()) ;
        Controller.getActiveturn().getActionManager().getShootManager().moveOpponent(targetList);

        //controllo
        thirdEffect(targetList);
    }
    private void secondaryEffect(){
        /** optional attack , after o before basic attack
         * target: yourself
         *
         *  move: 1,2
         */
        //controllo mobilità
        ArrayList<Player> targetList = new ArrayList<>();
        targetList.add(Controller.getActiveturn().getActivePlayer()) ;
        Controller.getActiveturn().getActionManager().getShootManager().moveOpponent(targetList);
    }
    private void thirdEffect(ArrayList<Player> targetFirst){
        /** target: cell (in the cell of target, including the target)
         *  damage: 1
         *
         */
        char damage[] = new char[1];
        damage [0] = Controller.getActiveturn().getActivePlayer().getFigure().getColor();
        Player target = targetFirst.get(0);
        ArrayList<Player> targetList = new ArrayList<>();
        targetList = Controller.getActiveturn().getActivePlayer().getFigure().getCell().getPlayers();
        Controller.getActiveturn().getActionManager().getShootManager().inflictDamage(targetList, damage);

    }
}
