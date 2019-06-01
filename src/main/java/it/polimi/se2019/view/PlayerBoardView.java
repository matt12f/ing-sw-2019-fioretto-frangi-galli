package it.polimi.se2019.view;

public class PlayerBoardView {
    private AmmoView ammo;
    private boolean front;
    private DamageView damageView;
    private ActionTileView actionTile;
    private int score;

    public void setScore(int toSet){
        this.score=toSet;
    }

    public int getScore(){
        return this.score;
    }

    public AmmoView getAmmo(){
        return ammo;
    }

    public boolean isFront() {
        return front;
    }

    public ActionTileView getActionTile() {
        return actionTile;
    }
}
