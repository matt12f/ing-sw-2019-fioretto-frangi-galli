package it.polimi.se2019.view;

import javax.swing.*;
import java.awt.*;
import javax.imageio.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;	//for CloseListener()
import java.awt.event.WindowAdapter;	//for CloseListener()
import java.lang.Integer;		//int from Model is passed as an Integer
import java.util.ArrayList;
import java.util.Observable;		//for update();
import java.util.Observer;
import java.awt.*;

public class GameBoardGui  {
    public MapViewGUI map;
    public PlayerZoneGUI playerZone;
    public ScoreViewGUI scoreZone;
    public BoardZoneGUI boardZone;
    public ImageIcon background;
    public boolean frenzyStatus = false;
    public GameBoardGui(int config, ArrayList<PlayerBoardView> opponentBoards,PlayerBoardView ownerBoard, CellView[][] boardMatrix){
        Frame frame = new Frame("ADRENALINE");

        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints container = new GridBagConstraints();
        mainPanel.setLayout(new GridBagLayout());


        this.background = new ImageIcon(new ImageIcon("src/main/sprite/maps/background.png").getImage().getScaledInstance(1280,720,Image.SCALE_DEFAULT));
        JLabel labelBackground = new JLabel(background);
        labelBackground.setIcon(background);


        //mainPanel.setLayout(new GridBagLayout());
        //container.anchor = GridBagConstraints.NORTHWEST;
        this.map = new MapViewGUI(config, boardMatrix);
        container.gridx=0;
        container.gridy=0;
        container.gridheight = 2;
        mainPanel.add(map, container);



        //mainPanel.setLayout(new GridBagLayout());
        //container.anchor = GridBagConstraints.EAST;
        this.boardZone = new BoardZoneGUI(opponentBoards, ownerBoard, 0);
        container.gridx=1;
        container.gridy=1;
        mainPanel.add(boardZone, container);


        mainPanel.setLayout(new GridBagLayout());
        container.anchor = GridBagConstraints.SOUTH;
        this.playerZone = new PlayerZoneGUI(ownerBoard);
        container.gridx=0;
        container.gridy=2;
        container.gridwidth = 2;
        mainPanel.add(playerZone, container);

        labelBackground.add(mainPanel);
        frame.addWindowListener(new GameBoardGui.CloseListener());
        frame.add(mainPanel);
        frame.setSize(1280,720);
        frame.setLocation(0,0);
        frame.setVisible(true);

    }



    public static class CloseListener extends WindowAdapter {
        public void windowClosing(WindowEvent e) {
            e.getWindow().setVisible(false);
            System.exit(0);
        } //windowClosing()
    } //CloseListener


    public void setFrenzy(ArrayList<PlayerBoardView> opponentBoards,PlayerBoardView ownerBoard){
        boardZone.updateBoards(opponentBoards, ownerBoard, true, 0);
        playerZone.setFrenzy(ownerBoard);
        frenzyStatus = true;
    }

    public void updateBoardGame(ArrayList<PlayerBoardView> opponentBoards,PlayerBoardView ownerBoard, CellView[][] boardMatrix, KillShotTrackerView kills, PlayerHandView ownerHand){
        boardZone.updateBoards(opponentBoards, ownerBoard, frenzyStatus, kills.getSkulls());
        map.setBoard(boardMatrix);
        playerZone.updateElements(ownerBoard,ownerHand, frenzyStatus);


    }
}
