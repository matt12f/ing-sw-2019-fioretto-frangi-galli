package it.polimi.se2019.controller.weapon_managers;


public class RocketLauncherManager {

    public static void action(){
        baseEffect();
        secondaryEffect();

    }
    private static void baseEffect(){
        /** target: 1 (that you can see but NOT in your square)
         *  damage: 2
         *  move: 1 (if you want)
         */
     }
    private static void secondaryEffect(){
        /** optional attack , after o before basic attack
         * target: yourself
         *
         *  move: 1,2
         */
    }
    private static void thirdEffect(){
        /** target: cell (in the cell of target, including the target)
         *  damage: 1
         *
         */
    }
}
