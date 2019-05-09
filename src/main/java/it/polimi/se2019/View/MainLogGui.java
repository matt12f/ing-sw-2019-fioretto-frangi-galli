package it.polimi.se2019.view;


import java.awt.Button;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.Panel;
import java.awt.Frame;
import java.awt.TextField;
import java.awt.Label;
import java.awt.event.WindowEvent;	//for CloseListener()
import java.awt.event.WindowAdapter;	//for CloseListener()
import java.lang.Integer;		//int from Model is passed as an Integer
import java.util.Observable;		//for update();
import java.awt.event.ActionListener;	//for addController()
public class MainLogGui implements java.util.Observer{
    private TextField nickTextField;
    private Button createGameButton;
    private Button joinGameButton;
    private Button startGameButton;
    //radioGroup
    private ButtonGroup groupRadio;
    private JRadioButton radio1;
    private JRadioButton radio2;
    private JRadioButton radio3;
    private JRadioButton radio4;


    public MainLogGui(){
        Frame frame = new Frame("LOG");
        frame.add("Top", new Label("choose your nickname and start a game"));
        this.nickTextField = new TextField();
        frame.add("Center", nickTextField);
        Panel mainPanel = new Panel();
        this.createGameButton= new Button("Create Game");
        this.joinGameButton= new Button("join Game");
        this.startGameButton= new Button("Start");
        this. radio1 = new JRadioButton("1");
        radio1.setSelected(true);
        this.radio2 = new JRadioButton("2");
        this.radio3 = new JRadioButton("3");
        this.radio4 = new JRadioButton("4");

        this.groupRadio = new ButtonGroup();
        groupRadio.add(radio1);
        groupRadio.add(radio2);
        groupRadio.add(radio3);
        groupRadio.add(radio4);


        mainPanel.add(createGameButton);
        mainPanel.add(joinGameButton);
        mainPanel.add(startGameButton);
        frame.add("bottom", mainPanel);
        frame.addWindowListener(new CloseListener());
        frame.setSize(600,600);
        frame.setLocation(100,100);
        frame.setVisible(true);

    }


    public void update(Observable obs, Object obj) {


    } //update()

    public void addController(ActionListener controller){
        createGameButton.addActionListener(controller);
        joinGameButton.addActionListener(controller);
        startGameButton.addActionListener(controller);
        radio1.addActionListener(controller);
        radio2.addActionListener(controller);
        radio3.addActionListener(controller);
        radio4.addActionListener(controller);

    } //addController()

    public static class CloseListener extends WindowAdapter {
        public void windowClosing(WindowEvent e) {
            e.getWindow().setVisible(false);
            System.exit(0);
        } //windowClosing()
    } //CloseListener
}
