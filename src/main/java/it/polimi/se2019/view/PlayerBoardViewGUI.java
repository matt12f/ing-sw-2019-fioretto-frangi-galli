package it.polimi.se2019.view;

import javax.swing.*;
import java.awt.*;

public class PlayerBoardViewGUI extends JPanel{

    private ImageIcon boardImage;
    protected JLabel board;

    private DamageIconGUI[] damageGUI;

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


        ///////DAMAGE//////

        this.damageGUI = new DamageIconGUI[10];


/**
        DamageIconGUI comodo = damageGUI[0];
        comodo.setLocation(60, 40);

        damageGUI[1].setLocation(85, 40);
        damageGUI[2].setLocation(110, 40);
        damageGUI[3].setLocation(130, 40);
        damageGUI[4].setLocation(160, 40);
        damageGUI[5].setLocation(180, 40);
        damageGUI[6].setLocation(200, 40);
        damageGUI[7].setLocation(225, 40);
        damageGUI[8].setLocation(250, 40);
        damageGUI[9].setLocation(275, 40);
        damageGUI[10].setLocation(295, 40);
        */



        add(board);





    }

}
