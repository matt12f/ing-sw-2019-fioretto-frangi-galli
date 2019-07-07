package it.polimi.se2019.view;

import it.polimi.se2019.AdrenalineClient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * this class shows the player the amount of ammo it has.
 * It also includes a button to send action requests that is not enabled when it's not the player's turn,
 * it's turned on to invite the player to play its turn.
 *
 */
public class AmmoGUI extends JPanel {
    private JButton actionButton;

    private JLabel blueAmmo;
    private JLabel redAmmo;
    private JLabel yellowAmmo;
    private String turnMessage;

    /**
     * this constructor builds the graphics
     * @param ammo is the ammo of the current player
     */
    public AmmoGUI(AmmoView ammo){
        this.turnMessage="Wait to select actions";
        this.actionButton = new JButton(this.turnMessage);
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
                turnMessage="Wait to select actions";
                actionButton.setEnabled(false);
            }
        });
    }

    /**
     * this updates the ammo counters
     * @param ammo is the ammo to update them with
     */
    public void updateAmmo(AmmoView ammo){
        this.blueAmmo.setText("BLUE:"+ ammo.getBLUE());
        this.redAmmo.setText("RED:"+ ammo.getRED());
        this.yellowAmmo.setText("YELLOW:"+ ammo.getYELLOW());
    }

    /**
     * this method enables the action button when it's the player's turn
     */
    public void updateActionsButton(){
        turnMessage="Premi per scegliere azione!";
        actionButton.setEnabled(true);
    }
}
