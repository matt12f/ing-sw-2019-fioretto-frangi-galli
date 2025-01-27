package it.polimi.se2019.view;

import it.polimi.se2019.model.cards.GunCard;
import it.polimi.se2019.model.cards.PowerupCard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayerHandViewGUI extends JPanel {
    private WeaponButtonGUI weapon1;
    private WeaponButtonGUI weapon2;
    private WeaponButtonGUI weapon3;
    private GunCard[] weapons;
    private PowerupCard[] powerups;
    private AmmoGUI ammo;
    private PowerupButtonGUI powerup1;
    private PowerupButtonGUI powerup2;
    private PowerupButtonGUI powerup3;

    /**
     * builder that create the players hand
     * @param ammoView contains the data of the ammo in the player's hand
     */
    public PlayerHandViewGUI(AmmoView ammoView){
        GridBagConstraints container = new GridBagConstraints();
        setLayout(new GridBagLayout());

         weapons = new GunCard[3];
         weapon1 = new WeaponButtonGUI(100,163, false);
         weapon2 = new WeaponButtonGUI(100,163 , false);
         weapon3 = new WeaponButtonGUI(100,163, false);

         powerups = new PowerupCard[3];
         powerup1 = new PowerupButtonGUI();
         powerup2 = new PowerupButtonGUI();
         powerup3 = new PowerupButtonGUI();
         this.ammo = new AmmoGUI(ammoView);


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
                if (weapons[0] != null){
                    WeaponMenuGUI frame =new WeaponMenuGUI(weapons[0]);
                    frame.setVisible(true);
                }
            }});
        weapon2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (weapons[1] != null){
                    WeaponMenuGUI frame =new WeaponMenuGUI(weapons[1]);
                    frame.setVisible(true);
                }
            }});
        weapon3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (weapons[2] != null){
                    WeaponMenuGUI frame =new WeaponMenuGUI(weapons[2]);
                    frame.setVisible(true);
                }


            }});

        powerup1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (powerups[0] != null){
                    PowerUpMenuGUI frame =new PowerUpMenuGUI(powerups[0]);
                    frame.setVisible(true);
                }
            }});

        powerup2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (powerups[1] != null){
                    PowerUpMenuGUI frame =new PowerUpMenuGUI(powerups[1]);
                    frame.setVisible(true);
                }
            }});

        powerup3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (powerups[2] != null){
                    PowerUpMenuGUI frame =new PowerUpMenuGUI(powerups[2]);
                    frame.setVisible(true);
                }
            }});



    }

    /**
     * update the player's hand
     * @param weapons vector of the weapon in the hand
     * @param powerups vector of the powerups in the hand
     * @param ammoView contains the data of the ammo in the player's hand
     */
    public void updateHand(GunCard[] weapons, PowerupCard[] powerups, AmmoView ammoView){
       weapon1.updateImage(weapons[0], false);
       weapon2.updateImage(weapons[1], false);
       weapon3.updateImage(weapons[2], false);
       this.weapons = weapons;
       this.powerups = powerups;
       powerup1.updateImage(powerups[0]);
       powerup2.updateImage(powerups[1]);
       powerup3.updateImage(powerups[2]);
       this.ammo.updateAmmo(ammoView);

    }

}
