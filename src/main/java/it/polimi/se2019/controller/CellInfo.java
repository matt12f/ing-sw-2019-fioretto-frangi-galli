package it.polimi.se2019.controller;

import it.polimi.se2019.model.game.NewCell;

public class CellInfo{
    private NewCell cell;
    private boolean canGrabCard;
    private boolean canGrabAmmo;

    /**
     * useful class used for controls
     * @param arrivalCell
     * @param canGrabCard says if it's possible to grab a card
     * @param canGrabAmmo says if it's possible to grab a drop
     */
    public CellInfo(NewCell arrivalCell, boolean canGrabCard, boolean canGrabAmmo) {
        this.cell = arrivalCell;
        this.canGrabCard = canGrabCard;
        this.canGrabAmmo = canGrabAmmo;
    }

    public NewCell getCell() {
        return cell;
    }

    public boolean isCanGrabCard() {
        return canGrabCard;
    }

    public boolean isCanGrabAmmo() {
        return canGrabAmmo;
    }
}
