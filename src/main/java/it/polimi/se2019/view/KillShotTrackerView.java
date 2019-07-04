package it.polimi.se2019.view;

import it.polimi.se2019.model.game.KillShotTrack;

import java.io.Serializable;
import java.util.ArrayList;

public class KillShotTrackerView  implements Serializable {
    private int skulls;
    private String [] kills;
    private ArrayList<Character> extraKills;

    public KillShotTrackerView(int skulls){
        this.skulls = skulls;
        this.kills = new String[skulls];

        for (int i = 0; i < skulls; i++) {
            this.kills[i] = null;
        }
    }

    public ArrayList<Character> getExtraKills() {
        return extraKills;
    }

    public String[] getKills() {
        return kills;
    }

    public int getSkulls() {
        return skulls;
    }

    public void setKills(String[] kills) {
        this.kills = kills;
    }

    public void setSkulls(int skulls) {
        this.skulls = skulls;
    }

    public void update(KillShotTrack killShotTrack){
        this.skulls = killShotTrack.getSkulls();
        this.kills = killShotTrack.getKills();
        this.extraKills = killShotTrack.getExtraKills();
    }
}
