package it.polimi.se2019.view;

import it.polimi.se2019.enums.Color;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class BoardZoneGUI extends JPanel {

    private ArrayList<PlayerBoardViewGUI> boardsGUI;
    private ArrayList<PlayerBoardView> boardsDynamic;
    private PlayerBoardView ownerBoardDynamic;
    private JButton score;
    private int killsDynamic;


    public BoardZoneGUI(ArrayList<PlayerBoardView> boards,PlayerBoardView ownerBoard, int kills){

        GridBagConstraints container = new GridBagConstraints();
       setLayout(new GridBagLayout());

     this.boardsDynamic = boards;
     this.ownerBoardDynamic= ownerBoard;
     this.killsDynamic = kills;


       //////////creazione dinamica della board zone///////
        this.boardsGUI= new ArrayList<>();
        for (int i = 0; i< boards.size();i++){
           boardsGUI.add(new PlayerBoardViewGUI( boards.get(i),420,109 )) ;
        }



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
                ScoreViewGUI frame =new ScoreViewGUI(boardsDynamic,ownerBoardDynamic, killsDynamic );
                frame.setVisible(true);
            }

        });

    }

    public void updateBoards(ArrayList<PlayerBoardView> boards,PlayerBoardView ownerBoard, boolean frenzy, int kills){

        for (int i = 0; i< boards.size();i++){
            boardsGUI.get(i).setBoard( 420,109, frenzy, boards.get(i));
        }
        this.boardsDynamic = boards;
        this.ownerBoardDynamic= ownerBoard;
        this.killsDynamic = kills;

    }


}
