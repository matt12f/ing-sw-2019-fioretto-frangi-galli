package it.polimi.se2019.model.game;

import it.polimi.se2019.model.cards.AmmoTileCard;

public class DropCell extends Cell{

    private AmmoTileCard drop;

    public DropCell(char color, char top, char bottom, char left, char right){
        super(color,top,bottom,left,right);
    }

    public AmmoTileCard getDrop(){
        return  drop;
    }

    /**
     * This method puts the card it receives in the slot without checking if it's already occupied
     */
    public void setDrop(AmmoTileCard ammoTileCard){
        this.drop =ammoTileCard;
    }

    /**
     * This method picks a drop card and returns it.
     * The slot will be refilled at the end of the turn, otherwise a player could use the move+grab
     * move twice in his turn and pick twice from the same DropCell (non compliant to game rules).
     */
    public AmmoTileCard pickDrop(){
        AmmoTileCard temp = this.drop;
        this.drop=null;
        return temp;
    }

}
