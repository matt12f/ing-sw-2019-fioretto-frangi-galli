package it.polimi.se2019.controller;

import it.polimi.se2019.enums.CellType;
import it.polimi.se2019.enums.Color;
import it.polimi.se2019.exceptions.CardNotFoundException;
import it.polimi.se2019.exceptions.FullException;
import it.polimi.se2019.model.cards.GunCard;
import it.polimi.se2019.model.cards.PowerupCard;
import it.polimi.se2019.model.game.Hand;
import it.polimi.se2019.model.game.NewCell;
import it.polimi.se2019.model.game.Player;
import it.polimi.se2019.view.ChosenActions;
import it.polimi.se2019.view.LocalView;


public class PlayerManager {

    /**
     * This method scores all of the boards at the end of a turn
     * Note: a kill happens on the 11th damage given -> 1 token on the KST; -> the board gets scored at the end of the turn
     * Note: an overkill happens on the 12th damage given -> double token on the KST; you get a mark from the player you overkilled
     * Note: damage over 12 is wasted
     * Note: more than one killshot in one turn -> awards you a point
     */
    public static void scoringProcess(){
        //TODO scrivere metodo
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
                if (map[i][j].getCellType().equals(CellType.SPAWN) && map[i][j].getColor() == cellNeeded)
                    map[i][j].addPlayers(controller.getMainGameModel().getPlayerList().get(id));
            }
        }
    }

    public static PowerupCard[] getCardsToSpawn(boolean setUpGame, Controller controller, int id) throws FullException {
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
        return cards;
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
