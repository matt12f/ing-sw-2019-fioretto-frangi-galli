package it.polimi.se2019.view;

import it.polimi.se2019.model.cards.GunCard;

public abstract class UserInteraction {
    public abstract String actionToRequest(int frenzy);
    public abstract boolean[]cardsToReload(GunCard [] cards, boolean[] reloadableCards);

}
