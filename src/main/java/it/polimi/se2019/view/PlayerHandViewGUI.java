package it.polimi.se2019.view;

import javax.swing.*;
import java.awt.*;

public class PlayerHandViewGUI extends JPanel {

    protected AmmoGUI ammo ;
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



    }
}
