package it.polimi.se2019.model.game;

import com.google.gson.Gson;

import it.polimi.se2019.view.modelChanged;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GameModel extends Observable{
    private static final Logger LOGGER = Logger.getLogger(GameModel.class.getName());

    private Decks currentDecks;
    private int gameNumberId; // Potrebbe servire per il multiGame
    private ArrayList<Player> playerList;
    private String gameMode;
    private Map currentMap;
    private KillShotTrack killshotTrack;
    private boolean finalFrenzy;
    private int turn;
    private List<modelChanged> viewObserver = new ArrayList<>();

    public void addObserver(modelChanged toAdd){
        viewObserver.add(toAdd);
    }
    
    public GameModel(int gameNumberId,ArrayList<Player> playerList, String gameMode,int mapNumber){
        this.currentDecks=new Decks();
        this.gameNumberId=gameNumberId;
        this.playerList = playerList;
        this.gameMode = gameMode;

        Gson gson = new Gson();
        try (Reader reader = new FileReader("src/main/JSONfiles/map"+mapNumber+".json")) {
            // Convert JSON File to Java Object
            this.currentMap = gson.fromJson(reader, Map.class);
        } catch (IOException e) {
            LOGGER.log(Level.FINE,"GameModel",e);
        }
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
