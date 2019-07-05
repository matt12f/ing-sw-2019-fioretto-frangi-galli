package it.polimi.se2019.view;

import javax.swing.*;
import java.awt.*;

public class SquareGUI extends JLabel {

    public SquareGUI(String content, String dropContent){

        ImageIcon pic;
        //this adds the player colored circles
        if (!content.equals("DROP") && !content.equals("EMPTY"))
            pic = new ImageIcon(new ImageIcon("src/main/sprite/figures/" + content.toLowerCase() + ".png").getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT));
        else if(content.equals("DROP"))
            pic = new ImageIcon(new ImageIcon("src/main/sprite/ammo/ammo_" + dropContent + ".png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));
        else
            pic=null;

        setIcon(pic);
    }





}
