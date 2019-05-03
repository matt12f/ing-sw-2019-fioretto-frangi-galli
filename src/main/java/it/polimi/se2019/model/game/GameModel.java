package it.polimi.se2019.model.game;

import java.util.ArrayList;
import java.util.Observable;

public class GameModel extends Observable{
    private Decks currentDecks;
    private int gameNumberId; // Potrebbe servire per il multiGame
    private ArrayList<Player> playerList;
    private String gameMode;
    private Map currentMap;
    private KillShotTrack killshotTrack;
    private boolean finalFrenzy;
    private int turn;

    public GameModel(ArrayList<Player> playerList, String gameMode){
        this.currentDecks=new Decks();
        this.gameMode = gameMode;
        this.playerList = playerList;

    }

    public Decks getCurrentDecks() {
        return currentDecks;
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

    public Map getCurrentMap() {
        return currentMap;
    }

    public KillShotTrack getKillshotTrack() {
        return killshotTrack;
    }

    public boolean getfinalFrenzy(){
        return finalFrenzy;
    }


    /**
     * this method builds the actiontile Frenzy objects for each player
     * and the method checks and sends the right amount of frenzy actions to the constructor.
     * @param activePlayer is the player that has to play the first frenzy turn (sent by the controller)
     */
    public void setFinalFrenzy(int activePlayer) {
        this.finalFrenzy = true;
        int actions, i = activePlayer;
        for (Player player : this.playerList){
            if(activePlayer != 1 && i >= activePlayer){
                actions = 2;
            }else{
                actions = 1;
            }
            if(i == this.playerList.size() ){
                i= 1;
            }else{
                i++;
            }
            player.getPlayerBoard().activateFrenzy(actions);
        }
    }

    public int getTurn() {
        return turn;
    }

    public void incrementTurn() {
        this.turn++;
    }
}
