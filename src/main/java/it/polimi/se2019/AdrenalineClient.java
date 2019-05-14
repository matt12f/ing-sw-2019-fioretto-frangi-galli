package it.polimi.se2019;

import it.polimi.se2019.network.NetworkHandler;
import it.polimi.se2019.network.NetworkHandlerRMI;
import it.polimi.se2019.view.LocalView;

public class AdrenalineClient
{
    //This controller contains and manages the  game logic for all players (it's initialized only
    // if a player creates a new game)
    private static NetworkHandler networkHandler;
    private static LocalView localView;

    public static void main( String[] args){

        //TODO chiedere all'utente il tipo di connessione da utilizzare, o imposto dal server ?
        networkStarterClient(true); //RMI
        networkStarterClient(false); //Socket

        //TODO sei nel caso "unisciti a partita" ci si connetterà a una partita in attesa di partire e si creerà solo la view

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
            localView = networkHandler.getLocalView(1); //TODO recuperare il numero del player corretto
            //TODO scrivere metodo
        }
    }
}