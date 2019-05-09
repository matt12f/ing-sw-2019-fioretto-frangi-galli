package it.polimi.se2019.view;

import it.polimi.se2019.model.game.Player;

import java.util.ArrayList;

public class CellView {
    private Square[][] matrix;
    private ArrayList<Player> Players;

    public void setPlayers(ArrayList<Player> toSet){
        this.Players = toSet;
    }
}


