package it.polimi.se2019.view;

import java.util.ArrayList;
import java.util.Observable;

public class LocalView  extends View {
    private ArrayList<PlayerBoardView> playerBoardViews;
    private MapView mapView;
    private PlayerHandView playerHand;

    public LocalView (){

    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
