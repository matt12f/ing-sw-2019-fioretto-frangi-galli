package it.polimi.se2019.view;

import it.polimi.se2019.model.cards.GunCard;
import it.polimi.se2019.model.game.*;

import java.util.ArrayList;
import java.util.Observer;
import java.util.Observable;

public class RemoteView  extends View {
    private ArrayList<PlayerBoardView> playerBoardViews;
    private MapView mapView;
    private ArrayList<PlayerHandView> playerHands;

    /**
     * create a view representation of the game
     * @param model model data
     * @param mapNum map configuration
     * @param skulls maximum kills allowed
     */
    public RemoteView (GameModel model, int mapNum, int skulls){
        this.playerHands = new ArrayList<>();
        this.playerBoardViews = new ArrayList<>();
        for (Player player: model.getPlayerList()){
            this.playerBoardViews.add(new PlayerBoardView(player.getPlayerBoard(),player.getScore()));
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

    /**
     * method that notify changes to all the clients
     */
    public void notifyLocalViews(){
        setChanged();
        notifyObservers(this);
    }

    @Override
    public synchronized void addObserver(Observer o) {
        super.addObserver(o);
    }

    /**
     * This method updates the Remote View (the one on the Server) when it's changed by the choices of a player
     * at the end of the turn.
     */
    @Override
    public void update(Observable o, Object arg) {
        GameModel model  = (GameModel) arg;
        boolean[] loaded = new boolean[Hand.getMaxcards()];
        GunCard gunCard;
        this.mapView.getKillView().update(model.getKillshotTrack());
        this.mapView.uploadBoardMatrix(model.getCurrentMap().getBoardMatrix());
        int indexOfPlayer;
        for (PlayerBoardView player: playerBoardViews) {
            indexOfPlayer=playerBoardViews.indexOf(player);

            player.update(model.getPlayerList().get(indexOfPlayer).getPlayerBoard());
            boolean[] temp = new boolean[3];
            if(model.getPlayerList().get(indexOfPlayer).getPlayerBoard().getHand().getGuns()[0] != null)
                temp[0] = model.getPlayerList().get(indexOfPlayer).getPlayerBoard().getHand().getGuns()[0].isLoaded();
            if(model.getPlayerList().get(indexOfPlayer).getPlayerBoard().getHand().getGuns()[1] != null)
                temp[1] = model.getPlayerList().get(indexOfPlayer).getPlayerBoard().getHand().getGuns()[1].isLoaded();
            if(model.getPlayerList().get(indexOfPlayer).getPlayerBoard().getHand().getGuns()[2] != null)
                temp[2] = model.getPlayerList().get(indexOfPlayer).getPlayerBoard().getHand().getGuns()[2].isLoaded();




            this.playerHands.get(indexOfPlayer).setPowerups(model.getPlayerList().get(indexOfPlayer).getPlayerBoard().getHand().getPowerups());
            this.playerHands.get(indexOfPlayer).setAdditionalPowerup(model.getPlayerList().get(indexOfPlayer).getPlayerBoard().getHand().getAdditionalPowerup());
            this.playerHands.get(indexOfPlayer).setGuns(model.getPlayerList().get(indexOfPlayer).getPlayerBoard().getHand().getGuns());
            this.playerHands.get(indexOfPlayer).setLoadedGuns(temp);
            for (int i = 0; i < Hand.getMaxcards() ; i++) {
                gunCard = model.getPlayerList().get(indexOfPlayer).getPlayerBoard().getHand().getGuns()[i];
                if(gunCard != null)
                    loaded[i] = gunCard.isLoaded();
            }
            this.playerHands.get(indexOfPlayer).setLoadedGuns(loaded);
        }
        notifyLocalViews();
    }
}
