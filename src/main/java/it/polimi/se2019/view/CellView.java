package it.polimi.se2019.view;

import it.polimi.se2019.model.cards.AmmoTileCard;
import it.polimi.se2019.model.game.Figure;
import it.polimi.se2019.model.game.NewCell;

import java.io.Serializable;
import java.util.ArrayList;

public class CellView implements Serializable {
    private NewCell correspondingCell;
    private int lineIndex;
    private int columnIndex;
    private String drop;

    private ArrayList<Figure> playerFigures;



    public CellView(int lineIndex, int columnIndex, NewCell playerPosition) {
        this.lineIndex = lineIndex;
        this.columnIndex = columnIndex;
        this.playerFigures = new ArrayList<>();
        try {
            setDrop(playerPosition.getDrop().getContent());
        }catch (NullPointerException e){
            this.drop=null;
        }
        setPlayerFigures(playerPosition);
        setCell(playerPosition);
    }

    public void setPlayerFigures(NewCell playerPosition){

        this.playerFigures.clear();
        if(playerPosition != null && !playerPosition.getPlayers().isEmpty())
            for (int i = 0; i < playerPosition.getPlayers().size(); i++){
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

    public void setDrop(String string){
        this.drop = string;
    }

    public String getDrop(){
        return drop;
    }




}


