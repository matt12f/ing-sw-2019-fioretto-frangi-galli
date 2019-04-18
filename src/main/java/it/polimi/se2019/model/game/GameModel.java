package it.polimi.se2019.model.game;

import java.util.ArrayList;
import java.util.Observable;

public class GameModel extends Observable{
    private int gameNumberId;
    private ArrayList<Player> playerList;
    private String gameMode;
    public Map currentMap;
    public Decks currentDecks;
    public KillShotTrack killshotTrack;
    private boolean finalFrenzy;
    private int turn;

    public GameModel(ArrayList<Player> playerList, String gameMode){
    //TODO scrivere costruttore
    }

    public int getGameNumberId() {
        return gameNumberId;
    }

    public ArrayList<Player> getPlayerList() {
        return playerList;
    }

    public String getGameMode() {
        return gameMode;
    }
    public boolean getfinalFrenzy(){
        return finalFrenzy;
    }

    public void setFinalFrenzy() {
        this.finalFrenzy = true;
    }

    public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }
}
