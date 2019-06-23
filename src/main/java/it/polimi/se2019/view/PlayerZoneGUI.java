package it.polimi.se2019.view;

import javax.swing.*;
import java.awt.*;

public class PlayerZoneGUI extends JPanel {
    public PlayerHandViewGUI hand;
    public PlayerBoardViewGUI board;

    public PlayerZoneGUI (){

        GridBagConstraints container = new GridBagConstraints();
        setLayout(new GridBagLayout());

        setLayout(new GridBagLayout());
        this.hand = new PlayerHandViewGUI();
        container.fill= GridBagConstraints.HORIZONTAL;
        container.gridx=0;
        container.gridy=0;

        add(hand, container);

        setLayout(new GridBagLayout());
        this.board = new PlayerBoardViewGUI('y',420, 109);
        container.gridx=1;
        container.gridy=0;

        add(board, container);

        /**
        Frame frame = new Frame("ADRENALINE");
        frame.addWindowListener(new MainLogGui.CloseListener());
        frame.add(mainPanel);

        frame.setSize(1280,720);
        frame.setLocation(0,0);
        frame.setVisible(true);*/

    }
}
