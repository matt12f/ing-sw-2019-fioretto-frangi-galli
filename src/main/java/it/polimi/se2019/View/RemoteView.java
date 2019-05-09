package it.polimi.se2019.view;

import it.polimi.se2019.controller.AvailableActions;
import it.polimi.se2019.network.RMIInterface;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Observable;

public class RemoteView  extends View implements RMIInterface{
    private ArrayList<PlayerBoardView> playerBoardViews;
    private MapView mapView;
    private ArrayList<PlayerHandView> playerHands;

    public RemoteView (){

    }

    public ArrayList<PlayerBoardView> getPlayerBoardViews() {
        return playerBoardViews;
    }

    public MapView getMapView() {
        return mapView;
    }

    public ArrayList<PlayerHandView> getPlayerHands() {
        return playerHands;
    }

    @Override
    public void update(Observable o, Object arg) {

    }

    @Override
    public LocalView getLocalView(int playerID) throws RemoteException {
        return null;
    }

    @Override
    public AvailableActions askAction(ActionRequestView codedAction, int playerID) throws RemoteException {
        AvailableActions availableActions=new AvailableActions();

        //TODO implementazione calcolo azioni fattibili chiamando il controller
        return availableActions;
    }
}
