package it.polimi.se2019.game;

public class PlayerBoard {
    private char color;
    private int skulls;
    public Ammo ammo;
    public DamageTracker damage;
    public ActionTileNormal actionTile;
    public ActionTileFrenzy actionTileFrenzy;
    public Hand hand;

    public char getColor() {
        return color;
    }

    public void setSkulls() {
    }

    public int getSkulls() {
        return skulls;
    }
    /**This method "flips" the action board, creating a frenzy one and dumping the normal one
     * */
    public void activateFrenzy(){}

}
