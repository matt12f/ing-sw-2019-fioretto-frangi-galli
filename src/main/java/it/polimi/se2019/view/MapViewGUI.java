package it.polimi.se2019.view;

import javax.swing.*;
import java.awt.*;


public class MapViewGUI {

    private ImageIcon map;

    public MapViewGUI(){

        this.map = new ImageIcon(new ImageIcon("src/main/sprite/maps/1.png").getImage().getScaledInstance(637,482,Image.SCALE_DEFAULT));
        JLabel label = new JLabel(map);
        label.setIcon(map);

        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints container = new GridBagConstraints();
        mainPanel.setLayout(new GridBagLayout());

        mainPanel.add(label);
        //toppa per test
        Frame frame = new Frame("ADRENALINE");
        frame.addWindowListener(new MainLogGui.CloseListener());
        frame.add(mainPanel);
        frame.setSize(1280,720);
        frame.setLocation(0,0);
        frame.setVisible(true);

    }
}
