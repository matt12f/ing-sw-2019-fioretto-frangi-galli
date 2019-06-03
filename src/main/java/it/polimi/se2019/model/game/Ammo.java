package it.polimi.se2019.model.game;

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

    /**
     * allows the controller to verify that the ammo are enough to do actions
     *
     */
    public int getBlue() {
        return blue;
    }
    public int getRed() {
        return red;
    }
    public int getYellow() {
        return yellow;
    }

    public void addAmmo(String ammo){
        for(int i=0;i<ammo.length();i++){
            if(ammo.charAt(i)=='b')
                setBlue(1);
            else if(ammo.charAt(i)=='y')
                setYellow(1);
            else if(ammo.charAt(i)=='r')
                setRed(1);
        }
    }

    public void subtractAmmo(String ammo){
        for(int i=0;i<ammo.length();i++){
            if(ammo.charAt(i)=='b')
                setBlue(-1);
            else if(ammo.charAt(i)=='y')
                setYellow(-1);
            else if(ammo.charAt(i)=='r')
                setRed(-1);
        }
    }

    /**
     * these set methods add or subtract whether the value passed is positive or negative.
     * They do not prevent the value to become negative because it is prevented at the method calling.
     */
    private void setBlue(int blue) {
        this.blue += blue;
        if (this.blue > 3){
            this.blue = 3;
        }
    }
    private void setRed(int red) {
        this.red += red;
        if (this.red > 3){
            this.red = 3;
        }
    }
    private void setYellow(int yellow) {
        this.yellow += yellow;
        if (this.yellow > 3){
            this.yellow = 3;
        }
    }
}
