package it.polimi.se2019.view;

import it.polimi.se2019.AdrenalineClient;
import it.polimi.se2019.controller.AvailableActions;
import it.polimi.se2019.controller.FictitiousPlayer;
import it.polimi.se2019.controller.SingleCardActions;
import it.polimi.se2019.controller.SingleEffectsCombinationActions;
import it.polimi.se2019.model.cards.GunCard;

import java.util.ArrayList;
import java.util.function.Predicate;

/**
 * this class is going to send back the objects it received to "select" them
 */
public class ChosenActions {
    private UserInteraction askUser;

    private ArrayList<String> orderOfExecution;

    private GunCard cardToPick;
    private GunCard cardToDiscard;



    public ChosenActions(AvailableActions actions) {
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

            //TODO scorrere la singola combinazione degli effetti

        }

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

    public ArrayList<String> getOrderOfExecution() {
        return orderOfExecution;
    }
}
