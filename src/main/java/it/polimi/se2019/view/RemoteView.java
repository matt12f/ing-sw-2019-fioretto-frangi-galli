package it.polimi.se2019.view;

import it.polimi.se2019.model.game.*;
import it.polimi.se2019.model.game.Map;
import it.polimi.se2019.network.RMIInterface;
import it.polimi.se2019.controller.AvailableActions;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Observer;
import java.util.Observable;


public class RemoteView  extends View implements RMIInterface {
    private ArrayList<PlayerBoardView> playerBoardViews;
    private MapView mapView;
    private ArrayList<PlayerHandView> playerHands;

    public RemoteView (){
        //da implementare ancora
    }

    @Override
    public synchronized void addObserver(Observer o) {
        super.addObserver(o);
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
        }else if(arg instanceof Integer){
            for (int i=0; i<playerBoardViews.size(); i++){
                this.playerBoardViews.get(i).setScore((int) arg);
            }
        }///todo Frangi rivedi il codice -Fabiano
    }

    @Override
    public LocalView getLocalView(int playerID) throws RemoteException {
        return null;
    }

    @Override
    public AvailableActions askAction(ActionRequestView codedAction, int playerID) throws RemoteException {
        AvailableActions availableActions=new AvailableActions();
        //TODO richiama ActionManager
        //TODO implementazione calcolo azioni fattibili chiamando il controller
        return availableActions;
    }
}
