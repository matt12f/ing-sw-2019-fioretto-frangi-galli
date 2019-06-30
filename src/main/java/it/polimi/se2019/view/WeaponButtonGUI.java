package it.polimi.se2019.view;

import it.polimi.se2019.model.cards.GunCard;

import javax.swing.*;
import java.awt.*;

public class WeaponButtonGUI extends JButton {

    public WeaponButtonGUI(int x, int y){
        setSize(x,y);

        ImageIcon wpImage1 = new ImageIcon(new ImageIcon("src/main/sprite/cards/weapons/weapons_back.png").getImage().getScaledInstance(x,y, Image.SCALE_DEFAULT));
        setIcon(wpImage1);
    }

    public void updateImage(GunCard weapon){
        String weaponType = weapon.toString();

        switch (weaponType){
            case "":
        }

    }
}
