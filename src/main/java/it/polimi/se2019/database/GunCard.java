package it.polimi.se2019.database;

public abstract class GunCard {
    protected   char [] ammoCost;
    //TODO rimuovere i riferimenti agli ID nelle classi delle carte!!
    protected int id;

    public GunCard(){

    }
    public char[] getAmmoCost() {
        return ammoCost;
    }
}