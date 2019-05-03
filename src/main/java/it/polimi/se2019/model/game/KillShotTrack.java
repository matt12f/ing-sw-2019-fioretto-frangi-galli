package it.polimi.se2019.model.game;

public class KillShotTrack {

    private int skulls;
    private String [] kills;
    private char extraKills;

    /**
     * Default constructor
     *
     */
    public KillShotTrack(){
        this.kills=new String[8];
    }

    public int getSkulls() {
        return skulls;
    }

    public void subtractSkulls() {
        this.skulls--;
    }

    public void setKills( String kill) {
        boolean found = false;
        int i = 0;
        do{
            if(this.kills[i].isEmpty()){
                this.kills[i] = kill;
                found = true;
            }
            i++;
        }while(!found);
    }

    public String[] getKills() {
        return this.kills;
    }

    public void setExtraKills(char kill) {
        this.extraKills = kill;
    }

    public char getExtraKills() {
        return extraKills;
    }
}
