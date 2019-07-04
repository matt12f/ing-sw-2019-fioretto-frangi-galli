package it.polimi.se2019.network;

import it.polimi.se2019.controller.*;
import it.polimi.se2019.enums.Color;
import it.polimi.se2019.enums.Status;
import it.polimi.se2019.exceptions.FullException;
import it.polimi.se2019.model.cards.PowerupCard;
import it.polimi.se2019.model.game.Player;
import it.polimi.se2019.view.ChosenActions;
import it.polimi.se2019.view.LocalView;
import it.polimi.se2019.view.PowerupUse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GameHandler implements Runnable {
    private static final Logger LOGGER = Logger.getLogger(GameHandler.class.getName());
    private ArrayList<ClientHandler> players;
    private Controller controller;
    private int mapNumber;
    private int skullsNumber;

    private boolean useTagBack;

    public Controller getController() {
        return controller;
    }

    private void shuffleClient(ArrayList<ClientHandler> clients){
        Collections.shuffle(clients);
        this.players = clients;
    }

    private Color fromIntToColor(int num){
        switch (num) {
            case 0:
                return Color.YELLOW;
            case 1:
                return Color.WHITE;
            case 2:
                return Color.GREEN;
            case 3:
                return Color.BLUE;
            case 4:
                return Color.VIOLET;
            default:
                return Color.RED;
        }
    }

    private void setClientColor(){
        ArrayList<Color> colors = new ArrayList<>();
        for (int  i = 0; i < this.players.size(); i++) {
            colors.add(fromIntToColor(i));
        }
        Collections.shuffle(colors);
        for (int j = 0; j < this.players.size(); j++) {
            this.players.get(j).setColor(colors.get(j));
        }
    }

    @Override
    public void run() {
        ClientHandler clientTurn;
        //scelta rotazione e assegnamento pedina
        setClientColor();
        for (ClientHandler player: players) {
            player.setGame(this);
        }
        shuffleClient(this.players);
        notifyGameHandlerStart();
        try {
            getMapSkull(this.players.get(0));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        createController();
        for (ClientHandler player: this.players) {
            player.setLocalView(new LocalView(this.players.indexOf(player), this.controller.getRemoteView()));
            this.controller.getRemoteView().addObserver(player.getLocalView());
            notifyView(player);
        }
        for (ClientHandler c: players) {
            try {
                PlayerManager.getCardsToSpawn(true, this.controller, c.getLocalView().getPlayerId());
            } catch (FullException e) {
                LOGGER.log(Level.FINE,"1st exception",e);
            }
            c.setStatus(Status.SPAWN);
            try {
                waitForSpawn(c);
            } catch (InterruptedException e) {
                LOGGER.log(Level.FINE,"2nd exception",e);
            }
            PlayerManager.spawnPlayers(this.controller, c.getLocalView().getPlayerId(), c.getSpawn());
            for (ClientHandler player: players) {
                player.setStatus(Status.VIEW);
                notifyView(player);
            }
        }
        setStart();
        //gestione dei turni
        while (this.controller.getMainGameModel().getKillshotTrack().getSkulls() > 0){
            turnPreparation(this.controller.getMainGameModel().getTurn());
            clientTurn = this.players.get(this.controller.getMainGameModel().getTurn());
            for(int i = 0; i<controller.getActiveTurn().getActivePlayer().getPlayerBoard().getActionTileNormal().getActionCounter(); i++) {
                waitingRequest(clientTurn);
                calculateActions(clientTurn);
                waitingRequest(clientTurn);
                PlayerManager.choiceExecutor(controller, clientTurn.getChosenAction());
                notifyView(clientTurn);

                //rileggi il codice
                for (ClientHandler client : players) {
                    client.setDeadsPlayer(this.controller.getMainGameModel().getDeadPlayers().size());
                }
                for (Player player: this.controller.getMainGameModel().getDeadPlayers()) {
                    for (ClientHandler client: this.players) {
                        if(player.getNickname().equals(client.getNickname())){
                            client.setStatus(Status.DEAD);
                            try {
                                 PlayerManager.getCardsToSpawn(false, controller, player.getId());
                                 waitForReSpawn(client);
                                 PlayerManager.spawnPlayers(controller, client.getLocalView().getPlayerId(), client.getSpawn());
                                 setSpawn(client);
                            } catch (FullException e) {
                                LOGGER.log(Level.FINE,"3rd exception",e);
                            }

                        }
                    }
                    for (ClientHandler client: players) {
                        try {
                            client.sendLocalView();
                        } catch (IOException e) {
                            LOGGER.log(Level.FINE,"4th exception",e);
                        }
                    }
                }
                MapManager.refillEmptiedCells(controller.getMainGameModel().getCurrentMap().getBoardMatrix(),controller.getMainGameModel().getCurrentDecks());
                if (this.controller.getMainGameModel().getKillshotTrack().getSkulls() == 0)
                    break;
            }
            clientTurn.setStatus(Status.NOTMYTURN); //valuta se magari ripassare da UPDATE piuttosto
            controller.getActiveTurn().nextTurn(controller);
            controller.getMainGameModel().getDeadPlayers().removeAll(controller.getMainGameModel().getDeadPlayers());
            PlayerManager.scoringProcess(controller);
        }
        TurnManager.frenzyActivator(controller);
        for (int j = 0; j<players.size(); j++){
            clientTurn = players.get(this.controller.getMainGameModel().getTurn());
            turnPreparation(this.controller.getMainGameModel().getTurn());
            for (int i = 0; i < controller.getActiveTurn().getActivePlayer().getPlayerBoard().getActionTileFrenzy().getActionCounter(); i ++) {
                waitingRequest(clientTurn);
                calculateActions(clientTurn);
                waitingRequest(clientTurn);
                ChosenActions chosenActions = clientTurn.getChosenAction();
                managePowerUps(PlayerManager.choiceExecutor(controller, chosenActions));
                notifyView(clientTurn);
            }
            clientTurn.setStatus(Status.NOTMYTURN);
            controller.getActiveTurn().nextTurn(controller);
            MapManager.refillEmptiedCells(controller.getMainGameModel().getCurrentMap().getBoardMatrix(),controller.getMainGameModel().getCurrentDecks());
        }
        for (ClientHandler player: players) {
            player.setStatus(Status.ENDGAME);
        }
        PlayerManager.scoringProcess(controller); //last scoring of the game (after the final frenzy round)
        winnerIs();
    }

    /**
     * this method manages powerups tagback grenade and targetting scope
     * @param playersHit are the players that received damage during this turn
     */
    private void managePowerUps(ArrayList<Player> playersHit) {
        if(!playersHit.isEmpty()){

            Player activePlayer=this.controller.getActiveTurn().getActivePlayer();

            //offering tag back grenade to players hit
            for(Player player:playersHit){
                //check if the active player (the one that hit others) is visible from their position
                if(!ActionManager.visibleTargets(this.controller,player.getFigure().getCell()).contains(activePlayer)){

                    for(int i=0;i< player.getPlayerBoard().getHand().getPowerups().length;i++)
                        if(player.getPlayerBoard().getHand().getPowerups()[i].getPowerupType().equals("TagbackGrenade")){

                            try{
                                //TODO è giusto così?
                                getTagBackUsage(this.players.get(player.getId()));
                                if(this.useTagBack){
                                //TODO messaggio da visualizzare in gui: "Sei stato colpito, vuoi usare il PowerUP TagBack Grenade?")
                                PowerupManager.grenadeManager(this.controller,activePlayer,player,i);
                                break;
                            }}
                            catch (InterruptedException e){
                                LOGGER.log(Level.FINE,"request tagback exception",e);
                            }
                    }
                }
            }


            //offering targetting scope to current player
            for(int i=0;i< activePlayer.getPlayerBoard().getHand().getPowerups().length;i++)
                if(activePlayer.getPlayerBoard().getHand().getPowerups()[i].getPowerupType().equals("TargettingScope") &&
                        (activePlayer.getPlayerBoard().getAmmo().getBlue()>0||
                                activePlayer.getPlayerBoard().getAmmo().getRed()>0||
                                activePlayer.getPlayerBoard().getAmmo().getYellow()>0)){



                    //askClient("Scegli il colore del cubo da pagare: ");
                    //askClient("Scegli il colore del player da colpire tra i seguenti: ",);
                    //TODO chiedi il colore del cubo che vuole usare e il colore del player che vuole colpire

                    Player target=new Player(1,"xx", Color.BLUE);//chi vuoi colpire ulteriormente?

                    PowerupManager.targetingScopeManager(this.controller,target,i);
                    break;
            }

        }
    }

    //TODO frangi controlla
    private void getTagBackUsage(ClientHandler clientTurn) throws InterruptedException {
        while(!clientTurn.getStatus().equals(Status.WAITING))
            Thread.sleep(1);
        clientTurn.setStatus(Status.TAGBACKUSAGE);
        while(clientTurn.getStatus() == Status.TAGBACKUSAGE){
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                LOGGER.log(Level.FINE,"get tagback usage",e);
            }
        }
    }


    private synchronized void notifyGameHandlerStart() {
        for (ClientHandler client: players) {
            client.setStatus(Status.NOTMYTURN);
        }

    }

    private synchronized void winnerIs() {
        int max = 0;
        String winnerNick = "";
        for (ClientHandler client : players) {
            if(client.getLocalView().getPersonalPlayerBoardView().getScore() > max)
                max = client.getLocalView().getPersonalPlayerBoardView().getScore();
        }
        for (ClientHandler client: players) {
            client.setWinnerNick(winnerNick);
            client.setStatus(Status.WINNERIS);
            notifyAll();
        }
    }

    private void waitForReSpawn(ClientHandler c) {
        while(c.getStatus() == Status.DEAD){
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                LOGGER.log(Level.FINE,"wait for respawn",e);
            }

        }
    }

    private synchronized void setSpawn(ClientHandler clientHandler){
        clientHandler.setStatus(Status.NOTMYTURN);
        notifyAll();
    }

    private synchronized void setStart() {
        for (ClientHandler player: players) {
            player.setStatus(Status.START);
            notifyAll();
        }
    }

    private void getMapSkull(ClientHandler clientTurn) throws InterruptedException {
        while(!clientTurn.getStatus().equals(Status.WAITING))
            Thread.sleep(1);
        clientTurn.setStatus(Status.MAPSKULL);
        while(clientTurn.getStatus() == Status.MAPSKULL){
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                LOGGER.log(Level.FINE,"get map skull",e);
            }
        }
    }

    private void notifyView(ClientHandler player) {
        player.setStatus(Status.VIEW);
    }

    private synchronized void calculateActions(ClientHandler clientTurn){
        clientTurn.setAvailableActions(new AvailableActions(clientTurn.getRequestView(), controller.getActiveTurn().getActivePlayer().getId(), controller));
        clientTurn.setStatus(Status.CALCULATED);
        this.notifyAll();
    }

    private void waitingRequest(ClientHandler clientTurn){
        while(clientTurn.getStatus()!= Status.WAITING){
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                LOGGER.log(Level.FINE,"waiting request",e);
            }
        }
    }

    public void createController(){
        ArrayList<Player> players = new ArrayList<>();
        int id = 0;
        for (ClientHandler client: this.players) {
            players.add(new Player(id, client.getNickname(), client.getColor()));
            id++;
        }
        this.controller = new Controller(players, this.mapNumber, this.skullsNumber);
    }

    private synchronized void turnPreparation(int turn){
        for (ClientHandler client: players) {
            client.setStatus(Status.NOTMYTURN);
        }
        ClientHandler clientTurn = this.players.get(turn);
        clientTurn.setActionsNumber(controller.getActiveTurn().getActivePlayer().getPlayerBoard().getActionTileNormal().getActionCounter());
        clientTurn.setStatus(Status.MYTURN);
    }

    private void waitForSpawn(ClientHandler player) throws InterruptedException {
        while(player.getStatus() == Status.SPAWN){
            Thread.sleep(1);
        }
    }

    public void setMap(int map) {
        this.mapNumber = map;
    }

     public void setSkull(int skull) {
        this.skullsNumber = skull;
    }


    public void setPlayers(ArrayList<ClientHandler> players) {
        this.players = players;
    }

    public void setUseTagBack(boolean useTagBack) {
        this.useTagBack = useTagBack;
    }
}
