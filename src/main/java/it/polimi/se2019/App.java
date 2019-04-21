package it.polimi.se2019;

import it.polimi.se2019.controller.Controller;


public class App 
{
    //This controller contains and manages the local game logic
    private static Controller mainController= new Controller();
    public static void main( String[] args)
    {
        //TODO bisogna immaginare che il funzionamento del programma gestisce un singolo giocatore nel
        // caso si unisca a una partita, mentre se la crea deve gestire la sincronizzazione dei vari
        // giocatori connessi, aggiornando i loro GameModel rispetto a quello principale salvato.

        //TODO all'avvio del programma (questo main) l'utente deve scegliere se unirsi a una partita
        // oppure se crearne un'altra. In questo modo salterà a una porzione di codice differente, che
        // gestirà questa differenza

        //TODO controller dovrà quindi essere pensato "in due parti" una che gestisce le logiche di gioco
        // per ogni giocatore (come il Controller di adesso) e una che gestisca la parte di rete (con due
        // classi ConnectionControllerMaster e ConnectionControllerSlave)
    }

    public static Controller getMainController() {
        return mainController;
    }
}