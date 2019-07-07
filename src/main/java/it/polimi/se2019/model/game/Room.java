package it.polimi.se2019.model.game;

import it.polimi.se2019.enums.Color;

import java.io.Serializable;
import java.util.ArrayList;

public class Room implements Serializable {
    private Color color;
    private ArrayList<Player> players;
    private ArrayList<NewCell> cells;

    /**
     *
     * @return if a room has n players
     */
    public boolean isEmpty(){
        if (players==null)
            return true;
        else if (players.isEmpty())
            return true;
        else
            return false;
    }

    public void addCell(NewCell cell) {
        this.cells.add(cell);
    }

    public ArrayList<NewCell> getCells() {
        return cells;
    }

    public Color getColor(){
        return color;
    }

    public ArrayList<Player> getPlayers(){
        return players;
    }

    public void addPlayers(Player newPlayer){
        this.players.add(newPlayer);
    }

    public void removePlayers(Player player){
        this.players.remove(player);
    }
}
