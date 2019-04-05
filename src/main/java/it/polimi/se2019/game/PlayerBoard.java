package it.polimi.se2019.game;

public class PlayerBoard {
    private char color;
    private int skulls;
    public Ammo ammo;
    public DamageTracker damage;
    public ActionTileNormal actionTile;
    public ActionTileFrenzy actionTileFrenzy;
    public Hand hand;

    /**Return the avatar color
     *
     */
    public char getColor() {
        return color;
    }

    /**Is used to update the player's death counter
     *
     */
    public void setSkulls() {
        //TODO scrivere codice
    }

    /**Return the player's number of death
     *
     */
    public int getSkulls() {
        return skulls;
    }
    /**This method "flips" the action board, creating a frenzy one and dumping the normal one
     * */
    public void activateFrenzy(){
        //TODO scrivere codice
    }
}
