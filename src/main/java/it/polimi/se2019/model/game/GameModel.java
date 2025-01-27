package it.polimi.se2019.model.game;

import com.google.gson.Gson;

import it.polimi.se2019.controller.MapManager;
import it.polimi.se2019.enums.CellType;
import it.polimi.se2019.enums.Color;
import it.polimi.se2019.view.modelChanged;

import java.awt.*;
import java.io.*;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GameModel extends Observable{
    private static final Logger LOGGER = Logger.getLogger(GameModel.class.getName());

    private Decks currentDecks;
    private ArrayList<Player> playerList;
    private ArrayList<Player> deadPlayers;
    private Map currentMap;
    private KillShotTrack killshotTrack;
    private boolean finalFrenzy;
    private int turn;
    private List<modelChanged> viewObserver = new ArrayList<>();

    /**
     * creation of the game
     * @param playerList list of the players
     * @param mapNumber map configuration id
     * @param skulls max kills allowed
     */
    public GameModel(ArrayList<Player> playerList, int mapNumber, int skulls){
        this.currentDecks=new Decks();
        this.playerList = playerList;
        this.deadPlayers=new ArrayList<>();

        //Map setup
        Gson gson = new Gson();

        InputStream in = getClass().getResourceAsStream("/JSONfiles/map"+mapNumber+".json");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
            // Convert JSON File to Java Object
            this.currentMap = gson.fromJson(reader, Map.class);
        } catch (IOException  e) {
            LOGGER.log(Level.FINE,"GameModel", e);
        }

        for(NewCell[] line:this.currentMap.getBoardMatrix())
            for(NewCell singleCell:line)
                if(!singleCell.getCellType().equals(CellType.OUTSIDEBOARD))
                    switch (singleCell.getColor()){
                        case RED: this.currentMap.getRooms()[0].addCell(singleCell);break;
                        case YELLOW: this.currentMap.getRooms()[1].addCell(singleCell);break;
                        case BLUE: this.currentMap.getRooms()[2].addCell(singleCell);break;
                        case WHITE: this.currentMap.getRooms()[3].addCell(singleCell);break;
                        case GREEN: this.currentMap.getRooms()[4].addCell(singleCell);break;
                        case VIOLET: this.currentMap.getRooms()[5].addCell(singleCell);break;
                    }

        this.killshotTrack=new KillShotTrack(skulls);
        this.finalFrenzy=false;
        this.turn=0;
    }

    public Decks getCurrentDecks() {
        return currentDecks;
    }

    public ArrayList<Player> getPlayerList() {
        return playerList;
    }

    public Player getPlayerByColor(Color color){
        return getPlayerByColor(color.toString().toLowerCase().charAt(0));
    }

    public Player getPlayerByColor(char color){
        for(Player player1: this.playerList)
            if(player1.getFigure().getColorChar()==color)
                return player1;
        return null;
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

    public ArrayList<Player> getDeadPlayers() {
        return deadPlayers;
    }

    /**
     * this method removes a player from the board and puts it aside to have its board scored at the end of the current
     * turn
     * @param deadPlayer the player that has just been killed
     */
    public void addDeadPlayer(Player deadPlayer) {
        if(!this.deadPlayers.contains(deadPlayer)){

            deadPlayer = this.playerList.get(this.playerList.indexOf(deadPlayer));

            for (int i = 0; i < this.currentMap.getRooms().length; i++) {
                if (this.currentMap.getRooms()[i] != null && this.currentMap.getRooms()[i].getColor() != null)
                    if (this.currentMap.getRooms()[i].getColor().equals(deadPlayer.getFigure().getCell().getColor()))
                        this.currentMap.getRooms()[i].removePlayers(deadPlayer);
            }
            NewCell realCell = this.currentMap.getBoardMatrix()[MapManager.getLineOrColumnIndex(this.currentMap.getBoardMatrix(), deadPlayer.getFigure().getCell(), true)]
                    [MapManager.getLineOrColumnIndex(this.currentMap.getBoardMatrix(), deadPlayer.getFigure().getCell(), false)];

            realCell.removePlayers(deadPlayer);
            this.deadPlayers.add(deadPlayer);
        }
    }

    /**
     * this method builds the action tile frenzy objects for each player
     * and the method checks and sends the right amount of frenzy actions to the constructor.
     * @param activePlayer is the player that has to play the first frenzy turn (sent by the controller)
     */
    public void activateFinalFrenzy(int activePlayer) {
        this.finalFrenzy = true;
        int actions;
        for (Player player : this.playerList){
            if(player.getId() > activePlayer){
                actions = 2;
            }else{
                actions = 1;
            }
            if(player.getPlayerBoard().getDamageTrack().hasNoDamage()) //All players with no damage switch to final frenzy tiles
                player.getPlayerBoard().flipPlayerBoard();
            player.getPlayerBoard().activateFrenzy(actions);
        }
    }

    public void notifyRemoteView(){
        setChanged();
        notifyObservers(this);
    }

    public int getTurn() {
        return turn;
    }

    public void setTurn (int turn){
        this.turn = turn;
    }
}
