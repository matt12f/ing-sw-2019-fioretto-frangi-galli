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
        JRadioButton move = new JRadioButton("grab");
        JRadioButton shoot = new JRadioButton("shoot");


        JRadioButton frenzy1 = new JRadioButton("frenzy move 1");
        JRadioButton frenzy2 = new JRadioButton("frenzy move 2");
        JRadioButton frenzy3 = new JRadioButton("frenzy move 3");


        JRadioButton frenzy4 = new JRadioButton("frenzy move 1");
        JRadioButton frenzy5 = new JRadioButton("frenzy move 2");


        ButtonGroup groupNormal = new ButtonGroup();
        groupNormal.add(move);
        groupNormal.add(grab);
        groupNormal.add(shoot);



        ButtonGroup groupFrenzy1 = new ButtonGroup();
        groupFrenzy1.add(frenzy1);
        groupFrenzy1.add(frenzy2);
        groupFrenzy1.add(frenzy3);


        ButtonGroup groupFrenzy2 = new ButtonGroup();
        groupFrenzy2.add(frenzy4);
        groupFrenzy2.add(frenzy5);






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



        }else if (frenzy == 2){

            container.gridx= 0;
            container.gridy= 0;
            mainPanel.add(frenzy4,container);

            container.gridx= 1;
            container.gridy=0;
            mainPanel.add(frenzy5,container);


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

        send.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(frenzy == 0){
                    if (move.isSelected()){
                        chosen = "move";
                    } else if (grab.isSelected()){
                        chosen = "grab";
                    } else if(shoot.isSelected()){
                        chosen = "shoot";
                    }
                }
            }
        });
        //Nota: questo return non attende il click del pulsante
        return chosen;
    }


    public static class CloseListener extends WindowAdapter {
        public void windowClosing(WindowEvent e) {
            e.getWindow().setVisible(false);

        } //windowClosing()
    } //CloseListener

}
