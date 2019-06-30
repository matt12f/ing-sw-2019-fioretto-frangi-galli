package it.polimi.se2019.view;

import it.polimi.se2019.model.game.NewCell;

import javax.swing.*;
import java.awt.*;

public class CellViewGUI extends JPanel {
    private SquareGUI[][] matrixGUI;
    public CellView dataCell;

    public CellViewGUI(int lineIndex, int columnIndex, NewCell playerPosition){

        this.matrixGUI = new SquareGUI[3][3];
        this.dataCell = new CellView(lineIndex, columnIndex, playerPosition);
        ////creazione dello square in base al contenuto della cella/////
        updateCell(playerPosition);

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

    public void updateCell(NewCell playerPosition){

        dataCell.setCell(playerPosition);

        int z = 0;
        for (int i = 0; i<=3 ; i++){
            for (int j = 0; j<= 3; j++){
                if(j==0 && i==0){
                    matrixGUI[j][i].setContent(null, playerPosition.getDrop());
                }else if(z <= playerPosition.getPlayers().size()){
                    matrixGUI[j][i].setContent(dataCell.getPlayerFigures().get(z), null);
                    z+=1;

                }else {
                    matrixGUI[j][i] = null;
                }

            }
        }
    }

}
