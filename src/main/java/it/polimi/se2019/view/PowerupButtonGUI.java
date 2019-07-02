package it.polimi.se2019.view;

import it.polimi.se2019.model.cards.PowerupCard;

import javax.swing.*;
import java.awt.*;

public class PowerupButtonGUI extends JButton {
    private ImageIcon pwImage;
    public PowerupButtonGUI(){

        updateImage(null);

    }
    public void updateImage(PowerupCard type){
        if (type!= null){
            pwImage= new ImageIcon(new ImageIcon("src/main/sprite/cards/powerups/powerups_"+ type.getPowerupType() + "_"+type.getCubeColor()+ ".png").getImage().getScaledInstance(70,92,Image.SCALE_DEFAULT));
        }else{
            pwImage = new ImageIcon(new ImageIcon("src/main/sprite/cards/powerups/powerups_back.png").getImage().getScaledInstance(70,92, Image.SCALE_DEFAULT));
        }

        setIcon(pwImage);
    }
}
