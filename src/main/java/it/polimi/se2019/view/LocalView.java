package it.polimi.se2019.view;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.lang.Object;
import it.polimi.se2019.model.*;
import it.polimi.se2019.model.game.*;

public class LocalView  extends View implements Observer {
    private ArrayList<PlayerBoardView> playerBoardViews;
    private PlayerBoardView personalPlayerBoardView;
    private MapView mapView;
    private PlayerHandView playerHand;

    public LocalView(ArrayList<PlayerBoardView> playerBoardViews, MapView mapView, PlayerHandView playerHand) {
        this.playerBoardViews = playerBoardViews;
        this.mapView = mapView;
        this.playerHand = playerHand;
    }

    public PlayerBoardView getPersonalPlayerBoardView() {
        return personalPlayerBoardView;
    }

    @Override
    public void update(Observable o, Object arg) {
        if(arg instanceof PlayerBoard){
            for(int i=0; i<playerBoardViews.size(); i++){
                this.playerBoardViews.get(i).setAmmo(((PlayerBoard) arg).getAmmo());
                this.playerBoardViews.get(i).setHand(((PlayerBoard) arg).getHand());
                this.playerBoardViews.get(i).setScore(((Player) arg).getScore());
            }
        }else if(arg instanceof Map){
            for (int i=0; i<3; i++) {
                for(int j=0; j<2; j++){
                    this.mapView.getCell(i, j).setPlayers((ArrayList<Player>) arg);
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
