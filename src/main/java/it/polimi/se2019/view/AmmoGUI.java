package it.polimi.se2019.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AmmoGUI extends JPanel {
    public JButton actionButton;
    public AmmoGUI(){

        this.actionButton = new JButton("ACTIONS");
        GridBagConstraints container = new GridBagConstraints();


        //ammo first raw//
        setLayout(new GridBagLayout());
        container.gridx = 0;
        container.gridy = 0;
        add(new Label("BLUE:"),container);

        //ammo second raw//
        //setLayout(new GridBagLayout());
        container.gridx = 0;
        container.gridy = 1;
        add(new Label("RED:"),container);

        //ammo third raw//
       //setLayout(new GridBagLayout());
        container.gridx = 0;
        container.gridy = 2;
        add(new Label("YELLOW:"), container);

        //ammo fourraw//
        //setLayout(new GridBagLayout());
        container.gridx = 0;
        container.gridy = 3;
        add(actionButton,container);




        actionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO turnConclusion va cambiato dinamicamente
                ActionRequestView actions = new ActionRequestView(false);
                //UserInteractionGUI interact = new UserInteractionGUI();
                //interact.actionToRequest(0);
            }

        });



    }
}