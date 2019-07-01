package it.polimi.se2019.view;

import it.polimi.se2019.model.cards.GunCard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class UserInteractionGUI extends UserInteraction {
    private String choice;
    private String chosenByList;
    private boolean yesNoChoice;
    private int map;
    private int skull;

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
        dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
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
        JLabel label = new JLabel(message);

        JButton yesButton = new JButton(textYesButton);
        JButton noButton = new JButton(textNoButton);

        yesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                yesNoChoice = true;

                JButton button = (JButton)e.getSource();
                SwingUtilities.getWindowAncestor(button).dispose();
            }
        });

        noButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                yesNoChoice = false;

                JButton button = (JButton)e.getSource();
                SwingUtilities.getWindowAncestor(button).dispose();
            }
        });
        JPanel buttons = new JPanel();
        buttons.add(yesButton);
        buttons.add(noButton);

        JPanel content = new JPanel(new BorderLayout(8, 8));
        content.add(label, BorderLayout.CENTER);
        content.add(buttons, BorderLayout.SOUTH);

        JDialog dialog = new JDialog();
        dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        dialog.setModal(true);
        dialog.setTitle("Finestra di selezione");
        dialog.getContentPane().add(content);
        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);


        return yesNoChoice;
    }

    @Override
    public String stringSelector(String message, ArrayList<String> listToChooseFrom) {
        JComboBox chooseList = new JComboBox(listToChooseFrom.toArray(new String[listToChooseFrom.size()]));
        chooseList.setSelectedIndex(0);

        JLabel label = new JLabel(message);
        JButton selectButton = new JButton("Select");

        selectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chosenByList = (String)chooseList.getSelectedItem();
                JButton button = (JButton)e.getSource();
                SwingUtilities.getWindowAncestor(button).dispose();
            }
        });

        JPanel buttons = new JPanel();
        buttons.add(selectButton);

        JPanel panel = new JPanel(new BorderLayout(8, 8));
        panel.add(label, BorderLayout.CENTER);
        panel.add(chooseList, BorderLayout.SOUTH);
        panel.add(buttons);

        JDialog dialog = new JDialog();
        dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        dialog.setModal(true);
        dialog.setTitle("Finestra di selezione");
        dialog.getContentPane().add(panel);
        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);

        return chosenByList;
    }

    @Override
    public void showMessage(String message) {
        JDialog dialog = new JDialog();
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setModal(true);
        dialog.setTitle("Finestra di selezione");
        dialog.getContentPane().add(new JLabel(message));
        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }

    @Override
    public boolean[] cardsToReload(GunCard[] cards, boolean[] reloadableCards) {
        //n    Jdialog sullo stesso concetto del metodo di sopra, con 3 checkbox, dove vengono attivate a seconda
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
        dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        dialog.setModal(true);
        dialog.setTitle("Selettore carte da ricaricare");
        dialog.getContentPane().add(content);
        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);

        return chosen;
    }

    /**
     * this metod allow the user to see the picture of the drop
     * @param content
     */
    public void  ammoTileViewer(String content){

        JPanel body = new JPanel(new BorderLayout(8, 8));
        ImageIcon pic = new ImageIcon(new ImageIcon("src/main/sprite/ammo/ammo_"+ content +".png").getImage().getScaledInstance(150,150, Image.SCALE_DEFAULT));
        JLabel image = new JLabel(pic);
        image.setIcon(pic);

        body.add(new JLabel("You picked this drop:"), BorderLayout.NORTH);
        body.add(image, BorderLayout.SOUTH);



        JDialog dialog = new JDialog();
        dialog.setModal(true);
        dialog.setTitle("Drop desclaimer");
        dialog.getContentPane().add(body);
        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }

    public int[] mapChooser(){

        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints container = new GridBagConstraints();
        mainPanel.setLayout(new GridBagLayout());



        JButton map1 = new JButton();
        ImageIcon pic1 = new ImageIcon(new ImageIcon("src/main/sprite/maps/1.png").getImage().getScaledInstance(300,220,Image.SCALE_DEFAULT));
        map1.setIcon(pic1);

        JButton map2 = new JButton();
        ImageIcon pic2 = new ImageIcon(new ImageIcon("src/main/sprite/maps/2.png").getImage().getScaledInstance(300,220,Image.SCALE_DEFAULT));
        map2.setIcon(pic2);

        JButton map3 = new JButton();
        ImageIcon pic3 = new ImageIcon(new ImageIcon("src/main/sprite/maps/3.png").getImage().getScaledInstance(300,220,Image.SCALE_DEFAULT));
        map3.setIcon(pic3);

        JButton map4 = new JButton();
        ImageIcon pic4 = new ImageIcon(new ImageIcon("src/main/sprite/maps/4.png").getImage().getScaledInstance(300,220,Image.SCALE_DEFAULT));
        map4.setIcon(pic4);


        container.gridx = 0;
        container.gridy = 0;
        mainPanel.add(new Label("choose your map"), container);

        container.gridx = 0;
        container.gridy = 1;
        mainPanel.add(map1, container);

        container.gridx = 1;
        container.gridy = 1;
        mainPanel.add(map2, container);

        container.gridx = 0;
        container.gridy = 2;
        mainPanel.add(map3, container);

        container.gridx = 1;
        container.gridy = 2;
        mainPanel.add(map4, container);


        //TODO con quesi bottoni aprire la plancia di gioco passando map
        map1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                map = 1;

            }});

        map2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                map = 2;

            }});
        map3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                map = 3;

            }});
        map4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                map = 4;

            }});


        JDialog dialog = new JDialog();
        dialog.setModal(true);
        dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        dialog.setTitle("map Chooser");
        dialog.getContentPane().add(mainPanel);
        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);


        int[] ret = {map, skull};
        return ret ;
    }

}
