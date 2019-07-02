package it.polimi.se2019.view;

import it.polimi.se2019.enums.Color;

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
        this.board = new PlayerBoardViewGUI(Color.YELLOW,420, 109);
        container.gridx=1;
        container.gridy=0;

        add(board, container);



    }
    //TODO controllare colore!!!!
    public void setFrenzy(){
        this.board.setBoard(Color.YELLOW,420, 109, true);
    }
    public void updateBoard(boolean frenzy){
        this.board.setBoard(Color.YELLOW,420, 109, frenzy);
    }
}
