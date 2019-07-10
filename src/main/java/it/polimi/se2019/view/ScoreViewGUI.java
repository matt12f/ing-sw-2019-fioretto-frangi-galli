package it.polimi.se2019.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class ScoreViewGUI extends JFrame  {


    /**
     * buid a frame hat shows every player's score and the total kills in the game
     * @param allBoards board of the opponents
     * @param ownerBoard board of the user
     * @param kills  number of kills
     */
    public ScoreViewGUI(ArrayList<PlayerBoardView> allBoards, PlayerBoardView ownerBoard, int kills){
        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints container = new GridBagConstraints();
        mainPanel.setLayout(new GridBagLayout());



        int x=1;

        for (int i = 0; i< allBoards.size();i++){
            container.gridx=0;
            container.gridy=x;
            mainPanel.add(new Label(allBoards.get(i).getColor().toString()+" Player:"+
                    allBoards.get(i).getScore() + " points"), container);
            x+=1;
        }
        container.gridx=0;
        container.gridy=x;
        mainPanel.add(new Label("Total kills: "+ kills), container);



        addWindowListener(new ScoreViewGUI.CloseListener());
        add(mainPanel);

        setSize(400,400);
        setLocation(0,0);

    }

    public static class CloseListener extends WindowAdapter {
        public void windowClosing(WindowEvent e) {
            e.getWindow().setVisible(false);
            //dispose();

        } //windowClosing()
    } //CloseListener

}
