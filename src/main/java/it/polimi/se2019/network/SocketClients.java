package it.polimi.se2019.network;

import it.polimi.se2019.AdrenalineServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketClients implements Runnable{

    private Thread thread;

    @Override
    public void run() {
        //Qui si accettano gli utenti tramite Socket
        ServerSocket serverSocket = null;
        ClientHandler newUser;
        Socket socketTemp;
        boolean condition = true;
        try {
            serverSocket = new ServerSocket(9000); //PORTA TEMPORANEA TODO Sostituire con caricamento da file
            while(condition){ //Finche siamo qui dentro stiamo aspettando le condizioni per startare la partita
                System.out.println("aspetto un client");
                socketTemp = serverSocket.accept();
                socketTemp.setKeepAlive(true);
                System.out.println("Nuovo client("+ AdrenalineServer.getLobbyClient().size() +"), socket: " + socketTemp);
                newUser = new ClientHandler();
                newUser.setSocket(socketTemp);
                newUser.setThread(null);
                newUser.setHost(null);
                AdrenalineServer.addClient(newUser);
                newUser.start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setThread(Thread t) {
        this.thread = t;
    }
}
