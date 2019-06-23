package it.polimi.se2019.view;

import javax.swing.*;
import java.awt.*;

public class ScoreViewGUI  {

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


        Frame frame = new Frame("boards");
        frame.addWindowListener(new MainLogGui.CloseListener());
        frame.add(mainPanel);

        frame.setSize(400,400);
        frame.setLocation(0,0);
        frame.setVisible(true);
    }

}
