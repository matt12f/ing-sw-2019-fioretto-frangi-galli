package it.polimi.se2019.controller;

import it.polimi.se2019.model.game.GameModel;
import java.util.ArrayList;

public class Controller {
    private ArrayList<GameModel> game;
    private ArrayList<it.polimi.se2019.view.MainView> view;
    private TurnManager activeturn;


    public Controller() {

    }

    public GameStats playGame(GameModel activeGame) {
        return new GameStats(activeGame.getPlayerList(),activeGame.getTurn());
    }

    private void startTurn(GameModel activeGame){

    }
}