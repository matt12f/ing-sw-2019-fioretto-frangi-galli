package it.polimi.se2019.network;

import it.polimi.se2019.controller.AvailableActions;
import it.polimi.se2019.view.ActionRequestView;
import it.polimi.se2019.view.LocalView;

import java.io.*;
import java.net.Socket;
import java.rmi.RemoteException;

public class ClientHandler implements Runnable, RMIInterface {
    private String nickname;
    private Thread thread;
    private Socket socket;
    private boolean accepted = false;
    private String host;

    @Override
    public void run() {
        try {
            ObjectInputStream input = (ObjectInputStream) this.socket.getInputStream();
            while (!this.accepted){
                this.nickname = (String) input.readObject();
                this.thread.wait();
                if(!this.accepted){
                    //todo comunicare al client di scegliere un nuovo nickname
                }
            }
            //da qui inizia la partita
            this.thread.wait();
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
        this.getThread().wait();
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

}
