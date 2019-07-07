package it.polimi.se2019.view;

import it.polimi.se2019.model.game.Ammo;

import java.io.Serializable;

public class AmmoView  implements Serializable {
    private int red;
    private int blue;
    private int yellow;

    public AmmoView(Ammo ammo){
        this.blue = ammo.getBlue();
        this.red = ammo.getRed();
        this.yellow = ammo.getYellow();
    }

    public void setRED(int red) {
        this.red = red;
    }

    public int getRED() {
        return red;
    }

    public void setBLUE(int blue) {
        this.blue = blue;
    }

    public int getBLUE() {
        return blue;
    }

    public void setYELLOW(int yellow) {
        this.yellow = yellow;
    }

    public int getYELLOW() {
        return yellow;
    }
}

