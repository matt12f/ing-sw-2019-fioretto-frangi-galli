package it.polimi.se2019.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayerHandViewGUI extends JPanel {

    protected AmmoGUI ammo ;
    public PlayerHandViewGUI(){
        //JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints container = new GridBagConstraints();
        setLayout(new GridBagLayout());


        WeaponButtonGUI weapon1 = new WeaponButtonGUI();
        WeaponButtonGUI weapon2 = new WeaponButtonGUI();
        WeaponButtonGUI weapon3 = new WeaponButtonGUI();

        PowerupButtonGUI powerup1 = new PowerupButtonGUI();
        PowerupButtonGUI powerup2 = new PowerupButtonGUI();
        PowerupButtonGUI powerup3 = new PowerupButtonGUI();
        this.ammo = new AmmoGUI();


        //first column//
        setLayout(new GridBagLayout());
        container.gridx = 0;
        container.gridy = 0;
        add(weapon1);
        //second column//
        setLayout(new GridBagLayout());
        container.gridx = 0;
        container.gridy = 1;
        add(weapon2);
        //third column//
        setLayout(new GridBagLayout());
        container.gridx = 0;
        container.gridy = 2;
        add(weapon3);
        //fourth column//
        setLayout(new GridBagLayout());
        container.gridx = 0;
        container.gridy = 3;
        add(powerup1);
        //fifth column//
        setLayout(new GridBagLayout());
        container.gridx = 0;
        container.gridy = 4;
        add(powerup2);
        //sixth column//
        setLayout(new GridBagLayout());
        container.gridx = 0;
        container.gridy = 5;
        add(powerup3);

        //seventh column//
        setLayout(new GridBagLayout());
        container.gridx = 0;
        container.gridy = 6;
        add(ammo);

        weapon1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                WeaponMenuGUI frame =new WeaponMenuGUI();
                frame.setVisible(true);
            }});
        weapon2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                WeaponMenuGUI frame =new WeaponMenuGUI();
                frame.setVisible(true);
            }});
        weapon3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                WeaponMenuGUI frame =new WeaponMenuGUI();
                frame.setVisible(true);
            }});



    }
}
