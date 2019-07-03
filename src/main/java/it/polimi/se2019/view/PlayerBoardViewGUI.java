package it.polimi.se2019.view;

import it.polimi.se2019.enums.Color;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PlayerBoardViewGUI extends JPanel{

    private ImageIcon boardImage;
    protected JLabel board;
    protected  JButton markerButton;


    private DamageIconGUI[] damageVector;


    public PlayerBoardViewGUI(PlayerBoardView boardView, int x, int y){



        setBoard(   x,  y, false, boardView );
        this.board = new JLabel(boardImage);
        board.setIcon(boardImage);

        this.markerButton = new JButton("markers");
        markerButton.setLocation(225, 10);

        ///////DAMAGE//////


        this.damageVector = new DamageIconGUI[12];



        damageVector[0].setLocation(40, 40);
        damageVector[1].setLocation(60, 40);
        damageVector[2].setLocation(85, 40);
        damageVector[3].setLocation(110, 40);
        damageVector[4].setLocation(130, 40);
        damageVector[5].setLocation(160, 40);
        damageVector[6].setLocation(180, 40);
        damageVector[7].setLocation(200, 40);
        damageVector[8].setLocation(225, 40);
        damageVector[9].setLocation(250, 40);
        damageVector[10].setLocation(275, 40);
        damageVector[11].setLocation(295, 40);


        board.add(damageVector[0]);
        board.add(damageVector[1]);
        board.add(damageVector[2]);
        board.add(damageVector[3]);
        board.add(damageVector[4]);
        board.add(damageVector[5]);
        board.add(damageVector[6]);
        board.add(damageVector[7]);
        board.add(damageVector[8]);
        board.add(damageVector[9]);
        board.add(damageVector[10]);
        board.add(damageVector[11]);

        add(board);

        markerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               showMarkers(boardView.getDamageView().getMarks());
            }});



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

    public void showMarkers(ArrayList<Character> marks){
        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints container = new GridBagConstraints();
        mainPanel.setLayout(new GridBagLayout());

        for (int i=0 ; i< marks.size();i++){
            container.gridx=0;
            container.gridy=i;

            mainPanel.add(new JLabel(i + ": " + marks.get(i)),container);
        }



        JDialog dialog = new JDialog();
        dialog.setModal(true);
        dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        dialog.setTitle("weapon Chooser");
        dialog.getContentPane().add(mainPanel);
        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }

}
