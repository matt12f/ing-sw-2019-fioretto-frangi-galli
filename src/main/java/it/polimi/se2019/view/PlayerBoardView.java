package it.polimi.se2019.view;

import it.polimi.se2019.enums.Color;
import it.polimi.se2019.model.game.PlayerBoard;

import java.io.Serializable;

public  class PlayerBoardView implements Serializable {
    private Color color;
    private AmmoView ammo;
    private boolean front;
    private boolean frontActionTile; //if true: show ActionTileNormal; if false: show ActionTileFrenzy
    private DamageView damageView;
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


    public DamageView getDamageView() {
        return damageView;
    }

    public void update(PlayerBoard playerBoard){
        this.front = playerBoard.isFront();
        try{
            if (playerBoard.getActionTileFrenzy().getActionCounter() == 1)
                this.frenzy = 2;
            else
                this.frenzy = 1;
        }catch (NullPointerException e){
            this.frenzy = 0;
        }
        this.ammo.setBLUE(playerBoard.getAmmo().getBlue());
        this.ammo.setRED(playerBoard.getAmmo().getRed());
        this.ammo.setYELLOW(playerBoard.getAmmo().getYellow());
        this.getDamageView().setDamage(playerBoard.getDamageTrack().getDamage());
        this.getDamageView().setMarks(playerBoard.getDamageTrack().getMarks());
    }

    public Color getColor() {
        return color;
    }
}
