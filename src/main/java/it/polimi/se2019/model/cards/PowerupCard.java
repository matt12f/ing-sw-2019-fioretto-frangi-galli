package it.polimi.se2019.model.cards;


import java.io.Serializable;

public class PowerupCard implements Serializable {
    private String powerupType;
    private char cubeColor;
    /**
     * Available Types: TargettingScope, Newton, TagbackGrenade, Teleporter
     */
    /**
     *
     * @param type
     * @param color
     */
    public PowerupCard(String type, char color) {
        this.cubeColor=color;
        this.powerupType=type;
    }

    public String getPowerupType() {
        return this.powerupType;
    }

    public char getCubeColor() {
        return this.cubeColor;
    }

    @Override
    public PowerupCard clone(){
        return new PowerupCard(this.powerupType,this.cubeColor);
    }

    @Override
    public boolean equals(Object obj){
        if(obj == null)
            return false;
        PowerupCard powerUp = (PowerupCard) obj;
        return this.getClass().isAssignableFrom(powerUp.getClass()) && this.cubeColor == powerUp.cubeColor && this.powerupType.equals(powerUp.getPowerupType());
    }

}