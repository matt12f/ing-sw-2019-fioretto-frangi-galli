package it.polimi.se2019.game;

public class Player {
    private String nickname;
    private int id;
    private int score;
    public Figure figure;
    public PlayerBoard playerBoard;

    public Player(){ }

    public int getId() {
        return id;
    }

    public int getScore() {
        return score;
    }
    /**This method adds point to a player
     *
     * */
    public void setScore(int points) {
       this.score += points;
    }
}
