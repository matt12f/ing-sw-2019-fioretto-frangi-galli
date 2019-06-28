package it.polimi.se2019.view;

import it.polimi.se2019.AdrenalineClient;
import it.polimi.se2019.controller.AvailableActions;
import it.polimi.se2019.controller.FictitiousPlayer;
import it.polimi.se2019.controller.SingleCardActions;
import it.polimi.se2019.controller.SingleEffectsCombinationActions;
import it.polimi.se2019.model.cards.GunCard;
import it.polimi.se2019.model.game.NewCell;

import java.util.ArrayList;
import java.util.function.Predicate;

/**
 * this class is going to send back the objects it received to "select" them
 */
public class ChosenActions {
    private ArrayList<String> orderOfExecution;

    private GunCard cardToPick;
    private GunCard cardToDiscard;


    public ChosenActions(AvailableActions actions) {
        //Here we'll build the list of arrival cells (that correspond to their Fictitious Players)
        ArrayList<NewCell> arrivalCells=new ArrayList<>();
        for(FictitiousPlayer fictitiousPlayer:actions.getFictitiousPlayers())
            arrivalCells.add(fictitiousPlayer.getPosition());

        //TODO rendere cliccabili queste celle e
        //TODO scelta di una(questa sotto è una toppa temporanea) l'indice dovrà essere lo stesso però
        NewCell chosenArrivalCell=arrivalCells.get(0);
        FictitiousPlayer choice= actions.getFictitiousPlayers().get(0);


        //Section for grab/move + grab action picking Ammo/GUns
        if(choice.getAvailableCardActions().isEmpty()) { //this is a grab/move + grab action picking Ammo
            if(choice.isGrabbedAmmo()){
                String ammoGrab= ("Raccoglierai: "+translatorOfAmmo(choice.getPosition().getDrop().getContent()));
                //TODO mostrare stringa in GUI/CLI
            }
            if(!choice.getPickableCards().isEmpty()){ //in case this is a grab/move + grab action picking a Gun
                cardToPick = gunCardManager(choice.getPickableCards());
                if(AdrenalineClient.getLocalView().getPlayerHand().isGunHandFull())
                    this.cardToDiscard=cardDiscardSelector();
            }

        }
        else{
            ArrayList<String> listOfCards=new ArrayList<>();
            ArrayList<String> listOfActionforCards=new ArrayList<>();

            //removes cards that have no available combinations
            Predicate<SingleCardActions> cardActionsPredicate= p-> p.getAvailableCombinations().isEmpty();
            choice.getAvailableCardActions().removeIf(cardActionsPredicate);

            for(SingleCardActions cardActions: choice.getAvailableCardActions()){ //this card is valid to be used
                    listOfCards.add("Usable card: " + cardActions.getUsableGunCardName() + "; Must swap to use it: " + cardActions.isMustSwap());
                    listOfActionforCards.add(cardActions.getAvailableCombinations().toString()); //shows all of the combinations for one card
            }
            //TODO far scegliere al player una di queste carte (mostrando sia la lista delle carte che le azioni
            // ad essa associate e prelevare l'oggetto corrispondente
            String cardSelected = listOfCards.get(0); //TODO qui e sotto l'indice 0 è quello dell'oggetto scelto
            SingleCardActions chosenCard = choice.getAvailableCardActions().get(0);

            if(chosenCard.isMustSwap())
                this.cardToDiscard=cardDiscardSelector();

            //TODO elencare tutti gli elementi di chosenCard.getAvailableCombinations() e farne scegliere uno
            //TODO qui sotto 0 è l'indice dell'elemento scelto
            SingleEffectsCombinationActions combinationActions=chosenCard.getEffectsCombinationActions().get(0);


        }

    }

    private GunCard cardDiscardSelector() {
        //TODO mostrare le carte della sua hand, per sceglierne una da scartare
        return AdrenalineClient.getLocalView().getPlayerHand().getGuns()[1];
    }

    private GunCard gunCardManager(ArrayList<GunCard> pickableCards) {
        //TODO mostra a video l'elenco delle carte nell'array pickableCards e ritorna la carta scelta dall'utente
        return null;
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
