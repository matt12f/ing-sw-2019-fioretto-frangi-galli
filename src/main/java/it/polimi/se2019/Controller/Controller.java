package it.polimi.se2019.controller;

import it.polimi.se2019.enums.CellType;
import it.polimi.se2019.exceptions.FullException;
import it.polimi.se2019.model.game.*;
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
    private TurnManager activeturn;

    /**
     * This constructor generates one local Controller object for one Player
     */
    public Controller() {
        ArrayList<Player> players = new ArrayList<>();

        //This part is about the first player
        String nickname="Da fare";
        char color='b';
        //TODO chiedere nickname passando da VIEW
        //TODO scelta del colore passando da VIEW
        players.add(new Player(1,nickname,color));

        //This part is about the game
        //TODO chiedere la modalità di gioco
        String gameMode="normal";
        int mapNumber=1; //TODO fare scegliere il numero di mappa
        this.mainGameModel=new GameModel(0,players,gameMode,mapNumber);
        this.remoteView=setupRemoteObjExport();
        this.activeturn = new TurnManager();
    }

    /**
     * This method offers the object on the LAN
     * @return
     */
    private RemoteView setupRemoteObjExport(){
        RemoteView virtualView = new RemoteView();

        //bind the object
        try {
            Naming.rebind("rmi://localhost/adrenaline", virtualView);
        } catch (RemoteException | MalformedURLException e){
            LOGGER.log(Level.FINE,"Controller",e);
        }
        return virtualView;
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

    public TurnManager getActiveturn() {
        return activeturn;
    }

    public GameStats playGame() {
        setupBoard();
        //TODO ciclo che fa partire startTurn()
        return new GameStats(mainGameModel.getPlayerList(),mainGameModel.getTurn());
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
            LOGGER.log(Level.FINE,"Setup game in Controller",e);
        }
    }

    private void startTurn(){
        //TODO scrivere preparazione (cos'altro serve?)
        activeturn.playTurn();
    }

    @Override
    public void update(Observable o, Object arg) {
        //TODO scrivere metodo
    }
}