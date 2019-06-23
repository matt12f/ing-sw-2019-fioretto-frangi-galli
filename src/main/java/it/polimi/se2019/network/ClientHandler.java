package it.polimi.se2019.network;

import it.polimi.se2019.AdrenalineServer;
import it.polimi.se2019.controller.AvailableActions;
import it.polimi.se2019.enums.Color;
import it.polimi.se2019.view.ActionRequestView;
import it.polimi.se2019.view.LocalView;
import it.polimi.se2019.enums.Status;

import java.io.*;
import java.net.Socket;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
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

    @Override
    public void run(){
        try {
            System.out.println("Partito clientHandler della socket: " + this.socket);
            if(this.socket == null){
                this.status = Status.READY;
                while(!this.accepted){

                }
                //todo Semaforo?
            }else{
                ObjectOutputStream output = new ObjectOutputStream(this.socket.getOutputStream());
                ObjectInputStream input = new ObjectInputStream(this.socket.getInputStream());
                output.writeBoolean(true);
                output.flush();
                while (!this.accepted){
                    this.nickname = (String) input.readObject();
                    System.out.println("nick ricevuto: " + this.nickname);
                    AdrenalineServer.nickController(this);
                    System.out.println("Nick controllato");
                    output.writeBoolean(this.accepted);
                    output.flush();
                }
            }
            while(!status.equals(Status.FRENZY_START)){
                switch (this.status){
                    case MYTURN:
                        //comunicazioni del turno
                    break;
                    case NOTMYTURN:
                        while(this.status == Status.NOTMYTURN){
                        }
                        //per update client
                    break;
                }
            }
            this.thread.wait(); //todo semaforo su status (START)
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public Socket getSocket() {
        return socket;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public String getNickname() {
        return nickname;
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
    public void setNicknameRMI(String nickname) throws RemoteException, IllegalArgumentException, InterruptedException {
        setNickname(nickname);
        this.getThread().wait(); //todo forse puoi sostituirli con dei semafori
        if(!this.accepted)
            throw (new IllegalArgumentException());
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Override
    public String actionRequest() throws RemoteException {
        return null;
    }

    public Thread getThread() {
        return thread;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }

    public void setThread(Thread thread) {
        this.thread = thread;
    }

    public void sendShootPack (String shootPack) throws IOException {
        ObjectOutputStream output = (ObjectOutputStream) this.socket.getOutputStream();
        output.writeObject(shootPack);
    }

    public String reciveShootRequest () throws IOException, ClassNotFoundException {
        ObjectInputStream input = (ObjectInputStream) this.socket.getInputStream();
        String choice = (String) input.readObject();
        return(choice);
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

    @Override
    public Status getStatus() {
        return this.status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public void update(Observable o, Object arg) {
        //todo devi creare l'aggiornamento della remoteView
    }
}
