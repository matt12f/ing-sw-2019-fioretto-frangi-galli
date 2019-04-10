package it.polimi.se2019.model.game;

import java.util.ArrayList;

public class GameModel {
    private int gameNumberId;

    public ArrayList<Player> playerList;
    public Map currentMap;
    private String gameMode;
    public Decks currentDecks;
    public KillshotTrack killshotTrack;
    private boolean finalFrenzy;

    public GameModel(){
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
}
