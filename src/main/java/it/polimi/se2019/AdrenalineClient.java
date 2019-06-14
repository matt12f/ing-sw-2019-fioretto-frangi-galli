package it.polimi.se2019;

import it.polimi.se2019.enums.Status;
import it.polimi.se2019.network.*;
import it.polimi.se2019.view.LocalView;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class AdrenalineClient

{
    //This controller contains and manages the  game logic for all players (it's initialized only
    // if a player creates a new game)
    private static NetworkHandler networkHandler;
    private static LocalView localView;
    private static Socket socket;

    public static void main( String[] args) throws IOException, AlreadyBoundException, NotBoundException {
        Connection connection;
        String ipServer = null;
        String Nickname = "Player";
        boolean isSocket = true;
        boolean accepted = false;
        clientCallBackClass callBackClass = new clientCallBackClass();
        clientCallBack stubClient = null;
        //todo form scelta connesione e indirizzo ip server (ipotizzo scelta connessione come un  boolean)

        connection = setConnection(ipServer, isSocket);

        if(!(connection.isSocket())){
            //export clientCallBack
            stubClient = (clientCallBack) UnicastRemoteObject.exportObject(callBackClass, 0);
            connection.getLocalRegistry().bind(("C" + connection.getHost()), stubClient);

            connection.getRegistry().lookup("");
        }


        while(!accepted){
            accepted = setNickname(Nickname, connection);
            if(!accepted){
                //todo comunica all'utente che il nickname è da cambiare perchè già preso e aggiorna valore di Nickname
            }
        }




    }

    public static LocalView getLocalView() {
        return localView;
    }

    public static boolean setNickname(String nickname, Connection connection) throws IOException {
        ObjectInputStream socketInput = null;
        ObjectOutputStream socketOutput = null;
        Registry registry = null;
        Status serveStatus;
        RMIInterface stub = null;
        boolean ready;
        if(connection.isSocket()){
            socketInput = (ObjectInputStream) connection.getSocket().getInputStream();
            socketOutput = (ObjectOutputStream) connection.getSocket().getOutputStream();
            try{
                ready = (boolean) socketInput.readObject();
                if(ready){
                    socketOutput.writeObject(nickname);
                }
            }catch (IllegalArgumentException e){
                return false;
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }else{
            try{
                registry = connection.getRegistry();
                stub = (RMIInterface) registry.lookup("");
                serveStatus = stub.getStatus();
                while(serveStatus == Status.NOTREADY){

                }
                stub.setNicknameRMI(nickname);
            }catch (IllegalArgumentException e){
                return false; //per dire che il nick è già stato preso, probabilmente è una cosa rindondante
            } catch (NotBoundException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        return true;
    }

    private void guiStarter(){
        //TODO scrivere metodo
    }

    private static void networkStarterClient(boolean rmi){
        if (rmi) {
            networkHandler = new NetworkHandlerRMI();
            localView = networkHandler.getLocalView(1); //TODO recuperare il numero del player corretto
            //TODO scrivere metodo
        }
    }

    /** This method creates the connection between Client and server
     *
     * @param ipServer
     * @param Socket
     * @return
     * @throws IOException
     */

    private static Connection setConnection(String ipServer, boolean Socket) throws IOException {
        int serverPort = 9000; //todo caricamento da file configurazione/definisci costante porta del server
        Socket socket = null;
        Registry registry = null;
        Registry local = LocateRegistry.getRegistry(null);
        InetAddress address = InetAddress.getLocalHost();
        String host = address.toString();
        if(Socket){
            socket = new Socket (ipServer, serverPort);
        }else{
            registry = LocateRegistry.getRegistry(ipServer, serverPort);
        }
        return new Connection(socket, registry, Socket, host, local);
    }
}

class Connection{
    private boolean isSocket; //non so come chiamarlo, se socket = true se RMI = false
    private Socket socket;
    private Registry registry;
    private Registry localRegistry;
    private String Host;

    public Connection(Socket socket, Registry registry, boolean isSocket, String host, Registry local){
        this.registry = registry;
        this.socket = socket;
        this.isSocket = isSocket;
        this.Host = host;
        this.localRegistry = local;
    }

    public Registry getRegistry() {
        return registry;
    }

    public void setRegistry(Registry registry) {
        this.registry = registry;
    }

    public java.net.Socket getSocket() {
        return socket;
    }

    public void setSocket(java.net.Socket socket) {
        this.socket = socket;
    }

    public void setSocket(boolean socket) {
        this.isSocket = socket;
    }

    public boolean isSocket() {
        return this.isSocket;
    }

    public void setHost(String host) {
        Host = host;
    }

    public String getHost() {
        return Host;
    }

    public Registry getLocalRegistry() {
        return localRegistry;
    }

}
class clientCallBackClass implements clientCallBack{

    @Override
    public void ClientCallBack() throws RemoteException {

    }
}