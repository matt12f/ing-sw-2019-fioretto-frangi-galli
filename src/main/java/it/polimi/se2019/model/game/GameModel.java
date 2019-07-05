package it.polimi.se2019.model.game;

import com.google.gson.Gson;

import it.polimi.se2019.controller.MapManager;
import it.polimi.se2019.enums.CellType;
import it.polimi.se2019.enums.Color;
import it.polimi.se2019.view.modelChanged;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
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

    public GameModel(ArrayList<Player> playerList, int mapNumber, int skulls){
        this.currentDecks=new Decks();
        this.playerList = playerList;
        this.deadPlayers=new ArrayList<>();

        //Map setup
        Gson gson = new Gson();
        try (Reader reader = new FileReader("src/main/resources/JSONfiles/map"+mapNumber+".json")) {
            // Convert JSON File to Java Object
            this.currentMap = gson.fromJson(reader, Map.class);
        } catch (IOException e) {
            LOGGER.log(Level.FINE,"GameModel",e);
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
        for(Room room: this.currentMap.getRooms())
            if(room.getColor().equals(deadPlayer.getFigure().getCell().getColor()))
                room.removePlayers(deadPlayer);

        deadPlayer.getFigure().getCell().removePlayers(deadPlayer);
        this.deadPlayers.add(deadPlayer);
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
