package it.polimi.se2019.model.game;

import it.polimi.se2019.model.cards.AmmoTileCard;


public class DropCell extends Cell{

    private AmmoTileCard drop;

    public DropCell(char color, char top, char bottom, char left, char right){
        super(color,top,bottom,left,right);
        this.drop = null;
    }

    /**
     * This method will be useb by the view to see what drop is in the cell
     * @return
     */
    public AmmoTileCard getDrop(){
        return  drop;
    }

    public void setDrop(AmmoTileCard drop){
        this.drop = drop;
    }

    /**
     *
     * @param drop this is the AmmoTileCard to place where the one you picked must go
     * @return this is the AmmoTileCard to discard
     */
    public AmmoTileCard pickDrop(AmmoTileCard drop){
        AmmoTileCard temp=this.drop;
        this.drop = drop;
        return temp;
    }

}
