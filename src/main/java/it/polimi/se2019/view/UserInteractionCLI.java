package it.polimi.se2019.view;

import it.polimi.se2019.model.cards.GunCard;
import it.polimi.se2019.model.cards.PowerupCard;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class UserInteractionCLI extends UserInteraction {
    /**
     * shows to the user the weapons that could be reloaded and the user can choose to reload them
     * @param cards
     * @param reloadableCards
     * @return
     */
    @Override
    public boolean[] cardsToReload(GunCard[] cards, boolean[] reloadableCards) {
        return new boolean[0];
    }

    /**
     * ask to th player if he wants to do or accept a situation or not
     * @param message
     * @param textYesButton
     * @param textNoButton
     * @return
     */
    @Override
    public boolean yesOrNo(String message, String textYesButton, String textNoButton) {
        return false;
    }

    /**
     * allows the user to choose from a list of possible decisions
     * @param message
     * @param listToChooseFrom
     * @return
     */
    @Override
    public String stringSelector(String message, ArrayList<String> listToChooseFrom) {
        return null;
    }

    /**
     * allows the user to choose a map configuration and the maximum of kill in the game
     * @return
     */
    @Override
    public int[] mapChooser() {
        return new int[0];
    }

    /**
     * ask to the user which powerup card he want to discard
     * @param list
     * @param optional
     * @return
     */
    @Override
    public PowerupCard spawnChooser(PowerupCard[] list, PowerupCard optional) {
        return null;
    }

    /**
     *  ask to the player wich action he wants to perform
     * @param frenzy
     * @return
     */
    @Override
    public String actionToRequest(int frenzy,String playerColor) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<String> available = new ArrayList<>();
        switch (frenzy) {
            case 0: {
                System.out.println("Azioni disponibili: move, grab o shoot");
                available.addAll(Arrays.asList("move", "grab", "shoot"));
            }
            break;
            case 1: {
                System.out.println("Azioni disponibili: frenzy1, frenzy2, frenzy3");
                available.addAll(Arrays.asList("frenzy1", "frenzy2", "frenzy3"));
            }
            break;
            case 2:{
                System.out.println("Azioni disponibili: frenzy4, frenzy5");
                available.addAll(Arrays.asList("frenzy4", "frenzy5"));
            }break;
            default:break;
        }

        System.out.print("Inserire la macro azione da svolgere: ");
        String action = null;

        do {

            try {
                action = reader.readLine().toLowerCase();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }while(!available.contains(action));
        return action;
    }

    /**
     * show a message to the user
     * @param message
     */
    @Override
    public void showMessage(String message) {
        System.out.println(message);
    }

    /**
     * show the payer's ammo
     * @param content
     */
    @Override
    public void ammoTileViewer(String content) {
        StringBuilder builder=new StringBuilder("La AmmoTile che hai raccolto contiene: ");
        for (int i = 0; i < content.length(); i++)
            switch (content.charAt(i)){
                case 'y':builder.append("1 Yellow Ammo");break;
                case 'b':builder.append("1 Blue Ammo");break;
                case 'r': builder.append("1 Red Ammo");break;
                case 'p':builder.append("1 PowerUp");break;
                default:break;
            }
        System.out.println(builder.toString());
    }

    /**
     * ask the server's ip
     * @return
     */
    public String ipRequest(){
        String ipServer;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Inserisci l'indirizzo IP del server: ");
        ipServer = scanner.nextLine();
        return ipServer;
    }

    /**
     * ask the player's nick
     * @param FirstTime
     * @return
     */
    public String nicknameRequest(boolean FirstTime){
        if(!FirstTime)
            System.out.println("Mi spiace, il nick che hai inserito non è disponibile, scegline un altro");
        String nickname;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Inserisci il tuo nickname adesso:");
        nickname = scanner.nextLine();
        return nickname;
    }

    /**
     * show the waiting list
     * @param otherPlayers
     */
    public void displayQue(ArrayList<String> otherPlayers) {
        if(!otherPlayers.isEmpty()){
            System.out.print("Sei in coda, al momento i giocatori connessi (oltre a te) sono:");
            for(int i = 0; i<otherPlayers.size(); i++){
                System.out.print(" " + otherPlayers.get(i));
                if(i == otherPlayers.size() - 1){
                    System.out.print(".");
                }else {
                    System.out.print(",");
                }
            }
        }else {
            System.out.println("Sei il solo in attessa di una nuova partita per ora");
        }
    }
}
