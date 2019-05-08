package it.polimi.se2019.view;

import java.util.ArrayList;
import java.util.Observable;

public class RemoteView  extends View{
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
}
