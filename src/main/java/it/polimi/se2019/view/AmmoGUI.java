package it.polimi.se2019.view;

import it.polimi.se2019.AdrenalineClient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AmmoGUI extends JPanel {
    public JButton actionButton;
    public AmmoGUI(AmmoView ammo ){

        updateAmmo(ammo);



    }



    public void updateAmmo(AmmoView ammo){
        this.actionButton = new JButton("ACTIONS");
        GridBagConstraints container = new GridBagConstraints();


        //ammo first raw//
        setLayout(new GridBagLayout());
        container.gridx = 0;
        container.gridy = 0;
        add(new Label("BLUE:"+ ammo.getBLUE()),container);

        //ammo second raw//
        //setLayout(new GridBagLayout());
        container.gridx = 0;
        container.gridy = 1;
        add(new Label("RED:" + ammo.getRED()),container);

        //ammo third raw//
        //setLayout(new GridBagLayout());
        container.gridx = 0;
        container.gridy = 2;
        add(new Label("YELLOW:"+ ammo.getYELLOW()), container);

        //ammo fourraw//
        //setLayout(new GridBagLayout());
        container.gridx = 0;
        container.gridy = 3;
        add(actionButton,container);




        actionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ActionRequestView actions = new ActionRequestView(AdrenalineClient.isLast());

            }

        });
    }
}
