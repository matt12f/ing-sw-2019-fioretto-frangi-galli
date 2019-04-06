package it.polimi.se2019.game;

public abstract class Cell {
    protected char color;
    protected Player [] players;
    protected char top;
    protected char bottom;
    protected char left;
    protected char right;
    public Cell(){

    }
    public Player[] getPlayers(){
        return players;
    }
    public void setPlayers(Player newPlayer){ }
}
