package it.polimi.se2019;

import it.polimi.se2019.controller.Controller;
import it.polimi.se2019.network.*;

import java.io.IOException;
import java.util.ArrayList;

public class AdrenalineServer{
    //This controller contains and manages the  game logic for all players (it's initialized only
    // if a player creates a new game)

    private static ArrayList<String> lobby;
    private static ArrayList<ClientHandler> lobbyClient;
    private static ArrayList<GameHandler> games;

    public static void main( String[] args) throws InterruptedException {
        SocketClients socketClient;
        RMIClients rmiClients;
        LobbyMonitor lobbyMonitor;
        System.out.println("Server partito");
        socketClient = new SocketClients();
        rmiClients= new RMIClients();
        lobbyMonitor = new LobbyMonitor();
        Thread threadSocket = new Thread(rmiClients); //prende gli utenti via Socket
        Thread threadRMI = new Thread(socketClient); //prende gli utenti via rmi
        Thread threadMonitor = new Thread(lobbyMonitor);
        AdrenalineServer.lobby = new ArrayList<>();
        AdrenalineServer.lobbyClient = new ArrayList<>();
        threadRMI.start();
        threadSocket.start();
        threadMonitor.start();
        System.out.println("threads partiti");

        //todo non so se ci va qualcosa

        try {
            threadRMI.join();
            threadSocket.join();
            threadMonitor.join();
        } catch (InterruptedException e) {
            throw new InterruptedException();
        }
    }

    public static ArrayList<String> getLobby(){
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
        //checking wich connections correctly works
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
            }else{
                //todo fare controllo tramite RMI
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

    public static synchronized void newGame(){

        //todo racchiudere i CH nel GH

        AdrenalineServer.lobbyClient = new ArrayList<>();
        AdrenalineServer.lobby = new ArrayList<>();
    }

    public static ArrayList<ClientHandler> getLobbyClient() {
        return AdrenalineServer.lobbyClient;
    }

}