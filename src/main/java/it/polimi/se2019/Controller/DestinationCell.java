package it.polimi.se2019.controller;

import it.polimi.se2019.model.game.NewCell;

import java.util.ArrayList;

public class DestinationCell{
    private ArrayList<NewCell> arrivalCell;
    private boolean [] canGrabCard; //TODO sarà quasi sempre un sì (come potrebbe essere no?)
    private boolean [] canGrabAmmo; //TODO come stabilisco se può raccogliere un ammo? 1. se può accogliere gli ammo; poi?

    public DestinationCell() {
        ArrayList<NewCell> arrivalCell=new ArrayList<>();
        boolean[] grabCard=new boolean[arrivalCell.size()];
        boolean[] grabAmmo=new boolean[arrivalCell.size()];
        //TODO qui andranno riempiti gli oggetti dichiarati qui sopra da offrire al giocatore
        this.arrivalCell = arrivalCell;
        this.canGrabCard = grabCard;
        this.canGrabAmmo = grabAmmo;
    }
}
