package it.polimi.se2019.view;

import javax.swing.*;
import java.awt.*;

public class SquareGUI extends JLabel {

    private String color;

    public SquareGUI(String content, String dropContent){
        switch (content) {
            case "WHITE": color = "white";break;
            case "BLUE": color = "blue";break;
            case "GREEN": color = "green";break;
            case "VIOLET": color = "purple";break;
            case "YELLOW": color = "yellow";break;
            case "DROP": color = "DROP";break;
            case "EMPTY": color ="EMPTY";break;
            default: break;
        }

        ImageIcon pic;
        //this adds the player colored circles
        if (!color.equals("DROP") && !color.equals("EMPTY"))
            pic = new ImageIcon(new ImageIcon("src/main/sprite/figures/" + color + ".png").getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT));
        else if(color.equals("DROP"))
            pic = new ImageIcon(new ImageIcon("src/main/sprite/ammo/ammo_" + dropContent + ".png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));
        else
            pic=null;

        setIcon(pic);
    }





}
