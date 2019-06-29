package it.polimi.se2019.view;

import it.polimi.se2019.model.cards.GunCard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class UserInteractionGUI extends UserInteraction {
    public String choice;

    @Override
    public String actionToRequest(int frenzy){

        JLabel label = new JLabel("Che macro azione vuoi fare?");

        JButton moveButton = new JButton("Move");
        moveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                choice = "move";
                JButton button = (JButton)e.getSource();
                SwingUtilities.getWindowAncestor(button).dispose();
            }
        });

        JButton grabButton = new JButton("Grab");
        grabButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                choice = "grab";
                JButton button = (JButton)e.getSource();
                SwingUtilities.getWindowAncestor(button).dispose();
            }
        });

        JButton shootButton = new JButton("Shoot");
        shootButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                choice = "shoot";
                JButton button = (JButton)e.getSource();
                SwingUtilities.getWindowAncestor(button).dispose();
            }
        });

        JButton frenzy1Button = new JButton("Frenzy move 1");
        frenzy1Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                choice = "frenzy1";
                JButton button = (JButton)e.getSource();
                SwingUtilities.getWindowAncestor(button).dispose();
            }
        });

        JButton frenzy2Button = new JButton("Frenzy move 2");
        frenzy2Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                choice = "frenzy2";
                JButton button = (JButton)e.getSource();
                SwingUtilities.getWindowAncestor(button).dispose();
            }
        });

        JButton frenzy3Button = new JButton("Frenzy move 3");
        frenzy3Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                choice = "frenzy3";
                JButton button = (JButton)e.getSource();
                SwingUtilities.getWindowAncestor(button).dispose();
            }
        });

        JButton frenzy4Button = new JButton("Frenzy move 4");
        frenzy4Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                choice = "frenzy4";
                JButton button = (JButton)e.getSource();
                SwingUtilities.getWindowAncestor(button).dispose();
            }
        });

        JButton frenzy5Button = new JButton("Frenzy move 5");
        frenzy5Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                choice = "frenzy5";
                JButton button = (JButton)e.getSource();
                SwingUtilities.getWindowAncestor(button).dispose();
            }
        });

        JPanel buttons = new JPanel();
        if(frenzy==0){
            buttons.add(moveButton);
            buttons.add(grabButton);
            buttons.add(shootButton);
        }else if(frenzy==1){
            buttons.add(frenzy1Button);
            buttons.add(frenzy2Button);
        }else {
            buttons.add(frenzy3Button);
            buttons.add(frenzy4Button);
            buttons.add(frenzy5Button);
        }

        JPanel content = new JPanel(new BorderLayout(8, 8));
        content.add(label, BorderLayout.CENTER);
        content.add(buttons, BorderLayout.SOUTH);

        JDialog dialog = new JDialog();
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setModal(true);
        dialog.setTitle("Selettore Mossa");
        dialog.getContentPane().add(content);
        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);

        return choice;

    }

    @Override
    public boolean yesOrNo(String message, String textYesButton, String textNoButton) {
        //TODO Jdialog che mostri il messaggio e chieda si o no (c'è la Jdialog apposta predisposta per Yes or no)
        return false;
    }

    @Override
    public String stringSelector(String message, ArrayList<String> listToChooseFrom) {
        //TODO Jdialog che mostri il messaggio passato per parametro ed elenchi le stringhe contenute in
        // listToChooseFrom e ne faccia selezionare una
        return null;
    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public boolean[] cardsToReload(GunCard[] cards, boolean[] reloadableCards) {
        //TODO Jdialog sullo stesso concetto del metodo di sopra, con 3 checkbox, dove vengono attivate a seconda
        // se ci sono carte arma che possono essere ricaricate.

        //In particolare scorrere cards, dove se un elemento è null la carta non c'è e abilitare la checkbox se
        // la carta è presente e scarica (lo vedi dall'altro vettore passato)

        JLabel label = new JLabel("Which weapons do you want to reload?");
        boolean[] chosen = new boolean[3];
        JCheckBox weapon1Check = new JCheckBox("Weapon 1");
        JCheckBox weapon2Check = new JCheckBox("Weapon 2");
        JCheckBox weapon3Check = new JCheckBox("Weapon 3");



        JButton buttonToSend = new JButton("Send");
        buttonToSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(weapon1Check.isSelected()){
                    chosen[0]= true;
                }
                if(weapon2Check.isSelected()){
                    chosen[1]= true;
                }
                if(weapon3Check.isSelected()){
                    chosen[2]= true;
                }

                JButton button = (JButton)e.getSource();
                SwingUtilities.getWindowAncestor(button).dispose();
            }
        });


        JPanel buttons = new JPanel();
        if(cards[0] != null && reloadableCards[0]){
            buttons.add(weapon1Check);
        }
        if(cards[1] != null && reloadableCards[1]){
            buttons.add(weapon2Check);
        }
        if(cards[2] != null && reloadableCards[2]){
            buttons.add(weapon3Check);
        }

        buttons.add(buttonToSend);

        JPanel content = new JPanel(new BorderLayout(8, 8));
        content.add(label, BorderLayout.CENTER);
        content.add(buttons, BorderLayout.SOUTH);

        JDialog dialog = new JDialog();
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setModal(true);
        dialog.setTitle("Selettore carte da ricaricare");
        dialog.getContentPane().add(content);
        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);

        return chosen;
    }



}
