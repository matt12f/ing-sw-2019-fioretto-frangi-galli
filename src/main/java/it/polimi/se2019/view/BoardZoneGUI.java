package it.polimi.se2019.view;

import javax.swing.*;
import java.awt.*;

public class BoardZoneGUI extends JPanel {
    private ImageIcon boardImage1;
    private ImageIcon boardImage2;
    private ImageIcon boardImage3;
    private ImageIcon boardImage4;

    private JLabel board1;
    private JLabel board2;
    private JLabel board3;
    private JLabel board4;

    public BoardZoneGUI(){

        GridBagConstraints container = new GridBagConstraints();
       setLayout(new GridBagLayout());

    //TODO questa roba va poi rifatta in un for dinamicamente
        this.boardImage1 = new ImageIcon(new ImageIcon("src/main/sprite/boards/normal/blue.jpg").getImage().getScaledInstance(280,69,Image.SCALE_DEFAULT));
        this.board1 = new JLabel(boardImage1);
        board1.setIcon(boardImage1);

        container.gridx=0;
        container.gridy=0;

        add(board1, container);
    ///////////////////

        this.boardImage2 = new ImageIcon(new ImageIcon("src/main/sprite/boards/normal/green.jpg").getImage().getScaledInstance(280,69,Image.SCALE_DEFAULT));
        this.board2 = new JLabel(boardImage2);
        board2.setIcon(boardImage2);

        container.gridx=0;
        container.gridy=1;

        add(board2, container);

        ///////////////////
        this.boardImage3 = new ImageIcon(new ImageIcon("src/main/sprite/boards/normal/grey.jpg").getImage().getScaledInstance(280,69,Image.SCALE_DEFAULT));
        this.board3 = new JLabel(boardImage3);
        board3.setIcon(boardImage3);

        container.gridx=0;
        container.gridy=2;

        add(board3, container);

        ///////////////////
        this.boardImage4 = new ImageIcon(new ImageIcon("src/main/sprite/boards/normal/purple.jpg").getImage().getScaledInstance(280,69,Image.SCALE_DEFAULT));
        this.board4 = new JLabel(boardImage4);
        board4.setIcon(boardImage4);

        container.gridx=0;
        container.gridy=3;

        add(board4, container);


        /**
        Frame frame = new Frame("boards");
        frame.addWindowListener(new MainLogGui.CloseListener());
        frame.add(mainPanel);

        frame.setSize(1280,720);
        frame.setLocation(0,0);
        frame.setVisible(true);
        */
    }
}
