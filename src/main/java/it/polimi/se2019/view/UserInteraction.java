package it.polimi.se2019.view;

import it.polimi.se2019.model.cards.GunCard;
import it.polimi.se2019.model.cards.PowerupCard;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class UserInteraction implements Serializable {
    public abstract String actionToRequest(int frenzy,String playerColor);
    public abstract boolean[]cardsToReload(GunCard [] cards, boolean[] reloadableCards);
    public abstract boolean yesOrNo(String message,String textYesButton, String textNoButton);
    public abstract String stringSelector(String message, ArrayList<String> listToChooseFrom);
    public abstract void showMessage(String message);
    public abstract void ammoTileViewer(String content);
    public abstract int[] mapChooser();
    public abstract PowerupCard spawnChooser(PowerupCard[] list, PowerupCard optional);
}
