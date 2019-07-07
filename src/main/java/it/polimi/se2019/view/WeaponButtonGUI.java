package it.polimi.se2019.view;

import it.polimi.se2019.model.cards.GunCard;

import javax.swing.*;
import java.awt.*;

public class WeaponButtonGUI extends JButton {
    private String weaponType;
    private int sizex;
    private int sizey;
    private ImageIcon pic;

    /**
     * create a button  with the image of the back of a weapon card
     * @param x button width
     * @param y button height
     * @param rotate indicate if the button is vertical or not
     */
    public WeaponButtonGUI(int x, int y, boolean rotate){
        sizex=x;
        sizey=y;
        setSize(sizex,sizey);
        if (!rotate){
            pic = new ImageIcon(new ImageIcon(getClass().getResource("/sprite/cards/weapons/weapons_back.png")).getImage().getScaledInstance(x,y, Image.SCALE_SMOOTH));
        }else{
            pic = new ImageIcon(new ImageIcon(getClass().getResource("/sprite/cards/weapons_rotate/back.png")).getImage().getScaledInstance(x,y, Image.SCALE_SMOOTH));
        }

        setIcon(pic);
    }

    /**
     * update the image of the button
     * @param weapon indicate the weapon type
     * @param rotate indicate if the button is vertical or not
     */
    public void updateImage(GunCard weapon, boolean rotate ){
         try{
             this.weaponType = weapon.getClass().getSimpleName().toLowerCase();
         }catch (NullPointerException e){
             this.weaponType="error";
         }

         if (!weaponType.equals("error")){
             if (!rotate){
                 pic = new ImageIcon(new ImageIcon(getClass().getResource("/sprite/cards/weapons/weapons_"+ weaponType +".png")).getImage().getScaledInstance(sizex,sizey, Image.SCALE_SMOOTH));
             }else{
                 pic = new ImageIcon(new ImageIcon(getClass().getResource("/sprite/cards/weapons_rotate/"+ weaponType +".png")).getImage().getScaledInstance(sizex,sizey, Image.SCALE_SMOOTH));
             }

         }else{
             if (!rotate){
                 pic = new ImageIcon(new ImageIcon(getClass().getResource("/sprite/cards/weapons/weapons_back.png")).getImage().getScaledInstance(sizex,sizey, Image.SCALE_SMOOTH));
             }else{
                 pic = new ImageIcon(new ImageIcon(getClass().getResource("/sprite/cards/weapons_rotate/back.png")).getImage().getScaledInstance(sizex,sizey, Image.SCALE_SMOOTH));
             }
         }
        setIcon(pic);

    }

    /**
     *
     * @return the weapon type
     */
    public String getWeaponType(){
        return weaponType;
    }
}
