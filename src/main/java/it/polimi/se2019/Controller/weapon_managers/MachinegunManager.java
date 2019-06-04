package it.polimi.se2019.controller.weapon_managers;

public class MachinegunManager {

    public static void action(){
         baseEffect();
        secondaryEffect();
        thirdEffect();
    }
    private static void baseEffect(){
        /** target: 1 or 2 (that you can see)
         *  damage: 1
         *
         */
    }
    private static void secondaryEffect(){
        /** optional attack
         * target: 1 (choose one of the target of base effect)
         *  damage: 1
         */
    }
    private static void thirdEffect(){
        /** target: 1 or 2 (one of the previous one, another that you can see BUT not one of the previous one)
         *  damage: 1
         *
         */
       }
}
