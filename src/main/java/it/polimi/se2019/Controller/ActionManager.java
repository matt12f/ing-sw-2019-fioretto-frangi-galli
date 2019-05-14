package it.polimi.se2019.controller;

import it.polimi.se2019.model.cards.AmmoTileCard;
import it.polimi.se2019.model.cards.GunCard;
import it.polimi.se2019.model.game.Cell;
import it.polimi.se2019.model.game.DropCell;
import it.polimi.se2019.model.game.Player;
import it.polimi.se2019.model.game.SpawnCell;

public class ActionManager {
    private ShootManager shootManager;

    public ActionManager(){
        this.shootManager=new ShootManager();
    }
    public void actionStream(char[] actions){
        //TODO scrivere metodo

    }
    private void move(Player player){
        //TODO scrivere metodo

    }
    private void grab(Player player, Cell cell){
        //TODO scrivere metodo
        if (cell instanceof DropCell){

        }else if (cell instanceof SpawnCell){

        }
        //NB le celle vuote si riempiono alla fine!!!!
    }
    private void shoot(GunCard weapon){
        //TODO Risolvere la questione associazione arma model - arma controller
        if(weapon.getLoaded()){
            //far partire la action flow dell'arma
        }

    }
    public void reload(GunCard weapon, Player player){

        int red =0, blue =0, yellow =0;

        for (int i = 0; i < weapon.getAmmoCost().length; i++){
            if (weapon.getAmmoCost()[i] == 'r'){
                red++;
            }else if (weapon.getAmmoCost()[i] == 'b'){
                blue++;
            }else if (weapon.getAmmoCost()[i] == 'y'){
                yellow++;
            }
        }

        if (red <= player.getPlayerBoard().getAmmo().getRed() && blue <= player.getPlayerBoard().getAmmo().getBlue()  && yellow<= player.getPlayerBoard().getAmmo().getYellow() ){
            player.getPlayerBoard().getAmmo().setRed(- red);
            player.getPlayerBoard().getAmmo().setBlue(- blue);
            player.getPlayerBoard().getAmmo().setYellow(- yellow);
            weapon.setLoaded(true);

        }//TODO scrivere controllo per powerups?????


    }
    public ShootManager getShootManager(){
            return shootManager;
    }

    /**
     * this method put new ammos in the player's ammo vectors and, if there is one, a powerup card,
     * controlling if it's possible
     * @param drop
     */
    public void dropManager(AmmoTileCard drop){

    }

    /**
     * this method allow to put a weapon from the spawn slot to the player's hand,
     * including the controlling cycle about the weapon player's hand
     */
    public void spawnDropManager(GunCard weapon){

    }
}
