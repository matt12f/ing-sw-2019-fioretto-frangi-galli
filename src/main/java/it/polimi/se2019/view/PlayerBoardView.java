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

    /**
     * this constructor builds the view counterpart of the player board based on the one it's given
     * @param playerBoard is the playerboard to mimic
     * @param score is the player's score
     */
    public PlayerBoardView(PlayerBoard playerBoard, int score) {
        this.score=score;
        this.color=playerBoard.getColor();

        if(playerBoard.getActionTileFrenzy()==null)
            this.frenzy=0;
        else if(playerBoard.getActionTileFrenzy().getActionCounter()==2)
            this.frenzy=1;
        else
            this.frenzy=2;

        this.front=playerBoard.isFront();
        this.ammo=new AmmoView(playerBoard.getAmmo());
        this.damageView=new DamageView(playerBoard.getDamageTrack().getDamage());
    }

    /**
     * update the score
     * @param toSet
     */
    public void setScore(int toSet){
        this.score=toSet;
    }

    /**
     * set the  board color
     * @param color
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     *
     * @param front
     */
    public void setFront(boolean front) {
        this.front = front;
    }

    /**
     * update the board damage
     * @param damageView
     */
    public void setDamageView(DamageView damageView) {
        this.damageView = damageView;
    }

    /**
     *
     * @return the player's score
     */
    public int getScore(){
        return this.score;
    }

    /**
     *
     * @return the player's ammo
     */
    public AmmoView getAmmo(){
        return ammo;
    }

    /**
     *
     * @return if the board is in frenzy mode or not
     */
    public int getFrenzy(){
        return frenzy;
    }

    /**
     * update frenzy  mode
     * @param x
     */
    public void  setFrenzy(int x){
        frenzy = x;
    }

    /**
     * update player's ammo
     * @param toSet
     */
    public void setAmmo(AmmoView toSet){
        this.ammo=toSet;
    }

    /**
     *
     * @return
     */
    public boolean isFront() {
        return front;
    }

    /**
     *
     * @return player's damage
     */
    public DamageView getDamageView() {
        return damageView;
    }

    /**
     * this updates the view board based on the one it's given
     * @param playerBoard given board
     */
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
        this.color = playerBoard.getColor();
        this.getDamageView().setDamage(playerBoard.getDamageTrack().getDamage());
        this.getDamageView().setMarks(playerBoard.getDamageTrack().getMarks());
    }

    /**
     *
     * @return board's color
     */
    public Color getColor() {
        return color;
    }

    /**
     *
     * @param item
     * @return
     */
    @Override
    public boolean equals(Object item) {
        if (item == null)
            return false;

        if (this.getClass() != item.getClass())
            return false;

        PlayerBoardView otherItem = (PlayerBoardView) item;

        return this.color.equals(otherItem.color);
}
}
