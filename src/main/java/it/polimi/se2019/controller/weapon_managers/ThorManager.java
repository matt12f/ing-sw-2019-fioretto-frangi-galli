package it.polimi.se2019.controller.weapon_managers;


public class ThorManager {

    public static void action(){
        baseEffect();


    }
    private static void baseEffect(){
        /** target: 1 (that you can see)
         *  damage: 2
         *
         */
     }
    private static void secondaryEffect(){
        /** optional effect
         * target: 1 (a target that the prievious target can see)
         *  damage: 1
         *
         */
    }
    private static void thirdEffect(){
        /** optional effect, it can be used ONLY if you use the secondary effect
         * target: 1 (a target that your SECOND target can see)
         *  damage: 2
         *
         */
     }
}
