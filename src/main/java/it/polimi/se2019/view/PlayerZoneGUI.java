package it.polimi.se2019.view;

import javax.swing.*;
import java.awt.*;

public class PlayerZoneGUI extends JPanel {
    private PlayerHandViewGUI hand;
    private PlayerBoardViewGUI board;

    /**
     * vreate the zone with the player's hand and the player's hand
     * @param ownerBoard data of the player's board
     */
    public PlayerZoneGUI (PlayerBoardView ownerBoard){

        GridBagConstraints container = new GridBagConstraints();
        setLayout(new GridBagLayout());

        setLayout(new GridBagLayout());
        this.hand = new PlayerHandViewGUI(ownerBoard.getAmmo());
        container.fill= GridBagConstraints.HORIZONTAL;
        container.gridx=0;
        container.gridy=0;

        add(hand, container);

        setLayout(new GridBagLayout());

        this.board = new PlayerBoardViewGUI(ownerBoard,420, 109);
        container.gridx=1;
        container.gridy=0;

        add(board, container);

    }


    /**
     * set the frenzy mode to "on"
     * @param ownerBoard  data of the player's board
     */
    public void setFrenzy(PlayerBoardView ownerBoard){
        this.board.setBoard(420, 109, true, ownerBoard);
    }

    /**
     * udate board and hand with the data in input
     * @param ownerBoard data of the player's board
     * @param handView data of the player's hand
     * @param frenzy boolean parameter that indicate if frenzy mode is on or not
     */
    public void updateElements(PlayerBoardView ownerBoard, PlayerHandView handView, boolean frenzy){
        this.board.setBoard(420, 109, frenzy, ownerBoard);
        this.hand.updateHand(handView.getGuns(), handView.getPowerups(), ownerBoard.getAmmo());
    }
}
