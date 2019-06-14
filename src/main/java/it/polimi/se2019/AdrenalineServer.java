package it.polimi.se2019;

import it.polimi.se2019.controller.Controller;
import it.polimi.se2019.network.*;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.rmi.*;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.util.ArrayList;
public class AdrenalineServer implements Runnable {
    //This controller contains and manages the  game logic for all players (it's initialized only
    // if a player creates a new game)
    private static Controller mainController;

    public static void main( String[] args) throws IOException, ClassNotFoundException, NotBoundException, InterruptedException {
        SocketClients socketClient;
        RMIClients rmiClients;
        String req;
        ArrayList<String> nicknames = new ArrayList<>();
        ArrayList<String> Host;
        ArrayList<Integer> toRemove;
        ArrayList<GameHandler> games = new ArrayList<>();
        ArrayList<Socket> sockets;
        ArrayList<ClientHandler> hostToRemove;
        ArrayList<ClientHandler> clients;
        clientCallBack stubClient;
        int connections = 0;
        boolean start =  false;
        int i;
        Registry registry;
        socketClient = new SocketClients();
        rmiClients= new RMIClients();
        Thread threadSocket = new Thread(rmiClients); //prende gli utenti via Socket
        Thread threadRMI = new Thread(socketClient); //prende gli utenti via rmi
        threadRMI.start();
        threadSocket.start();
        while (!start){
            clients = socketClient.getClients();
            for (ClientHandler client: clients) {
                if (client.getThread().getState() == Thread.State.valueOf("WAITING")){
                    req = client.getNickname();
                    if (nicknames.indexOf(req) == -1){
                        nicknames.add(req);
                        client.setAccepted(true);
                        connections++;
                    }else {
                        client.setAccepted(false);
                    }
                    client.getThread().notify();
                }
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
                    Host.remove(toRemove.get(toRemove.size() - 1));
                    hostToRemove.remove(toRemove.get(toRemove.size() - 1));
                    toRemove.remove(toRemove.size() - 1);
                }
                rmiClients.setHosts(Host);
                rmiClients.setClients(hostToRemove);

                if (connections == 5){  //TODO sostituire poi con costante
                    start = true;
                    socketClient.setStart(start);
                }
            }
            for (ClientHandler client: rmiClients.getClients()) {
                if (client.getThread().getState() == Thread.State.valueOf("WAITING")){
                    req = client.getNickname();
                    if(nicknames.indexOf(req) == -1){
                        client.setAccepted(false);
                    }else{
                        nicknames.add(req);
                        client.setAccepted(true);
                    }
                }
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
        }
        try {
            threadRMI.join();
            threadSocket.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        clients = socketClient.getClients();
        clients.addAll(rmiClients.getClients());
        games.add(new GameHandler(clients)); //TODO Definire Game handler e mettere i parametri
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

    @Override
    public void run() {

    }


}