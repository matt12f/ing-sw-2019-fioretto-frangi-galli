package it.polimi.se2019.controller.weapon_managers;

public class PlasmaGunManager {

    public static void action(){
        baseEffect();
        secondaryEffect();
    }
    private static void baseEffect(){
        /** target: 1 (that you can see)
         *  damage: 2
         *
         */
    }
    private static void secondaryEffect(){
        /** optional attack (could be used before o after basic attack)
         *  target: yourself
         *  move: 1 or 2
         */
    }
    private static void thirdEffect(){
        /** optional attack
         *  target: 1 (same as basic attack)
         *  damage: 1
         *
         */
    }
}
