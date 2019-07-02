package it.polimi.se2019.view;

public class KillShotTrackerView {
    private int skulls;
    private String [] kills;
    private char extraKills;

    public KillShotTrackerView(int skulls){
        this.skulls = skulls;
        this.kills = new String[skulls];

        for (int i = 0; i < skulls; i++) {
            this.kills[i] = null;

        }
    }

    public char getExtraKills() {
        return extraKills;
    }

    public String[] getKills() {
        return kills;
    }

    public int getSkulls() {
        return skulls;
    }

    public void setExtraKills(char extraKills) {
        this.extraKills = extraKills;
    }

    public void setKills(String[] kills) {
        this.kills = kills;
    }

    public void setSkulls(int skulls) {
        this.skulls = skulls;
    }
}
