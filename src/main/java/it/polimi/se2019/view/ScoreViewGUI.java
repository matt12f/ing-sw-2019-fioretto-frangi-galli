package it.polimi.se2019.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ScoreViewGUI extends JFrame  {

    public ScoreViewGUI(){
        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints container = new GridBagConstraints();
        mainPanel.setLayout(new GridBagLayout());

        container.gridx=0;
        container.gridy=0;

        mainPanel.add(new Label("Player 1: 0 points"), container);

        container.gridx=0;
        container.gridy=1;

        mainPanel.add(new Label("Player 2: 0 points"), container);

        container.gridx=0;
        container.gridy=2;

        mainPanel.add(new Label("Player 3: 0 points"), container);

        container.gridx=0;
        container.gridy=3;

        mainPanel.add(new Label("Player 4: 0 points"), container);

        container.gridx=0;
        container.gridy=4;

        mainPanel.add(new Label("Player 5: 0 points"), container);



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
