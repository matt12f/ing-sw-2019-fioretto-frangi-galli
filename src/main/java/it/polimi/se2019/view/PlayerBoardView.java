package it.polimi.se2019.view;

import it.polimi.se2019.model.game.*;

public class PlayerBoardView {
    private Ammo ammo;
    private boolean front;
    private DamageView damageView;
    private ActionTileView actionTile;
    private Hand hand;
    private int score;

    public void setScore(int toSet){
        this.score=toSet;
    }

    public int getScore(){
        return this.score;
    }

    public Ammo getAmmo(){
        return ammo;
    }

    public Hand getHand(){
        return this.hand;
    }

    public void setAmmo(Ammo toSet){
        this.ammo=toSet;
    }
    public void setHand(Hand toSet){
        this.hand=toSet;
    }

    public boolean isFront() {
        return front;
    }

    public ActionTileView getActionTile() {
        return actionTile;
    }
}