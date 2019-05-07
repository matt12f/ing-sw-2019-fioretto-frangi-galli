package it.polimi.se2019.model.game;

public class Player {
    private String nickname;
    private int id;
    private int score;
    private Figure figure;
    private PlayerBoard playerBoard;

    public Player(int id,String nickname,char color ){
        this.id = id;
        this.nickname = nickname;
        this.score = 0;
        this.playerBoard = new PlayerBoard(color);
        this.figure = new Figure(color);
    }
    public String getNickname() {
        return nickname;
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

    public Figure getFigure() {
        return figure;
    }

    public PlayerBoard getPlayerBoard() {
        return playerBoard;
    }
}
