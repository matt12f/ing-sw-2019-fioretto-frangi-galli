package it.polimi.se2019.network;

import java.net.*;
import java.io.*;

public class SocketServer {
    private Socket socket;
    private ServerSocket server;
    private ObjectInputStream input;
    private ObjectOutputStream output;

    public SocketServer(){

    }

    public Socket getSocket() {
        return socket;
    }

    public ServerSocket getServer() {
        return server;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public void send(ObjectOutputStream obj){

    }

    public void receive(ObjectInputStream obj){

    }
}
