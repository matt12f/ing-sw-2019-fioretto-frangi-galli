package it.polimi.se2019.view;

import javax.swing.*;
import java.awt.*;

public class PowerupButtonGUI extends JButton {

    public PowerupButtonGUI(){
        ImageIcon pwImage = new ImageIcon(new ImageIcon("src/main/sprite/cards/powerups/powerups_back.png").getImage().getScaledInstance(70,92, Image.SCALE_DEFAULT));

        setIcon(pwImage);
    }
}
