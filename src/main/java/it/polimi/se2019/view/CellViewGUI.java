package it.polimi.se2019.view;

import it.polimi.se2019.enums.Color;
import it.polimi.se2019.model.game.Figure;
import it.polimi.se2019.model.game.NewCell;

import javax.swing.*;
import java.awt.*;

public class CellViewGUI extends JPanel {
    private SquareGUI[][] matrixGUI;
    private String type;


    public CellViewGUI(CellView cell){

        this.matrixGUI = new SquareGUI[3][3];
        this.type= type;
        ////creazione dello square in base al contenuto della cella/////
        int z = 0;
        for (int i = 0; i<3 ; i++){
            for (int j = 0; j< 3; j++){
                if(j==0 && i==0 && cell.getDrop() != null){
                    matrixGUI[j][i]= new SquareGUI(Color.BLACK);
                }else if(z < cell.getPlayerFigures().size()){
                    matrixGUI[j][i]= new SquareGUI(cell.getPlayerFigures().get(z).getColor());
                    z+=1;
                }else {
                    matrixGUI[j][i]= new SquareGUI(Color.RED);
                }

            }
        }

        setOpaque(false);




        setSize(100,90);

    }

    public void updateCell(CellView cell){
        removeAll();
        for (int i = 0; i<3 ; i++){
            for (int j = 0; j< 3; j++){
                if(j==0 && i==0 && cell.getDrop() != null){
                    matrixGUI[j][i].updateImage(Color.BLACK);
                }else if(!cell.getPlayerFigures().isEmpty()){ //TODO controllare
                    for(Figure figure:cell.getPlayerFigures())
                        matrixGUI[j][i].updateImage(figure.getColor());
                }else {
                    matrixGUI[j][i].updateImage(null);
                }

            }
        }

        ///add on gui interface
        int x= 0,y = 0;
        for(int row = 0; row < 3; row++){
            for (int column =0 ; column < 3 ; column++){
                matrixGUI[row][column].setLocation(x,y);
                if(matrixGUI[row][column] != null){
                    add(matrixGUI[row][column]);
                }

                x+=30;
            }
            x= 0;
            y+= 30;
        }
    }

    public String getType(){

        return  type;
    }
    public void setType(String typex){
        this.type = typex;
    }


}
