package it.polimi.se2019.view;

import javax.swing.*;
import java.awt.*;

public class CellViewGUI extends JPanel {
    private SquareGUI[][] matrixGUI;

    public CellViewGUI(){

        this.matrixGUI = new SquareGUI[3][3];
        setOpaque(false);
        int x= 0,y = 0;
        for(int column = 0; column <= 3; column++){
            for (int row =0 ; row <= 3 ; row++){
                matrixGUI[row][column].setLocation(x,y);

                add(matrixGUI[row][column]);
                x+=30;
            }
            y+= 30;
        }



        setSize(100,90);





    }

}
