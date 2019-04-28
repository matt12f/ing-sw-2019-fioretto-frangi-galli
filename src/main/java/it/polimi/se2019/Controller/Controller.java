package it.polimi.se2019.controller;

import it.polimi.se2019.model.game.GameModel;
import it.polimi.se2019.model.game.Player;
import it.polimi.se2019.view.RemoteView;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class Controller implements Observer {
    private GameModel mainGameModel;
    private RemoteView remoteView;
    private TurnManager activeturn;

    /**
     * This constructor generates one local Controller object for one Player
     */
    public Controller() {
        ArrayList<Player> players = new ArrayList<>();
        String nickname="Da fare";
        char color='b';
        //TODO chiedere nickname passando da VIEW
        //TODO scelta del colore passando da VIEW
        players.add(new Player(1,nickname,color));
        //TODO chiedere la modalità di gioco
        String gameMode="normal";
        this.mainGameModel=new GameModel(players,gameMode);
        //TODO capire come usare le remoteviews
        this.remoteView=new RemoteView();
        this.activeturn = new TurnManager();
    }

    public GameModel getMainGameModel() {
        return mainGameModel;
    }

    public RemoteView getLocalView() {
        return remoteView;
    }

    public TurnManager getActiveturn() {
        return activeturn;
    }

    public GameStats playGame() {
        setupGame();
        //TODO ciclo che fa partire startTurn()
        return new GameStats(mainGameModel.getPlayerList(),mainGameModel.getTurn());
    }

    private void setupGame(){
        //TODO riempire spawnCell con carte
        //TODO riempire dropCell con carte

    }

    private void startTurn(){

    }

    @Override
    public void update(Observable o, Object arg) {

    }
}