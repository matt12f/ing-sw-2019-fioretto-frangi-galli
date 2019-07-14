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


    /**
     * builder that create the map of the game, choosing the right configuration looking for the player's choice
     * @param config number of the map configuration
     * @param boardMatrix matrix of the cell views
     */
    public MapViewGUI(int config, CellView[][] boardMatrix){

        this.map = new ImageIcon(new ImageIcon(getClass().getResource("/sprite/maps/"+ config +".png")).getImage().getScaledInstance(600,450,Image.SCALE_SMOOTH));
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




        /* ----------buttons zone------------*/

        weaponblue1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(weaponblue1.getWeapon()!=null)
                {
                    WeaponMenuGUI frame =new WeaponMenuGUI(weaponblue1.getWeapon());
                    frame.setVisible(true);
                }
            }});
        weaponblue2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(weaponblue2.getWeapon()!=null)
                {
                    WeaponMenuGUI frame =new WeaponMenuGUI(weaponblue2.getWeapon());
                    frame.setVisible(true);
                }
            }});
        weaponblue3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(weaponblue3.getWeapon()!=null)
                {
                    WeaponMenuGUI frame =new WeaponMenuGUI(weaponblue3.getWeapon());
                    frame.setVisible(true);
                }
            }});

        ///////red//////
        weaponred1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(weaponred1.getWeapon()!=null)
                {
                    WeaponMenuGUI frame =new WeaponMenuGUI(weaponred1.getWeapon());
                    frame.setVisible(true);
                }
            }});

        weaponred2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(weaponred2.getWeapon()!=null)
                {
                    WeaponMenuGUI frame =new WeaponMenuGUI(weaponred2.getWeapon());
                    frame.setVisible(true);
                }
            }});
        weaponred3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(weaponred3.getWeapon()!=null)
                {
                    WeaponMenuGUI frame =new WeaponMenuGUI(weaponred3.getWeapon());
                    frame.setVisible(true);
                }
            }});



        //////yellow/////

        weaponyellow1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(weaponyellow1.getWeapon()!=null)
                {
                    WeaponMenuGUI frame =new WeaponMenuGUI(weaponyellow1.getWeapon());
                    frame.setVisible(true);
                }
            }});

        weaponyellow2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(weaponyellow2.getWeapon()!=null)
                {
                    WeaponMenuGUI frame =new WeaponMenuGUI(weaponyellow2.getWeapon());
                    frame.setVisible(true);
                }
            }});

        weaponyellow3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(weaponyellow3.getWeapon()!=null)
                {
                    WeaponMenuGUI frame =new WeaponMenuGUI(weaponyellow3.getWeapon());
                    frame.setVisible(true);
                }
            }});
    }

    /**
     * update the cell board and the content of the spawn cells
     * @param boardMatrix matrix of the cell views
     */
    public void setBoard(CellView[][] boardMatrix){
        for (int row=0;row < 3;row++){
            for(int column=0;column < 4;column++){
                boardMatrixGUI[row][column].updateCell(boardMatrix[row][column]);
            }
        }

        //section that updates weapons
        if(!boardMatrix[0][2].getCorrespondingCell().getWeaponCards().isEmpty() && boardMatrix[0][2].getCorrespondingCell().getWeaponCards().get(0)!=null)
            weaponblue1.updateImage(boardMatrix[0][2].getCorrespondingCell().getWeaponCards().get(0), false);
        else
            weaponblue1.updateImage(null, false);

        if(boardMatrix[0][2].getCorrespondingCell().getWeaponCards().size()>=2 && boardMatrix[0][2].getCorrespondingCell().getWeaponCards().get(1)!=null)
            weaponblue2.updateImage(boardMatrix[0][2].getCorrespondingCell().getWeaponCards().get(1), false);
        else
            weaponblue2.updateImage(null, false);


        if(boardMatrix[0][2].getCorrespondingCell().getWeaponCards().size()>=3 && boardMatrix[0][2].getCorrespondingCell().getWeaponCards().get(2)!=null)
            weaponblue3.updateImage(boardMatrix[0][2].getCorrespondingCell().getWeaponCards().get(2), false);
        else
            weaponblue3.updateImage(null, false);


        if(!boardMatrix[1][0].getCorrespondingCell().getWeaponCards().isEmpty() && boardMatrix[1][0].getCorrespondingCell().getWeaponCards().get(0)!=null)
            weaponred1.updateImage(boardMatrix[1][0].getCorrespondingCell().getWeaponCards().get(0), true);
        else
            weaponred1.updateImage(null, true);


        if(boardMatrix[1][0].getCorrespondingCell().getWeaponCards().size()>=2 && boardMatrix[1][0].getCorrespondingCell().getWeaponCards().get(1)!=null)
            weaponred2.updateImage(boardMatrix[1][0].getCorrespondingCell().getWeaponCards().get(1), true);
        else
            weaponred2.updateImage(null, true);


        if(boardMatrix[1][0].getCorrespondingCell().getWeaponCards().size()>=3 && boardMatrix[1][0].getCorrespondingCell().getWeaponCards().get(2)!=null)
            weaponred3.updateImage(boardMatrix[1][0].getCorrespondingCell().getWeaponCards().get(2), true);
        else
            weaponred3.updateImage(null, true);


        if(!boardMatrix[2][3].getCorrespondingCell().getWeaponCards().isEmpty() && boardMatrix[2][3].getCorrespondingCell().getWeaponCards().get(0)!=null)
            weaponyellow1.updateImage(boardMatrix[2][3].getCorrespondingCell().getWeaponCards().get(0), true);
        else
            weaponyellow1.updateImage(null, true);


        if(boardMatrix[2][3].getCorrespondingCell().getWeaponCards().size()>=2 && boardMatrix[2][3].getCorrespondingCell().getWeaponCards().get(1)!=null)
            weaponyellow2.updateImage(boardMatrix[2][3].getCorrespondingCell().getWeaponCards().get(1), true);
        else
            weaponyellow2.updateImage(null, true);


        if(boardMatrix[2][3].getCorrespondingCell().getWeaponCards().size()>=3 && boardMatrix[2][3].getCorrespondingCell().getWeaponCards().get(2)!=null)
            weaponyellow3.updateImage(boardMatrix[2][3].getCorrespondingCell().getWeaponCards().get(2), true);
        else
            weaponyellow3.updateImage(null, true);




    }

}
