package it.polimi.se2019.view;

import java.io.Serializable;
import java.util.ArrayList;

public class DamageView implements Serializable {
    private char[] damage;
    private ArrayList<Character> marks;

    public DamageView(char [] damageFromModel){
        this.damage = new char[12];
        for (int i = 0; i < 12; i++)
            this.damage[i] = damageFromModel[i];

    }

    public void setDamage(char[] damage) {
        this.damage = damage;
    }

    public ArrayList<Character> getMarks() {
        return marks;
    }

    public void setMarks(ArrayList<Character> marks) {
        this.marks = marks;
    }

    public char[] getDamage() {
        return damage;
    }
}
