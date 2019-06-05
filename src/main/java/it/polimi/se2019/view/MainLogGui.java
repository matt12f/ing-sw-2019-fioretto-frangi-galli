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
        Frame frame = new Frame("LOG");
        JPanel mainpanel = new JPanel(new GridBagLayout());
        GridBagConstraints container = new GridBagConstraints();
        mainpanel.setLayout(new GridBagLayout());

        container.fill = GridBagConstraints.HORIZONTAL;
        container.gridx = 1;
        container.gridy = 1;
        mainpanel.add(new Label("choose your nickname and start a game"), container);
        this.nickTextField = new TextField();
        mainpanel.add(nickTextField, container);


        container.fill = GridBagConstraints.HORIZONTAL;
        container.gridx = 1;
        container.gridy = 2;
        mainpanel.add(new Label("insert ip address"), container);
        mainpanel.add(ipTextField, container);

        this.startGameButton= new Button("Start");
        container.fill = GridBagConstraints.HORIZONTAL;
        container.gridx = 1;
        container.gridy = 3;
        mainpanel.add(startGameButton, container);

        frame.addWindowListener(new CloseListener());
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
