package it.polimi.se2019.network;

import it.polimi.se2019.AdrenalineServer;
import it.polimi.se2019.controller.AvailableActions;
import it.polimi.se2019.enums.Color;
import it.polimi.se2019.model.cards.PowerupCard;
import it.polimi.se2019.view.ActionRequestView;
import it.polimi.se2019.view.ChosenActions;
import it.polimi.se2019.view.LocalView;
import it.polimi.se2019.enums.Status;
import java.io.*;
import java.net.Socket;
import java.rmi.RemoteException;
import java.util.ArrayList;



public class ClientHandler extends Thread implements RMIInterface {
    private String nickname;
    private Thread thread;
    private Socket socket = null;
    private boolean accepted = false; //used in a first moment to verify the nickname's uniqueness
    private String host;
    private Status status = Status.NOTREADY;
    private Color color;
    private ObjectOutputStream output;
    private ObjectInputStream input;
    private GameHandler game;
    private ActionRequestView requestView;
    private AvailableActions availableActions;
    private int actionsNumber;
    private ChosenActions chosenAction;
    private LocalView localView;
    private PowerupCard spawn;
    private String winnerNick;
    private int deadsPlayer;
    public Object lock;

    @Override
    public void run(){
        try {
            System.out.println("Partito clientHandler della socket: " + this.socket);
            if(this.socket == null){
                //todo RMI
            }else{
                this.output = new ObjectOutputStream(this.socket.getOutputStream());
                this.input = new ObjectInputStream(this.socket.getInputStream());
                while (!this.accepted){
                    this.nickname = (String) input.readObject();
                    AdrenalineServer.nickController(this);
                }
                initializeLobby();
                waitingPlayers();
            }
            this.output.writeObject("START");
            waitForView();
            sendLocalView(); //invio la view al client
            while(status != Status.START){
                waitForView();
                sendLocalView();
            }
            while(!status.equals(Status.ENDGAME)){
                switch (this.status){
                    case MYTURN:
                        this.output.writeObject("MYTURN");
                        this.input.readBoolean();
                        this.output.writeObject(this.actionsNumber); //comunica quante azioni può fare il giocatore
                        for(int j = 0; j<this.actionsNumber; j++){
                            requestView = (ActionRequestView) this.input.readObject();
                            statusChanged();
                            this.output.writeObject(availableActions);
                            this.chosenAction = (ChosenActions) this.input.readObject();
                            statusChanged();
                            sendLocalView();
                        }
                        break;
                    case NOTMYTURN:
                        this.output.writeObject("NOTMYTURN");
                        statusChanged();
                        sendLocalView();
                        break;
                    default:
                        break;
                }
                this.output.writeObject(deadsPlayer);
                if (status.equals(Status.DEAD)){
                    this.output.writeObject("DEAD");
                    this.output.reset();
                    this.output.writeObject(this.localView.getPlayerHand().getPowerups());
                    this.spawn = (PowerupCard) this.input.readObject();
                    this.status = Status.WAITING;
                }else{
                    this.output.writeObject("ALIVE");
                }

            }
            waitForWinner();
            this.output.writeObject(winnerNick);
        } catch (InterruptedException | IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
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
        System.out.println("invio");
        this.output.reset();
        this.output.writeObject(this.localView);
    }

    private void statusChanged() throws InterruptedException {
        this.status = Status.WAITING;
        while(this.status == Status.WAITING){
            sleep(1);
        }
    }

    //TODO frangi controlla
    private synchronized void setTagBackUsage() throws IOException, ClassNotFoundException {
        this.output.writeObject("TAGBACKUSAGE");
        boolean useTagBack = (boolean) this.input.readObject();
        this.game.setUseTagBack(useTagBack);
        this.status = Status.WAITING;
        notifyAll();
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

    void setHost(String host) {
        this.host = host;
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

    @Override
    public LocalView getLocalView(int playerID) throws RemoteException {

        return null;
    }

    @Override
    public AvailableActions askAction(ActionRequestView codedAction, int playerID) throws RemoteException {
        return null;
    }

    @Override
    public synchronized void setNicknameRMI(String nickname) throws RemoteException, InterruptedException {
        setNickname(nickname);
        this.getThread().wait();
        if(!this.accepted)
            throw (new IllegalArgumentException());
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    private void waitForView() throws InterruptedException, IOException, ClassNotFoundException {
        status = Status.WAITING;
        while(this.status != Status.VIEW ){
            Thread.sleep(1);
            if(this.status == Status.MAPSKULL)
                setMapSkull();
            if(this.status == Status.SPAWN)
                setSpawn();
            if(this.status == Status.START)
                break;
        }
    }

    private synchronized void setSpawn() throws IOException, ClassNotFoundException, InterruptedException {
        output.reset();
        output.writeObject("SPAWN");
        output.writeObject(this.localView);
        spawn = (PowerupCard) input.readObject();
        this.status = Status.WAITING;
        waiting();
    }

    @Override
    public String actionRequest() throws RemoteException {
        return null;
    }

    private Thread getThread() {
        return thread;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }

    void setThread(Thread thread) {
        this.thread = thread;
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

    @Override
    public Status getStatus() {
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

    void setWinnerNick(String winnerNick) {
        this.winnerNick = winnerNick;
    }
}
