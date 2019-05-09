package it.polimi.se2019.network;

import it.polimi.se2019.controller.AvailableActions;
import it.polimi.se2019.view.ActionRequestView;
import it.polimi.se2019.view.LocalView;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMIInterface extends Remote{
    LocalView getLocalView(int playerID) throws RemoteException; //this method returns the local view given a player's id
    AvailableActions askAction(ActionRequestView codedAction, int playerID) throws RemoteException; //this method returns the possible actions

}

