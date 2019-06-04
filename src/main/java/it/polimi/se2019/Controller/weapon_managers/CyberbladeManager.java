package it.polimi.se2019.controller.weapon_managers;


public class CyberbladeManager {

    public static void action(){
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
    }
    private static void secondaryEffect(){
        /** optional attack before or after any attack
         *  target: you
         *  move: 1
         *
         */
    }
    private static void thirdEffect(){
        /** optional attack
         *  target: 1 (another one, but in your cell)
         *  damage: 2
         *
         */
    }
}
