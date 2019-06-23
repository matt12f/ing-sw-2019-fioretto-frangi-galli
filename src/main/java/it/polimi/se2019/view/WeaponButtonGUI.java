package it.polimi.se2019.view;

import javax.swing.*;
import java.awt.*;

public class WeaponButtonGUI extends JButton {

    public WeaponButtonGUI(){
        ImageIcon wpImage1 = new ImageIcon(new ImageIcon("src/main/sprite/cards/weapons/weapons_back.png").getImage().getScaledInstance(100,163, Image.SCALE_DEFAULT));
        setIcon(wpImage1);
    }
}
