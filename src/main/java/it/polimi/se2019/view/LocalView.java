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

    //TODO rivedere update di Hand e Ammo con classi corrette: PlayerHandView e AmmoView
    @Override
    public void update(Observable o, Object arg) {
        if(arg instanceof PlayerBoard){
            for(int i=0; i<playerBoardViews.size(); i++){
            }
        }else if(arg instanceof Map){
            for (int i=0; i<3; i++) {
                for(int j=0; j<2; j++){
                }
            }
        }else if(arg instanceof GunDeck){

        }else if(arg instanceof AmmoTilesDeck){

        }else if(arg instanceof PowerupsDeck){

        }else if(arg instanceof Integer){
            for (int i=0; i<playerBoardViews.size(); i++){
                this.playerBoardViews.get(i).setScore((int) arg);
            }
        }else{
        }
    }
}
