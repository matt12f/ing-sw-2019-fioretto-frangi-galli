package it.polimi.se2019.test_model.test_game;

import it.polimi.se2019.enums.CellEdge;
import it.polimi.se2019.enums.CellType;
import it.polimi.se2019.enums.Color;
import it.polimi.se2019.model.cards.AmmoTileCard;
import it.polimi.se2019.model.game.NewCell;
import it.polimi.se2019.model.game.Player;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestNewCell{
    @Test
    public void testNewCellClone(){

        NewCell position1= new NewCell(Color.BLUE, CellEdge.WALL,CellEdge.DOOR,CellEdge.ROOM,CellEdge.ROOM, CellType.DROP);

        Player player1= new Player(2,"GeorgeForemanGrill",Color.BLUE);
        player1.getFigure().setCell(position1);

        Player player2= new Player(1,"SueMe",Color.YELLOW);
        player1.getFigure().setCell(position1);

        position1.addPlayers(player1);
        position1.addPlayers(player2);
        assertDoesNotThrow(()-> position1.setItem(new AmmoTileCard("xxx")));

        NewCell position2=position1.clone();

        //it works because we've made a custom equals
        assertEquals(position1, position2);

        //this is a clone of a NewCell and the list of players is simply copied
        assertEquals(position1.getPlayers(),position2.getPlayers());


    }

    @Test
    public void testDrop(){
        NewCell position1= new NewCell(Color.BLUE, CellEdge.WALL,CellEdge.DOOR,CellEdge.ROOM,CellEdge.ROOM, CellType.DROP);

        assertNull(position1.getDrop());
        AmmoTileCard tile=new AmmoTileCard("yyb");
        assertDoesNotThrow(()->position1.setItem(tile));

        assertEquals(tile, position1.pickItem());

        assertNull(position1.getDrop());
    }

}
