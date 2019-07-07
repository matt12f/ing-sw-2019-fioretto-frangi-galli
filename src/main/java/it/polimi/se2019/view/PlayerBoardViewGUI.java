package it.polimi.se2019.view;

import it.polimi.se2019.enums.Color;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class
PlayerBoardViewGUI extends JPanel{

    private ImageIcon boardImage;
    protected JLabel board;
    protected  JButton markerButton;

    private DamageIconGUI[] damageVector;

    public PlayerBoardViewGUI(PlayerBoardView boardView, int x, int y){

        ///////DAMAGE//////

        this.damageVector = new DamageIconGUI[12];
        char[] boardDamage = boardView.getDamageView().getDamage();
        for (int i = 0; i< 12; i++){
            damageVector[i]= new DamageIconGUI(boardDamage[i]);
        }

        this.boardImage = new ImageIcon(new ImageIcon(getClass().getResource("/sprite/boards/normal/"+
                boardView.getColor().toString().toLowerCase()+".jpg")).getImage().getScaledInstance(x,y,
                Image.SCALE_SMOOTH));

        this.board = new JLabel(boardImage);

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

        this.markerButton = new JButton("marks");
        markerButton.setSize(45,15);
        markerButton.setLocation(230, 5);
        board.add(markerButton);

        for(int i=0;i<12;i++)
            board.add(damageVector[i]);

        add(board);

        markerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               showMarkers(boardView.getDamageView().getMarks());
            }});


    }


    public void setBoard( int x, int y, boolean frenzy, PlayerBoardView boardView){
        Color color = boardView.getColor();
        if(!frenzy)
            this.boardImage = new ImageIcon(new ImageIcon(getClass().getResource("/sprite/boards/normal/"+color.toString().toLowerCase()+".jpg")).getImage().getScaledInstance(x,y, Image.SCALE_SMOOTH));
        else
            this.boardImage = new ImageIcon(new ImageIcon(getClass().getResource("/sprite/boards/frenzy/"+color.toString().toLowerCase()+".jpg")).getImage().getScaledInstance(x,y, Image.SCALE_SMOOTH));

        this.board.setIcon(boardImage);

        char[] boardDamage = boardView.getDamageView().getDamage();
        for (int i = 0; i< 12; i++){
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
            mainPanel.add(new JLabel( "1 " + colorSwitch(marks.get(i))),container);
        }
        if(marks.isEmpty())
            mainPanel.add(new JLabel("no marks, you're good!"));

        JDialog dialog = new JDialog();
        dialog.setModal(true);
        dialog.setTitle("marks");
        dialog.getContentPane().add(mainPanel);
        dialog.pack();
        dialog.setSize(300,100);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }

    static String colorSwitch (char car){
        switch(car){
            case 'y': return "yellow";
            case 'b': return  "blue";
            case 'w': return "white";
            case 'g': return "green";
            case 'v': return "purple";
            default: return null;
        }
    }

}
