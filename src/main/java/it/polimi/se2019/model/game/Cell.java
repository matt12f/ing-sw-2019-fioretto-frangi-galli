package it.polimi.se2019.model.game;

import java.util.ArrayList;

public abstract class Cell {
    protected char color;
    protected ArrayList <Player> players;
    protected char top;
    protected char bottom;
    protected char left;
    protected char right;
    public Cell(){

    }
    public ArrayList<Player> getPlayers(){
        return players;
    }
    public void addPlayers(Player newPlayer){ }
    public void removePlayers(Player Player){ }
}
