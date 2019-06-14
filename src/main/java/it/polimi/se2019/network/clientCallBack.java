package it.polimi.se2019.network;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface clientCallBack extends Remote {
    void ClientCallBack () throws RemoteException;
}
