package it.polimi.se2019.model.game;

public class KillshotTrack {

    protected int skulls;
    protected String [] kills;
    protected char extraKills;

    /**
     * Default constructor
     *
     */
    public KillshotTrack(){
        this.kills=new String[8];
    }

    public int getSkulls() {
        return skulls;
    }

    public void setSkulls() {
        this.skulls --;
    }

    public void setKills() {
        // TODO scrivere metodo
    }

    public String[] getKills() {
        return kills;
    }

    public void setExtraKills() {
        // TODO scrivere metodo
    }

    public char getExtraKills() {
        return extraKills;
    }
}
