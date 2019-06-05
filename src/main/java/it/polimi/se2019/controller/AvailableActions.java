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
 * This class builds the message object that the ActionManager in controller builds and sends over to the Client
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
    private ArrayList<FictitiousPlayer> fictitiousPlayers; //the player will choose one of these

    /**
     * Method that calculates the actions available to a player during a certain turn (after the selection of a macro action)
     * @param macroAction player's selection (in ActionRequestView through GUI or CLI)
     * @param playerId player's number (ID)
     * @return available actions depending on the game status
     */
    public AvailableActions(ActionRequestView macroAction, int playerId) {
        Player player=AdrenalineServer.getMainController().getMainGameModel().getPlayerList().get(playerId);
        switch (macroAction.getActionToRequest()){
            case NORMAL1:  buildActions(player,macroAction,3,false,false,false);break;
            case NORMAL2:  buildActions(player,macroAction,1,true,false,false);break;
            case NORMAL3:  buildActions(player,macroAction,0,false,true,false);break;
            case FRENZY1:  buildActions(player,macroAction,1,false,true,true);break;
            case FRENZY2:  buildActions(player,macroAction,4,false,false,false);break;
            case FRENZY3:  buildActions(player,macroAction,2,true,false,false);break;
            case FRENZY4:  buildActions(player,macroAction,2,false,true,true);break;
            case FRENZY5:  buildActions(player,macroAction,3,true,false,false);break;
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
    private void buildActions(Player player, ActionRequestView macroAction, int moveDistance, boolean grab, boolean shoot, boolean frenzyReload){

        //TODO chiamata per applicare modifiche dei powerup

        //checks for adrenaline modes
        if(player.getPlayerBoard().getActionTileNormal().getAdrenalineMode1() && grab)
            moveDistance++;
        if(player.getPlayerBoard().getActionTileNormal().getAdrenalineMode2() && shoot)
            moveDistance++;

        this.fictitiousPlayers=new ArrayList<>();
        this.singleArrivalCells=createArrivalCells(player,moveDistance,grab);

        for(CellInfo cell:this.singleArrivalCells)
            this.fictitiousPlayers.add(new FictitiousPlayer(player, cell,shoot,frenzyReload,macroAction.getPowerupUse().getUsedPowerups()));
    }

    /**
     * arrivalCell setup
     */
    private static  ArrayList<CellInfo> createArrivalCells(Player player, int moveDistance, boolean grab){
        ArrayList<CellInfo> singleArrivalCells = new ArrayList<>();

        if(moveDistance!=0){ //For move and move+grab actions
            //here I'll add the cells that the player can move into
            //TODO Parto dalla posizione del player, mi sposto da lì in cerca di percorsi con cicli while;
            // in ogni cella del percorso (fino a una lunghezza massima di maxDistance), per riempire l'array, dovrò usare
            // l'operazione sottostante, dove "cell" è la cella in cui è terminato l'algoritmo di esplorazione
            NewCell cell=new NewCell(Color.BLUE, CellEdge.WALL,CellEdge.WALL,CellEdge.WALL,CellEdge.WALL, CellType.DROP); //TODO da calcolare come indicato sopra
            singleArrivalCells.add(new CellInfo(cell,true/* calcolo1 && grab */,false /* calcolo2 && grab*/));
            //TODO calcolo1: da calcolare se può pagarne il prezzo, se non ha spazio nella mano può sempre scartarne una
            //TODO calcolo2: si stabilisce se ci sono ammo da raccogliere (quasi sempre si) (se si è solo spostato, senza grab, sarà false!!)

        }else if(grab) //for actions without movement but with grab
            singleArrivalCells.add(new CellInfo(player.getFigure().getCell(),true, player.getFigure().getCell().getCellType().equals(CellType.DROP) && player.getFigure().getCell().getDrop()!=null));
        else  //for actions without movement and grab
            singleArrivalCells.add(new CellInfo(player.getFigure().getCell(),false,false));
        return singleArrivalCells;
    }
}