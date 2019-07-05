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
    private Label weaponInfo;
    private Label weaponCharge;
    private ImageIcon img;


    public WeaponMenuGUI(GunCard weaponType){
            this.weaponName = new Label(weaponType.getClass().getSimpleName().toUpperCase());
            this.weaponInfo = new Label(weaponType.getDescription());
            this.weaponCharge = new Label("Loaded:" + weaponType.isLoaded());
            this.img = new ImageIcon(new ImageIcon("src/main/sprite/cards/weapons/weapons_"+ weaponType.getClass().getSimpleName().toLowerCase() +".png").getImage().getScaledInstance(120,203,Image.SCALE_DEFAULT));
            this.image = new JLabel(img);


        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints container = new GridBagConstraints();
        mainPanel.setLayout(new GridBagLayout());

        container.gridx = 0;
        container.gridy = 1;
        //container.gridheight = 3;
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
        setSize(700,700);
        setLocation(500,500);
        setVisible(true);

    }

    public void update(Observable obs, Object obj) {


    } //update()



    public static class CloseListener extends WindowAdapter {
        public void windowClosing(WindowEvent e) {
            e.getWindow().setVisible(false);

        } //windowClosing()
    } //CloseListener
}
