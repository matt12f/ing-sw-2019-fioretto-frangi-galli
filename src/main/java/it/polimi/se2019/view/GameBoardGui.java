package it.polimi.se2019.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;	//for CloseListener()
import java.awt.event.WindowAdapter;	//for CloseListener()
import java.lang.Integer;		//int from Model is passed as an Integer
import java.util.Observable;		//for update();
import java.util.Observer;

public class GameBoardGui implements Observer {
    public MapViewGUI map;
    public PlayerZoneGUI playerZone;
    public ScoreViewGUI scoreZone;
    public BoardZoneGUI boardZone;
    public GameBoardGui(int config){
        Frame frame = new Frame("ADRENALINE");

        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints container = new GridBagConstraints();
        mainPanel.setLayout(new GridBagLayout());


        this.map = new MapViewGUI();
        container.gridx=0;
        container.gridy=0;
        //mainPanel.add(map);


        this.scoreZone = new ScoreViewGUI();
        container.gridx=1;
        container.gridy=0;
        //mainPanel.add(scoreZone);


        this.boardZone = new BoardZoneGUI();
        container.gridx=1;
        container.gridy=1;
        //mainPanel.add(boardZone);

        this.playerZone = new PlayerZoneGUI();
        container.gridx=0;
        container.gridy=2;
        //mainPanel.add(playerZone);

        frame.addWindowListener(new MainLogGui.CloseListener());
        frame.add(mainPanel);
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
