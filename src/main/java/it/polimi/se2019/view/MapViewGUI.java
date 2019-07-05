package it.polimi.se2019.view;

import javax.sound.sampled.Line;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

import static it.polimi.se2019.enums.CellType.OUTSIDEBOARD;


public class MapViewGUI extends JPanel {

    private ImageIcon map;
    protected JLabel labelBackground;
    private WeaponButtonGUI weaponblue1;
    private WeaponButtonGUI weaponblue2;
    private WeaponButtonGUI weaponblue3;

    private WeaponButtonGUI weaponred1;
    private WeaponButtonGUI weaponred2;
    private WeaponButtonGUI weaponred3;

    private WeaponButtonGUI weaponyellow1;
    private WeaponButtonGUI weaponyellow2;
    private WeaponButtonGUI weaponyellow3;

    private CellViewGUI[][] boardMatrixGUI;

/**
    private CellViewGUI cella11;
    private CellViewGUI cella12;
    private CellViewGUI cella13;
    private CellViewGUI cella14;

    private CellViewGUI cella21;
    private CellViewGUI cella22;
    private CellViewGUI cella23;
    private CellViewGUI cella24;

    private CellViewGUI cella31;
    private CellViewGUI cella32;
    private CellViewGUI cella33;
    private CellViewGUI cella34;
*/

    public MapViewGUI(int config, CellView[][] boardMatrix){



            this.map = new ImageIcon(new ImageIcon("src/main/sprite/maps/"+ config +".png").getImage().getScaledInstance(597,442,Image.SCALE_DEFAULT));


        this.labelBackground = new JLabel(map);
        labelBackground.setIcon(map);

        ///////blue weapons
         this.weaponblue1 = new WeaponButtonGUI(56,80);
         this.weaponblue1.setLocation(317, 0);

        this.weaponblue2 = new WeaponButtonGUI(56,80);
        this.weaponblue2.setLocation(382, 0);

        this.weaponblue3 = new WeaponButtonGUI(56,80);
        this.weaponblue3.setLocation(448, 0);

        ////////red weapons

        this.weaponred1 = new WeaponButtonGUI(80,56);
        this.weaponred1.setLocation(0, 160);

        this.weaponred2 = new WeaponButtonGUI(80,56);
        this.weaponred2.setLocation(0, 225);

        this.weaponred3 = new WeaponButtonGUI(80,56);
        this.weaponred3.setLocation(0, 286);

        ///////yellow weapons

        this.weaponyellow1 = new WeaponButtonGUI(80,56);
        this.weaponyellow1.setLocation(518, 250);

        this.weaponyellow2 = new WeaponButtonGUI(80,56);
        this.weaponyellow2.setLocation(518, 311);

        this.weaponyellow3 = new WeaponButtonGUI(80,56);
        this.weaponyellow3.setLocation(518, 375);


        ////spazio celle////
        int x= 95, y= 90;
        boardMatrixGUI = new CellViewGUI[3][4];
        for (int row=0;row < 3;row++){
            for(int column=0;column < 4;column++){
                boardMatrixGUI[row][column]= new CellViewGUI(boardMatrix[row][column]);
                boardMatrixGUI[row][column].setLocation(x,y);

                if (boardMatrix[row][column].getCorrespondingCell().getCellType() == OUTSIDEBOARD){
                    boardMatrixGUI[row][column].setType("inBoard");
                }else {
                    boardMatrixGUI[row][column].setType("outBoard");

                }


                x+=105;
            }
            x=95;
            y+=110;
        }

 /**
        /////first line////7
         this.cella11 = new CellViewGUI();
         this.cella11.setLocation(95,90);

        this.cella12 = new CellViewGUI();
        this.cella12.setLocation(200,90);

        this.cella13 = new CellViewGUI();
        this.cella13.setLocation(305,90);

        this.cella14 = new CellViewGUI();
        this.cella14.setLocation(410,90);

        ////second line/////
        this.cella21 = new CellViewGUI();
        this.cella21.setLocation(95,200);

        this.cella22 = new CellViewGUI();
        this.cella22.setLocation(200,200);

        this.cella23 = new CellViewGUI();
        this.cella23.setLocation(305,200);

        this.cella24 = new CellViewGUI();
        this.cella24.setLocation(410,200);

        ////third line////
        this.cella31 = new CellViewGUI();
        this.cella31.setLocation(95,305);

        this.cella32 = new CellViewGUI();
        this.cella32.setLocation(200,305);

        this.cella33 = new CellViewGUI();
        this.cella33.setLocation(305,305);

        this.cella34 = new CellViewGUI();
        this.cella34.setLocation(410,305);

*/
        ///////add zone////

        //labelBackground.add(mainPanel);
        labelBackground.add(weaponblue1);
        labelBackground.add(weaponblue2);
        labelBackground.add(weaponblue3);
        labelBackground.add(weaponred1);
        labelBackground.add(weaponred2);
        labelBackground.add(weaponred3);
        labelBackground.add(weaponyellow1);
        labelBackground.add(weaponyellow2);
        labelBackground.add(weaponyellow3);



        for (int row=0;row < 3;row++) {
            for (int column = 0; column < 4; column++) {
                if(boardMatrixGUI[row][column].getType() == "inBoard"){
                    labelBackground.add(boardMatrixGUI[row][column]);
                }

            }

        }

        /**
        labelBackground.add(cella11);
        labelBackground.add(cella12);
        labelBackground.add(cella13);
        if(config != 2 && config != 4){
            labelBackground.add(cella14);
        }

        labelBackground.add(cella21);
        labelBackground.add(cella22);
        labelBackground.add(cella23);
        labelBackground.add(cella24);

        if (config != 1 && config != 2){
            labelBackground.add(cella31);
        }
        labelBackground.add(cella32);
        labelBackground.add(cella33);
        labelBackground.add(cella34);
*/
        add(labelBackground);

    }

    public void setBoard(CellView[][] boardMatrix){
        int x= 95, y= 90;

        for (int row=0;row < 3;row++){
            for(int column=0;column < 4;column++){
                boardMatrixGUI[row][column].updateCell(boardMatrix[row][column]);
                boardMatrixGUI[row][column].setLocation(x,y);

                if (boardMatrix[row][column].getCorrespondingCell().getCellType() == OUTSIDEBOARD){
                    boardMatrixGUI[row][column].setType("inBoard");
                }else {
                    boardMatrixGUI[row][column].setType("outBoard");

                }


                x+=105;
            }
            x=95;
            y+=110;
        }

        ///////update weapons//////
        weaponblue1.updateImage(boardMatrix[0][2].getCorrespondingCell().getWeaponCards().get(0));
        weaponblue2.updateImage(boardMatrix[0][2].getCorrespondingCell().getWeaponCards().get(1));
        weaponblue3.updateImage(boardMatrix[0][2].getCorrespondingCell().getWeaponCards().get(2));

        weaponred1.updateImage(boardMatrix[1][0].getCorrespondingCell().getWeaponCards().get(0));
        weaponred2.updateImage(boardMatrix[1][0].getCorrespondingCell().getWeaponCards().get(1));
        weaponred3.updateImage(boardMatrix[1][0].getCorrespondingCell().getWeaponCards().get(2));

        weaponyellow1.updateImage(boardMatrix[2][3].getCorrespondingCell().getWeaponCards().get(0));
        weaponyellow2.updateImage(boardMatrix[2][3].getCorrespondingCell().getWeaponCards().get(1));
        weaponyellow3.updateImage(boardMatrix[2][3].getCorrespondingCell().getWeaponCards().get(2));




    }

}
