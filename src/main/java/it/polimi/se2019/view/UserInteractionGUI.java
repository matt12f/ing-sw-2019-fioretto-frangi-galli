package it.polimi.se2019.view;

import it.polimi.se2019.model.cards.GunCard;
import it.polimi.se2019.model.cards.PowerupCard;

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
    PowerupCard chosenPowerup;
    GunCard chosenWeapon;
    private JDialog waitingList = new JDialog();
    private String[] answer = new String[2];

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
     * this metod allows the user to see the picture of the drop
     * @param content
     */
    @Override
    public void ammoTileViewer(String content){

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

    @Override
    public int[] mapChooser(){

        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints container = new GridBagConstraints();
        mainPanel.setLayout(new GridBagLayout());

        map = 1; //setting di default

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



        JRadioButton n5 = new JRadioButton("5");
        n5.setSelected(true);
        JRadioButton n8 = new JRadioButton("8");
        ButtonGroup group = new ButtonGroup();
        group.add(n5);
        group.add(n8);


        container.gridx = 0;
        container.gridy = 3;
        mainPanel.add(new Label("choose the skull number"), container);

        container.gridx = 0;
        container.gridy = 4;
        mainPanel.add(n5, container);

        container.gridx = 1;
        container.gridy = 4;
        mainPanel.add(n5, container);

        JButton send = new JButton("send");

        container.gridx = 0;
        container.gridy = 5;
        mainPanel.add(send, container);




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
        send.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(n5.isSelected()){
                        skull = 5;
                    }
                    if(n8.isSelected()){
                        skull = 8;
                    }

                    JButton button = (JButton)e.getSource();
                    SwingUtilities.getWindowAncestor(button).dispose();
                }
        });





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

    @Override
    public PowerupCard spawnChooser(PowerupCard[] list){

        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints container = new GridBagConstraints();
        mainPanel.setLayout(new GridBagLayout());


        JButton pw1 = new JButton();
        JButton pw2 = new JButton();
        JButton pw3 = new JButton();
        JButton pw4 = new JButton();

        ImageIcon im1 = new ImageIcon(new ImageIcon("src/main/sprite/cards/powerups/powerups_"+ list[0].getPowerupType() + "_"+list[0].getCubeColor()+ ".png").getImage().getScaledInstance(70,92,Image.SCALE_DEFAULT));
        ImageIcon im2 = new ImageIcon(new ImageIcon("src/main/sprite/cards/powerups/powerups_"+ list[1].getPowerupType() + "_"+list[1].getCubeColor()+ ".png").getImage().getScaledInstance(70,92,Image.SCALE_DEFAULT));
        pw1.setIcon(im1);
        pw2.setIcon(im2);

        container.gridx=0;
        container.gridy=0;
        mainPanel.add(new Label("choose the powerup to discard"),container);

        container.gridx=0;
        container.gridy=1;
        mainPanel.add(pw1,container);

        container.gridx=1;
        container.gridy=1;
        mainPanel.add(pw2,container);

        if(list[2]!= null){
            ImageIcon im3 = new ImageIcon(new ImageIcon("src/main/sprite/cards/powerups/powerups_"+ list[2].getPowerupType() + "_"+list[2].getCubeColor()+ ".png").getImage().getScaledInstance(70,92,Image.SCALE_DEFAULT));
            pw3.setIcon(im3);
            container.gridx=2;
            container.gridy=1;
            mainPanel.add(pw3,container);
        }
        if (list[3] != null){
            ImageIcon im4 = new ImageIcon(new ImageIcon("src/main/sprite/cards/powerups/powerups_"+ list[3].getPowerupType() + "_"+list[3].getCubeColor()+ ".png").getImage().getScaledInstance(70,92,Image.SCALE_DEFAULT));
            pw4.setIcon(im4);
            container.gridx=3;
            container.gridy=1;
            mainPanel.add(pw4,container);
        }

        pw1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chosenPowerup = list[0];

                JButton button = (JButton)e.getSource();
                SwingUtilities.getWindowAncestor(button).dispose();
            }
        });
        pw2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chosenPowerup = list[1];

                JButton button = (JButton)e.getSource();
                SwingUtilities.getWindowAncestor(button).dispose();
            }
        });
        pw3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chosenPowerup = list[2];

                JButton button = (JButton)e.getSource();
                SwingUtilities.getWindowAncestor(button).dispose();
            }
        });
        pw4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chosenPowerup = list[3];

                JButton button = (JButton)e.getSource();
                SwingUtilities.getWindowAncestor(button).dispose();
            }
        });



        JDialog dialog = new JDialog();
        dialog.setModal(true);
        dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        dialog.setTitle("powerup Chooser");
        dialog.getContentPane().add(mainPanel);
        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
        return chosenPowerup;
    }

    //Not in use at the moment
    public GunCard weaponChoose(GunCard[] list){

        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints container = new GridBagConstraints();
        mainPanel.setLayout(new GridBagLayout());

        WeaponButtonGUI w1  = new WeaponButtonGUI(100,160);
        WeaponButtonGUI w2  = new WeaponButtonGUI(100,160);
        WeaponButtonGUI w3  = new WeaponButtonGUI(100,160);
        WeaponButtonGUI w4  = new WeaponButtonGUI(100,160);

        w1.updateImage(list[0]);
        w2.updateImage(list[1]);
        w3.updateImage(list[2]);
        w4.updateImage(list[3]);

        container.gridx=0;
        container.gridy=0;
        mainPanel.add(new Label("choose the weapon to discard"),container);

        container.gridx=0;
        container.gridy=1;
        mainPanel.add(w1,container);
        container.gridx=1;
        container.gridy=1;
        mainPanel.add(w2,container);
        container.gridx=2;
        container.gridy=1;
        mainPanel.add(w3,container);
        container.gridx=3;
        container.gridy=1;
        mainPanel.add(w4,container);

        w1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chosenWeapon = list[0];

                JButton button = (JButton)e.getSource();
                SwingUtilities.getWindowAncestor(button).dispose();
            }
        });
        w2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chosenWeapon = list[1];

                JButton button = (JButton)e.getSource();
                SwingUtilities.getWindowAncestor(button).dispose();
            }
        });
        w3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chosenWeapon = list[2];

                JButton button = (JButton)e.getSource();
                SwingUtilities.getWindowAncestor(button).dispose();
            }
        });
        w4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chosenWeapon = list[3];

                JButton button = (JButton)e.getSource();
                SwingUtilities.getWindowAncestor(button).dispose();
            }
        });

        JDialog dialog = new JDialog();
        dialog.setModal(true);
        dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        dialog.setTitle("weapon Chooser");
        dialog.getContentPane().add(mainPanel);
        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
        return chosenWeapon;
    }

    public String[] mainLogGUI(){
        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints container = new GridBagConstraints();
        mainPanel.setLayout(new GridBagLayout());


        container.gridx = 1;
        container.gridy = 1;
        mainPanel.add(new Label("choose your nickname and start a game"), container);
        TextField nickTextField = new TextField(answer[0]);
        container.fill = GridBagConstraints.HORIZONTAL;
        container.gridx = 1;
        container.gridy = 2;
        mainPanel.add(nickTextField, container);



        container.gridx = 1;
        container.gridy = 3;
        mainPanel.add(new Label("insert ip address"), container);
        TextField ipTextField = new TextField(answer[1]);
        container.fill = GridBagConstraints.HORIZONTAL;
        container.gridx = 1;
        container.gridy = 4;
        mainPanel.add(ipTextField, container);

        JButton startGameButton = new JButton("Start");
        container.gridx = 1;
        container.gridy = 5;
        mainPanel.add(startGameButton, container);


        startGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                answer[0] = nickTextField.getText();
                answer[1] = ipTextField.getText();
                JButton button = (JButton) e.getSource();
                SwingUtilities.getWindowAncestor(button).dispose();
            }
        });


        JDialog dialog = new JDialog();
        dialog.setModal(true);
        dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        dialog.setTitle("Login");
        dialog.getContentPane().add(mainPanel);
        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);

        return answer;
    }

    public void errorDisplay(String error){

        JLabel message= new JLabel();
        switch(error){
            case "nick":
                message.setText("This nickname has been already taken, please chose another nickname.");
                break;
            case "connectionIP":
                message.setText("An error has occurred trying to connect to the specified server, please check the ip.");
                break;
            case "connection":
                message.setText("An internal error has occurred, please restart the game and try again");
                break;
        }


        GridBagConstraints container = new GridBagConstraints();
        container.gridx = 1;
        container.gridy = 1;
        JDialog dialog = new JDialog();
        dialog.setLayout(new GridBagLayout());
        dialog.setModal(true);

        dialog.setTitle("Error!");
        dialog.add(message, container);

        JButton ok= new JButton("Ok");
        container.gridx = 1;
        container.gridy = 2;
        dialog.add(ok, container);

        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton button = (JButton)e.getSource();
                SwingUtilities.getWindowAncestor(button).dispose();
                switch (error){
                    case "nick":
                        mainLogGUI();
                        SwingUtilities.getWindowAncestor(button).dispose();
                        break;
                    case "connectionIP":
                        mainLogGUI();
                        SwingUtilities.getWindowAncestor(button).dispose();
                        break;
                    case "connection":
                        break;
                }
            }
        });


        dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }





    public void waitingListCreation (ArrayList<String> players){
        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints container = new GridBagConstraints();
        mainPanel.setLayout(new GridBagLayout());


        for (int i=0; i < players.size(); i++){
            container.gridx = 0;
            container.gridy = i;
            mainPanel.add(new JLabel(players.get(i)), container);
        }


        waitingList.setModal(true);

        waitingList.setTitle("wait for other players");
        waitingList.getContentPane().add(mainPanel);
        waitingList.pack();
        waitingList.setLocationRelativeTo(null);
        waitingList.setVisible(true);



    }
    public void waitingListUpdate(ArrayList<String> players){
        waitingList.getContentPane().removeAll();
        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints container = new GridBagConstraints();
        mainPanel.setLayout(new GridBagLayout());


        for (int i=0; i < players.size(); i++){
            container.gridx = 0;
            container.gridy = i;
            mainPanel.add(new JLabel(players.get(i)), container);
        }

        waitingList.getContentPane().add(mainPanel);
        waitingList.revalidate();
        waitingList.repaint();
    }

}
