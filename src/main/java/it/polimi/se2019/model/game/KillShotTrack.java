package it.polimi.se2019.model.game;

import java.util.ArrayList;

public class KillShotTrack {

    private int skulls;
    private String [] kills;
    private ArrayList<Character> extraKills;

    /**
     * Default constructor
     *
     */
    public KillShotTrack(int skulls){
        this.kills=new String[skulls];
        this.skulls = skulls;
        this.extraKills=new ArrayList<>();
    }

    public int getSkulls() {
        return skulls;
    }

    /**
     * this method adds kills to the killshot track and extra kills during frenzy
     */
    public void setKills( String kill) {
        boolean found = false;
        int i = 0;
        do{
            if(this.kills[i]==null){
                this.kills[i] = kill;
                found = true;
            }
            i++;
        }while(!found &&i<this.kills.length);
        this.skulls--;

        if(!found){
            this.extraKills.add(kill.charAt(0));
        }

    }

    public String[] getKills() {
        return this.kills;
    }

    public ArrayList<Character> getExtraKills() {
        return extraKills;
    }
}
