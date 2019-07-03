package it.polimi.se2019.model.game;

import it.polimi.se2019.enums.Color;

import java.util.Observable;

public class PlayerBoard extends Observable {
    private Color color;
    private int currentBoardValue; //equivalent to the amount of skulls, that reduce the value of the scored card
    private boolean front;
    private ActionTileNormal actionTileNormal;
    private ActionTileFrenzy actionTileFrenzy;
    private Ammo ammo;
    private DamageTracker damageTrack;
    private Hand hand;

    public PlayerBoard(Color color){
        this.color = color;
        this.currentBoardValue = 8;
        this.front=true;
        this.ammo = new Ammo();
        this.damageTrack = new DamageTracker();
        this.actionTileNormal = new ActionTileNormal();
        this.hand = new Hand();
    }

    /**Return the board's color
     *
     */
    public Color getColor() {
        return color;
    }

    public boolean isFront() {
        return front;
    }

    public ActionTileNormal getActionTileNormal(){
        return actionTileNormal;
    }
    public ActionTileFrenzy getActionTileFrenzy(){
        return actionTileFrenzy;
    }

    /**
     * This method "flips only the player board, having its value start at 4
     */
    public void flipPlayerBoard(){
        this.front=false;
        this.currentBoardValue=4; //this is the board's worth when flipped

    }
    /**This method "flips" only the action board, creating a frenzy one and dumping the normal one
     * */
    public void activateFrenzy(int actions){
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

    public int getCurrentBoardValue(){
        return currentBoardValue;
    }
    /**
     * A fresh front playerboard is worth 8,6,4,2,1,1 points
     * A fresh back playerboard is worth 4,2,1,1,1 points
     */
    public void decreaseBoardValue(){
        if(this.front)
            switch (this.currentBoardValue){
                case 8: this.currentBoardValue=6;break;
                case 6: this.currentBoardValue=4;break;
                case 4: this.currentBoardValue=2;break;
                case 2:this.currentBoardValue=1;break;
                default:break;
            }
        else
            switch (this.currentBoardValue){
                case 4: this.currentBoardValue=2;break;
                case 2: this.currentBoardValue=1;break;
                default:break;
            }

        this.setChanged();
        this.notifyObservers();

    }

    @Override
    public PlayerBoard clone(){
        PlayerBoard playerBoard=new PlayerBoard(this.color);
        playerBoard.currentBoardValue = this.currentBoardValue;
        playerBoard.front=this.front;
        playerBoard.ammo=this.ammo.clone();
        playerBoard.damageTrack = this.damageTrack.clone();

        if(this.actionTileNormal!=null)
            playerBoard.actionTileNormal = this.actionTileNormal.clone();
        else
            playerBoard.actionTileNormal=null;

        if(this.actionTileFrenzy!=null)
            playerBoard.actionTileFrenzy = this.actionTileFrenzy.clone();
        else
            playerBoard.actionTileFrenzy=null;

        playerBoard.hand =this.hand.clone();
        return playerBoard;
    }
}
