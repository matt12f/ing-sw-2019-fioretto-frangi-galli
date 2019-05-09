package it.polimi.se2019.model.game;

import it.polimi.se2019.exceptions.FullException;

import java.util.ArrayList;

public abstract class Cell {
    protected char color;
    protected ArrayList <Player> players;
    protected char top;
    protected char bottom;
    protected char left;
    protected char right;

    /**
     * This constructor will serve as a super for its two sub classes
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

    /**
     * abstract methods for DropCell to be called on Cell objects (the boardMatrix in Map mainly)
     */
    public abstract Object pickItem(int pick);
    public abstract Object getItem();
    public abstract void setItem(Object card)throws FullException;

}
