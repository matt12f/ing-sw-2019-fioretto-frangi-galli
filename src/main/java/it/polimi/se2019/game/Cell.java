package it.polimi.se2019.game;

public class Cell {
    private int id;
    private char color;
    private int  players[];
    private char top;
    private char bottom;
    private char left;
    private char right;
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
