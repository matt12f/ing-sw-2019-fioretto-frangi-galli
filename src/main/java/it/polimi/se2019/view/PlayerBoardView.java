package it.polimi.se2019.view;

import it.polimi.se2019.enums.Color;

public  class PlayerBoardView {
    private Color color;
    private AmmoView ammo;
    private boolean front;
    private DamageView damageView;
    private ActionTileView actionTile;
    private int score;
    private int frenzy; //this value is =0 if frenzy is off; =1 if you can use x2 frenzy actions; =2 if you can use x1 frenzy action

    public void setScore(int toSet){
        this.score=toSet;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setFront(boolean front) {
        this.front = front;
    }

    public void setDamageView(DamageView damageView) {
        this.damageView = damageView;
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

    public Color getColor() {
        return color;
    }
}
