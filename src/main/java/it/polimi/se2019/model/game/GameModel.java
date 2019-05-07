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


    public GameModel(int gameNumberId,ArrayList<Player> playerList, String gameMode,int mapNumber){
        this.currentDecks=new Decks();
        this.gameNumberId=gameNumberId;
        this.playerList = playerList;
        this.gameMode = gameMode;
        this.currentMap=new Map(mapNumber);
        if(gameMode.equals("normal"))
            this.killshotTrack=new KillShotTrack();
        else if(gameMode.equals("turret"))
            this.killshotTrack=new KillShotTrackTurret();
        else if(gameMode.equals("domination"))
            this.killshotTrack=new KillShotTrackDomination();
        else
            this.killshotTrack=null;
        this.finalFrenzy=false;
        this.turn=0;
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

    public boolean getFinalFrenzy(){
        return finalFrenzy;
    }

    /**
     * this method builds the actiontile Frenzy objects for each player
     * and the method checks and sends the right amount of frenzy actions to the constructor.
     * @param activePlayer is the player that has to play the first frenzy turn (sent by the controller)
     */
    public void activateFinalFrenzy(int activePlayer) {
        this.finalFrenzy = true;
        int actions, i = activePlayer;
        for (Player player : this.playerList){
            if(activePlayer != 1 && i >= activePlayer){
                actions = 2;
            }else{
                actions = 1;
            }
            if(i == this.playerList.size()){
                i=1;
            }else{
                i++;
            }
            if(player.getPlayerBoard().getDamageTrack().hasNoDamage()) //All players with no damage switch to final frenzy tiles
                player.getPlayerBoard().flipPlayerBoard();
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
