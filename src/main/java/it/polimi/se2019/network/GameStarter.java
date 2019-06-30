package it.polimi.se2019.network;

import it.polimi.se2019.AdrenalineServer;

import java.util.ArrayList;
import java.util.TimerTask;

public class GameStarter extends TimerTask {
    @Override
    public synchronized void run() {
        GameHandler newGame = new GameHandler();
        Thread thread = new Thread(newGame);
        if(AdrenalineServer.getLobby().size() > 5){
            ArrayList<ClientHandler> players = AdrenalineServer.getLobbyClient();
            ArrayList<ClientHandler> temp = new ArrayList<>();
            ArrayList<ClientHandler> lobby = new ArrayList<>();
            ArrayList<String> nicks = new ArrayList<>();
            for(int i = 0; i<players.size(); i++){
                if(i<5)
                    lobby.add(players.get(i));
                else{
                    temp.add(players.get(i));
                    nicks.add(AdrenalineServer.getLobby().get(i));
                }
            }
            newGame.setPlayers(lobby);
            AdrenalineServer.newGame(newGame, temp, nicks);
        }else{
            newGame.setPlayers(AdrenalineServer.getLobbyClient());
            AdrenalineServer.newGame(newGame);
        }
        thread.start();
    }
}
