package it.polimi.se2019.view;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class CellViewGUI extends JButton {

    private ImageIcon icona;
    public CellViewGUI(){
        this.setContentAreaFilled(false);
        //this.setBorder(new LineBorder(Color.RED));
        setSize(100,90);
        this.icona = new ImageIcon(new ImageIcon("src/main/sprite/maps/cell.png").getImage().getScaledInstance(100,90,Image.SCALE_DEFAULT));
        this.setIcon(icona);


    }

}
