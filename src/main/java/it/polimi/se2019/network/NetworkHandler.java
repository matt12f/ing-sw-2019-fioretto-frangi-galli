package it.polimi.se2019.network;

import it.polimi.se2019.view.LocalView;

public abstract class NetworkHandler{
    public abstract LocalView getLocalView(int playerId);
    public abstract int [] buildAndSendActionRequest(int playerId); //this method returns the available actions (change int)

    //Mettere metodi per la gestione della comunicazione che possano essere usati sia in RMI che in Socket
}