package it.polimi.se2019.view;

import javax.swing.*;
import java.awt.*;
import javax.imageio.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;	//for CloseListener()
import java.awt.event.WindowAdapter;	//for CloseListener()
import java.lang.Integer;		//int from Model is passed as an Integer
import java.util.Observable;		//for update();
import java.util.Observer;
import java.awt.*;

public class GameBoardGui implements Observer {
    public MapViewGUI map;
    public PlayerZoneGUI playerZone;
    public ScoreViewGUI scoreZone;
    public BoardZoneGUI boardZone;
    public ImageIcon background;
    public GameBoardGui(int config){
        Frame frame = new Frame("ADRENALINE");

        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints container = new GridBagConstraints();
        mainPanel.setLayout(new GridBagLayout());


        this.background = new ImageIcon(new ImageIcon("src/main/sprite/maps/background.png").getImage().getScaledInstance(1280,720,Image.SCALE_DEFAULT));
        JLabel labelBackground = new JLabel(background);
        labelBackground.setIcon(background);

        container.anchor = GridBagConstraints.NORTHWEST;
        this.map = new MapViewGUI(config);
        container.gridx=0;
        container.gridy=0;
        //container.gridheight = 2;
        mainPanel.add(map, container);
        /**
        container.anchor = GridBagConstraints.NORTHEAST;
        this.scoreZone = new ScoreViewGUI();
        container.gridx=1;
        container.gridy=0;
        mainPanel.add(scoreZone, container);


        container.anchor = GridBagConstraints.EAST;
        this.boardZone = new BoardZoneGUI();
        container.gridx=1;
        container.gridy=1;
        mainPanel.add(boardZone, container);


        container.anchor = GridBagConstraints.SOUTH;
        this.playerZone = new PlayerZoneGUI();
        container.gridx=0;
        container.gridy=2;
        mainPanel.add(playerZone, container);
        */
        labelBackground.add(mainPanel);
        frame.addWindowListener(new MainLogGui.CloseListener());
        frame.add(labelBackground);
        frame.setSize(1280,720);
        frame.setLocation(0,0);
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
