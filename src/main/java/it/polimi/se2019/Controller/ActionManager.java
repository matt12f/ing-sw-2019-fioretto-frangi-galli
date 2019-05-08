package it.polimi.se2019.controller;

import it.polimi.se2019.model.cards.GunCard;

public class ActionManager {
    private static ShootManager shootManager;

    public ActionManager(){
        this.shootManager=new ShootManager();
    }
    public void actionStream(char[] actions){
        //TODO scrivere metodo

    }
    private void move(){
        //TODO scrivere metodo

    }
    private void grab(){
        //TODO scrivere metodo

    }
    private void shoot(GunCard weapon){
        //TODO scrivere metodo

    }
    public void reload(GunCard weapon){
        //TODO scrivere metodo

    }
    public static ShootManager getShootManager(){
            return shootManager;
    }
}
