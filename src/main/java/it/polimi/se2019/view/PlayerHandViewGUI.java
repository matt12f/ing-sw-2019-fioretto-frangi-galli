package it.polimi.se2019.view;

import javax.swing.*;
import java.awt.*;

public class PlayerHandViewGUI {

    public PlayerHandViewGUI(){
        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints container = new GridBagConstraints();
        mainPanel.setLayout(new GridBagLayout());

        JPanel weapon1 = new JPanel();
        JPanel weapon2 = new JPanel();
        JPanel weapon3 = new JPanel();
        JPanel powerup1 = new JPanel();
        JPanel powerup2 = new JPanel();
        JPanel powerup3 = new JPanel();
        JPanel ammo = new JPanel(new GridBagLayout());
        GridBagConstraints ammoContainer = new GridBagConstraints();
        ammo.setLayout(new GridBagLayout());
        //first column//
        container.gridx = 0;
        container.gridy = 0;
        mainPanel.add(weapon1);
        //second column//
        container.gridx = 0;
        container.gridy = 1;
        mainPanel.add(weapon2);
        //third column//
        container.gridx = 0;
        container.gridy = 2;
        mainPanel.add(weapon3);
        //fourth column//
        container.gridx = 0;
        container.gridy = 3;
        mainPanel.add(powerup1);
        //fifth column//
        container.gridx = 0;
        container.gridy = 4;
        mainPanel.add(powerup2);
        //sixth column//
        container.gridx = 0;
        container.gridy = 5;
        mainPanel.add(powerup3);
        //seventh column//
        container.gridx = 0;
        container.gridy = 6;
        mainPanel.add(ammo);

        //ammo first raw//
        ammoContainer.gridx = 0;
        ammoContainer.gridy = 0;
        ammo.add(new Label("BLUE:"));

        //ammo second raw//
        ammoContainer.gridx = 0;
        ammoContainer.gridy = 1;
        ammo.add(new Label("RED:"));

        //ammo third raw//
        ammoContainer.gridx = 0;
        ammoContainer.gridy = 2;
        ammo.add(new Label("YELLOW:"));


    }
}
