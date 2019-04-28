package it.polimi.se2019;

import it.polimi.se2019.controller.Controller;
import it.polimi.se2019.view.LocalView;


public class Adrenaline
{
    //This controller contains and manages the  game logic for all players (it's initialized only
    // if a player creates a new game
    private static Controller mainController;
    private static LocalView localView=new LocalView();
    public static void main( String[] args)
    {

        //TODO all'avvio del programma (questo main) l'utente deve scegliere se unirsi a una partita
        // oppure se crearne un'altra. In questo modo salterà a una porzione di codice differente, che
        // gestirà questa differenza

        //TODO caso "crea nuova partita" si creerà un oggetto Controller e si aprirà una connessione

        //TODO caso "unisciti a partita" ci si connetterà a una partita in attesa di partire e si creerà solo la View

    }

    public static Controller getMainController() {
        return mainController;
    }
    private void guiStarter(){}
    private void networkStarter(){}
}