package it.polimi.se2019.view;

import it.polimi.se2019.AdrenalineClient;
import it.polimi.se2019.controller.*;
import it.polimi.se2019.exceptions.NoActionsException;
import it.polimi.se2019.model.cards.GunCard;
import it.polimi.se2019.model.game.NewCell;
import it.polimi.se2019.model.game.Player;
import it.polimi.se2019.model.game.Room;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.function.Predicate;

/**
 * this class is going to send back the objects it received to "select" them
 */
public class ChosenActions implements Serializable {
    private UserInteraction askUser;

    private ArrayList<String> orderOfExecution;

    private GunCard cardToPick;
    private GunCard cardToDiscard;

    private boolean useExtra;

    private ArrayList<Player> targets;
    private NewCell targetCell;
    private Room targetRoom;
    private ArrayList<NewCell> selectedCells;
    private ArrayList<Player> targetsInOrder;

    /**
     *
     * @param actions is the package of available actions sent by the controller unit on the server
     * @throws NoActionsException if there are no actions to be performed
     */
    public ChosenActions(AvailableActions actions) throws NoActionsException {
        if(checkEmptyActions(actions))
            throw new NoActionsException("no actions available, try again");

        if(AdrenalineClient.isGUI())
            this.askUser=new UserInteractionGUI();
        else
            this.askUser=new UserInteractionCLI();
        LocalView localView= AdrenalineClient.getLocalView();

        //Section for selection of cells where the fictitious player will be
        ArrayList<String> arrivalCellsIndex=new ArrayList<>();
        for(FictitiousPlayer fictitiousPlayer:actions.getFictitiousPlayers())
            arrivalCellsIndex.add(actions.getFictitiousPlayers().indexOf(fictitiousPlayer)+". Cell in position ("+localView.getMapView().getXIndex(fictitiousPlayer.getPosition()) +", "+localView.getMapView().getYIndex(fictitiousPlayer.getPosition())+")");

        String chosenArrivalCell=this.askUser.stringSelector("Scegi una cella dove vuoi spostarti",arrivalCellsIndex);
        FictitiousPlayer choice= actions.getFictitiousPlayers().get(arrivalCellsIndex.indexOf(chosenArrivalCell));

        //Section for grab/move + grab action picking Ammo/Guns
        if(choice.getAvailableCardActions().isEmpty()) { //this is a grab/move + grab action picking Ammo
            if(choice.isGrabbedAmmo())
                this.askUser.showMessage("Raccoglierai: "+translatorOfAmmo(choice.getPosition().getDrop().getContent()));

            if(!choice.getPickableCards().isEmpty()){ //in case this is a grab/move + grab action picking a Gun
                cardToPick = gunCardManager(choice.getPickableCards());
                if(localView.getPlayerHand().isGunHandFull())
                    this.cardToDiscard = cardDiscardSelector(localView);
            }

        }
        else //Section for actions that include shooting
        {
            ArrayList<String> listOfCards=new ArrayList<>();

            //removes cards that have no available combinations
            Predicate<SingleCardActions> cardActionsPredicate= p-> p.getAvailableCombinations().isEmpty();
            choice.getAvailableCardActions().removeIf(cardActionsPredicate);

            for(SingleCardActions cardActions: choice.getAvailableCardActions()) //this card is valid to be used
                    listOfCards.add("Usable card: " + cardActions.getUsableGunCardName() + "; Must swap to use it: " + cardActions.isMustSwap());


            //asks the user which card it wants to use, listing the combination of effects it can perform
            boolean finalDecision;
            String cardSelected;
            do {
                cardSelected = this.askUser.stringSelector("Seleziona l'arma che vuoi usare", listOfCards);
                finalDecision = this.askUser.yesOrNo("Questa carta ha le seguenti combinazioni degli effetti: "+ choice.getAvailableCardActions().get(listOfCards.indexOf(cardSelected)).getAvailableCombinations().toString(),"Prosegui","Seleziona un'altra carta");
            }while (!finalDecision);

            //extracts the single card chosen by the player
            SingleCardActions chosenCard = choice.getAvailableCardActions().get(listOfCards.indexOf(cardSelected));

            if(chosenCard.isMustSwap())
                this.cardToDiscard=cardDiscardSelector(localView);

            //choice of the action combination
            String chosenCombination=this.askUser.stringSelector("Scegliere una combinazione di effetti",chosenCard.getAvailableCombinations());
            SingleEffectsCombinationActions combinationActions=chosenCard.getEffectsCombinationActions().get(chosenCard.getAvailableCombinations().indexOf(chosenCombination));

            selectActions(combinationActions);
        }

    }


    private void selectActions(SingleEffectsCombinationActions combination){

        this.orderOfExecution=combination.getEffectsCombination();

        this.targets=new ArrayList<>();

        if(combination.isOfferableExtra())
            this.useExtra=this.askUser.yesOrNo("vuoi usare la parte extra dell'effetto?","Si", "No");

        //TODO check carte con selezione target samelist different target

        if(!combination.getPlayersTargetList().isEmpty())
            this.targets.addAll(selectTargets(combination.getPlayersTargetList(), combination.getMaxNumPlayerTargets(), null));
        if(combination.isSameListDifferentTarget())
            this.targets.addAll(selectTargets(combination.getPlayersTargetList(),  1, this.targets));

        if(!combination.getTargetRooms().isEmpty())
            this.targetRoom = selectRoom(combination.getTargetRooms());


        if(!combination.getTargetCells().isEmpty())
            this.targetCell=selectCell(combination.getTargetCells(), 1, 1);


        //TODO rivedere meglio (considerare movimenti propri e altrui)
        if(!combination.getCellsWithTargets().isEmpty()) {
            this.selectedCells.addAll(selectCellSpecial(combination.getCellsWithTargets(),combination.getMinCellToSelect(),combination.getMaxCellToSelect()));
            this.targets.addAll(selectCellAndThenTargets(this.selectedCells, combination.isCanMoveYourself(), combination.isCanMoveOpponent()));
        }

        //means this is a THOR card using Optional1 and/or Optional2 on top of the base effect
        if(!combination.getPlayersWithTargets().isEmpty()){
            this.targetsInOrder=new ArrayList<>(Player.duplicateList(this.targets)); //this.targets must contain only one target here
            this.targets.clear();
            if(combination.getEffectsCombination().contains("Optional2"))
                selectPlayerAndThenTargets(combination.getPlayersWithTargets(),this.targetsInOrder,2);
            else
                selectPlayerAndThenTargets(combination.getPlayersWithTargets(),this.targetsInOrder,1);

        }
    }


    private ArrayList<Player> selectTargets(ArrayList<Player> playersTargetList, int maxTargets, ArrayList<Player> previousTargets) {
        ArrayList<Player> targets=new ArrayList<>();

        if(previousTargets!=null) //selection of target different from the previous list
        {
            ArrayList<Player> availTargets = new ArrayList<>(Player.duplicateList(playersTargetList));
            availTargets.removeAll(previousTargets);
            targets.add(selectOneTarget(availTargets, "Scegli target da colpire (sarà diverso dal precendente)"));
        }
        else { //normal selection of 1 or more targets
            if (maxTargets == 1)
                targets.add(selectOneTarget(playersTargetList, "Scegli target da colpire"));
            else
                targets.add(selectOneTarget(playersTargetList, "Scegli il 1° target da colpire"));

            //here we're removing the players already selected
            playersTargetList.remove(targets.get(0));

            int cont = 1;
            do {
                cont++;
                targets.add(selectOneTarget(playersTargetList, "scegli il " + cont + "° target da colpire"));
            } while (maxTargets > cont);

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


    private NewCell selectCell(ArrayList<NewCell> targetCells, int minCellToSelect, int maxCellToSelect) {
        //TODO scrivere metodo
        return null;
    }

    private ArrayList<Player> selectCellAndThenTargets(ArrayList<NewCell> selectedCells, boolean canMoveYourself, boolean canMoveOpponent) {
        //TODO scrivere metodo
        return null;
    }

    private ArrayList<NewCell> selectCellSpecial(ArrayList<CellWithTargets> cellsWithTargets, int minCellToSelect, int maxCellToSelect) {
        //TODO scrivere metodo
        return null;
    }

    /**
     *
     * @param playersWithTargets listo of all players with the targets they can see
     * @param targetList contains one player, which is the target choosen before
     * @return one target
     */
    private void selectPlayerAndThenTargets(ArrayList<PlayerWithTargets> playersWithTargets,ArrayList<Player> targetList,int targetsToAdd) {

        //I'm extracting the previous target with the targets it can see
        PlayerWithTargets target1 = getPlayerWithTargets(playersWithTargets,targetList.get(0));

        //I'm listing the targets it can see to be chosen from
        try {
            targetList.add(selectOneTarget(target1.getTargetsItCanSee(), "scegli il secondo target"));
        }catch (NullPointerException e){
            e.printStackTrace();
        }

        if(targetsToAdd==2){
            try {
                target1 = getPlayerWithTargets(playersWithTargets,targetList.get(1));
                targetList.add(selectOneTarget(target1.getTargetsItCanSee(), "scegli il terzo target"));
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
                    targetString.add("Player of color: " + player.getFigure().getColor().toString()));
        return targetString;
    }


    /**
     * checks if there are no actions available
     * Cases: shoot with no guns available
     *        shoot without targets
     *
     * They're evaluated inside FictitiousPlayer
     */
    private boolean checkEmptyActions(AvailableActions actions) {
        for(FictitiousPlayer player: actions.getFictitiousPlayers())
           if(!player.isNoTargets())
               return false;
           return true;
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
     * Method to easily visualize AmmoTiles content
     */
    private String translatorOfAmmo(String sequence){
        StringBuilder builder=new StringBuilder();
        for (int i = 0; i < sequence.length(); i++)
            switch (sequence.charAt(i)){
                case 'y':builder.append("yellow Ammo");break;
                case 'b':builder.append("Blue Ammo");break;
                case 'r': builder.append("Red Ammo");break;
                case 'p':builder.append("PowerUp");break;
                default:break;
            }
        return builder.toString();
    }


    /**--------------- GETTERS -----------------------*/

    public ArrayList<String> getOrderOfExecution() {
        return orderOfExecution;
    }

    public UserInteraction getAskUser() {
        return askUser;
    }

    public GunCard getCardToPick() {
        return cardToPick;
    }

    public GunCard getCardToDiscard() {
        return cardToDiscard;
    }

    public boolean isUseExtra() {
        return useExtra;
    }

    public ArrayList<Player> getTargets() {
        return targets;
    }

    public NewCell getTargetCell() {
        return targetCell;
    }

    public Room getTargetRoom() {
        return targetRoom;
    }

    public ArrayList<NewCell> getSelectedCells() {
        return selectedCells;
    }

    public ArrayList<Player> getTargetsInOrder() {
        return targetsInOrder;
    }
}
