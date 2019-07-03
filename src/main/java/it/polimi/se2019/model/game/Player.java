package it.polimi.se2019.model.game;

import it.polimi.se2019.enums.Color;

import java.util.ArrayList;

public class Player {
    private String nickname;
    private int id;
    private int score;
    private Figure figure; //contains color and position
    private PlayerBoard playerBoard;

    public Player(int id, String nickname, Color color ){
        this.id = id;
        this.nickname = nickname;
        this.score = 0;
        this.playerBoard = new PlayerBoard(color);
        this.figure = new Figure(color);
    }

    public static ArrayList<Player> duplicateList(ArrayList<Player> originalList) {
        ArrayList<Player> clone = new ArrayList<>();
        for(Player player: originalList)
            clone.add(player.clone());
        return clone;
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

    @Override
    public Player clone(){
        Player player=new Player(this.id,this.nickname,this.figure.getColor());
        player.score=this.score;
        player.figure=this.figure.clone();
        player.playerBoard=this.playerBoard.clone();
        return player;
    }

    @Override
    public boolean equals(Object item) {
        if (item == null)
            return false;

        if (this.getClass() != item.getClass())
            return false;

        Player otherItem = (Player) item;

        if(this.id!=otherItem.id||!this.nickname.equals(otherItem.nickname)||this.score!=otherItem.score)
            return false;

        return true;
    }

}
