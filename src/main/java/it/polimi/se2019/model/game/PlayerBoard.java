package it.polimi.se2019.model.game;

public class PlayerBoard {
    private char color;
    private int skulls; //the amount of skulls reduces the value of the scored card
    private ActionTileNormal actionTileNormal;
    private Ammo ammo;
    private DamageTracker damageTrack;
    private Hand hand;
    ActionTileFrenzy actionTileFrenzy;

    public PlayerBoard(char color){
        this.color = color;
        this.ammo = new Ammo();
        this.damageTrack = new DamageTracker();
        this.actionTileNormal = new ActionTileNormal();
        this.hand = new Hand();
        this.skulls = 0;
    }

    /**Return the board's color
     *
     */
    public char getColor() {
        return color;
    }

    /**It's used to update the value of the board
     */
    public void addSkull() {
        this.skulls++;
    }

    /**Return the player's number of death
     *
     */
    public int getSkulls() {
        return skulls;
    }

    public ActionTileNormal getActionTileNormal(){
        return actionTileNormal;
    }
    public ActionTileFrenzy getActionTileFrenzy(){
        return actionTileFrenzy;
    }

    /**This method "flips" the action board, creating a frenzy one and dumping the normal one
     * */
    public void activateFrenzy(int actions){
        this.skulls=0;//when final frenzy is turned on skulls don't count anymore, so they must
        // be reset to 0. (they could impede the calculation of a board's value otherwise)
        this.actionTileFrenzy= new ActionTileFrenzy(actions);
    }

    public Ammo getAmmo() {
        return ammo;
    }

    public DamageTracker getDamageTrack() {
        return damageTrack;
    }

    public Hand getHand() {
        return hand;
    }
}
