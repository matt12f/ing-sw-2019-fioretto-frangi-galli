package it.polimi.se2019.model.game;

import it.polimi.se2019.enums.Color;

import java.util.ArrayList;

public class Room {
    private Color color;
    private ArrayList<Player> players;

    public Room(Color color){
        this.players=new ArrayList<>();
        this.color = color;
    }

    /**
     * @return the color of the room
     */
    public Color getColors(){
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
