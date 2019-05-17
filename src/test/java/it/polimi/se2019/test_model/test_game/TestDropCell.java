package it.polimi.se2019.test_model.test_game;

import it.polimi.se2019.enums.CellEdge;
import it.polimi.se2019.enums.CellType;
import it.polimi.se2019.enums.Color;
import it.polimi.se2019.model.cards.AmmoTileCard;
import it.polimi.se2019.model.game.NewCell;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class TestDropCell {

    @Test
    public void testNewCell(){
        NewCell testDropCell=new NewCell(Color.BLUE,CellEdge.WALL,CellEdge.DOOR,CellEdge.WALL, CellEdge.ROOM, CellType.DROP);
        assertNull(testDropCell.getDrop());
    }

    @Test
    public void testSetGetItem(){
        NewCell testDropCell=new NewCell(Color.BLUE,CellEdge.WALL,CellEdge.DOOR,CellEdge.WALL,CellEdge.ROOM,CellType.DROP);
        AmmoTileCard ammoTileCard=new AmmoTileCard("pbb");
        assertDoesNotThrow(()->testDropCell.setItem(ammoTileCard));
        assertEquals(ammoTileCard,testDropCell.getDrop());
    }

    @Test
    public void testPickItem(){
        NewCell testDropCell=new NewCell(Color.BLUE,CellEdge.WALL,CellEdge.DOOR,CellEdge.WALL,CellEdge.ROOM,CellType.DROP);
        AmmoTileCard ammoTileCard=new AmmoTileCard("pbb");
        assertDoesNotThrow(()->testDropCell.setItem(ammoTileCard));
        AmmoTileCard picked=testDropCell.pickItem();
        assertEquals(picked,ammoTileCard);
        assertNull(testDropCell.getDrop());
    }

}
