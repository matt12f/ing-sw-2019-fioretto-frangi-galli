package it.polimi.se2019.view;

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
    public int askAction(int codedAction, int playerID) throws RemoteException {
        return 0;
    }
}
