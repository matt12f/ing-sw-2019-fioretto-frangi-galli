package it.polimi.se2019.view;

import it.polimi.se2019.model.game.KillShotTrack;

import java.io.Serializable;
import java.util.ArrayList;

public class KillShotTrackerView  implements Serializable {
    private int skulls;
    private String [] kills;
    private ArrayList<Character> extraKills;

    /**
     * create view structure for the killshot track
     * @param skulls number of maximum kills
     */
    public KillShotTrackerView(int skulls){
        this.skulls = skulls;
        this.kills = new String[skulls];

        for (int i = 0; i < skulls; i++) {
            this.kills[i] = null;
        }
    }

    /**
     *
     * @return extrakills
     */
    public ArrayList<Character> getExtraKills() {
        return extraKills;
    }

    /**
     *
     * @return the game kills
     */
    public String[] getKills() {
        return kills;
    }

    /**
     *
     * @return the maximum kills allowed
     */
    public int getSkulls() {
        return skulls;
    }

    /**
     * update the number of kills
     */
    public void setKills(String[] kills) {
        this.kills = kills;
    }

    /**
     * @return number of kills (overkills are not considered)
     */
    public int getNumKills(){
        int cont=0;
        for (int i = 0; i < this.kills.length; i++)
            if(this.kills[i]!=null)
                cont++;
        return cont;
    }

    /**
     * set the number of the maxmum kills allowed
     */
    public void setSkulls(int skulls) {
        this.skulls = skulls;
    }

    /**
     * update the killshot track view
     * @param killShotTrack model data
     */
    public void update(KillShotTrack killShotTrack){
        this.skulls = killShotTrack.getSkulls();
        this.kills = killShotTrack.getKills();
        this.extraKills = killShotTrack.getExtraKills();
    }
}
