package it.polimi.se2019;

import it.polimi.se2019.controller.Controller;

public class AdrenalineServer
{
    //This controller contains and manages the  game logic for all players (it's initialized only
    // if a player creates a new game)
    private static Controller mainController;

    public static void main( String[] args){
        mainController=new Controller(); //TODO qui di default parte la connessione RMI

        //TODO sei nel caso "crea nuova partita" si crerà un oggetto Controller e si aprirà una connessione

    }

    public static Controller getMainController() {
        return mainController;
    }

    private void guiStarter(){
        //TODO scrivere metodo
    }
}