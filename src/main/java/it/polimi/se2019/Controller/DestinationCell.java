package it.polimi.se2019.controller;

import java.util.ArrayList;

public class DestinationCell{
    private ArrayList<CellInfo> arrivalCell;

    public DestinationCell(int maxDistance, boolean grab) {
        ArrayList<CellInfo> arrivalCell=new ArrayList<>();
            //TODO Parto dalla posizione del player, mi sposto da lì in cerca di percorsi con cicli while;
            //in ogni cella del percorso (fino a una lunghezza massima di maxDistance, per riempire l'array, dovrò usare
            //l'operazione sottostante, dove "cell" è la cella in cui è terminato l'algoritmo di esplorazione
            arrivalCell.add(new CellInfo(cell,/* calcolo1 && grab */, /* calcolo2 && grab*/));
            //TODO calcolo1: da calcolare se può pagarne il prezzo, se non ha spazio nella mano può sempre scartarne una
            //TODO calcolo2: si stabilisce se ci sono ammo da raccogliere (quasi sempre si)
        this.arrivalCell =arrivalCell;
    }

    public ArrayList<CellInfo> getArrivalCell(){
        return arrivalCell;
    }
}
