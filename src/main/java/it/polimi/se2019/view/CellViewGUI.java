package it.polimi.se2019.view;

import javax.swing.*;
import java.awt.*;

public class CellViewGUI extends JPanel {
    private SquareGUI[][] matrixGUI;

    public CellViewGUI(){

        this.matrixGUI = new SquareGUI[3][3];



        setSize(100,90);

        //TODO ci vanno i player

        //TODO rimuovere il clic della cella a favore di sistemi che indicano la cella con l'indice della riga e colonna

    }

}
