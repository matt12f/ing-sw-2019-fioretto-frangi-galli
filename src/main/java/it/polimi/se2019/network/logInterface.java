package it.polimi.se2019.network;

import it.polimi.se2019.model.game.Player;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface logInterface extends Remote {

    void Welcome(String Host) throws RemoteException; //to add a Nickname in the Players ArrayList.

}