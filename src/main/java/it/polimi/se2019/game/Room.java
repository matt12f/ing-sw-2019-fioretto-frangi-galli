package it.polimi.se2019.game;

public class Room {
    private char color;
    private Player [] players;

    public Room(char color){
        this.color = color;
        this.players = new Player[5];

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
    public Player[] getPlayers(){
        return players;
    }

    /**setPlayers is used to set and update the list of the players in the room
     *
     */
    public void setPlayers(Player newPlayer){
        //TODO setPlayers scrivere codice

    }
}
