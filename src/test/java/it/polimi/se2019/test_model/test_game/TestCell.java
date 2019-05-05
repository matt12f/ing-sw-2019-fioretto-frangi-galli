package it.polimi.se2019.test_model.test_game;

import it.polimi.se2019.model.game.Cell;
import it.polimi.se2019.model.game.DropCell;
import it.polimi.se2019.model.game.Player;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TestCell {
    @Test
    public void testAddGetRemovePlayers() {
        Cell testCell = new DropCell('b','w','d','w','r');
        Player player1=new Player(1,"georgeFrank",'b');
        testCell.addPlayers(player1);
        assertTrue(testCell.getPlayers().contains(player1));
        testCell.removePlayers(player1);
        assertFalse(testCell.getPlayers().contains(player1));

    }
}