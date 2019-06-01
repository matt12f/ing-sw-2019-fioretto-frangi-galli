package it.polimi.se2019.controller;

import it.polimi.se2019.AdrenalineServer;
import it.polimi.se2019.model.cards.GunCard;
import it.polimi.se2019.model.game.Player;
import it.polimi.se2019.view.ActionRequestView;

import java.util.ArrayList;

/**
 * This class builds the message object that the ActionManager in Controller builds and sends over to the Client
 * to choose from
 *
 * In the Normal 1 combination (move, move, move) you will only use the cell object
 *
 * In the Normal 2 combination (move,(move) grab) you will only use the cell object
 *
 * In the Normal 3 combination ((move), shoot) you will use the cell object and an object for each card
 * in your hand that indicates if you can use it and in which way
 *
 * The client will then build an "answer object" containing his selection
 */
//TODO convert to JSON string before sending
public class AvailableActions {
    private DestinationCell arrivalCell; //for move and grab actions
    private ArrayList<SingleCardActions> availableCardActions; //for the actions you can do with one card
    private ArrayList<GunCard> usableCards;

    /**
     * Method that calculates the actions available to a player during a certain turn (after the selection of a
     * macro action)
     * @param macroAction player's selection (in ActionRequestView through GUI or CLI)
     * @param playerId player's number (ID)
     * @return available actions depending on the game status
     */
    public static AvailableActions computeActionsByMacro(ActionRequestView macroAction, int playerId) {
        Player player=AdrenalineServer.getMainController().getMainGameModel().getPlayerList().get(playerId);
        switch (macroAction.getActionToRequest()){
            case NORMAL1: return buildActions(player,3,false,false,false);
            case NORMAL2: return buildActions(player,1,true,false,false);
            case NORMAL3: return buildActions(player,0,false,true,false);
            case FRENZY1: return buildActions(player,1,false,true,true);
            case FRENZY2: return buildActions(player,4,false,false,false);
            case FRENZY3: return buildActions(player,2,true,false,false);
            case FRENZY4: return buildActions(player,2,false,true,true);
            case FRENZY5: return buildActions(player,3,true,false,false);
            default:return null;
        }
    }

    /**
     * method that builds the available actions from the macro actions
     * @param player: is the subject of the action
     * @param moveDistance: number of move actions in the macro actions
     * @param grab: if there is a grab option
     * @param shoot: if there is a shoot option (sometimes it's the only action possible)
     * @return available actions object to return to the player (CLIENT)
     */
    private static AvailableActions buildActions(Player player, int moveDistance, boolean grab, boolean shoot, boolean reload){
        //checks for adrenaline modes
        if(player.getPlayerBoard().getActionTileNormal().getAdrenalineMode1() && grab)
            moveDistance++;
        if(player.getPlayerBoard().getActionTileNormal().getAdrenalineMode2() && shoot)
            moveDistance++;

        AvailableActions temp=new AvailableActions();

        //arrivalCell setup
        if(moveDistance!=0) //For move and move+grab actions
            temp.arrivalCell=new DestinationCell(moveDistance, grab);
        else //for actions without movement
            temp.arrivalCell=null;

        //TODO Considerare implentazione della possibilità di reload nelle azioni frenzy
        // (aggiungere direttamente alle carte utilizzabili?) rivedi metodo evaluateUsableCards (?)

        //usableCards and SingleCardActions setup
        if(shoot){
            temp.usableCards=evaluateUsableCards(player);
            temp.availableCardActions=new ArrayList<>();
            for(GunCard gunCard :temp.usableCards)
                temp.availableCardActions.add(packAvailableCardActions(gunCard,player));
        }
        else{
            temp.usableCards=null;
            temp.availableCardActions=null;
        }
        return temp;
    }

    /**
     * this method simply evaluates whether the player can use a card by checking:
     * - if it's loaded
     * - ?
     */
    private static ArrayList<GunCard> evaluateUsableCards(Player player){
        ArrayList<GunCard> usableCards=new ArrayList<>();
        //evaluates if the guns are loaded
        for(int i=0;i<player.getPlayerBoard().getHand().getGuns().length;i++)
            if(player.getPlayerBoard().getHand().getGuns()[i]!=null && player.getPlayerBoard().getHand().getGuns()[i].isLoaded())
                usableCards.add(player.getPlayerBoard().getHand().getGuns()[i]);
        return usableCards;
    }

    /**this method evaluates all of the actions a certain player can do with a specific card
     */
    private static SingleCardActions packAvailableCardActions(GunCard card,Player player){
        //TODO scrivere codice
        ArrayList<String> effectsInOrder=new ArrayList<>();
        for(int i=0;i<card.getEffectsOrder().length;i++) {//this selects one effects combination
            for(int j=0;j<card.getEffectsOrder()[i].length;j++) //this scans a single effects combination
                if(!card.getEffectsOrder()[i][j].equals("Base"))
                    canAffordEffect(effectsInOrder, card.getEffectsOrder()[i][j]);
        }

        /*
        - parto da un vettore di stringhe con tutte le possibili combinazioni dell'arma
        - escludo gli effetti che il player non può pagare (considerare l'uso di powerup per pagare gli effetti)
        - Richiamo i metodi per "risolvere" le combinazioni, in ogni arma (alcune carte avranno più computazione di altre)
         */
        return null;
    }

    private static void canAffordEffect(ArrayList<String> effectsInOrder, String effect) {
        switch (effect){
            case "Base":break;
            case "Alternative":;
            case "Optional1":
            case "Optional2":
        }
    }
}