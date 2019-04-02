package it.polimi.se2019.database;

public abstract class GunCard {
    protected   char [] ammoCost;
    protected int id;
    public GunCard(){

    }

    public int getId() {
        return id;
    }

    public char[] getAmmoCost() {
        return ammoCost;
    }
}