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
     * this method finds the first empty cell in damage and adds the damage drop to the damage track.
     * if it returns false: it's damage over 12, meaning  it's wasted
     * if it returns true: everything is ok
     */
    public boolean addDamage(char damage){
        boolean spotFound = false;
        int i = 0;
        do{
            if(this.damage[i] == ' '){
                this.damage[i] = damage;
                spotFound = true;
            }
            i++;
        }while(!spotFound && i < 12);
        return spotFound;
    }

    /**
     * this method evaluates if a player is alive, killed or overkilled
     * @return
     */
    public String playerStatus(){
        if(this.damage[12]!=' ')
            return "overkill";
        else if(this.damage[11]!=' ')
            return "kill";
        else
            return "alive";
    }


    public String dealDamage(char [] damageToDeal){
        for (char damage: damageToDeal)
            if (addDamage(damage)) //the rest of the damage is wasted
                return "overkill";

        return "alive";
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
    public int pullMarks(char color){
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
