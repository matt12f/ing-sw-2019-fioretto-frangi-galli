package it.polimi.se2019.view;

import javax.swing.*;
import java.awt.*;

public class DamageIconGUI extends JLabel {
    private ImageIcon damageImage;
    public DamageIconGUI (){
        setSize(16,27);
        this.damageImage = new ImageIcon(new ImageIcon("src/main/sprite/boards/normal/damage.jpg").getImage().getScaledInstance(16,27, Image.SCALE_DEFAULT));
        setIcon(damageImage);
    }

}
