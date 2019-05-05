package it.polimi.se2019.test_model.test_game;

import it.polimi.se2019.model.cards.AmmoTileCard;
import it.polimi.se2019.model.game.DropCell;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


public class TestDropCell {

    @Test
    public void testDropCell(){
        DropCell testDropCell=new DropCell('b','w','d','w','r');
        assertNull(testDropCell.getDrop());
    }

    @Test
    public void testSetGetDrop(){
        DropCell testDropCell=new DropCell('b','w','d','w','r');
        AmmoTileCard ammoTileCard=new AmmoTileCard("pbb");
        testDropCell.setDrop(ammoTileCard);
        assertEquals(ammoTileCard,testDropCell.getDrop());
    }

    @Test
    public void testPickDrop(){
        DropCell testDropCell=new DropCell('b','w','d','w','r');
        AmmoTileCard ammoTileCard=new AmmoTileCard("pbb");
        testDropCell.setDrop(ammoTileCard);
        AmmoTileCard picked=testDropCell.pickDrop();
        assertEquals(picked,ammoTileCard);
        assertNull(testDropCell.getDrop());
    }

}
