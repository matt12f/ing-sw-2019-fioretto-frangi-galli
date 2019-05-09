package it.polimi.se2019.test_model.test_game;

import it.polimi.se2019.model.cards.AmmoTileCard;
import it.polimi.se2019.model.game.DropCell;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class TestDropCell {

    @Test
    public void testDropCell(){
        DropCell testDropCell=new DropCell('b','w','d','w','r');
        assertNull(testDropCell.getItem());
    }

    @Test
    public void testSetGetItem(){
        DropCell testDropCell=new DropCell('b','w','d','w','r');
        AmmoTileCard ammoTileCard=new AmmoTileCard("pbb");
        assertDoesNotThrow(()->testDropCell.setItem(ammoTileCard));
        assertEquals(ammoTileCard,testDropCell.getItem());
    }

    @Test
    public void testPickItem(){
        DropCell testDropCell=new DropCell('b','w','d','w','r');
        AmmoTileCard ammoTileCard=new AmmoTileCard("pbb");
        assertDoesNotThrow(()->testDropCell.setItem(ammoTileCard));
        AmmoTileCard picked=testDropCell.pickItem(0);
        assertEquals(picked,ammoTileCard);
        assertNull(testDropCell.getItem());
    }

}
