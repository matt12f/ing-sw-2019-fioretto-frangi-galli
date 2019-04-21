package it.polimi.se2019.model.game;

import java.util.ArrayList;

public class Room {
    private char color;
    private ArrayList<Player> players;

    public Room(char color){
        this.players=new ArrayList<>();
        this.color = color;
    }

    /**
     * @return the color of the room
     */
    public char getColors(){
        return color;
    }

    /**
     * @return all of the players in the room
     */
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
