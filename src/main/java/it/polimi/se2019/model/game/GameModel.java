package it.polimi.se2019.model.game;

import java.util.ArrayList;
import java.util.Observable;

public class GameModel extends Observable{
    public Decks currentDecks;
    private int gameNumberId; //TODO a cosa serve questo?
    private ArrayList<Player> playerList;
    private String gameMode;
    public Map currentMap;
    public KillShotTrack killshotTrack;
    private boolean finalFrenzy;
    private int turn;

    public GameModel(ArrayList<Player> playerList, String gameMode){
        this.currentDecks=new Decks();
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
