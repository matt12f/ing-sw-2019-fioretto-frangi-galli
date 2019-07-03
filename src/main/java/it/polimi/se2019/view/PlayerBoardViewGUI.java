package it.polimi.se2019.view;

import it.polimi.se2019.enums.Color;

import javax.swing.*;
import java.awt.*;

public class PlayerBoardViewGUI extends JPanel{

    private ImageIcon boardImage;
    protected JLabel board;


    private DamageIconGUI[] damageVector;


    public PlayerBoardViewGUI(PlayerBoardView boardView, int x, int y){



        setBoard(   x,  y, false, boardView );
        this.board = new JLabel(boardImage);
        board.setIcon(boardImage);


        ///////DAMAGE//////


        this.damageVector = new DamageIconGUI[12];



        damageVector[0].setLocation(40, 40);
        damageVector[0].setLocation(60, 40);
        damageVector[0].setLocation(85, 40);
        damageVector[0].setLocation(110, 40);
        damageVector[0].setLocation(130, 40);
        damageVector[0].setLocation(160, 40);
        damageVector[0].setLocation(180, 40);
        damageVector[0].setLocation(200, 40);
        damageVector[0].setLocation(225, 40);
        damageVector[0].setLocation(250, 40);
        damageVector[0].setLocation(275, 40);
        damageVector[0].setLocation(295, 40);


        board.add(damageVector[0]);
        board.add(damageVector[0]);
        board.add(damageVector[0]);
        board.add(damageVector[0]);
        board.add(damageVector[0]);
        board.add(damageVector[0]);
        board.add(damageVector[0]);
        board.add(damageVector[0]);
        board.add(damageVector[0]);
        board.add(damageVector[0]);
        board.add(damageVector[0]);
        board.add(damageVector[0]);

        add(board);





    }
    public void setBoard( int x, int y, boolean frenzy, PlayerBoardView boardView){

        if (!frenzy){
            switch (boardView.getColor()){
                case YELLOW: this.boardImage = new ImageIcon(new ImageIcon("src/main/sprite/boards/normal/yellow.jpg").getImage().getScaledInstance(x,y, Image.SCALE_DEFAULT));
                    break;
                case BLUE: this.boardImage = new ImageIcon(new ImageIcon("src/main/sprite/boards/normal/blue.jpg").getImage().getScaledInstance(x,y, Image.SCALE_DEFAULT));
                    break;
                case VIOLET: this.boardImage = new ImageIcon(new ImageIcon("src/main/sprite/boards/normal/purple.jpg").getImage().getScaledInstance(x,y, Image.SCALE_DEFAULT));
                    break;
                case GREEN: this.boardImage = new ImageIcon(new ImageIcon("src/main/sprite/boards/normal/green.jpg").getImage().getScaledInstance(x,y, Image.SCALE_DEFAULT));
                    break;
                case WHITE: this.boardImage = new ImageIcon(new ImageIcon("src/main/sprite/boards/normal/white.jpg").getImage().getScaledInstance(x,y, Image.SCALE_DEFAULT));
                    break;
            }
        }else {
            switch (boardView.getColor()){
                case YELLOW: this.boardImage = new ImageIcon(new ImageIcon("src/main/sprite/boards/frenzy/yellow.jpg").getImage().getScaledInstance(x,y, Image.SCALE_DEFAULT));
                    break;
                case BLUE: this.boardImage = new ImageIcon(new ImageIcon("src/main/sprite/boards/frenzy/blue.jpg").getImage().getScaledInstance(x,y, Image.SCALE_DEFAULT));
                    break;
                case VIOLET: this.boardImage = new ImageIcon(new ImageIcon("src/main/sprite/boards/frenzy/purple.jpg").getImage().getScaledInstance(x,y, Image.SCALE_DEFAULT));
                    break;
                case GREEN: this.boardImage = new ImageIcon(new ImageIcon("src/main/sprite/boards/frenzy/green.jpg").getImage().getScaledInstance(x,y, Image.SCALE_DEFAULT));
                    break;
                case WHITE: this.boardImage = new ImageIcon(new ImageIcon("src/main/sprite/boards/frenzy/white.jpg").getImage().getScaledInstance(x,y, Image.SCALE_DEFAULT));
                    break;
            }
        }

        char[] boardDamage = boardView.getDamageView().getDamage();
        for (int i = 0; i< 2; i++){
            damageVector[i].setDamageImage(boardDamage[i]);
        }


    }

}
