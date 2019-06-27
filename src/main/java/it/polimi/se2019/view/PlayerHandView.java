package it.polimi.se2019.view;


public abstract class PlayerHandView{
    private CardTileView[] guns;
    private boolean[] loadedGuns;
    private CardTileView[] powerups;

    public void setWeapons(CardTileView[] weapons) {
        this.guns = weapons;
    }

    public CardTileView[] getWeapons() {
        return guns;
    }

    public CardTileView[] getPowerups() {
        return powerups;
    }

    public void setPowerups(CardTileView[] powerups) {
        this.powerups = powerups;
    }
}
