package it.polimi.se2019.view;

public class PlayerBoardView {
    private AmmoView ammo;
    private boolean front;
    private DamageView damageView;
    private ActionTileView actionTile;
    private int score;

    private void fakeMethodForGit(String resolveIssue){
        if (resolveIssue.length()>4)
            System.out.println("OMG");
    }

    public void setScore(int toSet){
        this.score=toSet;
    }

    public int getScore(){
        return this.score;
    }

    public AmmoView getAmmo(){
        return ammo;
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
