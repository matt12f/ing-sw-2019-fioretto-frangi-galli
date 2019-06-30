package it.polimi.se2019.view;

import it.polimi.se2019.model.cards.AmmoTileCard;
import it.polimi.se2019.model.game.Figure;

public class Square {
    private Figure figure;
    private AmmoTileCard drop;

    public void setDrop(AmmoTileCard drop) {
        this.drop = drop;
    }

    public void setFigure(Figure figure) {
        this.figure = figure;
    }

    public AmmoTileCard getDrop() {
        return drop;
    }

    public Figure getFigure() {
        return figure;
    }
}
