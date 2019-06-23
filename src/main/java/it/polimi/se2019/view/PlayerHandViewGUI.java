package it.polimi.se2019.view;

import javax.swing.*;
import java.awt.*;

public class PlayerHandViewGUI extends JPanel {

    public PlayerHandViewGUI(){
        //JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints container = new GridBagConstraints();
        setLayout(new GridBagLayout());

        ImageIcon wpImage1 = new ImageIcon(new ImageIcon("src/main/sprite/cards/weapons/weapons_back.png").getImage().getScaledInstance(80,163,Image.SCALE_DEFAULT));
        JButton weapon1 = new JButton(wpImage1);
        JButton weapon2 = new JButton(wpImage1);
        JButton weapon3 = new JButton(wpImage1);
        ImageIcon pwImage1 = new ImageIcon(new ImageIcon("src/main/sprite/cards/powerups/powerups_back.png").getImage().getScaledInstance(49,92,Image.SCALE_DEFAULT));
        JButton powerup1 = new JButton(pwImage1);
        JButton powerup2 = new JButton(pwImage1);
        JButton powerup3 = new JButton(pwImage1);
        JPanel ammo = new JPanel(new GridBagLayout());
        GridBagConstraints ammoContainer = new GridBagConstraints();
        ammo.setLayout(new GridBagLayout());
        //first column//
        container.gridx = 0;
        container.gridy = 0;
        add(weapon1);
        //second column//
        container.gridx = 0;
        container.gridy = 1;
        add(weapon2);
        //third column//
        container.gridx = 0;
        container.gridy = 2;
        add(weapon3);
        //fourth column//
        container.gridx = 0;
        container.gridy = 3;
        add(powerup1);
        //fifth column//
        container.gridx = 0;
        container.gridy = 4;
        add(powerup2);
        //sixth column//
        container.gridx = 0;
        container.gridy = 5;
        add(powerup3);
        //seventh column//
        container.gridx = 0;
        container.gridy = 6;
        add(ammo);

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




        /**
        //mainPanel.setBorder(BorderFactory.createBevelBorder(1));
        //mainPanel.setSize(900,200);
        Frame frame = new Frame("ADRENALINE");
        frame.addWindowListener(new MainLogGui.CloseListener());
        frame.add(mainPanel);

        frame.setSize(1280,720);
        frame.setLocation(0,0);
        frame.setVisible(true);
         */
    }
}
