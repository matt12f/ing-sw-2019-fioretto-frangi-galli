package it.polimi.se2019.controller;

import it.polimi.se2019.AdrenalineServer;
import it.polimi.se2019.controller.weapon_managers.*;
import it.polimi.se2019.enums.ActionType;
import it.polimi.se2019.enums.CellType;
import it.polimi.se2019.model.cards.*;
import it.polimi.se2019.model.game.*;

public class ActionManager {
    private ShootManager shootManager;

    public ActionManager(){
        this.shootManager=new ShootManager();
    }
    public void actionStream(ActionType actions){
        //TODO scrivere metodo
        switch(actions){
            case NORMAL1: normal1Manager();break;
            case NORMAL2: normal2Manager();break;
            case NORMAL3: normal3Manager();break;
            case FRENZY1: frenzy1Manager();break;
            case FRENZY2: frenzy2Manager();break;
            case FRENZY3: frenzy3Manager();break;
            case FRENZY4: frenzy4Manager();break;
            case FRENZY5: frenzy5Manager();break;
        }

    }
    private void move(Player player){
        //TODO scrivere metodo

    }
    private void grab(Player player, NewCell cell){
        //TODO scrivere metodo
        if (cell.getCellType().equals(CellType.DROP)){

        }else if (cell.getCellType().equals(CellType.SPAWN)){

        }
        //NB le celle vuote si riempiono alla fine!!!!
    }
    private void shoot(GunCard weapon){
        //TODO Risolvere la questione associazione arma model - arma controller
        if(weapon.getLoaded()){
            String weaponclass = weapon.getClass().toString();
            //far partire la action flow dell'arma
            switch (weaponclass){
                case "Cyberblade":
                    CyberbladeManager.action();break;
                case "Electroscytthe":
                    ElectroscytheManager.action();break;
                case "FlameThrower":
                    FlamethrowerManager.action();break;
                case "Furnace":
                    FurnaceManager.action();break;
                case "GrenadeLauncher":
                    GrenadeLauncherManager.action();break;
                case "Heatseeker":
                    HeatseekerManager.action();break;
                case "Hellion":
                    HellionManager.action();break;
                case "LockRifle":
                    LockRifleManager.action();break;
                case "MachineGun":
                    MachinegunManager.action();break;
                case "PlasmaGun":
                    PlasmaGunManager.action();break;
                case "PowerGlove":
                    PowergloveManager.action();break;
                case "Railgun":
                    RailgunManager.action();break;
                case "RocketLauncher":
                    RocketLauncherManager.action();break;
                case "Shockwave":
                    ShockwaveManager.action();break;
                case "Shotgun":
                    ShotgunManager.action();break;
                case "Sledgehammer":
                    SledgehammerManager.action();break;
                case "Thor":
                    ThorManager.action();break;
                case "TractorBeam":
                    TractorBeamManager.action();break;
                case "VortexCannon":
                    VortexCannonManager.action();break;
                case "Whisper":
                    WhisperManager.action();break;
                case "Zx2":
                    Zx2Manager.action();break;

            }

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
    private void normal1Manager(){
        //mmm
        boolean stop =false;
        do{
            move(AdrenalineServer.getMainController().getActiveturn().getActivePlayer());
        }while(!stop);
    }
    private void normal2Manager(){
        boolean stop =false;
        if(AdrenalineServer.getMainController().getActiveturn().getActivePlayer().getPlayerBoard().getActionTileNormal().getAdrenalineMode1()==false){
            //mg
            move(AdrenalineServer.getMainController().getActiveturn().getActivePlayer());
            grab(AdrenalineServer.getMainController().getActiveturn().getActivePlayer(), AdrenalineServer.getMainController().getActiveturn().getActivePlayer().getFigure().getCell());
        }else {
            //mmg
            do{
                move(AdrenalineServer.getMainController().getActiveturn().getActivePlayer());
            }while(!stop);
        }

    }
    private void normal3Manager(){
        if(AdrenalineServer.getMainController().getActiveturn().getActivePlayer().getPlayerBoard().getActionTileNormal().getAdrenalineMode2()==false){
            //s
        }else {
            //ms
            move(AdrenalineServer.getMainController().getActiveturn().getActivePlayer());
        }
    }
    private void frenzy1Manager(){

    }
    private void frenzy2Manager(){

    }
    private void frenzy3Manager(){

    }
    private void frenzy4Manager(){

    }
    private void frenzy5Manager(){

    }
}
