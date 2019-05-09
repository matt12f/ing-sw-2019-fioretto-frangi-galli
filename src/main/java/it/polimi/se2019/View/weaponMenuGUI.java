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
public class weaponMenuGUI {
    private TextField weaponName;
    private TextField weaponInfo;
    private TextField weaponCharge;

    private Button baseAttackButton;
    private Button secondaryAttackButton;
    private Button thirdAttackButton ;

    public weaponMenuGUI(){
        Frame frame = new Frame("weapon menu");
        this.weaponName = new TextField();
        this.weaponInfo = new TextField();
        this.weaponCharge = new TextField();
        frame.add(weaponName);

        frame.add(weaponCharge);
        Panel attackPanel = new Panel();
        this.baseAttackButton= new Button("Base Attack");
        this.secondaryAttackButton= new Button("Second Attack");
        this.thirdAttackButton = new Button("Third Attack");

        attackPanel.add(baseAttackButton);
        attackPanel.add(secondaryAttackButton);
        attackPanel.add(thirdAttackButton);
        frame.add("bottom", attackPanel);
        frame.add(weaponInfo);
        frame.addWindowListener(new MainLogGui.CloseListener());
        frame.setSize(200,200);
        frame.setLocation(500,500);
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
