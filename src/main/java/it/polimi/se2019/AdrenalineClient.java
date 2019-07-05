package it.polimi.se2019;

import it.polimi.se2019.controller.AvailableActions;
import it.polimi.se2019.enums.Status;
import it.polimi.se2019.exceptions.NoActionsException;
import it.polimi.se2019.model.cards.PowerupCard;
import it.polimi.se2019.network.*;
import it.polimi.se2019.view.*;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
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
    private static Connection connection;
    private static UserInteractionGUI userInteractionGUI = new UserInteractionGUI();
    private static UserInteractionCLI userInteractionCLI = new UserInteractionCLI();
    private static boolean start = false;
    private static GameBoardGui gameBoardGui;
    private static ArrayList<PlayerBoardView> opponentsBoards = new ArrayList<>() ;
    private static boolean connected = false;
    private static String[] answer;

    public static void main(String[] args) throws IOException, AlreadyBoundException, NotBoundException, InterruptedException, ClassNotFoundException {
        connection = new Connection(null, null, true, null, null);
        new OpenerGUI();
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

    private static void setMap(int map) throws IOException {
        connection.getOutput().writeObject(map);
    }

    private static void setSkull(int skull) throws IOException {
        connection.getOutput().writeObject(skull);
    }



    private static void waitForUpdate(Connection connection) throws IOException, ClassNotFoundException {
        String req;
        if(connection.isSocket()){
            req = (String) connection.getInput().readObject();
            while(req.equals("Test"))
                req = (String) connection.getInput().readObject();
            if(req.equals("START")){
                start = true;
                System.out.println(req);
            }
        }
    }

    private static void displayQue(boolean firstTime) {
        if(GUI){
            if(firstTime)
                userInteractionGUI.waitingListCreation(otherPlayers);
            else
                userInteractionGUI.waitingListUpdate(otherPlayers);
        }else{
            userInteractionCLI.displayQue(otherPlayers);
        }
    }

    private static void getOtherPlayers(Connection connection) throws IOException, ClassNotFoundException {
        if(connection.isSocket()){
            boolean lobby = false;
            String temp;
            while(!lobby){
                temp = (String) connection.getInput().readObject();
                lobby = temp.equals("LOBBY");
            }
            otherPlayers = (ArrayList<String>) connection.getInput().readObject();
            otherPlayers.remove(nickname);
        }
    }

    private static void nicknameRequest() throws IOException, InterruptedException, ClassNotFoundException {
        String nickname;
        boolean accepted = false;
        nickname = userInteractionCLI.nicknameRequest(true);
        accepted = setNickname(nickname);
        while (!accepted) {
            nickname = userInteractionCLI.nicknameRequest(false);
            accepted = setNickname(nickname);
        }
        AdrenalineClient.nickname = nickname;
        waitForStart();
    }

    public static LocalView getLocalView() {
        return localView;
    }


    public static void ipServerRequest() {
        String ipServer;
        String[] temp;
        boolean accepted;
        if (GUI) {
            answer = userInteractionGUI.mainLogGUI();
            while(!connected) {
                try {
                    setConnection(answer[1]);
                    connection.setStream();
                    connected = true;

                } catch (IOException e) {
                    userInteractionGUI.errorDisplay("connectionIP");
                }
                if(!connected)
                    answer = userInteractionGUI.mainLogGUI();
            }
            try {
                accepted = setNickname(answer[0]);
                if(!accepted)
                    userInteractionGUI.errorDisplay("nick");
                else
                    waitForStart();
                while(!accepted) {
                    userInteractionGUI.mainLogGUI();
                    accepted =  setNickname(answer[0]);
                    if (accepted) {
                        waitForStart();
                    } else {
                        userInteractionGUI.errorDisplay("nick");
                    }
                }
            } catch (ClassNotFoundException | InterruptedException | IOException e ) {
                userInteractionGUI.errorDisplay("connection");
            }
        }else{
            connected = false;
            while(!connected) {
                ipServer = userInteractionCLI.ipRequest();
                try {
                    setConnection(ipServer);
                    connection.setStream();
                    connected = true;
                } catch (IOException e) {
                    userInteractionCLI.showMessage("Si è verificato un errore di connessione, spiacente riprova più tardi.");
                    connected = false;
                }
            }
            try {
                nicknameRequest();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static void waitForStart() throws IOException, ClassNotFoundException {
        boolean firstTime = true;
        while(!start){
            getOtherPlayers(connection);
            displayQue(firstTime);
            try {
                waitForUpdate(connection);
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            firstTime = false;
        }
        start = false;
        gamePreparation();
    }

    private static void gamePreparation() throws IOException, ClassNotFoundException {
        boolean guiStarted = false;
        while(!start){
            String message = (String) connection.getInput().readObject();
            int[] mapSkull;
            if(message.equals("MAP")) {
                if (isGUI()){
                    mapSkull = userInteractionGUI.mapChooser();
                }else{
                    mapSkull = userInteractionCLI.mapChooser();
                }
                setMap(mapSkull[0]);
                setSkull(mapSkull[1]);
                message = (String) connection.getInput().readObject();

            }
            if(message.equals("VIEW")){
                connection.getOutput().reset();
                connection.getOutput().writeBoolean(true);
                connection.getOutput().flush();
                localView = (LocalView) connection.getInput().readObject();
                if(guiStarted)
                    displayBoard();
                else{
                    guiStarted = true;
                    guiStarter();
                }
            }
            message = (String) connection.getInput().readObject();
            if(message.equals("SPAWN")) {
                localView = (LocalView) connection.getInput().readObject();
                if(isGUI()){
                    PowerupCard cardForSpawn = userInteractionGUI.spawnChooser(localView.getPlayerHand().getPowerups());
                    sendSpawnChoice(cardForSpawn);
                    updateLocalView();
                    displayBoard();
                }
            }
            if(message.equals("START")){
                start = true;
                matchPhase();
            }
        }
    }

    private static void displayBoard() {
        if(isGUI()){
            gameBoardGui.updateBoardGame(opponentsBoards,getLocalView().getPersonalPlayerBoardView(),getLocalView().getMapView().getBoardMatrix(),getLocalView().getMapView().getKillView(),getLocalView().getPlayerHand());
        }
    }

    private static void matchPhase() throws IOException, ClassNotFoundException {
        boolean myturn;
        boolean last;
        int deadPlayers;
        int actionNumber;
        String status;
        ActionRequestView actionRequested;
        AvailableActions actions;
        ChosenActions chosen;
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
                    actionRequested = getActionFromUser(last);
                    if (actionNumber == 0)
                        last = true;
                    actions = askForAction(connection, actionRequested);
                    try {
                        chosen = presentActions(actions);
                        connection.getOutput().writeObject(chosen);
                    } catch (NoActionsException e) {
                        e.printStackTrace();
                    }
                    status = (String) connection.getInput().readObject();
                    if(status.equals("TARGETINGSCOPE"))
                        targetScope();
                    connection.getInput().readObject();
                    updateLocalView();
                }
                actionRequested = new ActionRequestView(true);
                connection.getOutput().writeObject(actionRequested);
                connection.getInput().readObject();
                updateLocalView();
            }else{
                waitForInfo();
                updateLocalView();
            }
            //questo è il fine turno
            deadPlayers = (int) connection.getInput().readObject();
            status = (String) connection.getInput().readObject();
            if (!status.equals("ALIVE")){
                reSpawn();
                deadPlayers -= 1;
            }
            for (int i = 0; i < deadPlayers; i++) {
                localView = (LocalView) connection.getInput().readObject();
                displayBoard();
            }
            start  = connection.getInput().readBoolean();
        }
        String finale = (String) connection.getInput().readObject();
        userInteractionGUI.showMessage(finale);
    }

    private static void targetScope() throws IOException, ClassNotFoundException {
        ArrayList<String> choices = (ArrayList<String>) connection.getInput().readObject();
        String reply = userInteractionGUI.stringSelector("Scegli il colore del cubo da pagare: ", choices);
        connection.getOutput().writeObject(reply);
        if(!reply.equals("Nothing")) {
            connection.getInput().readObject();
            choices = (ArrayList<String>) connection.getInput().readObject();
            reply = userInteractionGUI.stringSelector("Scegli il colore del player da colpire tra i seguenti: ", choices);
            connection.getOutput().writeObject(reply);
        }

    }

    private static void waitForInfo() throws IOException, ClassNotFoundException {
        String message;
        message = (String) connection.getInput().readObject();
        if (message.equals("TAGBACKUSAGE"))
            granade();
    }

    private static void granade() throws IOException {
        boolean reply = userInteractionGUI.yesOrNo("Sei stato colpito, vuoi usare il PowerUP TagBack Grenade?", "Si", "No");
        connection.getOutput().writeBoolean(reply);
    }

    private static void reSpawn() throws IOException, ClassNotFoundException {
        PowerupCard[] cards = (PowerupCard[]) connection.getInput().readObject();
        PowerupCard card = null;
        if(isGUI())
            card = userInteractionGUI.spawnChooser(cards);
        connection.getInput().reset();
        connection.getOutput().writeObject(card);
    }

    private static ChosenActions presentActions(AvailableActions actions) throws NoActionsException {
        return new ChosenActions(actions);
    }

    private static void updateLocalView() throws IOException, ClassNotFoundException {
        localView = (LocalView) connection.getInput().readObject();
    }

    private static void sendSpawnChoice(PowerupCard cardForSpawn) throws IOException, ClassNotFoundException {
        connection.getOutput().reset();
        connection.getOutput().writeObject(cardForSpawn);
        localView = (LocalView) connection.getInput().readObject();
    }

    private static void connectionRequest(boolean GUI, Connection connection) {
        Scanner scanner;
        int choice = 5; //inizializzo a un numero a caso, mi basta sia diverso da 0 o 1
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

    private static boolean setNickname(String nickname) throws IOException, InterruptedException, ClassNotFoundException {
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
            return reply.equals("true");
        }else{
            //todo gestione nickname RMI
        }
        return true;
    }

    private static void guiStarter () {
        for(int i = 0; i< getLocalView().getPlayerBoardViews().size();i++){
            if (getLocalView().getPlayerBoardViews().get(i) != getLocalView().getPersonalPlayerBoardView()){
                opponentsBoards.add(getLocalView().getPlayerBoardViews().get(i));
            }
        }

        gameBoardGui = new GameBoardGui(getLocalView().getMapView().getMapNumber(),opponentsBoards,getLocalView().getPersonalPlayerBoardView(),getLocalView().getMapView().getBoardMatrix() );
    }

        /** This method creates the connection between Client and server
         *
         *
         */
    private static void setConnection (String ipServer) throws IOException {
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

    public static void setAnswer(String[] answer) {
        AdrenalineClient.answer = answer;
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
