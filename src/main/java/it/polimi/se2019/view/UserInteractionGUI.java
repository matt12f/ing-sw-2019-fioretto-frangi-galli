package it.polimi.se2019.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class UserInteractionGUI extends UserInteraction {
    public String chosen;
    @Override
    public String actionToRequest(int frenzy) {
        Frame frame = new Frame("Choose the macro action to perform");

        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints container = new GridBagConstraints();
        mainPanel.setLayout(new GridBagLayout());

        JRadioButton grab = new JRadioButton("move");
        grab.setMnemonic(KeyEvent.VK_D);
        grab.setActionCommand("move");


        JRadioButton move = new JRadioButton("grab");
        move.setMnemonic(KeyEvent.VK_D);
        move.setActionCommand("grab");

        JRadioButton shoot = new JRadioButton("shoot");
        shoot.setMnemonic(KeyEvent.VK_D);
        shoot.setActionCommand("shoot");



        JRadioButton frenzy1 = new JRadioButton("frenzy move 1");
        frenzy1.setMnemonic(KeyEvent.VK_D);
        frenzy1.setActionCommand("frenzy1");

        JRadioButton frenzy2 = new JRadioButton("frenzy move 2");
        frenzy2.setMnemonic(KeyEvent.VK_D);
        frenzy2.setActionCommand("frenzy2");

        JRadioButton frenzy3 = new JRadioButton("frenzy move 3");
        frenzy3.setMnemonic(KeyEvent.VK_D);
        frenzy3.setActionCommand("frenzy3");





        JRadioButton frenzy4 = new JRadioButton("frenzy move 1");
        frenzy4.setMnemonic(KeyEvent.VK_D);
        frenzy4.setActionCommand("frenzy4");

        JRadioButton frenzy5 = new JRadioButton("frenzy move 2");
        frenzy5.setMnemonic(KeyEvent.VK_D);
        frenzy5.setActionCommand("frenzy5");

        ButtonGroup groupNormal = new ButtonGroup();
        groupNormal.add(grab);
        groupNormal.add(move);
        groupNormal.add(shoot);



        ButtonGroup groupFrenzy1 = new ButtonGroup();
        groupNormal.add(frenzy1);
        groupNormal.add(frenzy2);
        groupNormal.add(frenzy3);


        ButtonGroup groupFrenzy2 = new ButtonGroup();
        groupNormal.add(frenzy4);
        groupNormal.add(frenzy5);






        if(frenzy == 0){
            container.gridx=0;
            container.gridy=0;
            mainPanel.add(grab,container);

            container.gridx=1;
            container.gridy=0;
            mainPanel.add(move,container);

            container.gridx=2;
            container.gridy=0;
            mainPanel.add(shoot,container);

            grab.addActionListener(this::actionPerformed);
            move.addActionListener(this::actionPerformed);
            shoot.addActionListener(this::actionPerformed);

        }else if (frenzy == 1){
            container.gridx=0;
            container.gridy=0;
            mainPanel.add(frenzy1,container);

            container.gridx=1;
            container.gridy=0;
            mainPanel.add(frenzy2,container);

            container.gridx=2;
            container.gridy=0;
            mainPanel.add(frenzy3,container);

            frenzy1.addActionListener(this::actionPerformed);
            frenzy2.addActionListener(this::actionPerformed);
            frenzy3.addActionListener(this::actionPerformed);

        }else if (frenzy == 2){

            container.gridx= 0;
            container.gridy= 0;
            mainPanel.add(frenzy4,container);

            container.gridx= 1;
            container.gridy=0;
            mainPanel.add(frenzy5,container);

            frenzy4.addActionListener(this::actionPerformed);
            frenzy5.addActionListener(this::actionPerformed);
        }

        JButton send = new JButton("Send action request");
        container.gridx= 1;
        container.gridy= 1;
        mainPanel.add(send,container);

        frame.addWindowListener(new UserInteractionGUI.CloseListener());
        frame.add(mainPanel);
        frame.setSize(400,400);
        frame.setLocation(0,0);
        frame.setVisible(true);

        /**send.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                frame.setVisible(false);
            }

        });*/
        return chosen;
    }

    public void actionPerformed(ActionEvent e) {
        chosen =  e.getActionCommand();

    }


    public static class CloseListener extends WindowAdapter {
        public void windowClosing(WindowEvent e) {
            e.getWindow().setVisible(false);

        } //windowClosing()
    } //CloseListener

}
