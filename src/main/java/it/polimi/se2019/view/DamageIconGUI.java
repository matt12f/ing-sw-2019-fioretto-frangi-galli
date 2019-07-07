package it.polimi.se2019.view;

import javax.swing.*;
import java.awt.*;

public class DamageIconGUI extends JLabel {
    private ImageIcon damageImage;

    /**
     * builder that create and set the right image for the damage
     * @param color color of the player that inclict the damage
     */
    public DamageIconGUI (char color){
        setDamageImage(color);
        setSize(16,27);

    }

    /**
     * set the right image for the damage
     * @param color color of the player that inclict the damage
     */
    public void setDamageImage(char color){
        String colorString=PlayerBoardViewGUI.colorSwitch(color);

        if (colorString != null)
            this.damageImage = new ImageIcon(new ImageIcon(getClass().getResource("/sprite/damage/"+ colorString +".png")).getImage().getScaledInstance(16,27, Image.SCALE_SMOOTH));
        else
            this.damageImage = null;

        setIcon(damageImage);
    }

}
