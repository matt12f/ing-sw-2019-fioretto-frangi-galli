package it.polimi.se2019.model.game;

import java.util.ArrayList;

public class DamageTracker {
    private char [] damage;
    private ArrayList<Character> marks;

    public DamageTracker(){
        this.damage = new char[12];
        this.marks = new ArrayList<>();
        for (int i=0; i<12; i++) {
            this.damage[i]= ' ';
        }
    }

    /**
     * this method finds the last empty cell in damage and adds the damage to the damage track.
     * The checking about kill and overkill will be done in the controller
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
        }while(!found && i<12);
    }

    //TODO perché sono string e non character?
    /**
     * this method adds the mark it receives
     * @param mark
     */
    public void addMarks(Character mark) {
        this.marks.add(mark);
    }

    public ArrayList<Character> getMarks() {
        return marks;
    }

    public char[] getDamage() {
        return damage;
    }

    /**
     * this method resets the vector damage
     */
    public void setKill(){
        for (int i=0; i < 12; i++) {
            this.damage[i]= ' ';
        }
    }

}
