package it.polimi.se2019.model.game;

import java.util.ArrayList;

public abstract class Cell {
    protected char color;
    protected ArrayList <Player> players;
    protected char top;
    protected char bottom;
    protected char left;
    protected char right;

    /**
     * THis constructor will serve as a super for its two sub classes
     */
    public Cell(char color, char top, char bottom, char left, char right) {
        this.color = color;
        this.players = new ArrayList<>();
        this.top = top;
        this.bottom = bottom;
        this.left = left;
        this.right = right;
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
