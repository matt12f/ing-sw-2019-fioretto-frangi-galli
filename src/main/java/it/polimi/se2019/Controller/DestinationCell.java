package it.polimi.se2019.controller;

import it.polimi.se2019.model.game.NewCell;

import java.util.ArrayList;

public class DestinationCell{
    private ArrayList<NewCell> arrivalCell;
    private boolean [] canGrabCard;
    private boolean [] canGrabAmmo;

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
