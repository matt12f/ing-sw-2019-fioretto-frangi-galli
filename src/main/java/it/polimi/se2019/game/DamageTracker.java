package it.polimi.se2019.game;

import java.util.ArrayList;

public class DamageTracker {
    private char [] damage;
    private ArrayList<String> marks;

    public DamageTracker(){
        this.damage = new char[12];
    }

    public void setDamage( char damage) {
    }

    public void setMarks(char mark) {
    }

    public ArrayList<String> getMarks() {
        return marks;
    }

    public char[] getDamage() {
        return damage;
    }

}
