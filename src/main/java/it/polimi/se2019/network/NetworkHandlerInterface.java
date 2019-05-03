package it.polimi.se2019.network;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface NetworkHandlerInterface extends Remote {
    String getName() throws RemoteException;
    void send(Object obj) throws RemoteException;
    void setClient(NetworkHandlerInterface c) throws RemoteException;
    NetworkHandlerInterface getClient() throws RemoteException;
}

