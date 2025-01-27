package it.polimi.se2019.view;

import it.polimi.se2019.AdrenalineClient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class OpenerGUI {

    /**
     * builder that create the first frame that ask to the player what type of visualization he wants
     */
    public  OpenerGUI(){

        JDialog frame = new JDialog();

        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints container = new GridBagConstraints();


        JButton gui = new JButton("GUI");
        JButton cli = new JButton("CLI");



        container.gridx=0;
        container.gridy=0;
        container.gridwidth = 2;
        mainPanel.add(new JLabel("choose your interface"), container);


        container.gridx=0;
        container.gridy=1;
        mainPanel.add(gui, container);


        container.gridx=0;
        container.gridy=2;
        mainPanel.add(cli, container);

        frame.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new OpenerGUI.CloseListener());
        frame.add(mainPanel);
        frame.setSize(400,400);
        frame.setLocation(0,0);
        frame.setVisible(true);


        gui.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AdrenalineClient.setGui(true);
                frame.dispose();
                AdrenalineClient.ipServerRequest();
            }
        });

        cli.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AdrenalineClient.setGui(false);
                frame.dispose();
                AdrenalineClient.ipServerRequest();
            }

        });
    }

    public static class CloseListener extends WindowAdapter {
        public void windowClosing(WindowEvent e) {
            e.getWindow().setVisible(false);

        } //windowClosing()
    } //CloseListener
}
