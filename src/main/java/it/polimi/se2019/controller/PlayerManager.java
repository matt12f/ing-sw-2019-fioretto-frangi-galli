package it.polimi.se2019.controller;

import it.polimi.se2019.enums.CellType;
import it.polimi.se2019.enums.Color;
import it.polimi.se2019.exceptions.CardNotFoundException;
import it.polimi.se2019.exceptions.FullException;
import it.polimi.se2019.model.cards.GunCard;
import it.polimi.se2019.model.cards.PowerupCard;
import it.polimi.se2019.model.game.*;
import it.polimi.se2019.view.ChosenActions;

import java.util.*;


public class PlayerManager  {

    /**
     * This method scores all of the boards at the end of a turn
     *
     * Note: more than one killshot in one turn : awards you a point
     */
    public static void scoringProcess(Controller currentController){
        //activating adrenaline modes for alive players
        ArrayList<Player> playersAlive=new ArrayList<>();
        for(Player player:currentController.getMainGameModel().getPlayerList())
            if(!currentController.getMainGameModel().getDeadPlayers().contains(player)) //selects alive players
                playersAlive.add(player);

        for(Player player: playersAlive)
            adrenalineManager(currentController, player);

        //scoring section
        //if there are dead players we must score their boards
        if(!currentController.getMainGameModel().getDeadPlayers().isEmpty()){
            ArrayList<PlayerBoard> boardsDeadPlayers = new ArrayList<>();
            for (Player deadPlayer: currentController.getMainGameModel().getDeadPlayers())
                boardsDeadPlayers.add(deadPlayer.getPlayerBoard());

            //Array to check if someone has dealt a double kill
            ArrayList<Character> offendersCharColors= new ArrayList<>();
            char offenderColor;
            for(PlayerBoard board: boardsDeadPlayers){
                offenderColor=scoreSingleBoard(currentController, board);
                offendersCharColors.add(offenderColor);
                if(Collections.frequency(offendersCharColors,offenderColor)==2) //it's a double kill
                    currentController.getMainGameModel().getPlayerByColor(offenderColor).setScore(1);
            }
        }

        //adrenaline and damage track reset
        for (Player deadPlayer: currentController.getMainGameModel().getDeadPlayers())
            adrenalineReset(currentController, deadPlayer);

        //here we'll update the RemoteView
        currentController.getMainGameModel().notifyRemoteView();
    }

    /**
     * this method scores the single PlayerBoard, dealing points
     * Points dealt:
     *      - first blood: 1 point
     *      - (first) player with most damage: boardvalue points
     *
     * update of killshot track:
     *      - 1 damage token of color of player that dealt the killshot; 2 if it dealt also overkill
     *
     * Note: a kill happens on the 11th damage given : 1 token on the KST; : the board gets scored at the end of the turn
     * Note: an overkill happens on the 12th damage given : double token on the KST; you get a mark from the player you overkilled
     * Note: in frenzy, boards in frenzy mode do not offer first blood points
     *
     * @param board is the board to score
     */
    private static char scoreSingleBoard(Controller currentController, PlayerBoard board){
        //gives one point for first blood (in frenzy there's no reward for first blood)
        if(!currentController.getMainGameModel().getFinalFrenzy())
            currentController.getMainGameModel().getPlayerByColor(board.getDamageTrack().getDamage()[0]).setScore(1);

        //section for dealing points
        //here we extract the current value of the board
        dealPoints(currentController,board.getCurrentBoardValue(),listOffenders(board.getDamageTrack().getDamage()));

        //we'll then decrease the value of the board after the kill
        board.decreaseBoardValue();

        //the playerBoard must have a color there, since the player is dead
        char offenderColor = board.getDamageTrack().getDamage()[10];

        //update killshot track
        StringBuilder kill=new StringBuilder();
        kill.append(offenderColor);
        //it's an overkill if the 12th drop is the same color as the player that killed it
        if(board.getDamageTrack().getDamage()[11] == offenderColor){
            kill.append(offenderColor); //double token
            //gives one mark back
            currentController.getMainGameModel().getPlayerByColor(offenderColor).getPlayerBoard().getDamageTrack().addMark(board.getColorChar());
        }

        currentController.getMainGameModel().getKillshotTrack().setKills(kill.toString());

        return offenderColor;
    }

    /**
     * this deals the points on a board (no killshot and or overkill management)
     * @param boardValue is the value of the board to score
     * @param offendersList is the list of offenders ordered by number of damages inflicted
     */
    static void dealPoints(Controller currentController, int boardValue, ArrayList<Character> offendersList){
        //It removes blanks, which may happen if you try to score a damage track at the end of the game (no kills)
        offendersList.removeIf(character -> character.equals(' '));

        for(Character playerColor: offendersList) {
            currentController.getMainGameModel().getPlayerByColor(playerColor).setScore(boardValue);
            if(boardValue>1)
                boardValue = boardValue - 2;
            else
                boardValue=1;
        }
    }

    /**
     * this method builds a list of the players char colors by the number of damage they've given
     * (See tests for example)
     *
     * It also considers the tie breaker in favour of the player that hit first
     *
     *  @param damage is the damageTrack
     */
    static ArrayList<Character> listOffenders(char[] damage) {
        ArrayList<Character> damageList=new ArrayList<>();
        for(char car: damage)
            damageList.add(car);

        //here it orders the damage (it's in the order it's given)

        LinkedHashMap<Character,Integer> occurrencesOf=new LinkedHashMap<>();
        //here I'll count the occurrences for each character
        for(Character character: damageList)
            if(!occurrencesOf.containsKey(character))
                occurrencesOf.put(character,Collections.frequency(damageList,character));

        ArrayList<Character> temp = new ArrayList<>();
        //here I'll sort the occurrences by order of appearance and amount
        occurrencesOf.entrySet().stream()
                .sorted((entry1, entry2) -> - entry1.getValue().compareTo(entry2.getValue()))
                .forEach(character -> temp.add(character.getKey()));

        return temp;

    }

    /**
     * method that allows  to spawn players in the right spawn point
     * @param controller
     * @param id player id
     * @param spawn color that indicates the spawn point
     * @throws CardNotFoundException
     */
    public static void spawnPlayers(Controller controller, int id, PowerupCard spawn) throws CardNotFoundException {
        NewCell[][] map = controller.getMainGameModel().getCurrentMap().getBoardMatrix();
        Color cellNeeded;
        if(spawn.getCubeColor() == 'b')
            cellNeeded = Color.BLUE;
        else if(spawn.getCubeColor() == 'r')
            cellNeeded = Color.RED;
        else
            cellNeeded = Color.YELLOW;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j].getCellType().equals(CellType.SPAWN) && map[i][j].getColor() == cellNeeded) {
                    map[i][j].addPlayers(controller.getMainGameModel().getPlayerList().get(id));
                    MapManager.getRoom(controller,map[i][j]).addPlayers(controller.getMainGameModel().getPlayerList().get(id));
                    controller.getMainGameModel().getPlayerList().get(id).getFigure().setCell(map[i][j]);
                }
            }
        }

        PowerupCard optional = controller.getMainGameModel().getPlayerList().get(id).getPlayerBoard().getHand().getAdditionalPowerup();
        if(optional == null){
            controller.getMainGameModel().getPlayerList().get(id).getPlayerBoard().getHand().substitutionPowerup(spawn, null);
        }else{
            if(optional.getPowerupType().equals(spawn.getPowerupType()) && optional.getCubeColor() == spawn.getCubeColor())
                controller.getMainGameModel().getPlayerList().get(id).getPlayerBoard().getHand().setAdditionalPowerup(null);
            else{
                controller.getMainGameModel().getPlayerList().get(id).getPlayerBoard().getHand().substitutionPowerup(spawn, optional);
                controller.getMainGameModel().getPlayerList().get(id).getPlayerBoard().getHand().substitutionPowerup(optional, null);
            }
        }

        controller.getMainGameModel().notifyRemoteView();
    }

    /**
     *
     * @param setUpGame
     * @param controller
     * @param id player's id
     * @throws FullException
     */
    public static void getCardsToSpawn(boolean setUpGame, Controller controller, int id) throws FullException {
        PowerupCard card;
        if(setUpGame){
            card = controller.getMainGameModel().getCurrentDecks().getPowerupsDeck().draw();
            controller.getMainGameModel().getPlayerList().get(id).getPlayerBoard().getHand().setPowerup(card);
            card= controller.getMainGameModel().getCurrentDecks().getPowerupsDeck().draw();
            controller.getMainGameModel().getPlayerList().get(id).getPlayerBoard().getHand().setPowerup(card);
        }else{
            if(!controller.getMainGameModel().getPlayerList().get(id).getPlayerBoard().getHand().isPowerUpHandFull()){
                card = controller.getMainGameModel().getCurrentDecks().getPowerupsDeck().draw();
                controller.getMainGameModel().getPlayerList().get(id).getPlayerBoard().getHand().setPowerup(card);
            }else{
                card = controller.getMainGameModel().getCurrentDecks().getPowerupsDeck().draw();
                controller.getMainGameModel().getPlayerList().get(id).getPlayerBoard().getHand().setAdditionalPowerup(card);
            }
        }
        controller.getMainGameModel().notifyRemoteView();
    }

    /**
     * this method applies the player's choices for the Macro Action it requested
     * @param currentController is the controller of the game
     * @param actions contains the choices
     * @return list of IDs of players that received damage
     */
    public static ArrayList<Player> choiceExecutor(Controller currentController, ChosenActions actions){
        //Here I'm extracting the player from the list in the controller
        Player player = currentController.getMainGameModel().getPlayerList().get(
                currentController.getMainGameModel().getPlayerList().
                        indexOf(currentController.getActiveTurn().getActivePlayer()));

        FictitiousPlayer fictitiousPlayer=actions.getFictitiousPlayer();
        //moves the player

        NewCell[][] board=currentController.getMainGameModel().getCurrentMap().getBoardMatrix();
        fictitiousPlayer.setPosition(board[MapManager.getLineOrColumnIndex(board,fictitiousPlayer.getPosition(),true)]
                [MapManager.getLineOrColumnIndex(board,fictitiousPlayer.getPosition(),false)]);

        ActionManager.movePlayer(currentController, player, fictitiousPlayer.getPosition());

        //grab management, ammo first, then gun cards
        if(fictitiousPlayer.isGrabbedAmmo()) {
            if(player.getPlayerBoard().getAmmo().addAmmo(fictitiousPlayer.getPosition().pickItem().getContent())){ //checks if you can draw a powerup
                try { //tries to place the powerup in your hand and then removing the card from the deck if it succeeded
                    player.getPlayerBoard().getHand().setPowerup(currentController.getMainGameModel().getCurrentDecks().getPowerupsDeck().peekCardOnTop());
                    currentController.getMainGameModel().getCurrentDecks().getPowerupsDeck().draw();
                }catch (FullException e){
                    //no hard feelings if it fails to place the card in the player's hand
                }
            }

        }else if(actions.getCardToPick()!=null) {//there's a card you can pick
            //cardToDiscard is the card to put back on the spawn slot
            if(actions.getCardToDiscard()==null) {
                //you simply pick the card from the slot and pay the cost
                try {
                    player.getPlayerBoard().getHand().setGun(player.getFigure().getCell().pickItem(actions.getCardToPick()));
                    payGunCardCost(player, actions.getCardToPick().getAmmoCost(), true);
                }catch (FullException e){
                   //won't happen
                }
            }else {
                //you  pick the card from the slot, putting the discarded one there and pay the cost
                try {
                    player.getPlayerBoard().getHand().substitutionGunCard(player.getFigure().getCell(), actions.getCardToDiscard(), actions.getCardToPick());
                    payGunCardCost(player,actions.getCardToPick().getAmmoCost(),true);
                } catch (CardNotFoundException e) {
                    //won't happen
                }
            }

        }

        ArrayList<Player> playersBefore=Player.duplicateList(currentController.getMainGameModel().getPlayerList());

        //applies shoot actions with the card selected if you have selected an action to shoot
        if(actions.getChosenGun()!=null) {
            payEffectsCosts(player,actions.getChosenGun(),actions.getOrderOfExecution());
            actions.getChosenGun().applyEffects(currentController, actions);
            //Unloads the card, by looking for the card selected in the player's hand (avoiding null spots!)
            for (int i = 0; i < player.getPlayerBoard().getHand().getGuns().length; i++)
                if(player.getPlayerBoard().getHand().getGuns()[i] != null &&
                        player.getPlayerBoard().getHand().getGuns()[i].equals(actions.getChosenGun()))
                    player.getPlayerBoard().getHand().getGuns()[i].setLoaded(false);

            //if there's a frenzy reload the card can be used even if it's unloaded and it stays unloaded
            //but you must pay the cost of the reload
            if(actions.getFictitiousPlayer().isFrenzyReload())
                payGunCardCost(player,actions.getChosenGun().getAmmoCost(),false);

        }
        ArrayList<Player> playersAfter = Player.duplicateList(currentController.getMainGameModel().getPlayerList());

        //it's necessary for powerup tagback grenade
        return  checkForDamage(playersBefore, playersAfter);
    }

    /**
     * This method makes the player pay for the optional effects used that have a cost
     * @param player
     * @param chosenGun
     * @param effectsUsed
     */
    private static void payEffectsCosts(Player player, GunCard chosenGun, ArrayList<String> effectsUsed) {
        for(String effect: effectsUsed)
            switch (effect){
                case "Optional1":
                    payGunCardCost(player,chosenGun.getSecondaryEffectCost(),false);
                break;
                case "Optional2":
                    payGunCardCost(player,chosenGun.getTertiaryEffectCost(),false);
                    break;
                default:break;
            }
    }

    /**
     * returns a list of the players damaged during this macroaction
     */
    private static ArrayList<Player> checkForDamage(ArrayList<Player> playersBefore, ArrayList<Player> playersAfter) {
        ArrayList<Player> playersToReturn=new ArrayList<>();

        playersBefore.forEach(player -> {
            if(!Arrays.equals(
                    playersAfter.get(playersAfter.indexOf(player)).getPlayerBoard().getDamageTrack().getDamage(),
                    player.getPlayerBoard().getDamageTrack().getDamage()))
                playersToReturn.add(player);
        });

        return playersToReturn;
    }

    /**
     * This method deals damage to the player it receives as a parameter
     *
     * Note: the damage must be dealt BEFORE the marks
     *
     * @param damageToDeal array of chars that contain the damage as characters representing the color of the offender.
     *                     Note that the damage drops "to be dealt" here come from a single player.
     */
    public static void damageDealer(Controller currentController, Player target, char[] damageToDeal, boolean targetingScope){
        //here we update the real player to be damaged
        target=currentController.getMainGameModel().getPlayerList().get(
                currentController.getMainGameModel().getPlayerList().indexOf(target));

        //it's initialized with the result of dealing the damage ( it will be "notOverkill" if it's alive/killed
        String dealingResponse=target.getPlayerBoard().getDamageTrack().dealDamage(damageToDeal);

        //Deal the damage first. Then IF it's still alive deal marks too (skipped otherwise)
        if(dealingResponse.equals("notOverkill") && !targetingScope){
            //here we "pull" the marks this player has left before
            int markNumber = target.getPlayerBoard().getDamageTrack().pullMarks(damageToDeal[0]);

            if(markNumber!=0){
            //i'll build the array of chars to deal the marks
            char[] marks=new char[markNumber];
            for (int i = 0; i <markNumber ; i++)
                marks[i]=damageToDeal[0];

            //this deals the marks
            dealingResponse = target.getPlayerBoard().getDamageTrack().dealDamage(marks);
            }
        }

        //If the player dies after the damage and/or marks are dealt, we put it aside to score his board later
        //if the player is overkill OR the kill box is not empty: it's added to the dead players
        if(dealingResponse.equals("overkill") || target.getPlayerBoard().getDamageTrack().getDamage()[10]!=' ')
            currentController.getMainGameModel().addDeadPlayer(target);
    }

    /**
     * this method adds new marks to a player's player board
     */
    public static void markerDealer(Controller currentController, Player target, char[] add) {
        //here we update the real player to be damaged
        target=currentController.getMainGameModel().getPlayerList().get(
                currentController.getMainGameModel().getPlayerList().indexOf(target));

        for (char mark: add)
            target.getPlayerBoard().getDamageTrack().addMark(mark);
        currentController.getMainGameModel().notifyRemoteView();
    }

    /**
     * this method enables the adrenaline modes in the player boards of a given player
     */
    private static void adrenalineManager(Controller currentController, Player player){
        player=currentController.getMainGameModel().getPlayerList().get(
                currentController.getMainGameModel().getPlayerList().indexOf(player));

        if(player.getPlayerBoard().getDamageTrack().getDamage()[2]!=' ')
            player.getPlayerBoard().getActionTileNormal().setAdrenalineMode1(true);

        if(player.getPlayerBoard().getDamageTrack().getDamage()[5]!=' ')
            player.getPlayerBoard().getActionTileNormal().setAdrenalineMode2(true);

    }

    /**
     * this method deactivates both adrenaline modes for players that have died
     * and also resets their damage tracks
     */
    private static void adrenalineReset(Controller currentController, Player player){
        player=currentController.getMainGameModel().getPlayerList().get(
                currentController.getMainGameModel().getPlayerList().indexOf(player));

        player.getPlayerBoard().getDamageTrack().resetDmgTrack();
        player.getPlayerBoard().getActionTileNormal().setAdrenalineMode1(false);
        player.getPlayerBoard().getActionTileNormal().setAdrenalineMode2(false);
    }

    /**
     * method that allows the player to pay the ammocost for a weapon
     * @param player
     * @param cost
     * @param pickOrFullReload
     */
    public static void payGunCardCost(Player player, char [] cost, boolean pickOrFullReload){
        //Here it extracts the real amount to pay
        char [] toPay;
        if(!pickOrFullReload)
            toPay=cost;
        else{
            char [] pickCost = new char[cost.length-1];
            for (int i = 0; i < cost.length-1; i++)
                pickCost[i]=cost[i+1];
            toPay=pickCost;
        }

        //this excludes payments of nothing (a pick of card with one cube already loaded)
        // and payments of effects with no cost (indicated by a char 'n')
        if(toPay.length!=0 && toPay[0]!='n') {
            int blue = 0;
            int yellow = 0;
            int red = 0;

            //calculating the price to pay of each ammo
            for (int i = 0; i < toPay.length; i++) {
                if (toPay[i] == 'b')
                    blue++;
                else if (toPay[i] == 'y')
                    yellow++;
                else if (toPay[i] == 'r')
                    red++;
            }

            payOneColor(player, red, 'r');
            payOneColor(player, yellow, 'y');
            payOneColor(player, blue, 'b');
        }
    }

    /**
     * this method pays one color of ammos using cubes and powerups
     * @param player is the player who must pay
     * @param amount is the amount to pay of that color
     * @param color is the color to pay
     */
    private static void payOneColor(Player player, int amount, char color) {

        boolean thereIsAmmo;

        //evaluates if there actually is ammo of this color
        switch (color) {
            case 'y':thereIsAmmo=player.getPlayerBoard().getAmmo().getYellow() > 0;break;
            case 'b':thereIsAmmo=player.getPlayerBoard().getAmmo().getBlue() > 0;break;
            case 'r':thereIsAmmo=player.getPlayerBoard().getAmmo().getRed() > 0;break;
            default:thereIsAmmo=false; break;
        }

        while(amount>0 && thereIsAmmo){
            thereIsAmmo = player.getPlayerBoard().getAmmo().payOneAmmo(color);
            amount--;
        }

        //this is the section that pays with powerups
        for (int i = 0; i < amount; i++) {
            int indexInHand;
            //it searches for a pwup of that color to pay with
            for(indexInHand=0;indexInHand<player.getPlayerBoard().getHand().getPowerups().length;indexInHand++)
                if(player.getPlayerBoard().getHand().getPowerups()[indexInHand]!=null &&
                        player.getPlayerBoard().getHand().getPowerups()[indexInHand].getCubeColor()==color)
                    break;

            //redundant check if the index is "legal to apply"
            if(indexInHand<player.getPlayerBoard().getHand().getPowerups().length)
                player.getPlayerBoard().getHand().removePowerUp(indexInHand);
        }

    }

    /**
     * method that allows to recharge weapons
     * @param player
     * @param reload
     */
    public static void reloadManager(Player player, boolean[] reload) {
        for(int i=0;i<player.getPlayerBoard().getHand().getGuns().length;i++)
            if(player.getPlayerBoard().getHand().getGuns()[i]!=null && reload[i]&&
                    ActionManager.canAffordCost(player,player.getPlayerBoard().getAmmo(),player.getPlayerBoard().getHand().getGuns()[i].getAmmoCost(),false)){
                player.getPlayerBoard().getHand().getGuns()[i].setLoaded(true);
                payGunCardCost(player, player.getPlayerBoard().getHand().getGuns()[i].getAmmoCost(), false);
            }
    }
}
