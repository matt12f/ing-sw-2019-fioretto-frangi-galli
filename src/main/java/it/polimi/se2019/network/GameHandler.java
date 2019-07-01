package it.polimi.se2019.network;

import it.polimi.se2019.controller.AvailableActions;
import it.polimi.se2019.controller.Controller;
import it.polimi.se2019.controller.PlayerManager;
import it.polimi.se2019.enums.Color;
import it.polimi.se2019.enums.Status;
import it.polimi.se2019.model.game.Player;
import it.polimi.se2019.view.ChosenActions;

import java.util.ArrayList;
import java.util.Collections;

public class GameHandler implements Runnable {
    private ArrayList<ClientHandler> players;
    private Controller controller;
    private int mapNumber;
    private int skullsNumber;


    public Controller getController() {
        return controller;
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
            c.setStatus(Status.SPAWN);
        }
        //todo GESTIONE SPAWN
        //gestione dei turni
        while (this.controller.getMainGameModel().getKillshotTrack().getSkulls() > 0){
            turnPreparation(this.controller.getMainGameModel().getTurn());
            for(int i = 0; i<controller.getActiveTurn().getActivePlayer().getPlayerBoard().getActionTileNormal().getActionCounter(); i++) {
                waitingRequest(clientTurn);
                calculateActions(clientTurn);
                waitingRequest(clientTurn);
                PlayerManager.choiceExecutor(controller, clientTurn.getChosenAction());
                //todo invio LocalView
                if (this.controller.getMainGameModel().getKillshotTrack().getSkulls() == 0)
                    break;
            }
            clientTurn.setStatus(Status.NOTMYTURN); //valuta se magari ripassare da UPDATE piuttosto
            controller.getActiveTurn().nextTurn(controller);
        }
        for (ClientHandler client: players) { //todo così non va bene perchè non segue l'ordine, cambialo
            turnPreparation(this.controller.getMainGameModel().getTurn());
            for (int i = 0; i < controller.getActiveTurn().getActivePlayer().getPlayerBoard().getActionTileFrenzy().getActionCounter(); i ++) {
                waitingRequest(clientTurn);
                calculateActions(clientTurn);
                waitingRequest(clientTurn);
                ChosenActions chosenActions = clientTurn.getChosenAction();
                PlayerManager.choiceExecutor(controller, chosenActions);
                //todo invio LocalView
            }
            clientTurn.setStatus(Status.NOTMYTURN);
            controller.getActiveTurn().nextTurn(controller);
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

    private synchronized void turnPreparation(int turn){
        for (ClientHandler client: players) {
            client.setStatus(Status.NOTMYTURN);
        }
        ClientHandler clientTurn = this.players.get(turn);
        clientTurn.setActionsNumber(controller.getActiveTurn().getActivePlayer().getPlayerBoard().getActionTileNormal().getActionCounter());
        clientTurn.setStatus(Status.MYTURN);
    }

    public void setPlayers(ArrayList<ClientHandler> players) {
        this.players = players;
    }
}
