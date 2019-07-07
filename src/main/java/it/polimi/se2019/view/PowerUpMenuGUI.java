package it.polimi.se2019.view;

import it.polimi.se2019.model.cards.PowerupCard;

import javax.swing.*;
import java.awt.*;

public class PowerUpMenuGUI extends  JFrame {

    private ImageIcon img;

    /**
     * build a frame that show the power up image
     * @param pw powerup card with data
     */
    public PowerUpMenuGUI(PowerupCard pw){
        img = new ImageIcon(new ImageIcon(getClass().getResource("/sprite/cards/powerups/powerups_"+ pw.getPowerupType().toLowerCase() +"_"+ pw.getCubeColor() +".png")).getImage().getScaledInstance(169,264,Image.SCALE_SMOOTH));
        JLabel imgLabel = new JLabel(img);
        imgLabel.setIcon(img);

        addWindowListener(new WeaponMenuGUI.CloseListener());
        add(imgLabel);
        setSize(400,400);
        setLocation(0,0);
        setVisible(true);
    }

}
