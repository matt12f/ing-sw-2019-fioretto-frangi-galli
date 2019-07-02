package it.polimi.se2019.view;

import it.polimi.se2019.enums.Color;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class BoardZoneGUI extends JPanel {

    private ArrayList<PlayerBoardViewGUI> boardsGUI;
    private PlayerBoardViewGUI board1;
    private PlayerBoardViewGUI board2;
    private PlayerBoardViewGUI board3;
    private PlayerBoardViewGUI board4;
    private JButton score;


    public BoardZoneGUI(ArrayList<PlayerBoardView> boards){

        GridBagConstraints container = new GridBagConstraints();
       setLayout(new GridBagLayout());

      //boards.get(0).setBoard(Color.BLUE, 420,109);


       //////////creazione dinamica della board zone///////

        updateBoards(boards, false);


        //////////aggiunta dinamica della board zone///////
        for (int i = 0; i<= boards.size();i++){
            container.gridx=0;
            container.gridy=i;
            add(boardsGUI.get(i), container);
        }




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

    public void updateBoards(ArrayList<PlayerBoardView> boards, boolean frenzy){

        for (int i = 0; i<= boards.size();i++){
            boardsGUI.get(0).setBoard(boards.get(i).getColor(), 420,109, frenzy);
        }

    }


}
