package it.polimi.se2019.view;

public class Square {
    private FigureView figure;
    private AmmoTileView drop;

    public void setDrop(AmmoTileView drop) {
        this.drop = drop;
    }

    public void setFigure(FigureView figure) {
        this.figure = figure;
    }

    public AmmoTileView getDrop() {
        return drop;
    }

    public FigureView getFigure() {
        return figure;
    }
}
