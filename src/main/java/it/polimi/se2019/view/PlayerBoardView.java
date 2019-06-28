package it.polimi.se2019.view;

public abstract class PlayerBoardView {
    private AmmoView ammo;
    private boolean front;
    private DamageView damageView;
    private ActionTileView actionTile;
    private int score;
    private int frenzy;

    public void setScore(int toSet){
        this.score=toSet;
    }

    public int getScore(){
        return this.score;
    }

    public AmmoView getAmmo(){
        return ammo;
    }

    public int getFrenzy(){
        return frenzy;
    }

    public void  setFrenzy(int x){
        frenzy = x;
    }


    public void setAmmo(AmmoView toSet){
        this.ammo=toSet;
    }

    public boolean isFront() {
        return front;
    }

    public ActionTileView getActionTile() {
        return actionTile;
    }
}
