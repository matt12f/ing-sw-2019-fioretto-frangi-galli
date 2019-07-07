package it.polimi.se2019.view;

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

    public LocalView(int playerId, RemoteView remoteView){
        this.playerBoardViews = remoteView.getPlayerBoardViews();
        this.playerId = playerId;
        this.mapView = remoteView.getMapView();
        this.playerPosition=remoteView.getMapView().getPlayerPosition(
                remoteView.getPlayerBoardViews().get(this.playerId).getColor());
        this.playerHand = remoteView.getPlayerHands().get(playerId);

    }

    public void setPlayerPosition(CellView playerPosition) {
        this.playerPosition = playerPosition;
    }

    /**
     * this method returns the playerboards in the right order, with the one from the current player first
     * @return all the playerboards in order
     */
    public ArrayList<PlayerBoardView> getPlayerBoardViews() {
        ArrayList<PlayerBoardView> temp= new ArrayList<>();
        temp.add(this.playerBoardViews.get(this.playerId));

        for (int i = 0; i < this.playerBoardViews.size() ; i++)
            if(this.playerBoardViews.get(i)!=temp.get(0))
                temp.add(this.playerBoardViews.get(i));

        return temp;
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


    @Override
    public void update(Observable o, Object remoteView) {
            RemoteView remote = (RemoteView) remoteView;
            this.mapView = remote.getMapView();
            this.playerHand = remote.getPlayerHands().get(this.playerId);
            this.playerBoardViews = remote.getPlayerBoardViews();
            this.playerPosition = remote.getMapView().getPlayerPosition(remote.getPlayerBoardViews().get(this.playerId).getColor());
    }
}
