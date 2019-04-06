package it.polimi.se2019.game;

import it.polimi.se2019.database.AmmoTileCard;

public class DropCell extends Cell{

    private AmmoTileCard drop;

    public DropCell(char color, char top, char bottom, char left, char right){

        this.color = color;
        this.top = top;
        this.bottom = bottom;
        this.left = left;
        this.right = right;
        this.drop = new AmmoTileCard();
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
