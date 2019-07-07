package it.polimi.se2019.controller;

import it.polimi.se2019.enums.CellType;
import it.polimi.se2019.exceptions.FullException;
import it.polimi.se2019.model.game.*;
import it.polimi.se2019.view.LocalView;
import it.polimi.se2019.view.RemoteView;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Controller{
    private static final Logger LOGGER = Logger.getLogger(Controller.class.getName());
    private GameModel mainGameModel;
    private RemoteView remoteView;
    private TurnManager activeTurn;

    /**
     * This constructor generates one local controller object for one game
     */
    public Controller(ArrayList<Player> players, int mapNumber, int skulls) {
        this.mainGameModel=new GameModel(players, mapNumber, skulls);
        this.remoteView=new RemoteView(this.mainGameModel, mapNumber, skulls);
        this.activeTurn = new TurnManager(this.getMainGameModel());
        this.getMainGameModel().addObserver(this.remoteView);
        setupBoard();
    }

    public TurnManager getActiveTurn() {
        return activeTurn;
    }

    public GameModel getMainGameModel() {
        return mainGameModel;
    }

    public RemoteView getRemoteView() {
        return remoteView;
    }


    private void setupBoard(){
        NewCell[][] mapMatrixToFill=mainGameModel.getCurrentMap().getBoardMatrix();
        try {
            for (int i = 0; i < 3; i++)
                for (int j = 0; j < 4; j++)
                    if (mapMatrixToFill[i][j].getCellType().equals(CellType.DROP)) {
                        mapMatrixToFill[i][j].setItem(mainGameModel.getCurrentDecks().getAmmotilesDeck().draw());
                    }else if (mapMatrixToFill[i][j].getCellType().equals(CellType.SPAWN)){
                        for (int k = 0; k < 3; k++)
                            mapMatrixToFill[i][j].setItem(mainGameModel.getCurrentDecks().getGunDeck().draw());
                    }
        }catch(FullException e){
            LOGGER.log(Level.FINE,"Setup game in controller", e);
        }
    }

}