package it.polimi.se2019.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;	//for CloseListener()
import java.awt.event.WindowAdapter;	//for CloseListener()
import java.lang.Integer;		//int from Model is passed as an Integer
import java.util.Observable;		//for update();

public class WeaponMenuGUI {
    private JPanel image;
    private Label weaponName;
    private Label weaponInfo;
    private Label weaponCharge;



    public WeaponMenuGUI(){
        Frame frame = new Frame("weapon menu");
        this.weaponName = new Label("Nome");
        this.weaponInfo = new Label("info");
        this.weaponCharge = new Label("charged");
        this.image = new JPanel();
        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints container = new GridBagConstraints();
        mainPanel.setLayout(new GridBagLayout());

        container.gridx = 0;
        container.gridy = 0;
        mainPanel.add(weaponName, container);
        container.gridx = 1;
        container.gridy = 0;
        mainPanel.add(weaponName, container);
        container.gridx = 1;
        container.gridy = 1;
        mainPanel.add(weaponInfo, container);


        container.gridx = 1;
        container.gridy = 2;
        mainPanel.add(weaponCharge, container);




        frame.addWindowListener(new MainLogGui.CloseListener());
        frame.add(mainPanel);
        frame.setSize(400,400);
        frame.setLocation(500,500);
        frame.setVisible(true);

    }

    public void update(Observable obs, Object obj) {


    } //update()

    public void addController(ActionListener controller){


    } //addController()

    public static class CloseListener extends WindowAdapter {
        public void windowClosing(WindowEvent e) {
            e.getWindow().setVisible(false);
            System.exit(0);
        } //windowClosing()
    } //CloseListener
}
