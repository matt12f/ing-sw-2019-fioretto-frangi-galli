package it.polimi.se2019.network;

import java.net.Socket;

public class SocketLobby extends Thread {
    private Socket socket;
    private String nick;
    private Thread currentThread;


    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public Socket getSocket() {
        return socket;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public Thread getCurrentThread() {
        return currentThread;
    }

    public void setCurrentThread(Thread currentThread) {
        this.currentThread = currentThread;
    }
}
