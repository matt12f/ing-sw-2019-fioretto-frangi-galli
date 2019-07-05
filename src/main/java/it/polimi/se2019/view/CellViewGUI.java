package it.polimi.se2019.view;

import it.polimi.se2019.enums.Color;
import it.polimi.se2019.model.game.Figure;
import it.polimi.se2019.model.game.NewCell;

import javax.swing.*;
import java.awt.*;

public class CellViewGUI extends JPanel {
    private SquareGUI[][] matrixGUI;
    private String type;


    public CellViewGUI(CellView cell) {

        this.matrixGUI = new SquareGUI[3][3];

        ////creazione dello square in base al contenuto della cella/////
        int z = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (z < cell.getPlayerFigures().size()) {
                    matrixGUI[j][i] = new SquareGUI(cell.getPlayerFigures().get(z).getColor());
                    z += 1;
                }  if (i ==1 && j== 2 && cell.getDrop() != null ) {
                    matrixGUI[j][i] = new SquareGUI(Color.BLACK);

                }else {
                    matrixGUI[j][i] = new SquareGUI(Color.RED);
                }
            }

        }



        int cordx = 0, cordy = 0;
        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 3; column++) {
                matrixGUI[row][column].setLocation(cordx, cordy);

                add(matrixGUI[row][column]);


                cordx += 30;

            }
            cordx = 0;
            cordy += 30;

        }

        setOpaque(false);


        setSize(100, 90);

    }


    public void updateCell(CellView cell) {
        removeAll();

        int z = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (z < cell.getPlayerFigures().size()) {
                    matrixGUI[j][i] = new SquareGUI(cell.getPlayerFigures().get(z).getColor());
                    z += 1;
                } else if (z == cell.getPlayerFigures().size() ) {

                    matrixGUI[j][i] = new SquareGUI(Color.BLACK);
                }else {
                    matrixGUI[j][i] = new SquareGUI(Color.RED);
                }
            }

        }

        ///add on gui interface
        int x = 0, y = 0;
        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 3; column++) {
                matrixGUI[row][column].setLocation(x, y);

                add(matrixGUI[row][column]);


                x += 30;
            }
            x = 0;
            y += 30;
        }
    }

    public String getType() {

        return type;
    }

    public void setType(String typex) {
        this.type = typex;
    }
}




