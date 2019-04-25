package it.polimi.se2019.model.game;

import it.polimi.se2019.model.cards.AmmoTileCard;

import static it.polimi.se2019.Adrenaline.getMainController;


public class DropCell extends Cell{

    private AmmoTileCard drop;

    public DropCell(char color, char top, char bottom, char left, char right){
        super(color,top,bottom,left,right);
        this.drop = getMainController().getMainGameModel().currentDecks.getAmmotilesDeck().draw();
    }

    public AmmoTileCard getDrop(){
        return  drop;
    }

    public void setDrop(){

        this.drop = getMainController().getMainGameModel().currentDecks.getAmmotilesDeck().draw();;

    }
    public AmmoTileCard pickDrop(){
        AmmoTileCard temp=this.drop;
        this.drop = getMainController().getMainGameModel().currentDecks.getAmmotilesDeck().draw();
        return temp;
    }

}
