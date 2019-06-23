package it.polimi.se2019.view;

import javax.swing.*;
import java.awt.*;

public class PlayerBoardViewGUI extends JPanel{

    private ImageIcon boardImage;
    protected JLabel board;

    public PlayerBoardViewGUI(){

        this.boardImage = new ImageIcon(new ImageIcon("src/main/sprite/boards/normal/yellow.jpg").getImage().getScaledInstance(340,109, Image.SCALE_DEFAULT));

        this.board = new JLabel(boardImage);
        board.setIcon(boardImage);


        GridBagConstraints container = new GridBagConstraints();
        setLayout(new GridBagLayout());
        add(board, container);
        //board.add(mainPanel);



    }

}
