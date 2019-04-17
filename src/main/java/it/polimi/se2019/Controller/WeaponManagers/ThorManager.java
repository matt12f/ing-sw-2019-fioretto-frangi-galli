package it.polimi.se2019.controller.weaponmanagers;

public class ThorManager {

    public void action(){

    }
    private void baseEffect(){
        /** target: 1 (thet you can see)
         *  damage: 2
         *
         */
    }
    private void secondaryEffect(){
        /** optional effect
         * target: 1 (a target that the prievious target can see)
         *  damage: 1
         *
         */
    }
    private void thirdEffect(){
        /** optional effect, it can be used ONLY if you use the secondary effect
         * target: 1 (a target that your SECOND target can see)
         *  damage: 2
         *
         */
    }
}
