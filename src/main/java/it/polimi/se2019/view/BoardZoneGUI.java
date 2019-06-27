package it.polimi.se2019.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BoardZoneGUI extends JPanel {


    private PlayerBoardViewGUI board1;
    private PlayerBoardViewGUI board2;
    private PlayerBoardViewGUI board3;
    private PlayerBoardViewGUI board4;
    private JButton score;


    public BoardZoneGUI(){

        GridBagConstraints container = new GridBagConstraints();
       setLayout(new GridBagLayout());

    //TODO questa roba va poi rifatta in un for dinamicamente

        this.board1 = new PlayerBoardViewGUI('b', 420,109);


        container.gridx=0;
        container.gridy=0;

        add(board1, container);
    ///////////////////


        this.board2 = new PlayerBoardViewGUI('g',420,109);


        container.gridx=0;
        container.gridy=1;

        add(board2, container);

        ///////////////////

        this.board3 = new PlayerBoardViewGUI('r',420,109);


        container.gridx=0;
        container.gridy=2;

        add(board3, container);

        ///////////////////

        this.board4 = new PlayerBoardViewGUI('p',420,109);


        container.gridx=0;
        container.gridy=3;

        add(board4, container);

        this.score = new JButton("View Score");

        container.gridx=0;
        container.gridy=4;
        add(score,container);

        score.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ScoreViewGUI frame =new ScoreViewGUI();
                frame.setVisible(true);
            }

        });

    }


}
