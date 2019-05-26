package it.polimi.se2019.controller;

import it.polimi.se2019.model.cards.GunCard;

import java.util.ArrayList;

/**
 * This class builds the message object that the ActionManager in Controller builds and sends over to the Client
 * to choose from
 *
 * In the Normal 1 combination (move, move, move) you will only use the arrivalCell object
 *
 * In the Normal 2 combination (move,(move) grab) you will only use the arrivalCell object
 *
 * In the Normal 3 combination ((move), shoot) you will use the arrivalCell object and an object for each card
 * in your hand that indicates if you can use it and in which way
 *
 * The client will then build an "answer object" containing his selection
 */

public class AvailableActions {
    private DestinationCell arrivalCell; //for move and grab actions
    private ArrayList<SingleCardActions> cardActions; //for the actions you can do with one card
    private ArrayList<GunCard> usableCards;

    public AvailableActions() {
        DestinationCell arrivalCell=new DestinationCell(); //TODO calcolo celle raggiungibili
        ArrayList<GunCard> usableCards=new ArrayList<>(); //TODO verifica delle carte utilizzabili
        ArrayList<SingleCardActions> cardActions =new ArrayList<>(); //TODO verifica azioni disponibili con le corrispondenti carte
        this.arrivalCell = arrivalCell;
        this.cardActions = cardActions;
        this.usableCards=usableCards;
    }
}