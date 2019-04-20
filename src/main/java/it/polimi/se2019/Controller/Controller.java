package it.polimi.se2019.controller;

import it.polimi.se2019.model.game.GameModel;
import it.polimi.se2019.model.game.Player;
import it.polimi.se2019.view.MainView;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class Controller implements Observer {
    private ArrayList<GameModel> game;
    private ArrayList<MainView> view;
    private TurnManager activeturn;

    /**
     * This constructor is supposed to generate only one Controller object for one Game
     * infact the controller is built around the first (starter) player
     */
    public Controller() {
        ArrayList<Player> players = new ArrayList<>();
        char [] availableColors ={'y','b','v','g','w'};
        players.add(new Player(1,availableColors));
        //TODO chiedere la modalità di gioco
        String gameMode="normal";

        //This is the list for each player's GameModel
        this.game = new ArrayList<>();
        //The GameModel for the first player is added to the list
        this.game.add(new GameModel(players,gameMode));

        //This is the list for each player's MainView
        this.view= new ArrayList<>();
        //The MainView for the first player is added to the list
        this.view.add(new MainView());

        this.activeturn = new TurnManager();
    }

    public GameStats playGame(GameModel activeGame) {
        return new GameStats(activeGame.getPlayerList(),activeGame.getTurn());
    }

    private void startTurn(GameModel activeGame){

    }

    @Override
    public void update(Observable o, Object arg) {

    }
}