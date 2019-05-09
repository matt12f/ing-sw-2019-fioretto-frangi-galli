package it.polimi.se2019.view;

public class ActionTileView {
    private boolean front;
    private boolean adrenaline1;
    private boolean adrenaline2;

    public void setAdrenaline1(boolean adrenaline1) {
        this.adrenaline1 = adrenaline1;
    }

    public void setAdrenaline2(boolean adrenaline2) {
        this.adrenaline2 = adrenaline2;
    }

    public void setFront(boolean front) {
        this.front = front;
    }

    public boolean isFront() {
        return front;
    }

    public boolean isAdrenaline1() {
        return adrenaline1;
    }

    public boolean isAdrenaline2() {
        return adrenaline2;
    }
}
