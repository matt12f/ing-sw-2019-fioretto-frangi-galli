package it.polimi.se2019.model.game;

import java.util.ArrayList;
import java.util.Observable;

public class GameModel extends Observable{
    public Decks currentDecks;
    private int gameNumberId; // Potrebbe servire per il multiGame
    private ArrayList<Player> playerList;
    private String gameMode;
    public Map currentMap;
    public KillShotTrack killshotTrack;
    private boolean finalFrenzy;
    private int turn;

    public GameModel(ArrayList<Player> playerList, String gameMode){
        this.currentDecks=new Decks();
        this.gameMode = gameMode;
        this.playerList = playerList;

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


    /**
     * this method allow to build the actiontile Frenzy objects for each player
     * the controller send the player that has to play the first frenzy turn, and the method control
     * and send the right amount of frenzy actions to the builder.
     * @param activePlayer
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
            player.playerBoard.activateFrenzy(actions);
        }
    }




    public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }
}
