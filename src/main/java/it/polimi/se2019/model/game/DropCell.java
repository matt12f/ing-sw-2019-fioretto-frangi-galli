package it.polimi.se2019.model.game;

import it.polimi.se2019.exceptions.FullException;
import it.polimi.se2019.model.cards.AmmoTileCard;

public class DropCell extends Cell{

    private AmmoTileCard drop;

    public DropCell(char color, char top, char bottom, char left, char right){
        super(color,top,bottom,left,right);
    }

    @Override
    public AmmoTileCard getItem(){
        return  drop;
    }

    /**
     * This method puts the card it receives in the slot
     */
    @Override
    public void setItem(Object card) throws FullException{
        if(this.drop==null)
        {
            this.drop =(AmmoTileCard) card;
        }
        else throw new FullException("Ammotile slot already full");
    }

    /**
     * This method picks a drop card and returns it.
     * The slot will be refilled at the end of the turn, otherwise a player could use the move+grab
     * move twice in his turn and pick twice from the same DropCell (non compliant to game rules).
     */
    @Override
    public AmmoTileCard pickItem(int pick){
        AmmoTileCard temp = this.drop;
        this.drop=null;
        return temp;
    }

}
