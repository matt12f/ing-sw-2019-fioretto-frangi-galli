package it.polimi.se2019.view;

import it.polimi.se2019.enums.CardName;

public class CardTileView {
    private String image;
    private CardName cardName;

    public String getImage() {
        return image;
    }

    public CardName getCardName() {
        return cardName;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setCardName(CardName cardName) {
        this.cardName = cardName;
    }
}
