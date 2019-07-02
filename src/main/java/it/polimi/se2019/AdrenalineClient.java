package it.polimi.se2019;

import it.polimi.se2019.controller.AvailableActions;
import it.polimi.se2019.enums.Status;
import it.polimi.se2019.network.*;
import it.polimi.se2019.view.ActionRequestView;
import it.polimi.se2019.view.LocalView;
import it.polimi.se2019.view.OpenerGUI;
import it.polimi.se2019.view.UserInteraction;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Scanner;

public class AdrenalineClient {
    //This controller contains and manages the  game logic for all players (it's initialized only
    // if a player creates a new game)
    private static NetworkHandler networkHandler;
    private static LocalView localView;
    private static String nickname;
    private static ArrayList<String> otherPlayers;
    private static boolean GUI; //Se l'utente sceglierà la GUI piuttosto che la cli sarà true

    public static void main(String[] args) throws IOException, AlreadyBoundException, NotBoundException, InterruptedException, ClassNotFoundException {
        Connection connection = new Connection(null, null, false, null, null);
        boolean start = false;
        boolean myturn = false;
        boolean last = false;
        int frenzy = 0;
        int actionNumber = 0;
        String message = null;
        ActionRequestView actionRequested;
        AvailableActions actions;
        ClientCallBackClass callBackClass = new ClientCallBackClass();
        clientCallBack stubClient = null;
        new OpenerGUI();
        connectionRequest(GUI, connection);
        ipServerRequest(connection);
        if(connection.isSocket())
            connection.setStream();
        else{
            //export clientCallBack
            stubClient = (clientCallBack) UnicastRemoteObject.exportObject(callBackClass, 0);
            connection.getLocalRegistry().bind(("C" + connection.getHost()), stubClient);
            connection.getRegistry().lookup("");
        }
        nicknameRequest(connection);
        while(!start){
            getOtherPlayers(connection);
            displayQue();
            start = waitForUpdate(connection);
        }
        message = (String) connection.getInput().readObject();
        if(message.equals("MAP")) {
            //todo fai partire scelta mappa
            message = (String) connection.getInput().readObject();
        }
        if(message.equals("VIEW")){
            //todo Ricevere la prima LocalView
        }
        message = (String) connection.getInput().readObject();
        if(message.equals("SPAWN")) {
            //todo gestione Spawnpoint
        }
        while(start){
            myturn = receiveServerMessage(connection);
            if(myturn){
                last = false;
                connection.getOutput().reset();
                connection.getOutput().writeBoolean(true);
                connection.getOutput().flush();
                actionNumber = connection.getInput().readInt();
                while(actionNumber > 0){
                    actionNumber --;
                    if (actionNumber == 0)
                        last = true;
                    actionRequested = getActionFromUser(last);
                    actions = askForAction(connection, actionRequested);
                    //todo presentare le scelte
                    connection.getOutput().writeObject(actions);
                    //todo UpdateLocalView(connection)
                }
            }else{
                UpdateLocalView(connection);
            }
        }
        //todo gestione fine partita
    }

    private static void UpdateLocalView(Connection connection) {
        //todo scrivere metodo
    }

    private static ActionRequestView getActionFromUser(boolean lastMove){
        return new ActionRequestView(lastMove);
    }

    private static AvailableActions askForAction(Connection connection, ActionRequestView action) throws IOException, ClassNotFoundException {
        AvailableActions actions = null;
        if (connection.isSocket()){
            connection.getOutput().writeObject(action);
            actions = (AvailableActions) connection.getInput().readObject();
        }
        return actions;
    }

    private static boolean receiveServerMessage(Connection connection) throws IOException, ClassNotFoundException {
            String msg;
            msg = (String) connection.getInput().readObject();
            return (msg.equals("YOURTURN"));
    }

    private static void setMap(Connection connection) {

    }

    private static void setSkull(Connection connection) {
        //todo chiedre numero di teschi
    }

    private static Boolean waitForUpdate(Connection connection) throws IOException, ClassNotFoundException {
        String req;
        if(connection.isSocket()){
            req = (String) connection.getInput().readObject();
            while(req.equals("Test"))
                req = (String) connection.getInput().readObject();
            if(req.equals("UPDATE"))
                return false;
            else
                return true;
        }
        return false;
    }

    private static void displayQue() {
        //todo rifai displayQue passando da UserInteraction
        if(GUI){

        }else{
            try {
                clearScreen();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(!otherPlayers.isEmpty()){
                System.out.print("Sei in coda, al momento i giocatori connessi (oltre a te) sono:");
                for(int i = 0; i<otherPlayers.size(); i++){
                    System.out.print(" " + otherPlayers.get(i));
                    if(i == otherPlayers.size() - 1){
                        System.out.print(".");
                    }else {
                        System.out.print(",");
                    }
                }
            }else {
                System.out.println("Sei il solo in attessa di una nuova partita per ora");
            }
        }
    }

    private static void clearScreen() throws IOException, InterruptedException {
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
    }

    private static void getOtherPlayers(Connection connection) throws IOException, ClassNotFoundException {
        if(connection.isSocket()){
            otherPlayers = (ArrayList<String>) connection.getInput().readObject();
            otherPlayers.remove(nickname);
        }
    }

    private static void nicknameRequest(Connection connection) throws IOException, InterruptedException, ClassNotFoundException {
        Scanner scanner;
        String nickname;
        boolean accepted = false;
        if (!GUI) {
            scanner = new Scanner(System.in);
            System.out.println("Inserisci il tuo nickname adesso:");
            nickname = scanner.nextLine();
            while (!accepted) {
                accepted = setNickname(nickname, connection);
                if (!accepted) {
                    System.out.println("Mi spiace, ma il nick scelto non è disponibile, scegliene un altro");
                    System.out.println("Inserisci il tuo nickname adesso:");
                    nickname = scanner.nextLine();
                } else {
                    System.out.println("Ottimo, ora verrai messo in coda, aspetta che la partita abbia inizio, grazie.");
                    AdrenalineClient.nickname = nickname;
                }
            }
        }

    }

    public static LocalView getLocalView() {
        return localView;
    }


    private static void ipServerRequest(Connection connection){
        Scanner scanner;
        String ipServer;
        boolean connected = true;
        if (!GUI) {
            scanner = new Scanner(System.in);
            System.out.println("Inserisci l'indirizzo IP del server: ");
            ipServer = scanner.nextLine();
            try{
                setConnection(ipServer, connection);
            } catch (IOException e) {
                System.out.println("Connessione fallita, server non attivo o ip sbagliato");
                connected = false;
            }
            if(connected)
                System.out.println("connessione correttamente stabilita");
            else
                System.exit(-1);
        }
    }

    private static void connectionRequest(boolean GUI, Connection connection) {
        Scanner scanner;
        int choice = 5; //inizzializzo a un numero a caso, mi basta sia diverso da 0 o 1
        if (!GUI) {
            scanner = new Scanner(System.in);
            System.out.println("Ciao, benvenuto su Adrenaline");
            while (choice != 1 && choice != 0) {
                System.out.println("Che connessione vuoi usare? digita 0 per Socket, 1 per RMI:   ");
                choice = scanner.nextInt();
                if (choice != 1 && choice != 0)
                    System.out.println("Mi spiace, hai inserito: " + choice + ", puoi inserire solo 0 o 1, riprova.");
            }
            switch (choice) {
                case 0:
                    connection.setSocket(true);
                    break;
                case 1:
                    connection.setSocket(false);
                    break;
                default:
                    connection.setSocket(true);
                    break;
            }
        }
    }

    private static boolean setNickname(String nickname, Connection connection) throws IOException, InterruptedException, ClassNotFoundException {
        Registry registry = null;
        Status serveStatus;
        RMIInterface stub = null;
        boolean isOk = false;
        String reply;
        ObjectOutputStream socketOutput = null;
        ObjectInputStream socketInput = null;
        if (connection.isSocket()) {
            socketInput = connection.getInput();
            socketOutput = connection.getOutput();
            socketOutput.writeObject(nickname);
            Thread.sleep(10);
            reply = (String) socketInput.readObject();
            if(reply.equals("true"))
                isOk = true;
            return isOk;
        }else{
            //todo gestione nickname RMI
        }
        return true;
    }

    private void guiStarter () {
        //TODO scrivere metodo
    }

    private static void networkStarterClient ( boolean rmi){
        if (rmi) {
            networkHandler = new NetworkHandlerRMI();
            localView = networkHandler.getLocalView(1); //TODO recuperare il numero del player corretto
            //TODO scrivere metodo
        }
    }

        /** This method creates the connection between Client and server
         *
         *
         */
    private static void setConnection (String ipServer, Connection connection) throws IOException {
            int serverPort = 9000; //todo caricamento da file configurazione/definisci costante porta del server
            InetAddress address = InetAddress.getLocalHost();
            String host = address.toString();
            connection.setHost(host);
            if (connection.isSocket()) {
                connection.setSocket(new Socket(ipServer, serverPort));
            } else {
                Registry registry = LocateRegistry.getRegistry(ipServer, serverPort);
                connection.setRegistry(registry);
            }
    }

    public static void setGui(boolean val){
        AdrenalineClient.GUI=val;
    }

    public static boolean isGUI() {
        return GUI;
    }
}

class Connection{
    private boolean isSocket; //non so come chiamarlo, se socket = true se RMI = false
    private Socket socket;
    private Registry registry;
    private Registry localRegistry;
    private String Host;
    private ObjectOutputStream output = null;
    private ObjectInputStream input = null;

    Connection(Socket socket, Registry registry, boolean isSocket, String host, Registry local) {
        this.registry = registry;
        this.socket = socket;
        this.isSocket = isSocket;
        this.Host = host;
        this.localRegistry = local;
    }

    void setStream() throws IOException {
        this.input = new ObjectInputStream(this.socket.getInputStream());
        this.output = new ObjectOutputStream(this.socket.getOutputStream());
    }

    Registry getRegistry() {
        return registry;
    }

    void setRegistry(Registry registry) {
        this.registry = registry;
    }

    public Socket getSocket() {
        return this.socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public void setSocket(boolean socket) {
        if(socket){
            this.registry = null;

        }else{
            this.socket = null;
        }
        this.isSocket = socket;

    }

    public boolean isSocket() {
        return this.isSocket;
    }

    void setHost(String host) {
        Host = host;
    }

    String getHost() {
        return Host;
    }

    Registry getLocalRegistry() {
        return localRegistry;
    }

    ObjectInputStream getInput() {
        return this.input;
    }

    ObjectOutputStream getOutput() {
        return this.output;
    }
}



class ClientCallBackClass implements clientCallBack{

    @Override
public void ClientCallBack() throws RemoteException {
        /*
        //this method is empty because is used by the server only to check if the connection works before the match start
         */
    }
}