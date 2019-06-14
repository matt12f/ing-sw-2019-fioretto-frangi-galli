package it.polimi.se2019.network;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class RMIClients implements Runnable, logInterface{

    private ArrayList<ClientHandler> Clients;
    private ArrayList<String> Hosts;
    private Thread t;
    private boolean start = false;

    //Qui si accettano gli utenti tramite RMI
    @Override
    public void run() {
        while(!start){

        }
        return;
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
    public void Welcome(String host) throws RemoteException, AlreadyBoundException {
        Thread thread;
        Registry registry = LocateRegistry.getRegistry();
        String name;
        this.Hosts.add(host);
        this.Clients.add(new ClientHandler());
        this.Clients.get(this.Clients.size() - 1).setHost(host);
        thread = new Thread (this.Clients.get(this.Clients.size() - 1));
        this.Clients.get(this.Clients.size() - 1).setThread(t);
        name = "S" + host;
        RMIInterface stub = (RMIInterface) UnicastRemoteObject.exportObject(this.Clients.get(this.Clients.size() - 1), 9000);
        registry.bind(name, stub);
        thread.start();
    }
}
