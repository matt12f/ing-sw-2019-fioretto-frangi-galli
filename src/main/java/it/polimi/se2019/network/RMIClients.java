package it.polimi.se2019.network;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class RMIClients implements Runnable, logInterface{

    private ArrayList<ClientHandler> Clients;
    private ArrayList<String> Hosts;
    private Thread t;
    private boolean start = false;

    //Qui si accettano gli utenti tramite RMI
    @Override
    public void run() {

    }

    public void setStart(boolean start) {
        this.start = start;
    }

    public boolean isStart() {
        return start;
    }

    public ArrayList<ClientHandler> getClients() {
        return Clients;
    }

    public void setClients(ArrayList<ClientHandler> clients) {
        Clients = clients;
    }

    public ArrayList<String> getHosts() {
        return Hosts;
    }

    public void setHosts(ArrayList<String> hosts) {
        Hosts = hosts;
    }

    public Thread getT() {
        return t;
    }

    public void setT(Thread t) {
        this.t = t;
    }

    @Override
    public void Welcome(String Host) throws RemoteException {
        Thread t;
        this.Hosts.add(Host);
        this.Clients.add(new ClientHandler());
        this.Clients.get(this.Clients.size() - 1).setHost(Host);
        t = new Thread (this.Clients.get(this.Clients.size() - 1));
        this.Clients.get(this.Clients.size() - 1).setThread(t);
        t.start();
    }
}
