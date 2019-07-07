package it.polimi.se2019.view;

import it.polimi.se2019.model.game.Ammo;

import java.io.Serializable;

public class AmmoView  implements Serializable {
    private int red;
    private int blue;
    private int yellow;

    /**
     * create the ammo view to store ammo data
     * @param ammo model data of the player's ammo
     */
    public AmmoView(Ammo ammo){
        this.blue = ammo.getBlue();
        this.red = ammo.getRed();
        this.yellow = ammo.getYellow();
    }

    /**
     * set red ammo
     * @param red
     */
    public void setRED(int red) {
        this.red = red;
    }

    /**
     *
     * @return red ammo
     */
    public int getRED() {
        return red;
    }

    /**
     * set blue ammo
     * @param blue
     */
    public void setBLUE(int blue) {
        this.blue = blue;
    }

    /**
     *
     * @return blue ammo
     */
    public int getBLUE() {
        return blue;
    }

    /**
     * set yellow ammo
     * @param yellow
     */
    public void setYELLOW(int yellow) {
        this.yellow = yellow;
    }

    /**
     *
     * @return yellow ammo
     */
    public int getYELLOW() {
        return yellow;
    }
}

