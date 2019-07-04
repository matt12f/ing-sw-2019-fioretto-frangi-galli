package it.polimi.se2019.model.cards;

import java.io.Serializable;

public class PowerupCard implements Serializable {
    private String powerupType;
    private char cubeColor;
    /**
     * Available Types: Targetting Scope, Newton, Tagback Grenade, Teleporter
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
}