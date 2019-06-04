package it.polimi.se2019.view;

import it.polimi.se2019.model.game.*;

import java.util.*;

public class MapView {
    //TODO variabile con immagine della mappa
    private CellView[][] boardMatrix;
    private spawnSlotView[] spawnSlots;
    private KillShotTrackerView killView;

    public CellView getCell (int i, int j){
        return boardMatrix[i][j];
    }
}
