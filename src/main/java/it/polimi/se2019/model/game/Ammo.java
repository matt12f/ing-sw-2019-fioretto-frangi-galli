package it.polimi.se2019.model.game;

import java.io.Serializable;

public class Ammo  implements Serializable {
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

    /**
     * add a number and a type of ammo depending on the string content
     * @param ammo is the number and type of ammo to add
     * @return
     */
    public boolean addAmmo(String ammo){
        boolean isTherePwUp=false;
        for(int i=0;i<ammo.length();i++){
            if(ammo.charAt(i)=='b')
                setBlue(1);
            else if(ammo.charAt(i)=='y')
                setYellow(1);
            else if(ammo.charAt(i)=='r')
                setRed(1);
            else if(ammo.charAt(i)=='p')
                isTherePwUp=true;
        }
        return isTherePwUp;
    }

    /**
     * this method deducts one ammo from the player, only if it has the ammo to pay for it
     * otherwise it will return false and the rest of the cost will be paid with powerups
     * @param ammo is the single ammo to pay
     * @return true if the ammo has been paid correctly, false otherwise
     */
    public boolean payOneAmmo(char ammo){
        if(ammo=='b' && this.blue>0)
            setBlue(-1);
        else if(ammo=='y' && this.yellow>0)
            setYellow(-1);
        else if(ammo=='r' && this.red>0)
            setRed(-1);
        else
            return false;

        return true;
    }

    /**
     * these set methods add or subtract whether the value passed is positive or negative.
     * They do not prevent the value to become negative because it is prevented at the method calling.
     */
    public void setBlue(int blue) {
        this.blue = this.blue+ blue;
        if (this.blue > 3){
            this.blue = 3;
        }
    }
    public void setRed(int red) {
        this.red = this.red + red;
        if (this.red > 3){
            this.red = 3;
        }
    }
    public void setYellow(int yellow) {
        this.yellow = this.yellow + yellow;
        if (this.yellow > 3){
            this.yellow = 3;
        }
    }

    @Override
    public Ammo clone(){
        Ammo ammo=new Ammo();
        ammo.blue=this.blue;
        ammo.red=this.red;
        ammo.yellow=this.yellow;
        return ammo;
    }
}
