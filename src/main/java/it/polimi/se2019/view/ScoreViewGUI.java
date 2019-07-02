package it.polimi.se2019.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class ScoreViewGUI extends JFrame  {

    public ScoreViewGUI(ArrayList<PlayerBoardView> opponentBoards, PlayerBoardView ownerBoard){
        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints container = new GridBagConstraints();
        mainPanel.setLayout(new GridBagLayout());

        container.gridx=0;
        container.gridy=0;

        mainPanel.add(new Label("your score:"+ownerBoard.getScore()+" points"), container);

        int x=0;

        for (int i = 0; i< opponentBoards.size();i++){
            container.gridx=0;
            container.gridy=x;
            mainPanel.add(new Label("your score:"+ opponentBoards.get(i).getScore() + " points"), container);
            x+=1;
        }
        container.gridx=0;
        container.gridy=x;
        mainPanel.add(new Label("Total kills: "), container);






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
