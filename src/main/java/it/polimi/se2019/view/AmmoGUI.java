package it.polimi.se2019.view;

import it.polimi.se2019.AdrenalineClient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AmmoGUI extends JPanel {
    private JButton actionButton;

    private JLabel blueAmmo;
    private JLabel redAmmo;
    private JLabel yellowAmmo;

    public AmmoGUI(AmmoView ammo){
        this.actionButton = new JButton("ACTIONS");
        GridBagConstraints container =new GridBagConstraints();
        this.actionButton.setEnabled(false);

        setLayout(new GridBagLayout());

        this.blueAmmo = new JLabel("Blue cubes:"+ ammo.getBLUE());
        container.gridx=0;
        container.gridy=0;
        add(this.blueAmmo,container);

        this.redAmmo = new JLabel("Red cubes:"+ ammo.getRED());
        container.gridx=0;
        container.gridy=1;
        add(this.redAmmo,container);

        this.yellowAmmo = new JLabel("Yellow cubes:"+ ammo.getYELLOW());
        container.gridx=0;
        container.gridy=2;
        add(this.yellowAmmo,container);

        container.gridx=0;
        container.gridy=3;
        add(this.actionButton,container);

        actionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AdrenalineClient.setActionRequested( new ActionRequestView(AdrenalineClient.isLast()));
                actionButton.setEnabled(false);
            }
        });
    }

    public void updateAmmo(AmmoView ammo){
        this.blueAmmo.setText("BLUE:"+ ammo.getBLUE());
        this.redAmmo.setText("RED:"+ ammo.getRED());
        this.yellowAmmo.setText("YELLOW:"+ ammo.getYELLOW());
    }

    public void updateActionsButton(){
        actionButton.setEnabled(true);
    }
}
