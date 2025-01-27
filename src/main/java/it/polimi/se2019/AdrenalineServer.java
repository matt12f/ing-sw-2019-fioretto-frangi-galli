package it.polimi.se2019;

import it.polimi.se2019.network.*;

import java.io.IOException;
import java.util.ArrayList;

public class AdrenalineServer{
    //This controller contains and manages the  game logic for all players (it's initialized only
    // if a player creates a new game)

    private static ArrayList<String> lobby;
    private static ArrayList<ClientHandler> lobbyClient;
    private static ArrayList<GameHandler> games = new ArrayList<>();

    public static void main( String[] args) throws InterruptedException {
        SocketClients socketClient;
        LobbyMonitor lobbyMonitor;
        System.out.println("Server partito");
        socketClient = new SocketClients();
        lobbyMonitor = new LobbyMonitor();
        Thread threadSocket = new Thread(socketClient); //prende gli utenti via Socket

        Thread threadMonitor = new Thread(lobbyMonitor);
        AdrenalineServer.lobby = new ArrayList<>();
        AdrenalineServer.lobbyClient = new ArrayList<>();
        threadSocket.start();
        threadMonitor.start();
        System.out.println("threads partiti");
        try {
            threadSocket.join();
            threadMonitor.join();
        } catch (InterruptedException e) {
            throw new InterruptedException();
        }
    }

    public synchronized static ArrayList<String> getLobby(){
        return AdrenalineServer.lobby;
    }

    public static synchronized void nickController (ClientHandler newPlayer) throws IOException {
        String req = newPlayer.getNickname();
        String reply;
        if(AdrenalineServer.lobby.indexOf(req) == -1){
            newPlayer.setAccepted(true);
            AdrenalineServer.lobby.add(req);
            AdrenalineServer.lobbyClient.add(newPlayer);
            reply = "true";
        }else{
            newPlayer.setAccepted(false);
            reply = "false";
        }
        if(newPlayer.getSocket() != null){
            newPlayer.getOutput().writeObject(reply);
        }
    }

    public static synchronized int clientCounter(){
        int connectionNumber = lobbyClient.size();
        ArrayList<Integer> indexToDelete = new ArrayList<>();
        //checking which connections correctly works
        for (ClientHandler client: lobbyClient) {
            if(client.getSocket() != null){
                try{
                    if(client.getOutput() != null)
                        client.getOutput().writeObject("Test");
                } catch (IOException e) {
                    connectionNumber--;
                    indexToDelete.add(lobbyClient.indexOf(client));
                    System.out.println("cliente disconnesso");
                }
            }
        }
        //deleting useless ClientHandler from lobbyClient
        if(!indexToDelete.isEmpty() && !lobby.isEmpty())
            for (int i = indexToDelete.size() - 1; i >= 0; i--){
                lobbyClient.remove(indexToDelete.get(i).intValue());
                lobby.remove(indexToDelete.get(i).intValue());
            }
        return connectionNumber;
    }

    public static synchronized void newGame(GameHandler game){
        games.add(game);
        AdrenalineServer.lobbyClient = new ArrayList<>();
        AdrenalineServer.lobby = new ArrayList<>();
    }

    public static ArrayList<ClientHandler> getLobbyClient() {
        return AdrenalineServer.lobbyClient;
    }

    public static synchronized void newGame(GameHandler newGame, ArrayList<ClientHandler> lobbyClients, ArrayList<String> lobbies) {
        AdrenalineServer.games.add(newGame);
        AdrenalineServer.lobbyClient = lobbyClients;
        AdrenalineServer.lobby = lobbies;
    }
}