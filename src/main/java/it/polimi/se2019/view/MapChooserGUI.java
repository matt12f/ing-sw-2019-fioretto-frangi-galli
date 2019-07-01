package it.polimi.se2019.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MapChooserGUI {
    private int map;

    public MapChooserGUI(){


        Frame frame = new Frame("Chooser");
        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints container = new GridBagConstraints();
        mainPanel.setLayout(new GridBagLayout());

        JButton map1 = new JButton();
        ImageIcon pic1 = new ImageIcon(new ImageIcon("src/main/sprite/maps/1.png").getImage().getScaledInstance(300,220,Image.SCALE_DEFAULT));
        map1.setIcon(pic1);

        JButton map2 = new JButton();
        ImageIcon pic2 = new ImageIcon(new ImageIcon("src/main/sprite/maps/2.png").getImage().getScaledInstance(300,220,Image.SCALE_DEFAULT));
        map2.setIcon(pic2);

        JButton map3 = new JButton();
        ImageIcon pic3 = new ImageIcon(new ImageIcon("src/main/sprite/maps/3.png").getImage().getScaledInstance(300,220,Image.SCALE_DEFAULT));
        map3.setIcon(pic3);

        JButton map4 = new JButton();
        ImageIcon pic4 = new ImageIcon(new ImageIcon("src/main/sprite/maps/4.png").getImage().getScaledInstance(300,220,Image.SCALE_DEFAULT));
        map4.setIcon(pic4);


        container.gridx = 0;
        container.gridy = 0;
        mainPanel.add(new Label("choose your map"), container);

        container.gridx = 0;
        container.gridy = 1;
        mainPanel.add(map1, container);

        container.gridx = 1;
        container.gridy = 1;
        mainPanel.add(map2, container);

        container.gridx = 0;
        container.gridy = 2;
        mainPanel.add(map3, container);

        container.gridx = 1;
        container.gridy = 2;
        mainPanel.add(map4, container);


        //TODO con quesi bottoni aprire la plancia di gioco passando map
        map1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                map = 1;
                frame.setVisible(true);
            }});

        map2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                map = 2;
                frame.setVisible(true);
            }});
        map3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                map = 3;
                frame.setVisible(true);
            }});
        map4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                map = 4;
                frame.setVisible(true);
            }});







        frame.addWindowListener(new MapChooserGUI.CloseListener());
        frame.add(mainPanel);
        frame.setSize(1280,720);
        frame.setLocation(100,100);
        frame.setVisible(true);
    }

    public static class CloseListener extends WindowAdapter {
        public void windowClosing(WindowEvent e) {
            e.getWindow().setVisible(false);
            System.exit(0);
        } //windowClosing()
    } //CloseListener
}
