package it.polimi.se2019.view;

import it.polimi.se2019.model.game.Player;

import java.util.ArrayList;

public class CellView {
    private Square[][] matrix;
    private ArrayList<Player> Players;

    public void setPlayers(ArrayList<Player> toSet){
        this.Players = toSet;
    }

    public ArrayList<Player> getPlayers(){
        return this.Players;
    }

    public Square[][] getSquare() {
        return matrix;
    }

    public void setSquare(Square[][] matrix) {
        this.matrix = matrix;
    }
}


