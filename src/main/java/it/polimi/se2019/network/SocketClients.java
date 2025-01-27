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
        ServerSocket serverSocket;
        ClientHandler newUser;
        Socket socketTemp;
        boolean condition = true;
        try {
            serverSocket = new ServerSocket(14567); //PORTA TEMPORANEA
            while(condition){ //Finche siamo qui dentro stiamo aspettando le condizioni per startare la partita
                System.out.println("aspetto un client");
                socketTemp = serverSocket.accept();
                socketTemp.setKeepAlive(true);
                newUser = new ClientHandler();
                newUser.setSocket(socketTemp);
                System.out.println("Nuovo client("+ AdrenalineServer.getLobbyClient().size() +"), socket: " + socketTemp);
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
