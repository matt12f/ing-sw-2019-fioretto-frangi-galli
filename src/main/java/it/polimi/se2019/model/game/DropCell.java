package it.polimi.se2019.model.game;

import it.polimi.se2019.model.cards.AmmoTileCard;

public class DropCell extends Cell{

    private AmmoTileCard drop;

    public DropCell(char color, char top, char bottom, char left, char right){

        this.color = color;
        this.top = top;
        this.bottom = bottom;
        this.left = left;
        this.right = right;
        this.drop = null;
    }

    public AmmoTileCard getDrop(){
        return  drop;
    }
    public void setDrop(AmmoTileCard drop){
        this.drop = drop;

    }
    public void pickDrop(){
    //TODO pickDrop
    }

}
