package it.polimi.se2019.network;

import it.polimi.se2019.controller.AvailableActions;
import it.polimi.se2019.controller.Controller;
import it.polimi.se2019.enums.Color;
import it.polimi.se2019.enums.Status;
import it.polimi.se2019.model.game.Player;
import it.polimi.se2019.view.ChosenActions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.TimerTask;

import static sun.management.snmp.jvminstr.JvmThreadInstanceEntryImpl.ThreadStateMap.Byte0.waiting;

public class GameHandler extends TimerTask {
    private ArrayList<ClientHandler> players;
    private Controller controller;
    private int mapNumber;
    private int skullsNumber;

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
        //scelta rotazione e assegnamento pedina
        setClientColor();
        shuffleClient(this.players);
        for (ClientHandler client: this.players) {
            client.setStatus(Status.NOTMYTURN);
        }
        clientTurn = this.players.get(0);
        clientTurn.setStatus(Status.MAPSKULL);
        while(clientTurn.getStatus() == Status.MAPSKULL){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        createController();
        for (ClientHandler c: players) {
            c.setAccepted(false);
        }
        //gestione dei turni
        while (this.controller.getMainGameModel().getKillshotTrack().getSkulls() > 0){
            TurnPreparation(this.controller.getMainGameModel().getTurn());
            waitingRequest(clientTurn);
            calculateActions(clientTurn);
            waitingRequest(clientTurn);
            ChosenActions chosenActions = clientTurn.getChosenAction();
            //todo applico gli effetti della mossa scelta
            clientTurn.setStatus(Status.NOTMYTURN);
            controller.getActiveTurn().nextTurn(controller);
        }
        //todo calcolo mosse frenzy e attivazione
        for (ClientHandler client: players) {
            try {

                client.getOutput().writeInt(controller.getActiveTurn().getActivePlayer().getPlayerBoard().getActionTileFrenzy().getActionCounter());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private synchronized void calculateActions(ClientHandler clientTurn){
        clientTurn.setAvailableActions(new AvailableActions(clientTurn.getRequestView(), controller.getActiveTurn().getActivePlayer().getId(), controller));
        clientTurn.setStatus(Status.CALCULATED);
        this.notifyAll();
    }

    private synchronized void waitingRequest(ClientHandler clientTurn){
        while(clientTurn.getStatus()!= Status.WAITING){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void createController(){
        ArrayList<Player> players = new ArrayList<>();
        for (ClientHandler client: this.players) {
            players.add(new Player(0, client.getNickname(), client.getColor()));
        }
        this.controller = new Controller(players, this.mapNumber, this.skullsNumber);
    }

    public void setMap(int map) {
        this.mapNumber = map;
    }

    void setSkull(int skull) {
        this.skullsNumber = skull;
    }

    public synchronized void TurnPreparation (int turn){
        ClientHandler clientTurn = this.players.get(turn);
        if(!clientTurn.isAccepted()){
            //todo scelta spawnpoint
        }
        clientTurn.setActionsNumber(controller.getActiveTurn().getActivePlayer().getPlayerBoard().getActionTileNormal().getActionCounter());
        clientTurn.setStatus(Status.MYTURN);
    }
}
