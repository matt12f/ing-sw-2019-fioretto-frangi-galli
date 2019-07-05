package it.polimi.se2019.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

    public MapViewGUI(int config, CellView[][] boardMatrix){

        this.map = new ImageIcon(new ImageIcon("src/main/resources/sprite/maps/"+ config +".png").getImage().getScaledInstance(600,450,Image.SCALE_DEFAULT));

        this.labelBackground = new JLabel(map);
        labelBackground.setIcon(map);

        ///////blue weapons
         this.weaponblue1 = new WeaponButtonGUI(56,80, false);
         this.weaponblue1.setLocation(317, 0);

        this.weaponblue2 = new WeaponButtonGUI(56,80, false);
        this.weaponblue2.setLocation(382, 0);

        this.weaponblue3 = new WeaponButtonGUI(56,80, false);
        this.weaponblue3.setLocation(448, 0);

        ////////red weapons

        this.weaponred1 = new WeaponButtonGUI(80,56, true);
        this.weaponred1.setLocation(0, 160);

        this.weaponred2 = new WeaponButtonGUI(80,56, true);
        this.weaponred2.setLocation(0, 225);

        this.weaponred3 = new WeaponButtonGUI(80,56, true);
        this.weaponred3.setLocation(0, 286);

        ///////yellow weapons

        this.weaponyellow1 = new WeaponButtonGUI(80,56, true);
        this.weaponyellow1.setLocation(518, 250);

        this.weaponyellow2 = new WeaponButtonGUI(80,56, true);
        this.weaponyellow2.setLocation(518, 311);

        this.weaponyellow3 = new WeaponButtonGUI(80,56, true);
        this.weaponyellow3.setLocation(518, 375);


        ////cell space ////
        boardMatrixGUI = new CellViewGUI[3][4];
        int x= 95, y= 90;
        for (int row=0; row < 3; row++){
            for(int column=0; column < 4;column++){
                boardMatrixGUI[row][column] = new CellViewGUI(boardMatrix[row][column]);
                boardMatrixGUI[row][column].setLocation(x,y);

                if (boardMatrix[row][column].getCorrespondingCell().getCellType() != OUTSIDEBOARD)
                    boardMatrixGUI[row][column].setType("inBoard");
                else
                    boardMatrixGUI[row][column].setType("outBoard");

                x+=100; //moves on the x-axis
            }
            x=95; //resets x-axis
            y+=110; //moves on the y-axis
        }

        //---- add zones ---- //

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
                if(boardMatrixGUI[row][column].getType().equals("inBoard"))
                    labelBackground.add(boardMatrixGUI[row][column]);
            }
        }

        add(labelBackground);




        /** ----------butoons zone------------*/

        weaponblue1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (boardMatrix[0][2].getCorrespondingCell().getWeaponCards().get(0) != null){
                    WeaponMenuGUI frame =new WeaponMenuGUI(boardMatrix[0][2].getCorrespondingCell().getWeaponCards().get(0));
                    frame.setVisible(true);
                }
            }});
        weaponblue2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (boardMatrix[0][2].getCorrespondingCell().getWeaponCards().get(1) != null){
                    WeaponMenuGUI frame =new WeaponMenuGUI(boardMatrix[0][2].getCorrespondingCell().getWeaponCards().get(1));
                    frame.setVisible(true);
                }
            }});
        weaponblue3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (boardMatrix[0][2].getCorrespondingCell().getWeaponCards().get(2) != null){
                    WeaponMenuGUI frame =new WeaponMenuGUI(boardMatrix[0][2].getCorrespondingCell().getWeaponCards().get(2));
                    frame.setVisible(true);
                }
            }});

        ///////red//////
        weaponred1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (boardMatrix[1][0].getCorrespondingCell().getWeaponCards().get(0) != null){
                    WeaponMenuGUI frame =new WeaponMenuGUI(boardMatrix[1][0].getCorrespondingCell().getWeaponCards().get(0));
                    frame.setVisible(true);
                }
            }});

        weaponred2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (boardMatrix[1][0].getCorrespondingCell().getWeaponCards().get(1) != null){
                    WeaponMenuGUI frame =new WeaponMenuGUI(boardMatrix[1][0].getCorrespondingCell().getWeaponCards().get(1));
                    frame.setVisible(true);
                }
            }});
        weaponred3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (boardMatrix[1][0].getCorrespondingCell().getWeaponCards().get(2) != null){
                    WeaponMenuGUI frame =new WeaponMenuGUI(boardMatrix[1][0].getCorrespondingCell().getWeaponCards().get(2));
                    frame.setVisible(true);
                }
            }});



        //////yellow/////

        weaponyellow1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (boardMatrix[2][3].getCorrespondingCell().getWeaponCards().get(0) != null){
                    WeaponMenuGUI frame =new WeaponMenuGUI(boardMatrix[2][3].getCorrespondingCell().getWeaponCards().get(0));
                    frame.setVisible(true);
                }
            }});

        weaponyellow2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (boardMatrix[2][3].getCorrespondingCell().getWeaponCards().get(1) != null){
                    WeaponMenuGUI frame =new WeaponMenuGUI(boardMatrix[2][3].getCorrespondingCell().getWeaponCards().get(1));
                    frame.setVisible(true);
                }
            }});

        weaponyellow3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (boardMatrix[2][3].getCorrespondingCell().getWeaponCards().get(2) != null){
                    WeaponMenuGUI frame =new WeaponMenuGUI(boardMatrix[2][3].getCorrespondingCell().getWeaponCards().get(2));
                    frame.setVisible(true);
                }
            }});
    }

    public void setBoard(CellView[][] boardMatrix){
        //removeAll();
        int x= 95, y= 90;
        for (int row=0;row < 3;row++){
            for(int column=0;column < 4;column++){
                boardMatrixGUI[row][column].updateCell(boardMatrix[row][column]);
                boardMatrixGUI[row][column].setLocation(x,y);

                if (boardMatrix[row][column].getCorrespondingCell().getCellType() != OUTSIDEBOARD)
                    boardMatrixGUI[row][column].setType("inBoard");
                else
                    boardMatrixGUI[row][column].setType("outBoard");

                x+=105;
            }
            x=95;
            y+=110;
        }

        ///////update weapons//////
        weaponblue1.updateImage(boardMatrix[0][2].getCorrespondingCell().getWeaponCards().get(0), false);
        weaponblue2.updateImage(boardMatrix[0][2].getCorrespondingCell().getWeaponCards().get(1), false);
        weaponblue3.updateImage(boardMatrix[0][2].getCorrespondingCell().getWeaponCards().get(2), false);

        weaponred1.updateImage(boardMatrix[1][0].getCorrespondingCell().getWeaponCards().get(0), true);
        weaponred2.updateImage(boardMatrix[1][0].getCorrespondingCell().getWeaponCards().get(1), true);
        weaponred3.updateImage(boardMatrix[1][0].getCorrespondingCell().getWeaponCards().get(2), true);

        weaponyellow1.updateImage(boardMatrix[2][3].getCorrespondingCell().getWeaponCards().get(0), true);
        weaponyellow2.updateImage(boardMatrix[2][3].getCorrespondingCell().getWeaponCards().get(1), true);
        weaponyellow3.updateImage(boardMatrix[2][3].getCorrespondingCell().getWeaponCards().get(2), true);




    }

}
