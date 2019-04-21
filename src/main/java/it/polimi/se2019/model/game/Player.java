package it.polimi.se2019.model.game;

public class Player {
    private String nickname;
    private int id;
    private int score;
    public Figure figure;
    public PlayerBoard playerBoard;

    /**
     *
     * @param id
     * @param nickname
     * @param color
     */
    public Player(int id,String nickname,char color ){
        this.id = id;
        this.nickname = nickname;
        this.score = 0;
        this.playerBoard = new PlayerBoard(color);
        this.figure = new Figure(color);
    }

    public int getId() {
        return id;
    }

    public int getScore() {
        return score;
    }
    /**This method adds points to a player
     *
     * */
    public void setScore(int points) {
       this.score += points;
    }
}
