package it.polimi.se2019.view;

import java.io.Serializable;

public class AmmoView  implements Serializable {
    private int red;
    private int blue;
    private int yellow;

    public AmmoView(){
        this.blue = 1;
        this.red = 1;
        this.yellow = 1;
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

