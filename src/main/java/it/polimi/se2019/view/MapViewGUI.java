package it.polimi.se2019.view;

import javax.swing.*;
import java.awt.*;


public class MapViewGUI {

    private ImageIcon map;

    public MapViewGUI(int config){


        switch (config){
            case 1: this.map = new ImageIcon(new ImageIcon("src/main/sprite/maps/1.png").getImage().getScaledInstance(637,482,Image.SCALE_DEFAULT));
            case 2: this.map = new ImageIcon(new ImageIcon("src/main/sprite/maps/2.png").getImage().getScaledInstance(637,482,Image.SCALE_DEFAULT));
            case 3: this.map = new ImageIcon(new ImageIcon("src/main/sprite/maps/3.png").getImage().getScaledInstance(637,482,Image.SCALE_DEFAULT));
            case 4: this.map = new ImageIcon(new ImageIcon("src/main/sprite/maps/4.png").getImage().getScaledInstance(637,482,Image.SCALE_DEFAULT));
        }

        JLabel labelBackground = new JLabel(map);
        labelBackground.setIcon(map);


        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints container = new GridBagConstraints();
        mainPanel.setLayout(new GridBagLayout());

        labelBackground.add(mainPanel);

        //container.gridx= 0;
        //container.gridy = 0;



        //toppa per test
        /**
         *

        Frame frame = new Frame("ADRENALINE");
        frame.addWindowListener(new MainLogGui.CloseListener());
        frame.add(mainPanel);

        frame.setSize(1280,720);
        frame.setLocation(0,0);
        frame.setVisible(true);
         */
    }
}
