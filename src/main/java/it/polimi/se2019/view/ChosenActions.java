package it.polimi.se2019.view;

import it.polimi.se2019.AdrenalineClient;
import it.polimi.se2019.controller.*;
import it.polimi.se2019.enums.CellType;
import it.polimi.se2019.exceptions.NoActionsException;
import it.polimi.se2019.model.cards.GunCard;
import it.polimi.se2019.model.game.NewCell;
import it.polimi.se2019.model.game.Player;
import it.polimi.se2019.model.game.Room;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.function.Predicate;

/**
 * this class is going to send back the objects it received to "select" them.
 * It's meant to encapsulate the player's choices, asking it which of the available actions it wants to perform
 */
public class ChosenActions implements Serializable {
    private UserInteractionGUI askUser;

    private ArrayList<String> orderOfExecution;

    private FictitiousPlayer fictitiousPlayer;

    private GunCard cardToPick;
    private GunCard cardToDiscard;

    private GunCard chosenGun;
    private boolean useExtra;

    private ArrayList<Player> targetsFromList1;

    private NewCell targetCell; //for damage to all of the players on one Cell
    private Room targetRoom; //for damage to all of the players in one Room

    private NewCell cellToMoveOpponent;
    private NewCell cellToMoveYourself;
    private NewCell cellFromCellWithTrg;

    private ArrayList<Player> targetsFromCell;

    /**
     *
     * @param actions is the package of available actions sent by the controller unit on the server
     * @throws NoActionsException if there are no actions to be performed
     */
    public ChosenActions(AvailableActions actions) throws NoActionsException {
        if(checkEmptyActions(actions))
            throw new NoActionsException("Non ci sono target disponibili con nessuna delle carte che hai, prova ancora");

        this.askUser=new UserInteractionGUI();

        LocalView localView= AdrenalineClient.getLocalView();

        //Section for selection of cells where the fictitious player will be
        ArrayList<String> arrivalCellsIndex=new ArrayList<>();
        for(FictitiousPlayer fictPlayer: actions.getFictitiousPlayers())
            arrivalCellsIndex.add(actions.getFictitiousPlayers().indexOf(fictPlayer)+". "+cellToText(localView, fictPlayer.getPosition()));

        String chosenArrivalCell=this.askUser.stringSelector("Scegli una cella dove vuoi spostarti",arrivalCellsIndex);
        this.fictitiousPlayer = actions.getFictitiousPlayers().get(arrivalCellsIndex.indexOf(chosenArrivalCell));


        //Section for grab/move + grab action picking Ammo/Guns
        if(this.fictitiousPlayer.getAvailableCardActions().isEmpty()) { //this is a grab/move + grab action picking Ammo
            if(this.fictitiousPlayer.isGrabbedAmmo()) {
                this.askUser.ammoTileViewer(this.fictitiousPlayer.getPosition().getDrop().getContent());
                powerupManagement(this.fictitiousPlayer);
            }
            if(!this.fictitiousPlayer.getPickableCards().isEmpty()){ //in case this is a grab/move + grab action picking a Gun
                this.cardToPick = gunCardManager(this.fictitiousPlayer.getPickableCards());
                if(localView.getPlayerHand().isGunHandFull())//this happens if you already have three cards in your hand and you want to pick a fourth one
                    this.cardToDiscard = cardDiscardSelector(localView);
            }
            //if you haven't picked anything and didn't move, you can retry
            if((!this.fictitiousPlayer.isGrabbedAmmo() && this.cardToPick==null) &&
                    this.fictitiousPlayer.getPosition().equals(fictitiousPlayer.getCorrespondingPlayer().getFigure().getCell()))
                throw new NoActionsException("Non ti sei spostato e non hai raccolto nulla, rifai l'azione");

        }
        else //Section for actions that include shooting
        {
            ArrayList<String> listOfCards=new ArrayList<>();

            //removes cards that have no available combinations
            Predicate<SingleCardActions> cardActionsPredicate= p-> p.getAvailableCombinations().isEmpty();
            this.fictitiousPlayer.getAvailableCardActions().removeIf(cardActionsPredicate);

            for(SingleCardActions cardActions: this.fictitiousPlayer.getAvailableCardActions()) //this card is valid to be used
                    listOfCards.add("Usable card: " + cardActions.getGunCardToUse().getClass().getSimpleName());


            //asks the user which card it wants to use, listing the combination of effects it can perform
            boolean finalDecision;
            String cardSelected;
            do {
                cardSelected = this.askUser.stringSelector("Seleziona l'arma che vuoi usare", listOfCards);
                finalDecision = this.askUser.yesOrNo("Questa carta ha le seguenti combinazioni degli effetti: "+ this.fictitiousPlayer.getAvailableCardActions().get(listOfCards.indexOf(cardSelected)).getAvailableCombinations().toString(),"Prosegui","Seleziona un'altra carta");
            }while (!finalDecision);

            //extracts the single card chosen by the player
            SingleCardActions chosenCard = this.fictitiousPlayer.getAvailableCardActions().get(listOfCards.indexOf(cardSelected));

            this.chosenGun=chosenCard.getGunCardToUse();

            //choice of the action combination
            String chosenCombination=this.askUser.stringSelector("Scegliere una combinazione di effetti",chosenCard.getAvailableCombinations());
            SingleEffectsCombinationActions combinationActions=chosenCard.getEffectsCombinationActions().get(chosenCard.getAvailableCombinations().indexOf(chosenCombination));

            selectActions(localView, combinationActions,chosenCard.getGunCardToUse().getClass().getSimpleName());
        }

    }

    /**
     * tells the player if the powerup hand is full and the powerup in the ammo tile is wasted
     * @param player
     */
    private void powerupManagement( FictitiousPlayer player) {
        //checks if there is a powerup in the Ammo Tile
        if(player.getPosition().getCellType().equals(CellType.DROP) && player.getPosition().getDrop().getContent().contains("p"))
            if(player.getCorrespondingPlayer().getPlayerBoard().getHand().isPowerUpHandFull())
                this.askUser.showMessage("Non puoi pescare questo powerUp, ne hai già 3");
    }

    private void selectActions(LocalView localView, SingleEffectsCombinationActions combination,String cardName){

        this.orderOfExecution=combination.getEffectsCombination();

        this.targetsFromList1 = new ArrayList<>();
        this.targetsFromCell = new ArrayList<>();

        if(combination.isOfferableExtra())
            this.useExtra = this.askUser.yesOrNo("vuoi usare la parte extra dell'effetto?", "Si", "No");

        //disabling the extra for PowerGlove card
        if(!this.useExtra && cardName.equals("PowerGlove"))
            combination.getCellsWithTargets().clear();


        //selection of normal targets and secondary targets different from the first
        if(!combination.getPlayersTargetList().isEmpty())
            this.targetsFromList1.addAll(selectTargets(combination.getPlayersTargetList(),combination.getMinNumPlayerTargets(), combination.getMaxNumPlayerTargets()));


        if(!combination.getTargetRooms().isEmpty())
            this.targetRoom = selectRoom(combination.getTargetRooms());

        if(!combination.getTargetCells().isEmpty())
            this.targetCell=selectOneCell(localView, combination.getTargetCells());


        //managing cells with targets (the selected targets are put in targetsFromCell within the method used below)
        if(!combination.getCellsWithTargets().isEmpty()) {
            if(combination.isCanMoveYourself())
                this.cellToMoveYourself=selectCellWithTargets(localView, combination,"MoveYourself");
            if(combination.isCanMoveOpponent())
                this.cellToMoveOpponent=selectCellWithTargets(localView, combination, cardName);
            if(!combination.isCanMoveOpponent() && !combination.isCanMoveYourself())
                this.cellFromCellWithTrg=selectCellWithTargets(localView, combination, cardName);
        }

        //means this is a THOR card using Optional1 and/or Optional2 on top of the base effect
        if(!combination.getPlayersWithTargets().isEmpty()){
            if(combination.getEffectsCombination().contains("Optional2"))
                selectPlayerAndThenTargets(combination.getPlayersWithTargets(),2);
            else
                selectPlayerAndThenTargets(combination.getPlayersWithTargets(),1);

        }
    }

    /**
     * this is used when:
     *      - you can move yourself;
     *      - you can move an opponent (distinguishing if it's optional of mandatory);
     *      - vortex/Flamethrower_Optional2, where you must select a cell;
     *      - you must choose a square where you will then select targets on
     *
     * in this.targetsFromCell  you'll add the targets you select in a cell
     *
     * @param combination passing the whole combination reduces the complexity of the method
     * @return cell to move yourself/a Player in
     */
    private NewCell selectCellWithTargets(LocalView localView, SingleEffectsCombinationActions combination, String mode) {
        int maxCell;
        //this evaluates and sets the maximum number of cells to select
        if(combination.getCellsWithTargets().size() < combination.getMaxCellToSelect())
            maxCell=combination.getCellsWithTargets().size();
        else
            maxCell=combination.getMaxCellToSelect();

        ArrayList<CellWithTargets> cellList=combination.getCellsWithTargets();
        CellWithTargets arrivalCell;

        ArrayList<String> stringList;
        ArrayList<CellWithTargets> possibleCells=new ArrayList<>();

        if(mode.equals("MoveYourself")) {
            //adds to a secondary list the cells where you can move
            cellList.forEach(cellWithTargets -> {
                if(cellWithTargets.isCanMoveYourselfHere())
                    possibleCells.add(cellWithTargets);
            });

            //lists as strings the cells for the player to then select one
            stringList=listCellWithTargets(localView,possibleCells);

            arrivalCell = possibleCells.get(stringList.indexOf(this.askUser.stringSelector("Scegli la cella in cui vuoi spostarti",stringList)));
            this.targetsFromCell.addAll(targetSelectionFromCell(arrivalCell));

        } //here we'll divide between the cards that offer opponent's movement as an option or not
        else if(mode.equals("GrenadeLauncher")||mode.equals("Shotgun")||mode.equals("RocketLauncher")){
            if(this.askUser.yesOrNo("Vuoi spostare il player selezionato prima?","Si","No")){

                //adds to a secondary list the cells where you can move the opponent
                cellList.forEach(cellWithTargets -> {
                    if (cellWithTargets.isCanMoveOthersHere())
                        possibleCells.add(cellWithTargets);
                });

                //lists as strings the cells for the player to then select one
                stringList = listCellWithTargets(localView, possibleCells);

                arrivalCell = possibleCells.get(stringList.indexOf(this.askUser.stringSelector("Scegli la cella in cui vuoi spostare il target precendente", stringList)));
                return arrivalCell.getTargetCell();
            }else
                return null;
        }else if((mode.equals("Sledgehammer")||mode.equals("TractorBeam"))){
            //adds to a secondary list the cells where you can move the opponent
            cellList.forEach(cellWithTargets -> {
                if (cellWithTargets.isCanMoveOthersHere())
                    possibleCells.add(cellWithTargets);
            });

            //lists as strings the cells for the player to then select one
            stringList = listCellWithTargets(localView, possibleCells);

            arrivalCell = possibleCells.get(stringList.indexOf(this.askUser.stringSelector("Scegli la cella in cui vuoi spostare il target precendente", stringList)));
            this.targetsFromCell.addAll(targetSelectionFromCell(arrivalCell));

        }else if(mode.equals("VortexCannon")||mode.equals("FlameThrower") && combination.getEffectsCombination().contains("Optional1")){
            //lists as strings all of the cells, for the player to then select one to become a vortex
            stringList=listCellWithTargets(localView,cellList);

            arrivalCell = cellList.get(stringList.indexOf(this.askUser.stringSelector("Scegli la cella per l'effetto",stringList)));
            this.targetsFromCell.addAll(targetSelectionFromCell(arrivalCell));

        }else if(mode.equals("FlameThrower")&&combination.getEffectsCombination().contains("Base")){
            //lists as strings all of the cells, for the player to then select one to choose targets on
            stringList = listCellWithTargets(localView, cellList);
            arrivalCell = cellList.get(stringList.indexOf(this.askUser.stringSelector("Scegli la cella in cui poi selezionare i target da colpire", stringList)));
            arrivalCell.setMaxTargetsInCell(1);
            this.targetsFromCell.addAll(targetSelectionFromCell(arrivalCell));

             if(this.askUser.yesOrNo("vuoi selezionare un secondo target? ", "Si", "No")){
                 //It removes the players with the same position as the one selected before
                 arrivalCell.getTargets().removeIf(p-> p.getFigure().getCell().equals(this.targetsFromCell.get(0).getFigure().getCell()));
                 this.targetsFromCell.addAll(targetSelectionFromCell(arrivalCell));
             }
            return null;
        }else {//it's the case where you must select a cell to then choose one target or more on it
            // (it works for any number of maxTargets). Note that the targets are selected on different cells
            int cont = 0;
            do {
                cont++;
                //lists as strings all of the cells, for the player to then select one to choose targets on
                stringList = listCellWithTargets(localView, cellList);

                arrivalCell = cellList.get(stringList.indexOf(this.askUser.stringSelector("Scegli la cella in cui poi selezionare il " + cont + "° target da colpire", stringList)));
                cellList.remove(arrivalCell); //removes cell already selected
                this.targetsFromCell.addAll(targetSelectionFromCell(arrivalCell));
            } while (maxCell > cont && this.askUser.yesOrNo("vuoi selezionare altri target? " + "\nTarget restanti: " + (maxCell - cont), "Si", "No"));
            return null;
        }
        return arrivalCell.getTargetCell();

    }

    private ArrayList<Player> targetSelectionFromCell(CellWithTargets cellWithTargets){
        ArrayList<Player> targets=new ArrayList<>();

        if(cellWithTargets.getMaxTargetsInCell()==0 && cellWithTargets.getMinTargetsInCell()==0)
            return targets;

        targets.addAll(selectTargets(cellWithTargets.getTargets(),cellWithTargets.getMinTargetsInCell(), cellWithTargets.getMaxTargetsInCell()));

        return targets;
    }

    private ArrayList<String> listCellWithTargets(LocalView localView, ArrayList<CellWithTargets> cells){
        ArrayList<String> stringList=new ArrayList<>();
        cells.forEach(cell -> stringList.add(cellToText(localView,cell.getTargetCell())+"\ncon player: "+listPlayers(cell.getTargets())));
        return stringList;
    }

    /**
     * this method selects one or more targets from a given list of Players
     * @param playersTargetList is the list of targets
     */
    private ArrayList<Player> selectTargets(ArrayList<Player> playersTargetList, int minTargets, int maxTargets) {
        ArrayList<Player> targets=new ArrayList<>();

        //normal selection of 1 or more targets
            if (maxTargets == 1)
                targets.add(selectOneTarget(playersTargetList, "Scegli target da colpire"));
            else
                targets.add(selectOneTarget(playersTargetList, "Scegli il 1° target da colpire"));

            //here we're removing the players already selected
            playersTargetList.remove(targets.get(0));

            //manages cards with 2 minimum targets
            int cont;
            if (minTargets == 2 && !playersTargetList.isEmpty()) {
                targets.add(selectOneTarget(playersTargetList, "Scegli il 2° target da colpire"));
                cont=2;
            }else
                cont = 1;

            while(maxTargets>cont && !playersTargetList.isEmpty() && this.askUser.yesOrNo("vuoi selezionare altri target? "+"\nTarget restanti: "+(maxTargets-cont), "Si", "No")){
                cont++;
                targets.add(selectOneTarget(playersTargetList, "scegli il " + cont + "° target da colpire"));
                playersTargetList.remove(targets.get(targets.size()-1)); //removes the player you just selected to avoid selecting someone twice
            }


        return targets;
    }

    private Player selectOneTarget(ArrayList<Player> playersTargetList,String message){
        ArrayList<String> playerList;

        playerList=listPlayers(playersTargetList); //it lists in a string array list the players by color

        String choice =this.askUser.stringSelector(message, playerList);

        return playersTargetList.get(playerList.indexOf(choice));

    }

    /**
     * selects a room from the list of rooms sent
     */
    private Room selectRoom(ArrayList<Room> targetRooms) {
        ArrayList<String> roomsToSelect=new ArrayList<>();
        for(Room room: targetRooms)
            roomsToSelect.add(room.getColor().toString()+" Room");
        return targetRooms.get(roomsToSelect.indexOf(this.askUser.stringSelector("Scegli la stanza da colpire",roomsToSelect)));
    }

    /**
     * selects one and only one cell from a list of cell passed by parameter
     * @param localView is used for getting the coordinates
     */
    private NewCell selectOneCell(LocalView localView, ArrayList<NewCell> targetCells) {
        ArrayList<String> listedCells=listCells(localView,targetCells);
        return targetCells.get(listedCells.indexOf(this.askUser.stringSelector("Scegli una cella in cui colpire tutti",listedCells)));
    }

    /**
     * THOR manager
     * @param playersWithTargets list of all players with the targets they can see
     * this.targetsFromList1 contains one player, which is the target choosen before
     * adds one target
     */
    private void selectPlayerAndThenTargets(ArrayList<PlayerWithTargets> playersWithTargets, int targetsToAdd) {

        //I'm extracting the previous target with the targetsFromList1 it can see
        PlayerWithTargets target1 = getPlayerWithTargets(playersWithTargets, this.targetsFromList1.get(0));

        //I'm listing the targetsFromList1 it can see to be chosen from
        try {
            this.targetsFromList1.add(selectOneTarget(target1.getTargetsItCanSee(), "scegli il secondo target"));
        }catch (NullPointerException e){
            e.printStackTrace();
        }

        if(targetsToAdd==2){
            try {
                target1 = getPlayerWithTargets(playersWithTargets,this.targetsFromList1.get(1));
                this.targetsFromList1.add(selectOneTarget(target1.getTargetsItCanSee(), "scegli il terzo target"));
            }catch (NullPointerException e){
                e.printStackTrace();
            }
        }

    }

    private PlayerWithTargets getPlayerWithTargets(ArrayList<PlayerWithTargets> list,Player target){
        return list.stream().filter(player -> player.getTarget().equals(target)).findFirst().orElse(null);
    }

    private ArrayList<String> listPlayers(ArrayList<Player> players){
        ArrayList<String> targetString=new ArrayList<>();
        players.forEach(player ->
                targetString.add("Player di colore: " + player.getFigure().getColor().toString()));
        return targetString;
    }

    private ArrayList<String> listCells(LocalView localView, ArrayList<NewCell> targetCells){
        ArrayList<String> listedCells=new ArrayList<>();
        targetCells.forEach(cell ->
                listedCells.add(cellToText(localView, cell)));
        return listedCells;
    }

    private String cellToText(LocalView localView, NewCell cell) {
        return ("Cella in coordinate (visive) ("+localView.getMapView().getXIndex(cell) +", "+localView.getMapView().getYIndex(cell)+")");
    }


    private GunCard cardDiscardSelector(LocalView localView) {
        ArrayList<String> listOfGuns=new ArrayList<>();
        for(GunCard gunCard: localView.getPlayerHand().getGuns())
            listOfGuns.add(gunCard.getClass().getSimpleName());
        String selection=this.askUser.stringSelector("Seleziona la carta da scartare",listOfGuns);

        return localView.getPlayerHand().getGuns()[listOfGuns.indexOf(selection)];
    }

    private GunCard gunCardManager(ArrayList<GunCard> pickableCards) {
        ArrayList<String> listOfGuns=new ArrayList<>();
        for(GunCard gunCard: pickableCards)
            listOfGuns.add(gunCard.getClass().getSimpleName());
        String selection=this.askUser.stringSelector("Seleziona la carta da pescare",listOfGuns);

        return pickableCards.get(listOfGuns.indexOf(selection));
    }

    /**
     * checks if there are no actions available for shoot
     * Cases: shoot with no guns available
     *        shoot without targets
     *        returns false

     * They're evaluated inside FictitiousPlayer
     */
    private boolean checkEmptyActions(AvailableActions actions) {
        for(FictitiousPlayer player: actions.getFictitiousPlayers())
            if(!player.isNoTargets())
                return false;
        return true;
    }

    /**--------------- GETTERS -----------------------*/

    public ArrayList<String> getOrderOfExecution() {
        return orderOfExecution;
    }

    public FictitiousPlayer getFictitiousPlayer() {
        return fictitiousPlayer;
    }

    public GunCard getCardToPick() {
        return cardToPick;
    }

    public GunCard getCardToDiscard() {
        return cardToDiscard;
    }

    public GunCard getChosenGun() {
        return chosenGun;
    }

    public boolean isUseExtra() {
        return useExtra;
    }

    public ArrayList<Player> getTargetsFromList1() {
        return targetsFromList1;
    }

    public NewCell getTargetCell() {
        return targetCell;
    }

    public Room getTargetRoom() {
        return targetRoom;
    }


    public NewCell getCellToMoveOpponent() {
        return cellToMoveOpponent;
    }

    public NewCell getCellToMoveYourself() {
        return cellToMoveYourself;
    }

    public NewCell getCellFromCellWithTrg() {
        return cellFromCellWithTrg;
    }

    public ArrayList<Player> getTargetsFromCell() {
        return targetsFromCell;
    }

    /**
     * this method builds the damage sequences to use to damage players
     * @return damage sequence containing chars repeated numOfDamage times
     */
    public char [] damageSequence(int numOfDamage){
        char [] damage =new char[numOfDamage];
        for(int i=0;i<damage.length;i++)
            damage[i] = this.fictitiousPlayer.getCorrespondingPlayer().getFigure().getColorChar();
        return damage;
    }

}
