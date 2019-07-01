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

        switch (type){
            case GREY: color = "grey";
            case BLUE:color = "blue";
            case GREEN:color = "green";
            case VIOLET:color = "purple";
            case YELLOW:color = "yellow";
            case BLACK: color = "black";
            default: break;
        }

        if (type != null){
            ImageIcon pic = new ImageIcon(new ImageIcon("src/main/sprite/cards/weapons/"+ color +".png").getImage().getScaledInstance(20,20, Image.SCALE_DEFAULT));

            setIcon(pic);
        }else{
            setIcon(null);
        }

    }


}
