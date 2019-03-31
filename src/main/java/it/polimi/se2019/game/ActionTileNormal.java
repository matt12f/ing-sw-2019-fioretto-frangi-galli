package it.polimi.se2019.game;

public class ActionTileNormal extends ActionTile {

    private boolean adrenalineMode1;
    private boolean adrenalineMode2;

    public ActionTileNormal() {
    }

    public boolean getAdrenalineMode1() {
        return adrenalineMode1;
    }

    public boolean getAdrenalineMode2() {
        return adrenalineMode2;
    }

    public void setAdrenalineMode1() {
        this.adrenalineMode1 = true;
    }

    public void setAdrenalineMode2() {
        this.adrenalineMode2 = true;
    }

}
