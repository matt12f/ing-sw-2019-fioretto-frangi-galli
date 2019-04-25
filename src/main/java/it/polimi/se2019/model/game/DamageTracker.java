package it.polimi.se2019.model.game;

import java.util.ArrayList;

public class DamageTracker {
    private char [] damage;
    private ArrayList<String> marks;

    public DamageTracker(){
        this.damage = new char[12];
        this.marks = new ArrayList<>();
        for (int i=0; i<12; i++) {
            this.damage[i]= ' ';
        }
    }

    /**
     * this method find the last empty cell in damage, the control about kill and overkill will be done in the controller
     *
     *
     * @param damage
     */
    public void addDamage( char damage) {
        boolean found = false;
        int i = 0;
        do{
            if(this.damage[i] == ' '){
                this.damage[i] = damage;
                found = true;
            }
            i++;
        }while(found == false);

    }

    /**
     * this method add a new mark
     * @param mark
     */
    public void addMarks(String mark) {
        this.marks.add(mark);
    }

    public ArrayList<String> getMarks() {
        return marks;
    }

    public char[] getDamage() {
        return damage;
    }

    /**
     * this method allows to reset the vector damage
     */
    public void setKill(){
        for (int i=0; i < 12; i++) {
            this.damage[i]= ' ';
        }

    }

}
