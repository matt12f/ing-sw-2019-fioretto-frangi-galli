package it.polimi.se2019.view;

import it.polimi.se2019.model.cards.GunCard;

import java.util.ArrayList;

public abstract class UserInteraction {
    public abstract String actionToRequest(int frenzy);
    public abstract boolean[]cardsToReload(GunCard [] cards, boolean[] reloadableCards);
    public abstract boolean yesOrNo(String message,String textYesButton, String textNoButton);
    public abstract String stringSelector(String message, ArrayList<String> listToChooseFrom);
    public abstract void showMessage(String message);
}