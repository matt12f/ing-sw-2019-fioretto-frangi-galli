package it.polimi.se2019.view;

import it.polimi.se2019.model.cards.GunCard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;	//for CloseListener()
import java.awt.event.WindowAdapter;	//for CloseListener()
import java.lang.Integer;		//int from Model is passed as an Integer
import java.util.Observable;		//for update();

public class WeaponMenuGUI extends JFrame{
    private JLabel image;
    private Label weaponName;
    private TextArea weaponInfo;
    private Label weaponCharge;
    private ImageIcon img;

    /**
     * create a frame that shows the weapon's information
     * @param weaponType guncard with the data
     */
    public WeaponMenuGUI(GunCard weaponType){
        this.weaponName = new Label(weaponType.getClass().getSimpleName().toUpperCase());
        this.weaponInfo = new TextArea(weaponType.getDescription());
        this.weaponCharge = new Label("Loaded: " + weaponType.isLoaded());
        this.img = new ImageIcon(new ImageIcon(getClass().getResource("/sprite/cards/weapons/weapons_"+ weaponType.getClass().getSimpleName().toLowerCase() +".png")).getImage().getScaledInstance(120,203,Image.SCALE_DEFAULT));
        this.image = new JLabel(img);

        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints container = new GridBagConstraints();
        mainPanel.setLayout(new GridBagLayout());

        container.gridx = 0;
        container.gridy = 1;
        mainPanel.add(image, container);


        container.gridx = 1;
        container.gridy = 0;
        mainPanel.add(weaponName, container);
        container.gridx = 1;
        container.gridy = 1;
        mainPanel.add(weaponInfo, container);


        container.gridx = 1;
        container.gridy = 2;
        mainPanel.add(weaponCharge, container);


        addWindowListener(new WeaponMenuGUI.CloseListener());
        add(mainPanel);
        setSize(600,400);
        setLocation(0,0);
        setVisible(true);

    }


    public static class CloseListener extends WindowAdapter {
        public void windowClosing(WindowEvent e) {
            e.getWindow().setVisible(false);

        } //windowClosing()
    } //CloseListener
}
