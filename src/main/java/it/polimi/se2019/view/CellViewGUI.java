package it.polimi.se2019.view;

import javax.swing.*;

public class CellViewGUI extends JPanel {
    private SquareGUI[][] matrixGUI;
    private String type;

    public CellViewGUI(CellView cell) {
        this.matrixGUI = new SquareGUI[3][3];
        //creates the square based on the content

        int z = 0;
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (z < cell.getPlayerFigures().size()){
                    matrixGUI[i][j] = new SquareGUI(cell.getPlayerFigures().get(z).getColor().toString(),"");
                    z++;
                }else
                    matrixGUI[i][j]=new SquareGUI("EMPTY","");

        if(!cell.getDrop().equals("spawn"))
            matrixGUI[2][2] = new SquareGUI("DROP",cell.getDrop());
        else
            matrixGUI[2][2] =new SquareGUI("EMPTY","");

        //add on gui interface
        int x = 0, y= 0;
        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 3; column++) {
                matrixGUI[row][column].setLocation(x, y);
                add(matrixGUI[row][column]);
                x += 20;
            }
            x = 0;
            y += 20;
        }

        setOpaque(false);
        setSize(100, 90);
    }

    //TODO check
    public void updateCell(CellView cell) {
        //this adds only the figures
        int z = 0;
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (z < cell.getPlayerFigures().size()){
                    matrixGUI[i][j].updateContent(cell.getPlayerFigures().get(z).getColor().toString(),"");
                    z++;
                }else 
                    matrixGUI[i][j].updateContent("EMPTY","");

        if(!cell.getDrop().equals("spawn"))
            matrixGUI[2][2].updateContent("DROP",cell.getDrop());
        else
            matrixGUI[2][2].updateContent("EMPTY","");

    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}




