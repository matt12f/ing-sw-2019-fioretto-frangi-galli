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

    /**
     * create a local representation of the game
     * @param playerId id of the player
     * @param remoteView remote view data in the server
     */
    public LocalView(int playerId, RemoteView remoteView){
        this.playerBoardViews = remoteView.getPlayerBoardViews();
        this.playerId = playerId;
        this.mapView = remoteView.getMapView();
        this.playerPosition=remoteView.getMapView().getPlayerPosition(
                remoteView.getPlayerBoardViews().get(this.playerId).getColor());
        this.playerHand = remoteView.getPlayerHands().get(playerId);

    }

    /**
     * set the position cell of the user
     * @param playerPosition
     */
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

    /**
     *
     * @return the user board
     */
    public PlayerBoardView getPersonalPlayerBoardView() {
        return playerBoardViews.get(playerId);
    }

    /**
     *
     * @return the view map data
     */
    public MapView getMapView() {
        return mapView;
    }

    /**
     *
     * @return the player's hand
     */
    public PlayerHandView getPlayerHand() {
        return playerHand;
    }

    /**
     *
     * @return the cell the player's figure is staying
     */
    public CellView getPlayerPosition() {
        return playerPosition;
    }

    /**
     *
     * @return the player's id
     */
    public int getPlayerId() {
        return playerId;
    }

    /**
     * update the local view
     * @param o
     * @param remoteView
     */
    @Override
    public void update(Observable o, Object remoteView) {
            RemoteView remote = (RemoteView) remoteView;
            this.mapView = remote.getMapView();
            this.playerHand = remote.getPlayerHands().get(this.playerId);
            this.playerBoardViews = remote.getPlayerBoardViews();
            this.playerPosition = remote.getMapView().getPlayerPosition(remote.getPlayerBoardViews().get(this.playerId).getColor());
    }
}
