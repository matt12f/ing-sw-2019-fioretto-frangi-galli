package it.polimi.se2019.view;

import javax.swing.*;
import java.awt.*;

public class BoardZoneGUI extends JPanel {


    private PlayerBoardViewGUI board1;
    private PlayerBoardViewGUI board2;
    private PlayerBoardViewGUI board3;
    private PlayerBoardViewGUI board4;

    public BoardZoneGUI(){

        GridBagConstraints container = new GridBagConstraints();
       setLayout(new GridBagLayout());

    //TODO questa roba va poi rifatta in un for dinamicamente

        this.board1 = new PlayerBoardViewGUI('b', 320,89);


        container.gridx=0;
        container.gridy=0;

        add(board1, container);
    ///////////////////


        this.board2 = new PlayerBoardViewGUI('g',320,89);


        container.gridx=0;
        container.gridy=1;

        add(board2, container);

        ///////////////////

        this.board3 = new PlayerBoardViewGUI('r',320,89);


        container.gridx=0;
        container.gridy=2;

        add(board3, container);

        ///////////////////

        this.board4 = new PlayerBoardViewGUI('p',320,89);


        container.gridx=0;
        container.gridy=3;

        add(board4, container);


        /**
        Frame frame = new Frame("boards");
        frame.addWindowListener(new MainLogGui.CloseListener());
        frame.add(mainPanel);

        frame.setSize(1280,720);
        frame.setLocation(0,0);
        frame.setVisible(true);
        */
    }
}
