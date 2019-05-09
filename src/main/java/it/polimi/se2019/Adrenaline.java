package it.polimi.se2019;

import it.polimi.se2019.controller.Controller;
import it.polimi.se2019.network.NetworkHandler;
import it.polimi.se2019.network.NetworkHandlerRMI;
import it.polimi.se2019.view.LocalView;

public class Adrenaline
{
    //This controller contains and manages the  game logic for all players (it's initialized only
    // if a player creates a new game)
    private static Controller mainController;
    private static NetworkHandler networkHandler;
    private static LocalView localView;

    public static void main( String[] args){

        //TODO chiedere se l'utente vuole unirsi a una partita oppure se crearne un'altra.
        // In questo modo salterà a una porzione di codice differente, che gestirà questa differenza

        //TODO chiedere all'utente il tipo di connessione da utilizzare
        networkStarterClient(true); //RMI
        networkStarterClient(false); //Socket


        //TODO caso "crea nuova partita" si creerà un oggetto Controller e si aprirà una connessione

        //TODO caso "unisciti a partita" ci si connetterà a una partita in attesa di partire e si creerà solo la View

    }

    public static Controller getMainController() {
        return mainController;
    }

    public static LocalView getLocalView() {
        return localView;
    }

    private void guiStarter(){
        //TODO scrivere metodo
    }

    private static void networkStarterClient(boolean rmi){
        if (rmi) {
            networkHandler = new NetworkHandlerRMI();
            localView = ((NetworkHandlerRMI) networkHandler).getLocalView(1); //TODO recuperare il numero del player corretto
            //TODO scrivere metodo
        }
    }
}