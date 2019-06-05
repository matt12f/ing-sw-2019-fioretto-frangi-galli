package it.polimi.se2019.view;

import java.awt.Button;
import java.awt.Panel;
import java.awt.Frame;
import java.awt.TextField;
import java.awt.Label;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;	//for CloseListener()
import java.awt.event.WindowAdapter;	//for CloseListener()
import java.lang.Integer;		//int from Model is passed as an Integer
import java.util.Observable;		//for update();
import java.util.Observer;

public class GameBoardGui implements Observer {

    public GameBoardGui(int config){
        Frame frame = new Frame("ADRENALINE");

        Panel mainPanel = new Panel();
        Panel mapPanel = new Panel();
        Panel ownPanel = new Panel();
        Panel playersPanel = new Panel();
        Panel handPanel = new Panel();
        Panel boardPanel = new Panel();

        frame.add("main", mainPanel);
        frame.addWindowListener(new MainLogGui.CloseListener());
        frame.setSize(1280,720);
        frame.setLocation(0,0);
        frame.setVisible(true);
    }

    public void update(Observable obs, Object obj) {


    } //update()

    public void addController(ActionListener controller){


    } //addController()

    public static class CloseListener extends WindowAdapter {
        public void windowClosing(WindowEvent e) {
            e.getWindow().setVisible(false);
            System.exit(0);
        } //windowClosing()
    } //CloseListener
}
