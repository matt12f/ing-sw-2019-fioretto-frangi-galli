package it.polimi.se2019.view;

public class MapView {
    //TODO variabile con immagine della mappa
    private CellView[][] boardMatrix;
    private KillShotTrackerView killView;

    public CellView getCell (int i, int j){
        return boardMatrix[i][j];
    }
}
