package it.polimi.se2019.view;


import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.WindowEvent;	//for CloseListener()
import java.awt.event.WindowAdapter;	//for CloseListener()
import java.lang.Integer;		//int from Model is passed as an Integer
import java.util.Observable;		//for update();
import java.awt.event.ActionListener;	//for addController()
public class MainLogGui implements java.util.Observer{
    private TextField nickTextField;
    private TextField ipTextField;

    private Button startGameButton;



    public MainLogGui(){

        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints container = new GridBagConstraints();
        mainPanel.setLayout(new GridBagLayout());


        container.gridx = 0;
        container.gridy = 1;
        mainPanel.add(new Label("choose your nickname and start a game"), container);
        this.nickTextField = new TextField();
        container.gridx = 0;
        container.gridy = 2;
        mainPanel.add(nickTextField, container);



        container.gridx = 0;
        container.gridy = 3;
        mainPanel.add(new Label("insert ip address"), container);
        container.gridx = 0;
        container.gridy = 4;
        mainPanel.add(ipTextField, container);

        this.startGameButton= new Button("Start");
        container.gridx = 0;
        container.gridy = 5;
        mainPanel.add(startGameButton, container);

        Frame frame = new Frame("LOG");

        frame.addWindowListener(new CloseListener());
        frame.add(mainPanel);
        frame.setSize(600,600);
        frame.setLocation(100,100);
        frame.setVisible(true);



    }



    public void update(Observable obs, Object obj) {


    } //update()

    public void addController(ActionListener controller){

        startGameButton.addActionListener(controller);


    } //addController()

    public static class CloseListener extends WindowAdapter {
        public void windowClosing(WindowEvent e) {
            e.getWindow().setVisible(false);
            System.exit(0);
        } //windowClosing()
    } //CloseListener
}
