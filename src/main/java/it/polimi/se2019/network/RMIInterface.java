package it.polimi.se2019.network;

import it.polimi.se2019.view.LocalView;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMIInterface extends Remote{
    LocalView getLocalView(int playerID) throws RemoteException; //this method returns the local view given a player's id
    int [] askAction(int codedAction,int playerID) throws RemoteException; //this method returns the possible actions

}

