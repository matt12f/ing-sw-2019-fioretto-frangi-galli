package it.polimi.se2019.model.game;

import java.util.ArrayList;

public class Player {
    private String nickname;
    private int id;
    private int score;
    public Figure figure;
    public PlayerBoard playerBoard;

    /**
     *
     * @param id
     * @param availableColors is a char vector built based upon the existing player's colors
     */
    public Player(int id, ArrayList<Character> availableColors){
        String nickname="frank",colorString="Scelta Utente";
        char color='y';
        //TODO Chiedere il nickname
        if(availableColors==null)//può scegliere tra tutti
            //TODO chiedere il colore al giocatore (scegliendo da colori disponibili)
        switch (colorString){
            case ("Yellow"): color='y'; break;
            case ("Blue"): color='b'; break;
            case ("Violet"): color='v'; break;
            case ("Green"): color='g'; break;
            case ("White"): color='w'; break;
        }
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
    /**This method adds point to a player
     *
     * */
    public void setScore(int points) {
       this.score += points;
    }
}
