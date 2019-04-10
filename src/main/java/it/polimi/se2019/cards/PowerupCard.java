package it.polimi.se2019.cards;

public class PowerupCard {
    private String powerupType;
    private char cubeColor;
    /**
     * Default constructor
     */
    public PowerupCard(String type, char color) {
        this.cubeColor=color;
        this.powerupType=type;
    }

    public PowerupCard( PowerupCard card){
        this.powerupType = card.getPowerupType();
        this.cubeColor = card.getCubeColor();
    }
    /**
     * @return
     */
    public String getPowerupType() {
        return this.powerupType;
    }

    /**
     * @return
     */
    public char getCubeColor() {
        return this.cubeColor;
    }

    public void doAction() {
    }

}