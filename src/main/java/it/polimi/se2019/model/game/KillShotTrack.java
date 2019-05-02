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
