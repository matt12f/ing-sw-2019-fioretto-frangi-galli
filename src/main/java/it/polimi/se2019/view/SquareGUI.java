package it.polimi.se2019.view;

import javax.swing.*;
import java.awt.*;

public class SquareGUI extends JLabel {
    ImageIcon pic;

    public SquareGUI(String content, String dropContent){
        //this adds the player colored circles
        updateContent(content,dropContent);
    }

    public void updateContent(String content, String dropContent){
        if (!content.equals("DROP") && !content.equals("EMPTY"))
            this.pic = new ImageIcon(new ImageIcon(getClass().getResource("/sprite/figures/" + content.toLowerCase() + ".png")).getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT));
        else if(content.equals("DROP"))
            this.pic = new ImageIcon(new ImageIcon(getClass().getResource("/sprite/ammo/ammo_" + dropContent + ".png")).getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));
        else
            this.pic=null;
        setIcon(this.pic);
    }


}
