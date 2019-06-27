package it.polimi.se2019.view;

import javax.swing.*;
import java.awt.*;

public class WeaponButtonGUI extends JButton {

    public WeaponButtonGUI(int x, int y){
        setSize(x,y);

        ImageIcon wpImage1 = new ImageIcon(new ImageIcon("src/main/sprite/cards/weapons/weapons_back.png").getImage().getScaledInstance(x,y, Image.SCALE_DEFAULT));
        setIcon(wpImage1);
    }
}
