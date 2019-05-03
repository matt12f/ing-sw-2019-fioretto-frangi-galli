package it.polimi.se2019.network;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class NetworkHandler extends UnicastRemoteObject implements NetworkHandlerInterface{
    public String name;
    public NetworkHandlerInterface client=null;

    public NetworkHandler(String n)  throws RemoteException {
            this.name=n;
    }
    public String getName() throws RemoteException {
            return this.name;
    }

    public void setClient(NetworkHandlerInterface c){
        this.client=c;
    }

    public NetworkHandlerInterface getClient(){
            return client;
    }

    public void send(Object obj) throws RemoteException{
        //TODO progettare l'invio di oggetti
    }
}

