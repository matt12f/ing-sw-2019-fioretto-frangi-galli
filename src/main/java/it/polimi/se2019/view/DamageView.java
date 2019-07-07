package it.polimi.se2019.view;

import java.io.Serializable;
import java.util.ArrayList;

public class DamageView implements Serializable {
    private char[] damage;
    private ArrayList<Character> marks;

    /**
     * create the view data of the damage and marks of a player
     * @param damageFromModel list of the player's damage
     */
    public DamageView(char [] damageFromModel){
        this.damage = new char[12];
        for (int i = 0; i < 12; i++)
            this.damage[i] = damageFromModel[i];

    }

    /**
     * update the list of the damage
     * @param damage list of the damage to update
     */
    public void setDamage(char[] damage) {
        this.damage = damage;
    }

    /**
     *
     * @return the list of the marks
     */
    public ArrayList<Character> getMarks() {
        return marks;
    }

    /**
     * update of the player's marks
     * @param marks
     */
    public void setMarks(ArrayList<Character> marks) {
        this.marks = marks;
    }

    /**
     *
     * @return the damage list
     */
    public char[] getDamage() {
        return damage;
    }
}
