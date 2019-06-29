package it.polimi.se2019.view;

import javax.swing.*;
import java.awt.*;

public class SquareGUI extends JLabel {

    public SquareGUI(){
        //TODO appena capiamo come erediare al posto di type mettere figure.color se != null, altrimenti se drop !=null mettere nero
        updateImage(null);
        setSize(20,20);
    }

    public void updateImage(String type){
        if (type != null){
            ImageIcon pic = new ImageIcon(new ImageIcon("src/main/sprite/cards/weapons/"+ type+".png").getImage().getScaledInstance(20,20, Image.SCALE_DEFAULT));

            setIcon(pic);
        }else{
            setIcon(null);
        }

    }

}
