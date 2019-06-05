package it.polimi.se2019;

import it.polimi.se2019.controller.Controller;
import it.polimi.se2019.network.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Array;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.AccessException;
import java.rmi.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class AdrenalineServer implements Runnable {
    //This controller contains and manages the  game logic for all players (it's initialized only
    // if a player creates a new game)
    private static Controller mainController;

    public static void main( String[] args) throws IOException, ClassNotFoundException {
        SocketClients socketClient;
        RMIClients rmiClients;
        String req = "";
        ArrayList<String> Nicknames = new ArrayList<>();
        Timer timer = null;
        ArrayList<Integer> socketToRemove;
        ArrayList<GameHandler> games = new ArrayList<>();
        ArrayList<Socket> sockets;
        ArrayList<ClientHandler> socketClients;
        int connections = 0;
        boolean start =  false;
        int i = 0;
        socketClient = new SocketClients();
        rmiClients= new RMIClients();
        Thread threadSocket = new Thread(rmiClients); //prende gli utenti via Socket
        Thread threadRMI = new Thread(socketClient); //prende gli utenti via rmi
        threadRMI.start();
        threadSocket.start();
        while (!start){
            socketClients = socketClient.getClients();
            for (ClientHandler client: socketClients) {
                if (client.getThread().getState() == Thread.State.valueOf("WAITING")){
                    req = client.getNickname();
                    if (Nicknames.indexOf(req) == -1){
                        Nicknames.add(req);
                        client.setAccepted(true);
                        connections++;
                    }else {
                        client.setAccepted(false);
                    }
                    client.getThread().notify();
                }
                sockets = socketClient.getSocket();
                socketToRemove = socketClientCounter(connections, sockets);
                connections = socketToRemove.get(0);
                socketToRemove.remove(0);
                while(!(socketToRemove.isEmpty())){
                    i = socketToRemove.get(socketToRemove.size() - 1);
                    sockets.remove(i);
                }
                socketClient.setSocket(sockets);
                if (connections == 5){  //TODO sostituire poi con costante
                    start = true;
                    socketClient.setStart(start);
                }



            }
            if (threadRMI.getState() == Thread.State.valueOf("WAITING")){
            }
            //qui riconta i client, per essere sicuri di non eccedere i 5 utenti
        }
        //usa addAll per mergiare due ArrayList
        games.add(new GameHandler()); //TODO Definire Game handler e mettere i parametri


        /**try{
            //penso non servi --> mainController=new controller();
            logInterface stubLogIn = (logInterface) UnicastRemoteObject.exportObject(lobbies, 0);
            Registry registry = LocateRegistry.getRegistry();
            registry.bind("lobbyCreation", lobbies);

        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (AlreadyBoundException e) {
            e.printStackTrace();
        } catch (AccessException e) {
            e.printStackTrace();
        }

        //accesso lobby
        try{
            log
        }*/



    }

    /**this method return information about the number of clients connected and which socket are not working
     *
     * @param openedConnections
     * @param client
     * @return
     * @throws IOException
     */

    public static ArrayList<Integer> socketClientCounter (int openedConnections, ArrayList<Socket> client) throws IOException {
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