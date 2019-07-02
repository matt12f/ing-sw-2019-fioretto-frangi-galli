package it.polimi.se2019.network;

import it.polimi.se2019.AdrenalineServer;
import it.polimi.se2019.controller.AvailableActions;
import it.polimi.se2019.enums.Color;
import it.polimi.se2019.view.ActionRequestView;
import it.polimi.se2019.view.ChosenActions;
import it.polimi.se2019.view.LocalView;
import it.polimi.se2019.enums.Status;
import java.io.*;
import java.net.Socket;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;



public class ClientHandler extends Thread implements RMIInterface, Observer {
    private String nickname;
    private Thread thread;
    private Socket socket = null;
    private boolean accepted = false; //used in a first moment to verify the nickname's uniqueness
    private String host;
    private Status status = Status.NOTREADY;
    private Registry registry = null;
    private Color color;
    private ObjectOutputStream output;
    private ObjectInputStream input;
    private GameHandler game;
    private ActionRequestView requestView;
    private AvailableActions availableActions;
    private int actionsNumber;
    private ChosenActions chosenAction;
    private LocalView localView;

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

                this.output.writeObject(AdrenalineServer.getLobby());
                while(!status.equals(Status.START)){
                    Thread.sleep(100);
                    if(this.status == Status.UPDATE){
                        this.output.writeObject("UPDATE");
                        ArrayList<String> temp = AdrenalineServer.getLobby();
                        this.output.writeObject(temp);
                        this.output.reset();
                        this.status = Status.WAITING;
                    }
                }
            }
            waitForView();
            waitForSpawn();
            //todo SPAWN
            //todo localView.setPlayerPosition();
            while(!status.equals(Status.FRENZY_START)){
                switch (this.status){
                    case MYTURN:
                        this.output.writeObject("MYTURN");
                        this.input.readBoolean();
                        this.output.writeObject(this.actionsNumber); //comunica quante azioni può fare il giocatore
                        for(int j = 0; j<this.actionsNumber; j++){
                            requestView = (ActionRequestView) this.input.readObject();
                            statusChanged();
                            this.output.writeObject(availableActions); //mi da warning ma non so perchè
                            this.chosenAction = (ChosenActions) this.input.readObject();
                            statusChanged();
                            //todo invia la LocalView;
                        }
                        break;
                    case NOTMYTURN:
                        this.output.writeObject("NOTMYTURN");
                        while(this.status == Status.NOTMYTURN){ //todo magari wait e aspoetti la notify
                        }
                        break;
                    default:
                        break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void waitForSpawn() {
    }

    private synchronized void statusChanged() throws InterruptedException {
        this.status = Status.WAITING;
        notifyAll();
        waiting();
    }

    private synchronized void setMapSkull() throws IOException, ClassNotFoundException {
        this.output.writeObject("MAP");
        int map = (int) this.input.readObject();
        this.game.setMap(map);
        this.output.writeObject("SKULL");
        int skull = (int) this.input.readObject();
        this.game.setSkull(skull);
        this.status = Status.WAITING;
        notifyAll();
    }

    private synchronized void waiting() throws InterruptedException {
        while(this.status == Status.WAITING)
            wait();
    }

    public Socket getSocket() {
        return socket;
    }

    public String getHost() {
        return host;
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

    public ObjectInputStream getInput() {
        return input;
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
    public void setNicknameRMI(String nickname) throws RemoteException, InterruptedException {
        setNickname(nickname);
        this.getThread().wait();
        if(!this.accepted)
            throw (new IllegalArgumentException());
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    private synchronized void waitForView() throws InterruptedException, IOException, ClassNotFoundException {
        while(this.status != Status.VIEW){
            if(this.status == Status.MAPSKULL)
                setMapSkull();
            wait();
        }
    }

    @Override
    public String actionRequest() throws RemoteException {
        return null;
    }

    private Thread getThread() {
        return thread;
    }

    boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }

    void setThread(Thread thread) {
        this.thread = thread;
    }

    public void setRegistry(Registry registry) {
        this.registry = registry;
    }

    public Registry getRegistry() {
        return registry;
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

    public void setLocalView(LocalView localView) {
        this.localView = localView;
    }

    public LocalView getLocalView() {
        return localView;
    }

    @Override
    public void update(Observable o, Object arg) {
        //todo devi creare l'aggiornamento della remoteView
    }
}
