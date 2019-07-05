package it.polimi.se2019.test_controller;

import it.polimi.se2019.controller.Controller;
import it.polimi.se2019.enums.CellType;
import it.polimi.se2019.enums.Color;
import it.polimi.se2019.model.game.NewCell;
import it.polimi.se2019.model.game.Player;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class TestController {

    @Test
    public void testVarious(){
        Player player1=new Player(1,"frank", Color.BLUE);
        Player player2=new Player(2,"george",Color.YELLOW);
        Player player3=new Player(3,"miles",Color.WHITE);
        Player player4=new Player(4,"carl",Color.GREEN);

        ArrayList<Player> players=new ArrayList<>();

        Controller controller=new Controller(players,2,3);

        players.add(player1);
        players.add(player2);
        players.add(player3);
        players.add(player4);

        player1.setScore(19);
        player2.setScore(27);
        player3.setScore(12);
        player4.setScore(5);

        assertNotNull(controller.getMainGameModel());
        assertNotNull(controller.getActiveTurn());
        assertNotNull(controller.getRemoteView());


        //Test filling of matrix
        NewCell[][] mapMatrixToFill=controller.getMainGameModel().getCurrentMap().getBoardMatrix();
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 4; j++)
                if (mapMatrixToFill[i][j].getCellType().equals(CellType.DROP)){
                    assertNotNull(mapMatrixToFill[i][j].getDrop());
                }else if (mapMatrixToFill[i][j].getCellType().equals(CellType.SPAWN)){
                    assertNotNull(mapMatrixToFill[i][j].getWeaponCards());
                    assertEquals(3,mapMatrixToFill[i][j].getWeaponCards().size());
                }else
                    assertEquals(CellType.OUTSIDEBOARD,mapMatrixToFill[i][j].getCellType());

    }
}
