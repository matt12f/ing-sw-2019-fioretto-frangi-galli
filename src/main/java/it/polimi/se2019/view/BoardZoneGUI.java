package it.polimi.se2019.view;

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

    /**
     * constructor that create the zone to show the opponent's boards
     * @param boards is the list of the opponents' oards
     * @param ownerBoard board of the user/player
     * @param kills number of the actual kills in the game
     */
    public BoardZoneGUI(ArrayList<PlayerBoardView> boards, PlayerBoardView ownerBoard, int kills){

        GridBagConstraints container = new GridBagConstraints();
        setLayout(new GridBagLayout());

        this.boardsDynamic = boards;
        this.ownerBoardDynamic = ownerBoard;
        this.killsDynamic = kills;


        this.score = new JButton("View Score");

        container.gridx=0;
        container.gridy=0;
        add(score,container);


       //creating board zone dinamically
        this.boardsGUI = new ArrayList<>();

        for (int i = 1; i< boards.size();i++)
           boardsGUI.add(new PlayerBoardViewGUI( boards.get(i),420,109 ));


        //adding board zone dinamically
        for (int i = 0; i < boardsGUI.size(); i++){
            container.gridx=0;
            container.gridy=i+1;
            add(boardsGUI.get(i), container);
        }


        score.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ScoreViewGUI frame =new ScoreViewGUI(boardsDynamic, ownerBoardDynamic, killsDynamic );
                frame.setVisible(true);
            }

        });

    }

    /**
     * this method update all the boards and the score after a player's move
     * @param boards is the list of the opponents' oards
     * @param ownerBoard board of the user/player
     * @param frenzy is the boolean indicator that says if frenzy mode is on or off
     * @param kills number of the actual kills in the game
     */
    public void updateBoards(ArrayList<PlayerBoardView> boards,PlayerBoardView ownerBoard, boolean frenzy, int kills){

        for (int i = 0; i< boardsGUI.size(); i++){
            boardsGUI.get(i).setBoard( 420,109, frenzy, boards.get(i+1));
        }

        this.boardsDynamic = boards;
        this.ownerBoardDynamic= ownerBoard;
        this.killsDynamic = kills;

    }


}
