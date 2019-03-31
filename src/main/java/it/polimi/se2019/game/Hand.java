package it.polimi.se2019.game;

public class Hand {
    private int powerups [];
    private int guns [];

    public Hand(){
        this.powerups=new int[3];
        this.guns=new int[3];
    }

    /**this method draws a weapon card from the player's hand
     * */
    public int getGun() {
        return 0;
    }

    /**this method draws a powerup from the player's hand
     * */
    public int getPowerup() {
        return 0;
    }
    /**this method puts a gun in the player's hand
     * */
    public void setGun() {
    }
    /**this method puts a powerup in the player's hand
     * */
    public void setPowerup() {
    }
}
