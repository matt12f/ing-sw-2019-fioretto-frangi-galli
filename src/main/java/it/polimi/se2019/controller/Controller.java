package it.polimi.se2019.controller;

import it.polimi.se2019.enums.CellType;
import it.polimi.se2019.enums.Color;
import it.polimi.se2019.exceptions.FullException;
import it.polimi.se2019.model.game.*;
import it.polimi.se2019.network.ClientHandler;
import it.polimi.se2019.view.LocalView;
import it.polimi.se2019.view.RemoteView;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Controller implements Observer {
    private static final Logger LOGGER = Logger.getLogger(Controller.class.getName());
    private GameModel mainGameModel;
    private RemoteView remoteView;
    private TurnManager activeTurn;

    /**
     * This constructor generates one local controller object for one game
     */
    //TODO rivedere costrutture con la nuova modalità di connessione
    public Controller(ArrayList<Player> players, int mapNumber, int skulls) {
        this.mainGameModel=new GameModel(players, mapNumber, skulls);
        this.remoteView=new RemoteView(this.mainGameModel, mapNumber, skulls);
        this.activeTurn = new TurnManager();
    }

    public GameModel getMainGameModel() {
        return mainGameModel;
    }

    public RemoteView getRemoteView() {
        return remoteView;
    }

    public LocalView getPlayerLocalView(int playerId){
        return new LocalView(remoteView.getPlayerBoardViews(),playerId,remoteView.getMapView(),remoteView.getPlayerHands().get(playerId));
    }

    public TurnManager getActiveTurn() {
        return activeTurn;
    }

    //TODO Work in progress
    public GameStats playGame() {
        setupBoard();
        //TODO ciclo provvisorio che gioca il turno
        while(mainGameModel.getKillshotTrack().getSkulls()>0){
            playRound();
        }
        PlayerManager.frenzyActivator(this);
        playRound();
        return new GameStats(mainGameModel.getPlayerList(),mainGameModel.getTurn());
    }

    private void playRound(){
        for(Player player:mainGameModel.getPlayerList()){
            activeTurn.setActivePlayer(player);
            activeTurn.playTurn(this);
        }
    }
    private void setupBoard(){
        NewCell[][] mapMatrixToFill=mainGameModel.getCurrentMap().getBoardMatrix();
        try {
            for (int i = 0; i < 3; i++)
                for (int j = 0; j < 4; j++)
                    if (mapMatrixToFill[i][j].getCellType().equals(CellType.DROP))
                    {
                        mapMatrixToFill[i][j].setItem(mainGameModel.getCurrentDecks().getAmmotilesDeck().draw());
                    }else if (mapMatrixToFill[i][j].getCellType().equals(CellType.SPAWN))
                    {
                        for (int k = 0; k < 3; k++)
                            mapMatrixToFill[i][j].setItem(mainGameModel.getCurrentDecks().getGunDeck().draw());
                    }else{
                        mapMatrixToFill[i][j]=null;
                    }
        }catch(FullException e){
            LOGGER.log(Level.FINE,"Setup game in controller", e);
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        //TODO scrivere metodo
    }
}