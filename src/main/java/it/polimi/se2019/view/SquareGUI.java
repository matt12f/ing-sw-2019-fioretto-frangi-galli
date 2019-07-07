package it.polimi.se2019.view;

import javax.swing.*;
import java.awt.*;

public class SquareGUI extends JLabel {
    ImageIcon pic;

    /**
     * create a square of the cell and put a figure depending on the players' color that arw staying into that cell
     * @param content indicate if the square is a drop, is a figure or is empty
     * @param dropContent data with the content of the drop (if the cell is a drop cell)
     */
    public SquareGUI(String content, String dropContent){
        //this adds the player colored circles
        updateContent(content,dropContent);
    }

    /**
     * update the content of the square
     * @param content indicate if the square is a drop, is a figure or is empty
     * @param dropContent data with the content of the drop (if the cell is a drop cell)
     */
    public void updateContent(String content, String dropContent){
        if (!content.equals("DROP") && !content.equals("EMPTY"))
            this.pic = new ImageIcon(new ImageIcon(getClass().getResource("/sprite/figures/" + content.toLowerCase() + ".png")).getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH));
        else if(content.equals("DROP"))
            this.pic = new ImageIcon(new ImageIcon(getClass().getResource("/sprite/ammo/ammo_" + dropContent + ".png")).getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));
        else
            this.pic=null;

        setIcon(this.pic);
    }


}
