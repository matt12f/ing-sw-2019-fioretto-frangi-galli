package it.polimi.se2019.network;

import it.polimi.se2019.controller.AvailableActions;
import it.polimi.se2019.controller.Controller;
import it.polimi.se2019.enums.Color;
import it.polimi.se2019.enums.Status;
import it.polimi.se2019.model.game.Player;

import java.util.ArrayList;
import java.util.Collections;

public class GameHandler implements Runnable {
    private ArrayList<ClientHandler> players;
    private Controller controller;

    public GameHandler(ArrayList<ClientHandler> players){
        this.players = players;
    }


    public Controller getController() {
        return controller;
    }

    private ArrayList<String> getPlayersNickname(ArrayList<ClientHandler> clients){
        ArrayList<String> nicknames = new ArrayList<>();
        for (ClientHandler client: clients) {
            nicknames.add(client.getNickname());
        }
        return  nicknames;
    }

    private void nextTurn(){
        int turn = this.controller.getMainGameModel().getTurn();
        if(turn == (this.players.size() - 1))
            turn = 0;
        this.controller.getMainGameModel().setTurn(turn);
    }

    private void shuffleClient(ArrayList<ClientHandler> clients){
        Collections.shuffle(clients);
        this.players = clients;
    }

    private Color fromIntToColor(int num){
        switch (num) {
            case 0:
                return Color.YELLOW;
            case 1:
                return Color.WHITE;
            case 2:
                return Color.GREEN;
            case 3:
                return Color.BLUE;
            case 4:
                return Color.VIOLET;
            default:
                return Color.RED;
        }
    }

    private void setClientColor(){
        int i=0;
        for (ClientHandler client: this.players) {
            client.setColor(fromIntToColor(i));
            i++;
        }
    }

    @Override
    public void run() {
        ClientHandler clientTurn;
        int i;
        //scelta rotazione e assegnamento pedina
        setClientColor();
        shuffleClient(this.players);


        //TODO chiedere al primo mappa e numero di teschi


        createController();
        //gestione dei turni
        while (this.controller.getMainGameModel().getKillshotTrack().getSkulls() > 0){
            i = this.controller.getMainGameModel().getTurn();
            clientTurn = this.players.get(i);
            clientTurn.setStatus(Status.MYTURN);

            //TODO logica del turno
            //TODO deve ricevere da ClientHandler l'oggetto ActionRequestView per costruire l'oggetto AvailableActions
            //TODO deve poi ricevere l'oggetto ChosenActions e applicarne gli effetti
            

            clientTurn.setStatus(Status.NOTMYTURN);
            nextTurn();
        }
    }

    private void createController(){
        ArrayList<Player> players = new ArrayList<>();
        for (ClientHandler client: this.players) {
            players.add(new Player(0, client.getNickname(), client.getColor()));
        }
        this.controller = new Controller(players, 0, 5); //todo SONO TEMPORANEI, RIMUOVILI FACENDOLO BENE
    }
}
