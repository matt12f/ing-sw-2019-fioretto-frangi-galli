package it.polimi.se2019.view;

import javax.swing.*;
import java.awt.*;

public class PlayerBoardViewGUI extends JPanel{

    private ImageIcon boardImage;
    protected JLabel board;

    public PlayerBoardViewGUI(char color, int x, int y){

        switch (color){
            case 'y': this.boardImage = new ImageIcon(new ImageIcon("src/main/sprite/boards/normal/yellow.jpg").getImage().getScaledInstance(x,y, Image.SCALE_DEFAULT));
                break;
            case 'b': this.boardImage = new ImageIcon(new ImageIcon("src/main/sprite/boards/normal/blue.jpg").getImage().getScaledInstance(x,y, Image.SCALE_DEFAULT));
                break;
            case 'p': this.boardImage = new ImageIcon(new ImageIcon("src/main/sprite/boards/normal/purple.jpg").getImage().getScaledInstance(x,y, Image.SCALE_DEFAULT));
                break;
            case 'g': this.boardImage = new ImageIcon(new ImageIcon("src/main/sprite/boards/normal/green.jpg").getImage().getScaledInstance(x,y, Image.SCALE_DEFAULT));
                break;
            case 'r': this.boardImage = new ImageIcon(new ImageIcon("src/main/sprite/boards/normal/grey.jpg").getImage().getScaledInstance(x,y, Image.SCALE_DEFAULT));
                break;

        }


        this.board = new JLabel(boardImage);
        board.setIcon(boardImage);


        GridBagConstraints container = new GridBagConstraints();
        setLayout(new GridBagLayout());
        add(board, container);
        //board.add(mainPanel);



    }

}
