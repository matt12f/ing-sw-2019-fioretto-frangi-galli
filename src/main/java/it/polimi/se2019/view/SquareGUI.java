package it.polimi.se2019.view;

import it.polimi.se2019.model.cards.AmmoTileCard;
import it.polimi.se2019.model.game.Figure;

import javax.swing.*;
import java.awt.*;
import it.polimi.se2019.enums.Color;

public class SquareGUI extends JLabel {

    private String color;



    public SquareGUI(Color color){

        updateImage(color);
        setSize(20,20);
    }

    public void updateImage(Color type){
        if(type==null)
            setIcon(null);
        else {
            switch (type){
                case WHITE: color = "white";break;
                case BLUE:color = "blue";break;
                case GREEN:color = "green";break;
                case VIOLET:color = "purple";break;
                case YELLOW:color = "yellow";break;
                case BLACK: color = "black";break;
                case RED : color = "null"; break;
                default: break;
        }

        if (type != Color.RED){
            ImageIcon pic = new ImageIcon(new ImageIcon("src/main/sprite/cards/weapons/"+ color +".png").getImage().getScaledInstance(20,20, Image.SCALE_DEFAULT));
            setIcon(pic);
        }
        }
    }


}
