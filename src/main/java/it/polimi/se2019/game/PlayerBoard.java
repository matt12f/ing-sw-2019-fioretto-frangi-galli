package it.polimi.se2019.game;

public class PlayerBoard {
    private char color;
    private int skulls;
    public Ammo ammo;
    public DamageTracker damage;
    public ActionTileNormal actionTile;
    public ActionTileFrenzy actionTileFrenzy;
    public Hand hand;

    public PlayerBoard(char color){
        this.color = color;
        this.ammo = new Ammo();
        this.damage = new DamageTracker();
        this.actionTile = new ActionTileNormal();
        this.hand = new Hand();
        this.skulls = 0;

    }

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
