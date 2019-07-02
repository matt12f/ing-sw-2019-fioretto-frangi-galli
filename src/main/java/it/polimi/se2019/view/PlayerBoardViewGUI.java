package it.polimi.se2019.view;

import it.polimi.se2019.enums.Color;

import javax.swing.*;
import java.awt.*;

public class PlayerBoardViewGUI extends JPanel{

    private ImageIcon boardImage;
    protected JLabel board;

    private DamageIconGUI damage0;
    private DamageIconGUI damage1;
    private DamageIconGUI damage2;
    private DamageIconGUI damage3;
    private DamageIconGUI damage4;
    private DamageIconGUI damage5;
    private DamageIconGUI damage6;
    private DamageIconGUI damage7;
    private DamageIconGUI damage8;
    private DamageIconGUI damage9;
    private DamageIconGUI damage10;
    private DamageIconGUI damage11;



    public PlayerBoardViewGUI(Color color, int x, int y){



        setBoard( color,  x,  y, false);
        this.board = new JLabel(boardImage);
        board.setIcon(boardImage);


        ///////DAMAGE//////

        this.damage0 = new DamageIconGUI();
        this.damage1 = new DamageIconGUI();
        this.damage2 = new DamageIconGUI();
        this.damage3 = new DamageIconGUI();
        this.damage4 = new DamageIconGUI();
        this.damage5 = new DamageIconGUI();
        this.damage6 = new DamageIconGUI();
        this.damage7 = new DamageIconGUI();
        this.damage8 = new DamageIconGUI();
        this.damage9 = new DamageIconGUI();
        this.damage10 = new DamageIconGUI();
        this.damage11 = new DamageIconGUI();




        damage0.setLocation(40, 40);
        damage1.setLocation(60, 40);
        damage2.setLocation(85, 40);
        damage3.setLocation(110, 40);
        damage4.setLocation(130, 40);
        damage5.setLocation(160, 40);
        damage6.setLocation(180, 40);
        damage7.setLocation(200, 40);
        damage8.setLocation(225, 40);
        damage9.setLocation(250, 40);
        damage10.setLocation(275, 40);
        damage11.setLocation(295, 40);


        board.add(damage0);
        board.add(damage1);
        board.add(damage2);
        board.add(damage3);
        board.add(damage4);
        board.add(damage5);
        board.add(damage6);
        board.add(damage7);
        board.add(damage8);
        board.add(damage9);
        board.add(damage10);
        board.add(damage11);

        add(board);





    }
    public void setBoard(Color color, int x, int y, boolean frenzy){

        if (!frenzy){
            switch (color){
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
            switch (color){
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

    }

}
