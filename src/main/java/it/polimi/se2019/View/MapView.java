package it.polimi.se2019.view;

import it.polimi.se2019.model.game.*;

import java.util.*;

public class MapView {
    private CellView[][] boardMatrix;
    private spawnSlotView[] spawnSlots;
    private KillShotTrackerView killView;
    private ArrayList<Player> players;

    public CellView getCell (int i, int j){
        return boardMatrix[i][j];
    }
}
