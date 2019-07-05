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

        this.board = new JLabel(boardImage);

        this.markerButton = new JButton("markers");
        markerButton.setLocation(225, 10);
        board.add(markerButton);

        ///////DAMAGE//////

        this.damageVector = new DamageIconGUI[12];
        char[] boardDamage = boardView.getDamageView().getDamage();
        for (int i = 0; i< 12; i++){
            damageVector[i]= new DamageIconGUI(boardDamage[i]);
        }

        setBoard(x,y, false, boardView);
        board.setIcon(boardImage);
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
            this.boardImage = new ImageIcon(new ImageIcon(getClass().getResource("/sprite/boards/normal/"+color.toString().toLowerCase()+".jpg")).getImage().getScaledInstance(x,y, Image.SCALE_DEFAULT));
        else
            this.boardImage = new ImageIcon(new ImageIcon(getClass().getResource("/sprite/boards/frenzy/"+color.toString().toLowerCase()+".jpg")).getImage().getScaledInstance(x,y, Image.SCALE_DEFAULT));

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

            mainPanel.add(new JLabel(i + ": " + colorSwitch(marks.get(i))),container);
        }

        JDialog dialog = new JDialog();
        dialog.setModal(true);
        dialog.setTitle("weapon Chooser");
        dialog.getContentPane().add(mainPanel);
        dialog.pack();
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
