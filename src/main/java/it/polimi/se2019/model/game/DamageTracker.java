package it.polimi.se2019.model.game;

import java.util.ArrayList;
import java.util.Collections;

import static java.util.Collections.sort;

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
        }while(!found && i < 12);
    }

    /**
     * this method adds the single mark it receives
     * NOTE: there can only be up to three marks of each color on a player board
     */
    public void addMark(char mark){
        if(markCounter(mark)<3)
            this.marks.add(mark);
    }

    /**
     * This method counts the amount of marks of a given color
     * and then removes them from the array
     * @param color
     * @return
     */
    public int checkMarks(char color){
        int numberOfMarks= markCounter(color);
        this.marks.removeAll(Collections.singleton(color));
        return numberOfMarks;
    }

    /**
     * This method counts the amount of marks of a given color
     */
    private int markCounter(char color){
        sort(this.marks); //this orders the marks in alphabetical order
        int firstOccurrence=this.marks.indexOf(color);
        if (firstOccurrence==-1) //there are no marks of the given color
            return 0;
        int i=firstOccurrence;
        while (i<this.marks.size() && this.marks.get(i)==color)
            i++;
        return i-firstOccurrence;
    }

    public ArrayList<Character> getMarks() {
        return marks;
    }

    public char[] getDamage() {
        return damage;
    }

    public boolean hasNoDamage(){
        for (int i=0; i<12; i++)
            if(this.damage[i]!=' ')
                return false;
        return true;
    }

    /**
     * this method resets the vector damage
     */
    public void setKill(){
        for (int i=0; i < 12; i++) {
            this.damage[i]= ' ';
        }
    }

    @Override
    public DamageTracker clone(){
        DamageTracker damageTracker=new DamageTracker();
        damageTracker.damage=this.damage.clone();
        damageTracker.marks.addAll(this.marks);
        return damageTracker;
    }
}
