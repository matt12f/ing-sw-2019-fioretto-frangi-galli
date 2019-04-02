package it.polimi.se2019.game;

public class Cell {
    protected int id;
    protected char color;
    protected int  players[];
    protected char top;
    protected char bottom;
    protected char left;
    protected char right;
    public Cell(){

    }
    public int getId(){
        return id;
    }
    public int[] getPlayers(){
        return players;
    }
    public void setPlayers(){

    }
}
