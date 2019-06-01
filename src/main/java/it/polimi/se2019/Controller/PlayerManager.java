package it.polimi.se2019.controller;

import it.polimi.se2019.AdrenalineServer;
import it.polimi.se2019.model.cards.GunCard;
import it.polimi.se2019.model.cards.PowerupCard;
import it.polimi.se2019.model.game.NewCell;
import it.polimi.se2019.model.game.Player;


public class PlayerManager {

    public void damageDealer(Player player, char[] damage){

        int markNumber = player.getPlayerBoard().getDamageTrack().checkMarks(damage[0]);

        for (char dam:damage){
            if (isAlive(player))
                player.getPlayerBoard().getDamageTrack().addDamage(dam);
            else{
                //TODO setkill
                //return eccezione morte
            }

        }
        for (int k=0;k<markNumber;k++)
            if (isAlive(player))
                player.getPlayerBoard().getDamageTrack().addDamage(damage[0]);
    }

    public static boolean isAlive(Player player){
        return (player.getPlayerBoard().getDamageTrack().getDamage().length<12);
    }

   public void markerManager(Player player, char[] add) {
        for (char mark:add)
            player.getPlayerBoard().getDamageTrack().addMark(mark);
    }


    public void adrenalineManager(Player player){
        if(player.getPlayerBoard().getDamageTrack().getDamage().length >= 3){
            player.getPlayerBoard().getActionTileNormal().setAdrenalineMode1(true);
        }else if(player.getPlayerBoard().getDamageTrack().getDamage().length >= 6){
            player.getPlayerBoard().getActionTileNormal().setAdrenalineMode1(false);
        }
    }


    //TODO rivedere in funzione del nuovo modo di gestione delle scelte dell'utente
    public void gunHandManager(Player player, int pick,NewCell cell){
        GunCard [] temp = player.getPlayerBoard().getHand().getGuns();

            if(temp[0] == null || temp[1] == null || temp[2] == null){
                //TODO scrivere processo di richiesta all'utente, di quale arma vuole scartare
                //richiesta all'utente di quale arma vuole scartare
                //attesa notify da view che ritorn un integer
                //scambio delle carte selezionate

                //toppa momentanea dello scambio carte


               // player.getPlayerBoard().getHand().substitutionGunCard(temp[0],((SpawnCell) cell).pickItem(pick) );
                //(cell).setItem(temp[0]);

            }else{

                //take the first empty space in the player's hand and put the weapon in it

                if (temp[0] == null){
                    temp[0]=  cell.pickItem(pick);
                }else  if (temp[1] == null){
                    temp[1]= cell.pickItem(pick);
                }else if (temp[2] == null){
                    temp[2]=  cell.pickItem(pick);
                }
            }
    }


    public void powerupHandManager(Player player, PowerupCard powerup){
        //TODO scrivere metodo

    }



    public boolean ammoManager(Player player, int [] ammo ){
        boolean enoughAmmo = true;
        /**
         * viene inviato un vettore di int , l'ordine è blue - red - yellow
         */

            if (ammo [0] > player.getPlayerBoard().getAmmo().getBlue()){
                enoughAmmo = false;
            }else if (ammo [1] > player.getPlayerBoard().getAmmo().getRed()){
                enoughAmmo = false;
            }else if (ammo [2] > player.getPlayerBoard().getAmmo().getYellow()){
            enoughAmmo = false;
            }else{
                return enoughAmmo;
            }

        //TODO altrimenti verifica se può pagare con un powerup


        return enoughAmmo;
    }



    public void frenzyManager(){
        //TODO scrivere metodo
        AdrenalineServer.getMainController().getMainGameModel().activateFinalFrenzy(AdrenalineServer.getMainController().getActiveturn().getActivePlayer().getId());
    }
}
