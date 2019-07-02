package it.polimi.se2019.view;

import it.polimi.se2019.model.cards.GunCard;

import javax.swing.*;
import java.awt.*;

public class WeaponButtonGUI extends JButton {
    private String weaponType;
    private int sizex;
    private int sizey;
    private ImageIcon pic;

    public WeaponButtonGUI(int x, int y){
        sizex=x;
        sizey=y;
        setSize(sizex,sizey);

        pic = new ImageIcon(new ImageIcon("src/main/sprite/cards/weapons/weapons_back.png").getImage().getScaledInstance(x,y, Image.SCALE_DEFAULT));
        setIcon(pic);
    }

    public void updateImage(GunCard weapon){
         this.weaponType = weapon.getClass().getSimpleName().toLowerCase();
         if (weaponType != null){
             pic = new ImageIcon(new ImageIcon("src/main/sprite/cards/weapons/weapons_"+ weaponType +".png").getImage().getScaledInstance(sizex,sizey, Image.SCALE_DEFAULT));
         }else{
             pic = new ImageIcon(new ImageIcon("src/main/sprite/cards/weapons/weapons_back.png").getImage().getScaledInstance(sizex,sizey, Image.SCALE_DEFAULT));
         }
        setIcon(pic);

    }

    public String getWeaponType(){
        return weaponType;
    }
}
