package it.polimi.se2019.view;

import javax.swing.*;
import java.awt.*;


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
    public MapViewGUI(int config){


        switch (config){
            case 1: this.map = new ImageIcon(new ImageIcon("src/main/sprite/maps/1.png").getImage().getScaledInstance(597,442,Image.SCALE_DEFAULT));
                break;
            case 2: this.map = new ImageIcon(new ImageIcon("src/main/sprite/maps/2.png").getImage().getScaledInstance(597,442,Image.SCALE_DEFAULT));
                break;
            case 3: this.map = new ImageIcon(new ImageIcon("src/main/sprite/maps/3.png").getImage().getScaledInstance(597,442,Image.SCALE_DEFAULT));
                break;
            case 4: this.map = new ImageIcon(new ImageIcon("src/main/sprite/maps/4.png").getImage().getScaledInstance(597,442,Image.SCALE_DEFAULT));
                break;
        }

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



        GridBagConstraints container = new GridBagConstraints();

        setLayout(new GridBagLayout());
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
        add(labelBackground,container);

        //container.gridx= 0;
        //container.gridy = 0;



        //toppa per test

        /**
        Frame frame = new Frame("ADRENALINE");
        frame.addWindowListener(new MainLogGui.CloseListener());
        frame.add(labelBackground);

        frame.setSize(1280,720);
        frame.setLocation(0,0);
        frame.setVisible(true);*/

    }

}
