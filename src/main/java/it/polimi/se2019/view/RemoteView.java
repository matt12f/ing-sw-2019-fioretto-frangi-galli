package it.polimi.se2019.view;

import it.polimi.se2019.AdrenalineServer;
import it.polimi.se2019.enums.Status;
import it.polimi.se2019.model.game.*;
import it.polimi.se2019.network.RMIInterface;
import it.polimi.se2019.controller.AvailableActions;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Observer;
import java.util.Observable;

public class RemoteView  extends    View implements RMIInterface {
    private ArrayList<PlayerBoardView> playerBoardViews;
    private MapView mapView;
    private ArrayList<PlayerHandView> playerHands;

    public RemoteView (GameModel model, int mapNum, int skulls){
        this.playerHands = new ArrayList<>();
        this.playerBoardViews = new ArrayList<>();
        for (Player player: model.getPlayerList()) {
            this.playerBoardViews.add(new PlayerBoardView());
            this.playerBoardViews.get(this.playerBoardViews.size() - 1).setScore(0);
            this.playerBoardViews.get(this.playerBoardViews.size() - 1).setColor(player.getPlayerBoard().getColor());
            this.playerBoardViews.get(this.playerBoardViews.size() - 1).setFrenzy(0);
            this.playerBoardViews.get(this.playerBoardViews.size() - 1).setFront(true);
            this.playerBoardViews.get(this.playerBoardViews.size() -1).setAmmo(new AmmoView());
            this.playerBoardViews.get(this.playerBoardViews.size() -1).setDamageView(new DamageView());

            this.playerHands.add(new PlayerHandView());
        }
        this.mapView = new MapView(mapNum, skulls, model);
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

    public void notifyLocalViews(){
        setChanged();
        notifyObservers(this);
    }

    @Override
    public synchronized void addObserver(Observer o) {
        super.addObserver(o);
    }

    //TODO rivedere con classi della view invece che del model


    @Override
    public LocalView getLocalView(int playerID) throws RemoteException {
        return null; //TODO rivedere
    }

    @Override
    public AvailableActions askAction(ActionRequestView codedAction, int playerID) throws RemoteException {
        return null; //TODO rivedere
    }

    @Override
    public void setNicknameRMI(String nickname) throws RemoteException, IllegalArgumentException, InterruptedException {

    }

    @Override
    public String actionRequest() throws RemoteException {
        return null;
    }

    @Override
    public Status getStatus() throws RemoteException {
        return null;
    }

    @Override
    public void update(Observable o, Object arg) {
        GameModel model  = (GameModel) arg;
        boolean[] loaded = new boolean[Hand.getMaxcards()];
        this.mapView.getKillView().update(model.getKillshotTrack());
        this.mapView.uploadBoardMatrix(model.getCurrentMap().getBoardMatrix());
        for (PlayerBoardView player: playerBoardViews) {
            player.update(model.getPlayerList().get(playerBoardViews.indexOf(player)).getPlayerBoard());
            this.playerHands.get(playerBoardViews.indexOf(player)).setPowerups(model.getPlayerList().get(playerBoardViews.indexOf(player)).getPlayerBoard().getHand().getPowerups());
            this.playerHands.get(playerBoardViews.indexOf(player)).setGuns(model.getPlayerList().get(playerBoardViews.indexOf(player)).getPlayerBoard().getHand().getGuns());
            for (int i = 0; i < Hand.getMaxcards() ; i++) {
                loaded[i] = model.getPlayerList().get(playerBoardViews.indexOf(player)).getPlayerBoard().getHand().getGuns()[i].isLoaded();
            }
            this.playerHands.get(playerBoardViews.indexOf(player)).setLoadedGuns(loaded);
        }
        notifyLocalViews();
    }
}
