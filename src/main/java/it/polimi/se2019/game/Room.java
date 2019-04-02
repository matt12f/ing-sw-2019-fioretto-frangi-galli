package it.polimi.se2019.game;

public class Room {
    private int cells[][];
    private char color;
    private int players[];

    public Room(){

    }

    /**This method return the cells that compose the room
     *
     * @return
     */
    public int[][] getcells(){
        return cells;
    }

    /**getColors returns the color of the room
     *
     * @return
     */
    public char getColors(){
        return color;
    }

    /**This method returns all the player in the room
     *
     * @return
     */
    public int[] getPlayers(){
        return players;
    }

    /**setPlayers is used to set and update the list of the player in the room
     *
     */
    public void setPlayers(){
        //TODO setPlayers scrivere codice

    }
}
