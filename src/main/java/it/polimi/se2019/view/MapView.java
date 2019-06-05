package it.polimi.se2019.view;

public class MapView {
    //TODO variabile con immagine della mappa
    private CellView[][] boardMatrix;
    private spawnSlotView[] spawnSlots;
    private KillShotTrackerView killView;

    private void fakeMethodForGit(String resolveIssue){
        if (resolveIssue.length()>4)
            System.out.println("OMG");
    }
    public CellView getCell (int i, int j){
        return boardMatrix[i][j];
    }
}
