package it.polimi.se2019.game;

public class Ammo {
    private int blue;
    private int red;
    private int yellow;

    /**
     * The default amount for Ammos on the first turn is one of each color
     */
    public Ammo(){
        this.blue=1;
        this.red=1;
        this.yellow=1;

    }

    public int getBlue() {
        return blue;
    }
    public int getRed() {
        return red;
    }
    public int getYellow() {
        return yellow;
    }

    /**
     * these set methods could be re-engineered as "adding" or "subtract" methods rather than set. Considering that
     * we'll only be adding/removing cubes from the tile's Ammo space.
     */
    public void setBlue(int blue) {

    }
    public void setRed(int red) {

    }
    public void setYellow(int yellow) {

    }
}
