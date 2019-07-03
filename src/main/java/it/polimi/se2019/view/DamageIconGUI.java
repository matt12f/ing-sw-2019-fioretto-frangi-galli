package it.polimi.se2019.view;

import javax.swing.*;
import java.awt.*;

public class DamageIconGUI extends JLabel {
    private ImageIcon damageImage;
    public DamageIconGUI (char color){
        setSize(16,27);


    }

    public void setDamageImage(char color){
        String colorString = null;
        switch(color){
            case 'y': colorString = "yellow";
            break;
            case 'b': colorString = "blue";
                break;
            case 'w': colorString = "white";
                break;
            case 'g': colorString = "green";
                break;
            case 'v': colorString = "purple";
                break;
                default: colorString = null;
                break;
        }


        if (colorString != null){
            this.damageImage = new ImageIcon(new ImageIcon("src/main/sprite/damage/"+ color +".png").getImage().getScaledInstance(16,27, Image.SCALE_DEFAULT));
        }else{
            this.damageImage = null;
        }
        setIcon(damageImage);
    }

}
