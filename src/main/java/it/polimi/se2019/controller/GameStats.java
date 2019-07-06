package it.polimi.se2019.controller;

import it.polimi.se2019.model.game.Player;

import java.util.ArrayList;

public class GameStats {
    private ArrayList<Player> ranking;
    private int numberOfTurns; //it's the number of turns it took to end the game
    private boolean singleWinner;

    public GameStats(Controller currentController, int numberOfTurns){
        ArrayList<Player> players = currentController.getMainGameModel().getPlayerList();

        ArrayList<Character> killshotTrackScoring = finalScoring(currentController, players);
        this.numberOfTurns = numberOfTurns;
        this.ranking=new ArrayList<>();

        //section for TIE BREAKING
        //if there's a tie, it will break in favor of the player that has the highest score on the killshot track
        //if it's still a tie, they both win
        players.stream()
                .sorted((Player entry1, Player entry2) -> -compare(entry1,entry2,killshotTrackScoring))
                .forEach(player -> this.ranking.add(player));


        //the first if determines if there's a TIE between the first two players
        //the second if evaluates if both of the player have scores on the killshot track (so it's already been considered they're tied)
        if(this.ranking.get(0).getScore()==this.ranking.get(1).getScore()){
            if(killshotTrackScoring.contains(this.ranking.get(0).getPlayerBoard().getColorChar()) &&
                    killshotTrackScoring.contains(this.ranking.get(1).getPlayerBoard().getColorChar())){
                this.singleWinner=false;
            }
            else
                this.singleWinner=true;
        }
        else
            this.singleWinner=true;
    }

    public int compare(Player o1, Player o2, ArrayList<Character> secondaryRanking){
        int result=Integer.compare(o1.getScore(), o2.getScore());
        if(result==0)
            return Integer.compare(secondaryRanking.indexOf(o2.getPlayerBoard().getColorChar()), secondaryRanking.indexOf(o1.getPlayerBoard().getColorChar()));
        return result;
    }

    /**
     * this method scores the killshot track at the end of the fame
     * it will:
     *      - score all boards that still have damage tokens, as usual but without killshot
     *      - score the killshot track: players (ordered by number of tokens) will get 8, 6, 4, 2, 1 points
     *      - ties in the score on the killshot track break in favor of the player that has the earliest token(s)
     *
     * @param players are all of the players in the game
     * @return the player that has the highest score on the killshot track
     */
    private ArrayList<Character> finalScoring(Controller currentController, ArrayList<Player> players){
        //scores the boards
        players.forEach(player -> PlayerManager.dealPoints(currentController,
                player.getPlayerBoard().getCurrentBoardValue(),
                PlayerManager.listOffenders(player.getPlayerBoard().getDamageTrack().getDamage())));

        //here we "translate" the tokens to a single string, and then we process it as
        ArrayList<Character> tokens=new ArrayList<>();
        for(String token: currentController.getMainGameModel().getKillshotTrack().getKills())
            for(int i=0;i<token.length();i++)
                tokens.add(token.charAt(i));

        //this considers also the extra kills from frenzy
        tokens.addAll(currentController.getMainGameModel().getKillshotTrack().getExtraKills());

        char [] tokensArray=new char[tokens.size()];
        for (int i = 0; i < tokensArray.length; i++)
            tokensArray[i]=tokens.get(i);

        ArrayList<Character> killShotTrackOffenders = PlayerManager.listOffenders(tokensArray);

        //deals the points
        PlayerManager.dealPoints(currentController,8, killShotTrackOffenders);

        //returns the ranking of players in terms of killshot track points
        return killShotTrackOffenders;
    }

    public ArrayList<Player> getRanking() {
        return ranking;
    }

    public int getNumberOfTurns() {
        return numberOfTurns;
    }

    @Override
    public String toString() {
        StringBuilder gameStats= new StringBuilder("<html>Game results<br><br>");

        gameStats.append("The game was ");
        gameStats.append(this.numberOfTurns);
        gameStats.append(" turns long<br><br>");

        if(this.singleWinner) {
            gameStats.append("There's a single winner: ");
            gameStats.append(this.ranking.get(0).toString());
        }
        else{
            gameStats.append("There's a tie between Player: <br>");
            gameStats.append(this.ranking.get(0).toString());
            gameStats.append("<br>");
            gameStats.append(this.ranking.get(1).toString());
        }
        gameStats.append("<br><br>Ranking: <br>");

        //listing players
        for(Player player:this.ranking){
            gameStats.append(this.ranking.indexOf(player)+1);
            gameStats.append("° place: ");
            gameStats.append(player.toString());
            gameStats.append("<br></html>");
        }
        return gameStats.toString();
    }
}
