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
    private MapViewGUI map;
    private PlayerZoneGUI playerZone;
    private BoardZoneGUI boardZone;
    private boolean frenzyStatus = false;

    public GameBoardGui(int config, ArrayList<PlayerBoardView> allBoards, PlayerBoardView ownerBoard, CellView[][] boardMatrix){
        Frame frame = new Frame("ADRENALINE");

        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints container = new GridBagConstraints();
        mainPanel.setLayout(new GridBagLayout());


        this.map = new MapViewGUI(config, boardMatrix);
        container.gridx=0;
        container.gridy=0;
        container.gridheight = 2;
        mainPanel.add(map, container);


        this.boardZone = new BoardZoneGUI(allBoards, ownerBoard, 0);
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
        boardZone.updateBoards(opponentBoards, ownerBoard, frenzyStatus, kills.getNumKills());
        map.setBoard(boardMatrix);
        playerZone.updateElements(ownerBoard, ownerHand, frenzyStatus);
    }

    /**
     * this method enables the action button for the player that will select the actions to
     */
    public void enableActionsButton(){
        this.playerZone.getHand().getAmmo().updateActionsButton();
    }
}
