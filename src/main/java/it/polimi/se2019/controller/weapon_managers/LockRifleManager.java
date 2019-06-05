package it.polimi.se2019.controller.weapon_managers;

public class LockRifleManager {

    public static void action(){
        baseEffect();
        secondaryEffect();
    }
    private static void baseEffect(){
        /** target: 1 (that you can see)
         *  damage: 1
         *  marker: 1
         */
    }
    private static void secondaryEffect(){
        /** optional attack
         *  target: 1 (different from the one of base effect)
         *
         *  marker: 1
         */
    }
}
