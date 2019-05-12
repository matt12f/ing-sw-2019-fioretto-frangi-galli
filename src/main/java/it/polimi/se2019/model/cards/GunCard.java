package it.polimi.se2019.model.cards;

public abstract class GunCard {
    protected char [] ammoCost;
    protected String description;
    private boolean loaded;

    public GunCard(){
    }

    public char[] getAmmoCost() {
        return ammoCost;
    }
    public String getDescription() {
        return description;
    }
    public boolean getLoaded() {
        return loaded;
    }
    public void setLoaded(boolean load){
        this.loaded = load;
    }
}