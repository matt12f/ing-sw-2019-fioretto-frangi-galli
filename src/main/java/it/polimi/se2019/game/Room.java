package it.polimi.se2019.game;

import java.util.ArrayList;

public class Room {
    private char color;
    private ArrayList<Player>  players;

    public Room(char color){
        this.color = color;


    }

    /**getColors returns the color of the room
     *
     * @return
     */
    public char getColors(){
        return color;
    }

    /**This method returns all the players in the room
     *
     * @return
     */
    public ArrayList<Player> getPlayers(){
        return players;
    }

    /**setPlayers is used to set and update the list of the players in the room
     *
     */
    public void addPlayers(Player newPlayer){
        //TODO setPlayers scrivere codice

    }
    public void removePlayers(Player player){
        //TODO setPlayers scrivere codice

    }
}
