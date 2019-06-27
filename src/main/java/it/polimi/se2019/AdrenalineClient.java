package it.polimi.se2019;

import it.polimi.se2019.enums.Status;
import it.polimi.se2019.network.*;
import it.polimi.se2019.view.LocalView;

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

    public static void main(String[] args) throws IOException, AlreadyBoundException, NotBoundException, InterruptedException, ClassNotFoundException {
        Connection connection = new Connection(null, null, false, null, null);
        boolean start = false;
        boolean GUI = false; //Se l'utente sceglierà la GUI piuttosto che la cli sarà true
        clientCallBackClass callBackClass = new clientCallBackClass();
        clientCallBack stubClient = null;
        ArrayList<String> nicknames = new ArrayList<>();
        ObjectOutputStream outputStream;
        ObjectInputStream inputStream;
        //Todo finestra per chiedere se CLI o GUI (anche solo un'alert window va bene) cambiando opportunamente il valore di GUI

        connectionRequest(GUI, connection);
        ipServerRequest(GUI, connection);
        if(connection.isSocket())
            connection.setStream();
        if (!(connection.isSocket())) {
            //export clientCallBack
            stubClient = (clientCallBack) UnicastRemoteObject.exportObject(callBackClass, 0);
            connection.getLocalRegistry().bind(("C" + connection.getHost()), stubClient);
            connection.getRegistry().lookup("");
        }
        nicknameRequest(GUI, connection);
        inputStream = connection.getInput();
        outputStream = connection.getOutput();
        while (!start) {
            //chiedi chi è connesso
            outputStream.writeObject("Players");
            System.out.print("Attualmente sei in coda con: ");//stampa i nomi di chi è in coda
            String nickn;
            nickn = (String) inputStream.readObject();
            while(!nickn.equals("Finished")){
                nicknames.add(nickn);
                outputStream.writeBoolean(true);
                outputStream.flush();
                nickn = (String) inputStream.readObject();
            }
            for (String nick: nicknames) {
                System.out.print(nick + ", ");
            }
            Thread.sleep(100);
            //funziona che riorna boolean che scopre se siamo pronti
        }
    }

    private static void nicknameRequest(boolean GUI, Connection connection) throws IOException {
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


    private static void ipServerRequest(boolean GUI, Connection connection) throws IOException {
        Scanner scanner;
        String ipServer;
        if (!GUI) {
            scanner = new Scanner(System.in);
            System.out.println("Inserisci l'indirizzo IP del server: ");
            ipServer = scanner.nextLine();
            setConnection(ipServer, connection);
            System.out.println("connessione correttamente stabilita");

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

    private static boolean setNickname(String nickname, Connection connection) throws IOException {
        Registry registry = null;
        Status serveStatus;
        RMIInterface stub = null;
        boolean isOk = false;
        ObjectOutputStream socketOutput = null;
        ObjectInputStream socketInput = null;
        if (connection.isSocket()) {
            try {
                socketInput = connection.getInput();
                socketOutput = connection.getOutput();
                System.out.println("Aspettando che il server sia pronto...");
                isOk = socketInput.readBoolean();
                System.out.println("Ok, il server è pronto");
                if (isOk) {
                    socketOutput.writeObject(nickname);
                }
                isOk = socketInput.readBoolean();
                return isOk;
            } catch (IOException e) {
                System.out.println("Mi spiace, si è verificato un problema di connessione e sei stato disconnesso dal server");
            }
        }else{
                try {
                    registry = connection.getRegistry();
                    stub = (RMIInterface) registry.lookup("");
                    serveStatus = stub.getStatus();
                    while (serveStatus == Status.NOTREADY) {

                    }
                    stub.setNicknameRMI(nickname);
                } catch (IllegalArgumentException e) {
                    return false; //per dire che il nick è già stato preso, probabilmente è una cosa rindondante
                } catch (NotBoundException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


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
}

class Connection{
    private boolean isSocket; //non so come chiamarlo, se socket = true se RMI = false
    private Socket socket;
    private Registry registry;
    private Registry localRegistry;
    private String Host;
    private ObjectOutputStream output = null;
    private ObjectInputStream input = null;

    public Connection(Socket socket, Registry registry, boolean isSocket, String host, Registry local) throws IOException {
        this.registry = registry;
        this.socket = socket;
        this.isSocket = isSocket;
        this.Host = host;
        this.localRegistry = local;
    }

    public void setStream() throws IOException {
        this.input = new ObjectInputStream(this.socket.getInputStream());
        this.output = new ObjectOutputStream(this.socket.getOutputStream());
    }

    public Registry getRegistry() {
        return registry;
    }

    public void setRegistry(Registry registry) {
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

    public void setHost(String host) {
        Host = host;
    }

    public String getHost() {
        return Host;
    }

    public Registry getLocalRegistry() {
        return localRegistry;
    }

    public ObjectInputStream getInput() {
        return this.input;
    }

    public ObjectOutputStream getOutput() {
        return this.output;
    }
}


class clientCallBackClass implements clientCallBack{

    @Override
public void ClientCallBack() throws RemoteException {
        /*
        //this method is empty because is used by the server only to check if the connection works before the match' start
         */
    }
}