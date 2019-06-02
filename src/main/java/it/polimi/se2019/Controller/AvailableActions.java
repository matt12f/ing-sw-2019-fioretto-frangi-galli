package it.polimi.se2019.controller;

import it.polimi.se2019.AdrenalineServer;
import it.polimi.se2019.enums.CellEdge;
import it.polimi.se2019.enums.CellType;
import it.polimi.se2019.enums.Color;
import it.polimi.se2019.model.cards.GunCard;
import it.polimi.se2019.model.game.NewCell;
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
    private ArrayList<CellInfo> singleArrivalCells; //for move and grab actions
    private ArrayList<GunCard> usableCards;
    private ArrayList<SingleCardActions> availableCardActions; //for the actions you can do with one card

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
     * @param frenzyReload: if there's a frenzy action with the possibility to reload a gun before shooting it must
     *                    be considered when evaluating which cards the player can use
     * @return available actions object to return to the player (CLIENT)
     */
    private static AvailableActions buildActions(Player player, int moveDistance, boolean grab, boolean shoot, boolean frenzyReload){
        //checks for adrenaline modes
        if(player.getPlayerBoard().getActionTileNormal().getAdrenalineMode1() && grab)
            moveDistance++;
        if(player.getPlayerBoard().getActionTileNormal().getAdrenalineMode2() && shoot)
            moveDistance++;

        AvailableActions temp=new AvailableActions();

        //arrivalCell setup
        if(moveDistance!=0) { //For move and move+grab actions
            temp.singleArrivalCells = new ArrayList<>();

            //here I'll add the cells that the player can move into
            //TODO Parto dalla posizione del player, mi sposto da lì in cerca di percorsi con cicli while;
            //in ogni cella del percorso (fino a una lunghezza massima di maxDistance, per riempire l'array, dovrò usare
            //l'operazione sottostante, dove "cell" è la cella in cui è terminato l'algoritmo di esplorazione
            NewCell cell=new NewCell(Color.BLUE, CellEdge.WALL,CellEdge.WALL,CellEdge.WALL,CellEdge.WALL, CellType.DROP); //TODO da calcolare come indicato sopra
            temp.singleArrivalCells.add(new CellInfo(cell,true/* calcolo1 && grab */,false /* calcolo2 && grab*/));
            //TODO calcolo1: da calcolare se può pagarne il prezzo, se non ha spazio nella mano può sempre scartarne una
            //TODO calcolo2: si stabilisce se ci sono ammo da raccogliere (quasi sempre si)

        }else //for actions without movement and grab
            temp.singleArrivalCells=null; //TODO qui va anche considerato il caso in cui il player voglia solo raccogliere, senza muoversi


        //Here I'll distinguish between the case in which a player hasn't moved/grabbed anything: where singleArrivalCell is null
        //and the case in which the player has moved/grabbed something: I'll then have to create fictitious players to represent
        //the player's status if he chooses a certain movement/grab before shooting

        if(shoot) {
            //usableCards and SingleCardActions setup
            if (temp.singleArrivalCells == null) { //player hasn't changed its original status
                cardActionsMasterBuilder(temp,player,frenzyReload);
            }else{ //the player's status has different possibilities of change
                for(CellInfo cell:temp.singleArrivalCells){
                    Player fictitiousPlayer= makeFictitiousPlayer(player, cell);
                    cardActionsMasterBuilder(temp,fictitiousPlayer,frenzyReload);
                    //TODO considerare anche che un giocatore può (se può pagarle) raccogliere ciascuna arma in una spawncell
                }
            }
        }else{
            temp.usableCards = null;
            temp.availableCardActions = null;
        }
        return temp;
    }

    /**
     * This method creates a fictitious player that has supposedly taken the choices contained in the parameter cell
     * @param cell incapsulates the choices about movement and
     * @param player used to add the ammo picked up to the existing ammo a player has
     * @return fictitious player
     */
    private static Player makeFictitiousPlayer(Player player,CellInfo cell){
        Player fictitiousPlayer=new Player(-1,"fictitious",Color.WHITE);
        fictitiousPlayer.getFigure().setCell(cell.getCell());
        //TODO aggiungere ammo raccolti e carte arma raccolte
        return fictitiousPlayer;
    }

    private static void cardActionsMasterBuilder(AvailableActions temp,Player player,boolean frenzyReload){
        temp.usableCards = evaluateUsableCards(player, frenzyReload);
        temp.availableCardActions = new ArrayList<>();
        for (GunCard gunCard : temp.usableCards)
            temp.availableCardActions.add(packAvailableCardActions(gunCard, player));
    }
    /**
     * this method simply evaluates whether the player can use a card by checking:
     * - if it's loaded
     * - ?
     */
    private static ArrayList<GunCard> evaluateUsableCards(Player player,boolean frenzyReload){
        ArrayList<GunCard> usableCards=new ArrayList<>();
        //evaluates if the guns are loaded
        for(GunCard gunCard: player.getPlayerBoard().getHand().getGuns()){
            if(gunCard!=null && (gunCard.isLoaded()||frenzyReload && canAffordReload(player,gunCard))) //case without frenzy reload possible
                    usableCards.add(gunCard);
            }
        return usableCards;
    }

    private static boolean canAffordReload(Player player, GunCard gunCard) {
        return gunCard.getRedAmmoCost() <= player.getPlayerBoard().getAmmo().getRed() && gunCard.getBlueAmmoCost() <= player.getPlayerBoard().getAmmo().getBlue()  && gunCard.getYellowAmmoCost()<= player.getPlayerBoard().getAmmo().getYellow();
    }

    /**this method evaluates all of the actions a certain player can do with a specific card
     */
    private static SingleCardActions packAvailableCardActions(GunCard card,Player player){
        //this part builds the list of combination of the effects a player can use
        ArrayList<String> effectsInOrder=new ArrayList<>();
        for(int i=0;i<card.getEffectsOrder().length;i++) {//this selects one effects combination
            for(int j=0;j<card.getEffectsOrder()[i].length;j++) //this scans a single effects combination
                if(!card.getEffectsOrder()[i][j].equals("Base"))
                    canAffordEffect(effectsInOrder, card.getEffectsOrder()[i][j], player);
        }

        //TODO scrivere codice
        /*
        - parto da un vettore di stringhe con tutte le possibili combinazioni dell'arma
        - escludo gli effetti che il player non può pagare (considerare l'uso di powerup per pagare gli effetti)
        - Richiamo i metodi per "risolvere" le combinazioni, in ogni arma (alcune carte avranno più computazione di altre)
         */
        return null;
    }

    /**
     * This method simply evaluates whether the player can afford to use an alternative or Optional effect
     * @param effectsInOrder is the Arraylist to which to add the effect if it's affordable
     * @param effect is the effect to evaluate
     * @param player is the player to evaluate for the affordability of the effect
     */
    private static void canAffordEffect(ArrayList<String> effectsInOrder, String effect,Player player) {
        //TODO scrivere codice
        switch (effect){
            case "Base":break;
            case "Alternative":break;
            case "Optional1":break;
            case "Optional2":break;
        }
    }
}