package it.polimi.se2019.view;

import it.polimi.se2019.model.game.Figure;
import it.polimi.se2019.model.game.NewCell;
import it.polimi.se2019.model.game.Player;

import java.util.ArrayList;

public class CellView {
    private NewCell correspondingCell;
    private int lineIndex;
    private int columnIndex;

    private ArrayList<Figure> playerFigures;



    public CellView(int lineIndex, int columnIndex, NewCell playerPosition) {
        this.lineIndex = lineIndex;
        this.columnIndex = columnIndex;
        this.playerFigures = new ArrayList<>();
        setPlayerFigures(playerPosition);
        setCell(playerPosition);
    }

    public void setPlayerFigures(NewCell playerPosition){

        this.playerFigures.clear();

        for (int i = 0; i <= playerPosition.getPlayers().size(); i++){
            this.playerFigures.add(playerPosition.getPlayers().get(i).getFigure());
        }
    }

    public ArrayList<Figure> getPlayerFigures(){
        return this.playerFigures;
    }

    public NewCell getCorrespondingCell() {
        return correspondingCell;
    }

    public void setCell(NewCell playerPosition){
        this.correspondingCell = playerPosition;
    }

    public int getLineIndex() {
        return lineIndex;
    }

    public int getColumnIndex() {
        return columnIndex;
    }




}


