package it.polimi.se2019.view;

import it.polimi.se2019.model.game.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class LocalView  extends View implements Observer, Serializable {
    private ArrayList<PlayerBoardView> playerBoardViews;
    private int playerId;
    private MapView mapView;
    private CellView playerPosition;
    private PlayerHandView playerHand;

    public LocalView(ArrayList<PlayerBoardView> playerBoardViews, int playerId, MapView mapView, PlayerHandView playerHand) {
        this.playerBoardViews = playerBoardViews;
        this.playerId=playerId;
        this.mapView = mapView;
        this.playerHand = playerHand;
        this.playerPosition = null;
    }

    public void setPlayerPosition(CellView playerPosition) {
        this.playerPosition = playerPosition;
    }

    public ArrayList<PlayerBoardView> getPlayerBoardViews() {
        return playerBoardViews;
    }

    public PlayerBoardView getPersonalPlayerBoardView() {
        return playerBoardViews.get(playerId);
    }

    public MapView getMapView() {
        return mapView;
    }

    public PlayerHandView getPlayerHand() {
        return playerHand;
    }

    public CellView getPlayerPosition() {
        return playerPosition;
    }

    public int getPlayerId() {
        return playerId;
    }

    public LocalView(int playerId, RemoteView remoteView){
        this.mapView = remoteView.getMapView();
        this.playerBoardViews = remoteView.getPlayerBoardViews();
        this.playerId = playerId;
        this.playerHand = remoteView.getPlayerHands().get(playerId);
    }

    @Override
    public void update(Observable o, Object remoteView) {
            RemoteView remote = (RemoteView) remoteView;
            this.mapView = remote.getMapView();
            this.playerHand = remote.getPlayerHands().get(this.playerId);
            this.playerBoardViews = remote.getPlayerBoardViews();
            this.playerPosition = remote.getMapView().getPlayerPosition(remote.getPlayerBoardViews().get(this.playerId).getColor());
    }
}
