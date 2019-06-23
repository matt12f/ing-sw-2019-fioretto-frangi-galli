package it.polimi.se2019;

import it.polimi.se2019.controller.Controller;
import it.polimi.se2019.enums.Status;
import it.polimi.se2019.network.*;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.rmi.*;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.util.ArrayList;
public class AdrenalineServer{
    //This controller contains and manages the  game logic for all players (it's initialized only
    // if a player creates a new game)
    private static Controller mainController;
    private static ArrayList<String> lobby;
    private static ArrayList<ClientHandler> lobbyClient;

    public static void main( String[] args) throws IOException, ClassNotFoundException, NotBoundException, InterruptedException {
        SocketClients socketClient;
        RMIClients rmiClients;
        Thread.currentThread().setPriority(10);
        ArrayList<String> host;
        ArrayList<Integer> toRemove;
        ArrayList<GameHandler> games = new ArrayList<>();
        ArrayList<ClientHandler> hostToRemove;
        clientCallBack stubClient;
        int connections = 0;
        boolean start =  false;
        System.out.println("Server partito");
        Registry registry;
        socketClient = new SocketClients();
        rmiClients= new RMIClients();
        Thread threadSocket = new Thread(rmiClients); //prende gli utenti via Socket
        Thread threadRMI = new Thread(socketClient); //prende gli utenti via rmi
        AdrenalineServer.lobby = new ArrayList<>();
        AdrenalineServer.lobbyClient = new ArrayList<>();
        threadRMI.start();
        threadSocket.start();
        System.out.println("threads partiti");
        while (!start){

            if(lobbyClient.size()==5){

            }
            /*if(!rmiClients.getClients().isEmpty()){
                System.out.println("controllo rmi");
                ocio = rmiClients.getClients();
                for (int j = 0; j<ocio.size(); j++) {
                    client = ocio.get(j);
                    nickController(ocio, nicknames);
                    //sockets counter
                    sockets = socketClient.getSocket();
                    toRemove = socketClientCounter(connections, sockets);
                    connections = toRemove.get(0);
                    toRemove.remove(0);
                    while(!(toRemove.isEmpty())){
                        i = toRemove.get(toRemove.size() - 1);
                        sockets.remove(i);
                    }
                    socketClient.setSocket(sockets);
                    //rmi counter
                    toRemove = rmiClientCounter(connections, rmiClients.getClients());
                    connections -= toRemove.size();
                    Host = rmiClients.getHosts();
                    hostToRemove = rmiClients.getClients();
                    while(!toRemove.isEmpty()){
                        i = toRemove.get(toRemove.size() - 1);
                        Host.remove(i);
                        hostToRemove.remove(i);
                        toRemove.remove(toRemove.size() - 1);
                    }
                    rmiClients.setHosts(Host);
                    rmiClients.setClients(hostToRemove);

                    if (connections == 5){  //TODO sostituire poi con costante
                        start = true;
                        socketClient.setStart(start);
                    }
                }
            }*/
        }
        try {
            threadRMI.join();
            threadSocket.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        lobbyClient = socketClient.getClients();
        lobbyClient.addAll(rmiClients.getClients());
        games.add(new GameHandler(lobbyClient)); //TODO Definire Game handler e mettere i parametri
    }


    private static ArrayList<Integer> rmiClientCounter (int openedConnections, ArrayList<ClientHandler> hosts) throws RemoteException, NotBoundException {
        ArrayList<Integer> hostToRemove = new ArrayList<>();
        for (ClientHandler client: hosts) {
            Registry registry = LocateRegistry.getRegistry(client.getHost());
            clientCallBack stubClient = (clientCallBack) registry.lookup(("C" + client.getHost()));
            try {
                stubClient.ClientCallBack();
            }catch (RemoteException e ){
                hostToRemove.add(hosts.indexOf(client));
            }
        }
        return hostToRemove;
    }

    public static ArrayList<String> getLobby(){
        return AdrenalineServer.lobby;
    }

    public static synchronized void nickController (ClientHandler newPlayer){
        String req = newPlayer.getNickname();
        if(AdrenalineServer.lobby.indexOf(req) == -1){
            newPlayer.setAccepted(true);
            AdrenalineServer.lobby.add(req);
            AdrenalineServer.lobbyClient.add(newPlayer);
        }else{
            newPlayer.setAccepted(false);
        }

    }
    private static int clientCounter( SocketClients socketClient, RMIClients rmiClients){
        int connectionNumber = 0;
        int index = 0;
        ArrayList<Socket> sockets;
        Socket socket;
        ObjectOutputStream outputStream;
        if(!socketClient.getClients().isEmpty()){
            connectionNumber += socketClient.getClients().size();
            for (int i = 0; i<socketClient.getClients().size(); i++) {
                socket = socketClient.getSocket().get(i);
                try {
                    outputStream = new ObjectOutputStream(socket.getOutputStream());
                    outputStream.writeObject("SeiConnesso");
                } catch (IOException e) {
                    connectionNumber -= 1;
                    index = socketClient.getSocket().indexOf(socket);
                    socketClient.getClients().remove(index);
                    socketClient.getSocket().remove(index);
                }
            }
        }
        if(!rmiClients.getClients().isEmpty()){
            connectionNumber += rmiClients.getClients().size();
            for (ClientHandler client: rmiClients.getClients()) {
                try {
                    Registry registry = LocateRegistry.getRegistry(client.getHost());
                    clientCallBack stubClient = (clientCallBack) registry.lookup(("C" + client.getHost()));
                    stubClient.ClientCallBack();
                }catch (RemoteException e ){
                    connectionNumber -= 1;
                    index = rmiClients.getClients().lastIndexOf(client);

                } catch (NotBoundException e) {
                    e.printStackTrace();
                }
            }
        }
        return connectionNumber;
    }

    /**this method return information about the number of clients connected and which socket are not working
     *
     * @param openedConnections
     * @param client
     * @return
     * @throws IOException
     */
    private static ArrayList<Integer> socketClientCounter (int openedConnections, ArrayList<Socket> client) throws IOException {
        ArrayList<Integer> update = new ArrayList<>();
        int i=0;
        int count;
        ObjectOutputStream outputStream = null;
        update.add(openedConnections);
        for (Socket connection: client) {
            outputStream = (ObjectOutputStream) connection.getOutputStream();
            try {
                outputStream.writeObject("SeiConnesso");
            } catch (IOException e) {
                count = update.get(0) - 1;
                update.set(0, count);
                update.add(i); //ATTENZIONE CANCELLA AL CONTRARIO SE NO SMINCHI GLI INDICI
            }
            i++;
        }
        return update;
    }

    public static Controller getMainController() {
        return mainController;
    }

    private void guiStarter(){
        //TODO scrivere metodo
    }
}