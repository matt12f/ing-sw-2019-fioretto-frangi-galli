package it.polimi.se2019.network;

import it.polimi.se2019.AdrenalineServer;
import it.polimi.se2019.controller.AvailableActions;
import it.polimi.se2019.controller.GameStats;
import it.polimi.se2019.enums.Color;
import it.polimi.se2019.model.cards.PowerupCard;
import it.polimi.se2019.view.ActionRequestView;
import it.polimi.se2019.view.ChosenActions;
import it.polimi.se2019.view.LocalView;
import it.polimi.se2019.enums.Status;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;



public class ClientHandler extends Thread {
    private String nickname;
    private Socket socket = null;
    private boolean accepted = false; //used in a first moment to verify the nickname's uniqueness
    private Status status = Status.NOTREADY;
    private Color color;
    private ObjectOutputStream output;
    private ObjectInputStream input;
    private GameHandler game;
    private ActionRequestView requestView;
    private AvailableActions availableActions;
    private int actionsNumber;
    private boolean reload;
    private ChosenActions chosenAction;
    private LocalView localView;
    private PowerupCard spawn;
    private int deadsPlayer;
    private ArrayList<String> choices;
    private GameStats finale;

    @Override
    public void run(){
        try {
            System.out.println("Partito clientHandler della socket: " + this.socket);
            this.output = new ObjectOutputStream(this.socket.getOutputStream());
            this.input = new ObjectInputStream(this.socket.getInputStream());
            while (!this.accepted){
                this.nickname = (String) input.readObject();
                AdrenalineServer.nickController(this);
            }
            initializeLobby();
            waitingPlayers();
            this.output.writeObject("START");
            status = Status.WAITING;
            waitForView();
            sendLocalView(); //invio la view al client
            while(status != Status.START){
                status = Status.WAITING;
                waitForView();
                sendLocalView();
            }
            while(!status.equals(Status.ENDGAME)) {
                while(this.status != Status.ENDTURN) {
                    switch (this.status) {
                        case MYTURN:
                            reload = true;
                            this.output.writeObject("MYTURN");
                            this.input.readBoolean();
                            this.output.writeObject(this.actionsNumber); //comunica quante azioni può fare il giocatore
                            for (int j = 0; j < this.actionsNumber; j++) {
                                requestView = (ActionRequestView) this.input.readObject();
                                statusChanged();
                                this.output.writeObject(availableActions);
                                this.chosenAction = (ChosenActions) this.input.readObject();
                            }
                            waitForTurn();
                            break;
                        case NOTMYTURN:
                            reload = false;
                            this.output.writeObject("NOTMYTURN");
                            waitForTurn();
                            break;
                        default:
                            break;
                    }
                }
                //gestione di fine turno
                if(reload){
                    this.requestView = (ActionRequestView) this.input.readObject();
                    statusChanged();

                }
                this.output.writeObject(deadsPlayer);
                if (status.equals(Status.DEAD)){
                    this.output.writeObject("DEAD");
                    this.output.reset();
                    this.output.writeObject(this.localView);
                    this.spawn = (PowerupCard) this.input.readObject();
                    this.status = Status.WAITING;
                }else{
                    this.output.writeObject("ALIVE");
                }
                if(this.status == Status.ENDGAME)
                    this.output.writeBoolean(false);
                else
                    this.output.writeBoolean(true);
                this.output.flush();
            }
            waitForWinner();
            this.output.writeObject(finale.toString());
        } catch (InterruptedException | IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void waitForTurn() throws InterruptedException, IOException, ClassNotFoundException {
        this.status = Status.WAITING;
        while(status != Status.MYTURN && status != Status.NOTMYTURN && status != Status.ENDGAME && status != Status.ENDTURN){
            sleep(1);
            if(status == Status.TAGBACKUSAGE)
                setTagBackUsage();
            if(status == Status.TRGSCOPE)
                setTargetingUsage();
            if(status == Status.VIEW)
                sendLocalView();
        }
        this.setStatus(Status.ENDTURN);
        while (status == Status.ENDTURN)
            sleep(1);

    }

    private synchronized void initializeLobby() throws IOException {
        this.output.writeObject("LOBBY");
        this.output.writeObject(AdrenalineServer.getLobby());
    }

    private void waitingPlayers() throws InterruptedException, IOException {
        while(!this.status.equals(Status.NOTMYTURN)){
            sleep(1);
            if(this.status.equals(Status.UPDATE)){
                    updateLobby();
            }
        }
    }

    private synchronized void updateLobby() throws IOException {
        this.output.writeObject("UPDATE");
        this.output.writeObject("LOBBY");
        ArrayList<String> temp = AdrenalineServer.getLobby();
        this.output.writeObject(temp);
        this.output.reset();
        this.status = Status.WAITING;
    }

    private void waitForWinner() throws InterruptedException {
        while(this.status != Status.WINNERIS)
            sleep(1);
    }

    synchronized void sendLocalView() throws IOException {
        this.output.writeObject("VIEW");
        this.input.readBoolean();
        this.output.reset();
        this.output.writeObject(this.localView);
    }

    private void statusChanged() throws InterruptedException {
        this.status = Status.WAITING;
        while(this.status == Status.WAITING){
            sleep(1);
        }
    }

    private synchronized void setTagBackUsage() throws IOException{
        this.output.writeObject("TAGBACKUSAGE");
        boolean useTagBack = this.input.readBoolean();
        this.game.setUseTagBack(useTagBack);
        this.status = Status.WAITING;
    }

    private synchronized void setTargetingUsage() throws IOException, ClassNotFoundException {
        this.output.writeObject("TARGETINGSCOPE");
        this.output.reset();
        this.output.writeObject(this.choices);
        String useTagBack = (String) this.input.readObject();
        this.game.setColorReceived(useTagBack);
        this.status = Status.WAITING;
    }

    private synchronized void setMapSkull() throws IOException, ClassNotFoundException {
        this.output.writeObject("MAP");
        int map = (int) this.input.readObject();
        this.game.setMap(map);
        int skull = (int) this.input.readObject();
        this.game.setSkull(skull);
        this.status = Status.WAITING;
        notifyAll();
    }

    private void waiting() throws InterruptedException {
        while(this.status == Status.WAITING)
            sleep(1);
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public String getNickname() {
        return nickname;
    }

    public ObjectOutputStream getOutput() {
        return output;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    private void waitForView() throws InterruptedException, IOException, ClassNotFoundException {
        while(this.status != Status.VIEW ){
            Thread.sleep(1);
            if(this.status == Status.MAPSKULL)
                setMapSkull();
            if(this.status == Status.START)
                break;
            if(this.status == Status.TAGBACKUSAGE)
                setTagBackUsage();

        }
    }

    synchronized void setSpawn() throws IOException, ClassNotFoundException, InterruptedException {
        output.reset();
        System.out.println("richiesta inv");
        output.writeObject("SPAWN");
        output.writeObject(this.localView);
        spawn = (PowerupCard) input.readObject();
        this.status = Status.WAITING;
        waiting();
    }



    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }


    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    ActionRequestView getRequestView() {
        return requestView;
    }

    public void setGame(GameHandler game) {
        this.game = game;
    }

    void setAvailableActions(AvailableActions availableActions) {
        this.availableActions = availableActions;
    }


    Status getStatus() {
        return this.status;
    }

    void setStatus(Status status) {
        this.status = status;
    }

    void setActionsNumber(int actionsNumber) {
        this.actionsNumber = actionsNumber;
    }

    ChosenActions getChosenAction() {
        return chosenAction;
    }

    void setLocalView(LocalView localView) {
        this.localView = localView;
    }

    public LocalView getLocalView() {
        return localView;
    }

    public PowerupCard getSpawn() {
        return spawn;
    }

    void setDeadsPlayer(int deadsPlayer) {
        this.deadsPlayer = deadsPlayer;
    }

    void setChoices(ArrayList<String> choices) {
        this.choices = choices;
    }

    void setGameStats(GameStats stats) {
        this.finale = stats;
    }
}
