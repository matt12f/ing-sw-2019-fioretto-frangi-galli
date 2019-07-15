package it.polimi.se2019.test_controller;

import it.polimi.se2019.controller.Controller;
import it.polimi.se2019.controller.MapManager;
import it.polimi.se2019.controller.PlayerManager;
import it.polimi.se2019.enums.Color;
import it.polimi.se2019.model.cards.CyberBlade;
import it.polimi.se2019.model.cards.PlasmaGun;
import it.polimi.se2019.model.cards.PowerupCard;
import it.polimi.se2019.model.cards.VortexCannon;
import it.polimi.se2019.model.game.NewCell;
import it.polimi.se2019.model.game.Player;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

public class TestPlayerManager {
    @Test
     public void testMarkerDealer(){
        Controller controller;
        ArrayList<Player> players = new ArrayList<>();
        Player player=new Player(0,"george000", Color.YELLOW);
        players.add(player);
        player=new Player(1,"george001",Color.BLUE);
        players.add(player);
        player=new Player(2,"george010",Color.WHITE);
        players.add(player);
        player=new Player(3,"george011",Color.GREEN);
        players.add(player);
        player=new Player(4,"george100",Color.VIOLET);
        players.add(player);
        controller = new Controller(players, 1, 5);
        char[] marks = new char[2];
        marks[0] = players.get(1).getFigure().getColorChar();
        marks[1] = players.get(1).getFigure().getColorChar();
        PlayerManager.markerDealer(controller, players.get(0), marks);
        assertEquals(2, controller.getMainGameModel().getPlayerList().get(0).getPlayerBoard().getDamageTrack().getMarks().size());
        assertEquals('b', controller.getMainGameModel().getPlayerList().get(0).getPlayerBoard().getDamageTrack().getMarks().get(0));
        assertEquals('b', controller.getMainGameModel().getPlayerList().get(0).getPlayerBoard().getDamageTrack().getMarks().get(1));
        controller.getMainGameModel().notifyRemoteView();
        assertEquals(2, controller.getRemoteView().getPlayerBoardViews().get(0).getDamageView().getMarks().size());
        assertEquals('b', controller.getRemoteView().getPlayerBoardViews().get(0).getDamageView().getMarks().get(0));
        assertEquals('b', controller.getRemoteView().getPlayerBoardViews().get(0).getDamageView().getMarks().get(1));
    }

    @Test
    public void testDamageDealer() {
        Controller controller;
        ArrayList<Player> players = new ArrayList<>();
        Player player1 = new Player(0, "george000", Color.YELLOW);
        Player player2 = new Player(1, "george001", Color.GREEN);
        Player player3 = new Player(2, "george010", Color.BLUE);
        Player player4 = new Player(3, "george011", Color.WHITE);
        players.add(player1);
        players.add(player2);
        players.add(player3);
        players.add(player4);
        controller = new Controller(players, 1, 5);

        NewCell position1 = controller.getMainGameModel().getCurrentMap().getBoardMatrix()[2][2];

        MapManager.getRoom(controller, position1).addPlayers(player1);
        position1.addPlayers(player1);
        player1.getFigure().setCell(position1);

        MapManager.getRoom(controller, position1).addPlayers(player2);
        position1.addPlayers(player2);
        player2.getFigure().setCell(position1);

        MapManager.getRoom(controller, position1).addPlayers(player3);
        position1.addPlayers(player3);
        player3.getFigure().setCell(position1);

        MapManager.getRoom(controller, position1).addPlayers(player4);
        position1.addPlayers(player4);
        player4.getFigure().setCell(position1);

        char[] damageToDeal = {'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b'}; //11 BLUE damages


        PlayerManager.damageDealer(controller, player1, damageToDeal,false);
        assertEquals('b', player1.getPlayerBoard().getDamageTrack().getDamage()[10]);
        assertEquals(' ', player1.getPlayerBoard().getDamageTrack().getDamage()[11]);

        //it's dead
        assertTrue(controller.getMainGameModel().getDeadPlayers().contains(player1));

        //it's no longer on the board
        for (int i = 0; i < controller.getMainGameModel().getCurrentMap().getBoardMatrix().length; i++)
            for (int j = 0; j < controller.getMainGameModel().getCurrentMap().getBoardMatrix()[i].length; j++)
                assertFalse(controller.getMainGameModel().getCurrentMap().getBoardMatrix()[i][j].getPlayers().contains(player1));


        char[] damageToDeal2 = {'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'w', 'w'}; //11 BLUE + 1 White damages

        PlayerManager.damageDealer(controller, player2, damageToDeal2,false);
        assertEquals('b', player2.getPlayerBoard().getDamageTrack().getDamage()[9]);
        assertEquals('w', player2.getPlayerBoard().getDamageTrack().getDamage()[10]);
        assertEquals('w', player2.getPlayerBoard().getDamageTrack().getDamage()[10]);
        assertEquals('w', player2.getPlayerBoard().getDamageTrack().getDamage()[11]);
        //it's dead
        assertTrue(controller.getMainGameModel().getDeadPlayers().contains(player2));
        assertEquals(2,controller.getMainGameModel().getDeadPlayers().size());

        //it's no longer on the board
        for (int i = 0; i < controller.getMainGameModel().getCurrentMap().getBoardMatrix().length; i++)
            for (int j = 0; j < controller.getMainGameModel().getCurrentMap().getBoardMatrix()[i].length; j++)
                assertFalse(controller.getMainGameModel().getCurrentMap().getBoardMatrix()[i][j].getPlayers().contains(player2));

        PlayerManager.scoringProcess(controller);

        assertEquals(0, player1.getScore());
        assertEquals(0, player2.getScore());
        assertEquals(18, player3.getScore()); //1 first blood +8from board1 +1 first blood +8from board
        assertEquals(6, player4.getScore());

        assertEquals(1,controller.getMainGameModel().getPlayerByColor('w').getPlayerBoard().getDamageTrack().getMarks().size());
        assertEquals(0,controller.getMainGameModel().getPlayerByColor('y').getPlayerBoard().getDamageTrack().getMarks().size());
        assertEquals(0,controller.getMainGameModel().getPlayerByColor('b').getPlayerBoard().getDamageTrack().getMarks().size());
        assertEquals(0,controller.getMainGameModel().getPlayerByColor('g').getPlayerBoard().getDamageTrack().getMarks().size());

        assertEquals(3, controller.getMainGameModel().getKillshotTrack().getSkulls());

        assertEquals("b", controller.getMainGameModel().getKillshotTrack().getKills()[0]);
        assertEquals("ww", controller.getMainGameModel().getKillshotTrack().getKills()[1]);

        assertEquals(6, controller.getMainGameModel().getPlayerByColor('y').getPlayerBoard().getCurrentBoardValue());
        assertEquals(6, controller.getMainGameModel().getPlayerByColor('g').getPlayerBoard().getCurrentBoardValue());

        assertTrue(controller.getMainGameModel().getPlayerByColor('y').getPlayerBoard().getDamageTrack().hasNoDamage());
        assertTrue(controller.getMainGameModel().getPlayerByColor('g').getPlayerBoard().getDamageTrack().hasNoDamage());

        char[] damageToDeal3 = {'y', 'y', 'y','y'};

        PlayerManager.damageDealer(controller, player4, damageToDeal3,false);
        assertEquals('y', player4.getPlayerBoard().getDamageTrack().getDamage()[0]);
        assertEquals('y', player4.getPlayerBoard().getDamageTrack().getDamage()[1]);
        assertEquals('y', player4.getPlayerBoard().getDamageTrack().getDamage()[2]);
        assertEquals('y', player4.getPlayerBoard().getDamageTrack().getDamage()[3]);
        assertEquals(' ', player4.getPlayerBoard().getDamageTrack().getDamage()[4]);

        assertFalse(player4.getPlayerBoard().getActionTileNormal().getAdrenalineMode1());

        // --- here we'll respawn the player, clear the dead players and then score another board --- //
        for (Player player: controller.getMainGameModel().getDeadPlayers()){
            assertDoesNotThrow(()->PlayerManager.getCardsToSpawn(false, controller, player.getId()));
            if(!player.getPlayerBoard().getHand().isEmptyPU())
                assertDoesNotThrow(()->PlayerManager.spawnPlayers(controller, player.getId(), player.getPlayerBoard().getHand().getPowerups()[0]));
            else
                assertDoesNotThrow(()->PlayerManager.spawnPlayers(controller, player.getId(), controller.getMainGameModel().getCurrentDecks().getPowerupsDeck().draw()));
        }

        controller.getMainGameModel().getDeadPlayers().clear();

        // --- second scoring --- //
        PlayerManager.scoringProcess(controller);

        assertTrue(player4.getPlayerBoard().getActionTileNormal().getAdrenalineMode1());

        // --- nothing happens except the adrenaline 1 is activated for the first player --- //

        assertEquals(0, player1.getScore());
        assertEquals(0, player2.getScore());
        assertEquals(18, player3.getScore()); //1 first blood +8from board1 +1 first blood +8from board
        assertEquals(6, player4.getScore());

        assertEquals(1,controller.getMainGameModel().getPlayerByColor('w').getPlayerBoard().getDamageTrack().getMarks().size());
        assertEquals(0,controller.getMainGameModel().getPlayerByColor('y').getPlayerBoard().getDamageTrack().getMarks().size());
        assertEquals(0,controller.getMainGameModel().getPlayerByColor('b').getPlayerBoard().getDamageTrack().getMarks().size());
        assertEquals(0,controller.getMainGameModel().getPlayerByColor('g').getPlayerBoard().getDamageTrack().getMarks().size());

        assertEquals(3, controller.getMainGameModel().getKillshotTrack().getSkulls());

        assertEquals("b", controller.getMainGameModel().getKillshotTrack().getKills()[0]);
        assertEquals("ww", controller.getMainGameModel().getKillshotTrack().getKills()[1]);

        assertEquals(6, controller.getMainGameModel().getPlayerByColor('y').getPlayerBoard().getCurrentBoardValue());
        assertEquals(6, controller.getMainGameModel().getPlayerByColor('g').getPlayerBoard().getCurrentBoardValue());

        assertTrue(controller.getMainGameModel().getPlayerByColor('y').getPlayerBoard().getDamageTrack().hasNoDamage());
        assertTrue(controller.getMainGameModel().getPlayerByColor('g').getPlayerBoard().getDamageTrack().hasNoDamage());

    }

    @Test
    public void testReloadManager(){
        Player player=new Player(1,"george",Color.BLUE);
        CyberBlade card1=new CyberBlade();
        VortexCannon card2=new VortexCannon();
        PlasmaGun card3=new PlasmaGun();

        card1.setLoaded(false);
        card2.setLoaded(false);
        card3.setLoaded(false);

        assertDoesNotThrow(()->player.getPlayerBoard().getHand().setGun(card1));
        assertDoesNotThrow(()->player.getPlayerBoard().getHand().setGun(card2));
        assertDoesNotThrow(()->player.getPlayerBoard().getHand().setGun(card3));

        for (int i = 0; i < player.getPlayerBoard().getHand().getGuns().length; i++)
            assertFalse(player.getPlayerBoard().getHand().getGuns()[i].isLoaded());

        boolean [] cardsToReload={true,false,true};

        assertEquals(1,player.getPlayerBoard().getAmmo().getBlue());
        assertEquals(1,player.getPlayerBoard().getAmmo().getYellow());
        assertEquals(1,player.getPlayerBoard().getAmmo().getRed());

        PlayerManager.reloadManager(player,cardsToReload);

        assertTrue(player.getPlayerBoard().getHand().getGuns()[0].isLoaded());
        assertFalse(player.getPlayerBoard().getHand().getGuns()[1].isLoaded());
        assertFalse(player.getPlayerBoard().getHand().getGuns()[2].isLoaded()); //can't afford it

        assertEquals(1,player.getPlayerBoard().getAmmo().getBlue());
        assertEquals(0,player.getPlayerBoard().getAmmo().getYellow());
        assertEquals(0,player.getPlayerBoard().getAmmo().getRed());

        player.getPlayerBoard().getAmmo().addAmmo("yyr");

        assertEquals(1,player.getPlayerBoard().getAmmo().getBlue());
        assertEquals(2,player.getPlayerBoard().getAmmo().getYellow());
        assertEquals(1,player.getPlayerBoard().getAmmo().getRed());

        boolean [] cardsToReload2={false,false,true};
        PlayerManager.reloadManager(player,cardsToReload2);

        assertEquals(0,player.getPlayerBoard().getAmmo().getBlue());
        assertEquals(1,player.getPlayerBoard().getAmmo().getYellow());
        assertEquals(1,player.getPlayerBoard().getAmmo().getRed());

    }

    @Test
    public void testSpawnPlayers(){
        Controller controller;
        ArrayList<Player> players = new ArrayList<>();
        Player player1 = new Player(0, "george000", Color.YELLOW);
        Player player2 = new Player(1, "george001", Color.GREEN);
        Player player3 = new Player(2, "george010", Color.BLUE);
        Player player4 = new Player(3, "george011", Color.WHITE);
        players.add(player1);
        players.add(player2);
        players.add(player3);
        players.add(player4);
        controller = new Controller(players, 1, 5);

        NewCell position1 = controller.getMainGameModel().getCurrentMap().getBoardMatrix()[2][2];

        MapManager.getRoom(controller, position1).addPlayers(player1);
        position1.addPlayers(player1);
        player1.getFigure().setCell(position1);

        MapManager.getRoom(controller, position1).addPlayers(player2);
        position1.addPlayers(player2);
        player2.getFigure().setCell(position1);

        MapManager.getRoom(controller, position1).addPlayers(player3);
        position1.addPlayers(player3);
        player3.getFigure().setCell(position1);

        MapManager.getRoom(controller, position1).addPlayers(player4);
        position1.addPlayers(player4);
        player4.getFigure().setCell(position1);

        assertTrue(controller.getMainGameModel().getDeadPlayers().isEmpty());

        controller.getMainGameModel().addDeadPlayer(player1);

        //it's dead
        assertTrue(controller.getMainGameModel().getDeadPlayers().contains(player1));

        //it's no longer on the board
        for (int i = 0; i < controller.getMainGameModel().getCurrentMap().getBoardMatrix().length; i++)
            for (int j = 0; j < controller.getMainGameModel().getCurrentMap().getBoardMatrix()[i].length; j++)
                assertFalse(controller.getMainGameModel().getCurrentMap().getBoardMatrix()[i][j].getPlayers().contains(player1));

        assertDoesNotThrow(()->PlayerManager.getCardsToSpawn(false,controller,0));

        PowerupCard powerupCard = controller.getMainGameModel().getPlayerList().get(0).getPlayerBoard().getHand().getPowerup(0);

        assertDoesNotThrow(()->PlayerManager.spawnPlayers(controller,0,powerupCard));

        assertTrue(player1.getPlayerBoard().getHand().isEmptyPU());
        controller.getMainGameModel().getDeadPlayers().clear();
        assertTrue(controller.getMainGameModel().getDeadPlayers().isEmpty());

        if(powerupCard.getCubeColor()=='b')
            assertTrue(controller.getMainGameModel().getCurrentMap().getBoardMatrix()[0][2].getPlayers().contains(player1));
        else if(powerupCard.getCubeColor()=='r')
            assertTrue(controller.getMainGameModel().getCurrentMap().getBoardMatrix()[1][0].getPlayers().contains(player1));
        else if(powerupCard.getCubeColor()=='y')
            assertTrue(controller.getMainGameModel().getCurrentMap().getBoardMatrix()[2][3].getPlayers().contains(player1));



    }







}
