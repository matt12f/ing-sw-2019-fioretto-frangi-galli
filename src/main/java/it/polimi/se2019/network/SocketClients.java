package it.polimi.se2019.network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class SocketClients implements Runnable{

    private Thread t;
    private ArrayList<ClientHandler> Clients = new ArrayList<>();
    private ArrayList<Socket> socket = new ArrayList<>();
    private boolean start = false;

    @Override
    public void run() {
        //Qui si accettano gli utenti tramite Socket
        ServerSocket serverSocket = null;
        ClientHandler lobby;
        Thread t;
        int count=0;
        int i=0;
        try {
            serverSocket = new ServerSocket(9000); //PORTA TEMPORANEA TODO Sostituire con caricamento da file
            while(!this.start){ //Finche siamo qui dentro stiamo aspettando le condizioni per startare la partita
                this.socket.add(serverSocket.accept());
                lobby = new ClientHandler();
                t = new Thread(lobby);
                lobby.setSocket(this.socket.get(this.socket.size() - 1));
                lobby.setThread(t);
                lobby.setHost(null); //TODO valuta se tenerlo null o dargli l'host effettivo, tanto socket basta per distinguerli
                this.Clients.add(lobby);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setThread(Thread t) {
        this.t = t;
    }

    public ArrayList<ClientHandler> getClients() {
        return Clients;
    }

    public ArrayList<Socket> getSocket() {
        return socket;
    }

    public void setStart(boolean start) {
        this.start = start;
    }

    public void setSocket(ArrayList<Socket> socket) {
        this.socket = socket;
    }


}
