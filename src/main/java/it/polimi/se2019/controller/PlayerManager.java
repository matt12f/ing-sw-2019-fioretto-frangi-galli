package it.polimi.se2019.controller;

import it.polimi.se2019.enums.CellType;
import it.polimi.se2019.enums.Color;
import it.polimi.se2019.exceptions.CardNotFoundException;
import it.polimi.se2019.exceptions.FullException;
import it.polimi.se2019.model.cards.GunCard;
import it.polimi.se2019.model.cards.PowerupCard;
import it.polimi.se2019.model.game.*;
import it.polimi.se2019.view.ChosenActions;
import it.polimi.se2019.view.LocalView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


public class PlayerManager {

    /**
     * This method scores all of the boards at the end of a turn
     *
     * Note: more than one killshot in one turn -> awards you a point
     */
    public static void scoringProcess(Controller currentController){
        //if there are dead players we must score their boards
        if(!currentController.getMainGameModel().getDeadPlayers().isEmpty()){
            ArrayList<PlayerBoard> boardsDeadPlayers = new ArrayList<>();
            for (Player deadPlayer: currentController.getMainGameModel().getDeadPlayers())
                boardsDeadPlayers.add(deadPlayer.getPlayerBoard());

            //Array to check if someone has dealt a double kill
            ArrayList<Character> offendersCharColors= new ArrayList<>();
            char offenderColor;
            for(PlayerBoard board: boardsDeadPlayers){
                offenderColor=scoreSingleBoard(currentController, board);
                offendersCharColors.add(offenderColor);
                if(Collections.frequency(offendersCharColors,offenderColor)==2) //it's a double kill
                    currentController.getMainGameModel().getPlayerByColor(offenderColor).setScore(1);
            }

        }

        //here we'll update the RemoteView
        currentController.getMainGameModel().notifyRemoteView();
    }

    /**
     * this method scores the single PlayerBoard, dealing points
     * Points dealt:
     *      - first blood: 1 point
     *      - (first) player with most damage: boardvalue points
     *
     * update of killshot track:
     *      - 1 damage token of color of player that dealt the killshot; 2 if it dealt also overkill
     *
     * Note: a kill happens on the 11th damage given -> 1 token on the KST; -> the board gets scored at the end of the turn
     * Note: an overkill happens on the 12th damage given -> double token on the KST; you get a mark from the player you overkilled
     *
     * @param board is the board to score
     */
    private static char scoreSingleBoard(Controller currentController, PlayerBoard board){
        //gives one point for first blood
        currentController.getMainGameModel().getPlayerByColor(board.getDamageTrack().getDamage()[0]).setScore(1);

        //section for dealing points
        //here we extract the current value of the board
        int value=board.getCurrentBoardValue();

        for(char playerColor: listOffenders(board.getDamageTrack().getDamage())) {
            currentController.getMainGameModel().getPlayerByColor(playerColor).setScore(value);
            if(value>1)
                value = value - 2;
            else
                value=1;
        }

        //we'll then decrease the value of the board after the kill
        board.decreaseBoardValue();

        //the playerBoard must have a color there, since the player is dead
        char offenderColor = board.getDamageTrack().getDamage()[10];

        //update killshot track
        StringBuilder kill=new StringBuilder();
        kill.append(offenderColor);
        //it's an overkill if the 12th drop is the same color as the player that killed it
        if(board.getDamageTrack().getDamage()[11] == offenderColor){
            kill.append(offenderColor); //double token
            //gives one mark back
            currentController.getMainGameModel().getPlayerByColor(offenderColor).getPlayerBoard().getDamageTrack().addMark(board.getColorChar());
        }
        currentController.getMainGameModel().getKillshotTrack().setKills(kill.toString());

        return offenderColor;
    }

    /**
     * this method builds a list of the players char colors by the number of damage they've given
     * (See tests for example)
     *
     * It also considers the tie breaker in favour of the player that hit first
     *
     *  @param damage is the damageTrack
     */
    private static char[] listOffenders(char[] damage) {
        ArrayList<Character> damageList=new ArrayList<>();
        for(char car: damage)
            damageList.add(car);

        int numberOfOffenders=0;
        //here it orders the damage (it's in the order it's given)
        ArrayList<Character> temp=new ArrayList<>();
        for(Character character: damageList){
            if(!temp.contains(character)){
                numberOfOffenders++;
                for (int i = 0; i < Collections.frequency(damageList,character); i++)
                    temp.add(character);
            }
        }

        char [] offendersList=new char[numberOfOffenders];
        for (int i = 0; i < numberOfOffenders; i++)
            offendersList[i]=temp.stream().distinct().collect(Collectors.toList()).get(i);
        return offendersList;

    }

    public static void spawnPlayers(Controller controller, int id, PowerupCard spawn){
        NewCell[][] map = controller.getMainGameModel().getCurrentMap().getBoardMatrix();
        Color cellNeeded;
        if(spawn.getCubeColor() == 'b')
            cellNeeded = Color.BLUE;
        else if(spawn.getCubeColor() == 'r')
            cellNeeded = Color.RED;
        else
            cellNeeded = Color.YELLOW;
        for (int i = 0; i < map.length - 1; i++) {
            for (int j = 0; j < map[0].length - 1; j++) {
                if (map[i][j].getCellType().equals(CellType.SPAWN) && map[i][j].getColor() == cellNeeded) {
                    map[i][j].addPlayers(controller.getMainGameModel().getPlayerList().get(id));
                    controller.getMainGameModel().getPlayerList().get(id).getFigure().setCell(map[i][j]);
                }
            }
        }
        controller.getMainGameModel().notifyRemoteView();
    }

    public static void getCardsToSpawn(boolean setUpGame, Controller controller, int id) throws FullException {
        PowerupCard[] cards = new PowerupCard[Hand.getMaxcards()+1];
        if(setUpGame){
            PowerupCard card;
            card = controller.getMainGameModel().getCurrentDecks().getPowerupsDeck().draw();
            controller.getMainGameModel().getPlayerList().get(id).getPlayerBoard().getHand().setPowerup(card);
            card= controller.getMainGameModel().getCurrentDecks().getPowerupsDeck().draw();
            controller.getMainGameModel().getPlayerList().get(id).getPlayerBoard().getHand().setPowerup(card);
        }else{
            for (int i = 0; i < Hand.getMaxcards(); i++) {
                cards[i] = controller.getMainGameModel().getPlayerList().get(id).getPlayerBoard().getHand().getPowerup(i);
            }
            cards[Hand.getMaxcards()] = controller.getMainGameModel().getCurrentDecks().getPowerupsDeck().draw();
        }
        controller.getRemoteView().getPlayerHands().get(id).setPowerups(cards);
        controller.getRemoteView().notifyLocalViews();
    }

    /**
     * this method applies the player's choices for the Macro Action it requested
     * @param currentController is the controller of the game
     * @param actions contains the choices
     */
    public static void choiceExecutor(Controller currentController, ChosenActions actions){
        Player player = currentController.getActiveTurn().getActivePlayer();
        FictitiousPlayer fictitiousPlayer=actions.getFictitiousPlayer();
        //moves the player
        ActionManager.movePlayer(currentController, player, fictitiousPlayer.getPosition());

        //grab management, ammo first, then guncards
        GunCard cardToDiscard;
        if(fictitiousPlayer.isGrabbedAmmo()) {
            if(player.getPlayerBoard().getAmmo().addAmmo(player.getFigure().getCell().pickItem().getContent())){ //checks if you can draw a powerup
                try { //tries to place the powerup in your hand and then removing the card from the deck if it succeeded
                    player.getPlayerBoard().getHand().setPowerup(currentController.getMainGameModel().getCurrentDecks().getPowerupsDeck().peekCardOnTop());
                    currentController.getMainGameModel().getCurrentDecks().getPowerupsDeck().draw();
                }catch (FullException e){
                    //no hard feelings if it fails to place the card in the player's hand
                }
            }

        }else if(actions.getCardToPick()!=null) {
            if(actions.getCardToDiscard()==null)
                cardToDiscard = currentController.getMainGameModel().getCurrentDecks().getGunDeck().draw();
            else
                cardToDiscard=actions.getCardToDiscard();
            try {
                payGunCardCost(player,actions.getCardToPick().getAmmoCost(),true);
                player.getPlayerBoard().getHand().substitutionGunCard(player.getFigure().getCell(),cardToDiscard, actions.getCardToPick());
                } catch (CardNotFoundException e) {
                //nothing to see here
                }
        }

        //applies shoot actions with the card selected
        actions.getChosenGun().applyEffects(currentController,actions);
    }

    /**
     * This method deals damage to the player it receives as a parameter
     *
     * Note: the damage must be dealt BEFORE the marks
     *
     * @param damageToDeal array of chars that contain the damage as characters representing the color of the offender.
     *                     Note that the damage drops "to be dealt" here come from a single player.
     */
    public static void damageDealer(Controller currentController, Player target, char[] damageToDeal){

        //Deal the damage first and if it's still alive deal marks too
        if(target.getPlayerBoard().getDamageTrack().dealDamage(damageToDeal).equals("alive")){
            //here we "pull" the marks this player has left before
            int markNumber = target.getPlayerBoard().getDamageTrack().pullMarks(damageToDeal[0]);
            //i'll build the array of chars to deal the marks
            char[] marks=new char[markNumber];
            for (int i = 0; i <markNumber ; i++)
                marks[i]=damageToDeal[0];

            //If the player dies after the marks are dealt we put it aside to score his board later
            if(!target.getPlayerBoard().getDamageTrack().dealDamage(marks).equals("alive"))
                currentController.getMainGameModel().addDeadPlayer(target);
        }
        else
            currentController.getMainGameModel().addDeadPlayer(target);
    }

    /**
     * this method adds new marks to a player's player board
     */
    public static void markerDealer(Player player, char[] add) {
        for (char mark: add)
            player.getPlayerBoard().getDamageTrack().addMark(mark);
    }

    /**
     * this method enables the adrenaline modes in the player boards of a given player
     */
    public static void adrenalineManager(Player player){
        if(player.getPlayerBoard().getDamageTrack().getDamage().length >= 3)
            player.getPlayerBoard().getActionTileNormal().setAdrenalineMode1(true);

        else if(player.getPlayerBoard().getDamageTrack().getDamage().length >= 6)
            player.getPlayerBoard().getActionTileNormal().setAdrenalineMode1(false);

    }

    public static void payGunCardCost(Player player, char [] cost, boolean fullOrReload){
        char [] reloadCost=new char[cost.length-1];
        if(fullOrReload)
            player.getPlayerBoard().getAmmo().subtractAmmo(cost);
        else{
            for (int i = 0; i < cost.length-1; i++)
                reloadCost[i]=cost[i+1];
            player.getPlayerBoard().getAmmo().subtractAmmo(reloadCost);
        }
    }

    /**
     * This method activates the final frenzy mode for the whole game (it's centered around a the active player)
     */
    public static void frenzyActivator(Controller currentController){
        currentController.getMainGameModel().activateFinalFrenzy(currentController.getActiveTurn().getActivePlayer().getId());
    }

    public static void reloadManager(Player player, boolean[] reload) {
        for(int i=0;i<player.getPlayerBoard().getHand().getGuns().length;i++)
            if(player.getPlayerBoard().getHand().getGuns()[i]!=null && reload[i]) {
                player.getPlayerBoard().getHand().getGuns()[i].setLoaded(true);
                payGunCardCost(player, player.getPlayerBoard().getHand().getGuns()[i].getAmmoCost(), false);
            }
    }
}
