package it.polimi.se2019.controller;

import it.polimi.se2019.AdrenalineServer;
import it.polimi.se2019.enums.CellEdge;
import it.polimi.se2019.enums.CellType;
import it.polimi.se2019.exceptions.FullException;
import it.polimi.se2019.exceptions.OuterWallException;
import it.polimi.se2019.model.game.*;
import it.polimi.se2019.view.CellView;

import java.util.ArrayDeque;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MapManager {
    private static final Logger LOGGER = Logger.getLogger(MapManager.class.getName());

    public static void refillEmptiedCells() {
        NewCell[][] mapMatrixToFill = AdrenalineServer.getMainController().getMainGameModel().getCurrentMap().getBoardMatrix();
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 4; j++)
                if (mapMatrixToFill[i][j].getCellType().equals(CellType.DROP)) {
                    if (mapMatrixToFill[i][j].needsRefill(false))
                        refillCell(mapMatrixToFill[i][j]);
                } else if (mapMatrixToFill[i][j].getCellType().equals(CellType.SPAWN)) {
                    if (mapMatrixToFill[i][j].needsRefill(AdrenalineServer.getMainController().getMainGameModel().getCurrentDecks().getGunDeck().getActiveDeck().isEmpty()))
                        refillCell(mapMatrixToFill[i][j]);
                }
    }

    private static void refillCell(NewCell cell) {
        try {
            if (cell.getCellType().equals(CellType.DROP))
                cell.setItem(AdrenalineServer.getMainController().getMainGameModel().getCurrentDecks().getAmmotilesDeck().draw());
            else
                cell.setItem(AdrenalineServer.getMainController().getMainGameModel().getCurrentDecks().getGunDeck().draw());
        } catch (FullException e) {
            LOGGER.log(Level.FINE, "MapManager refill", e);
        }
    }

    public static void movePlayer(Player player, NewCell arrivalCell) {
        player.getFigure().getCell().removePlayers(player);
        arrivalCell.addPlayers(player);
    }

    public static NewCell cellViewToNewCell(CellView cellView) {
        return AdrenalineServer.getMainController().getMainGameModel().getCurrentMap().getBoardMatrix()[cellView.getLineIndex()][cellView.getColumnIndex()];
    }

    public static int getLineOrColumnIndex(NewCell[][] board, NewCell referenceCell, boolean lineOrColumn) {
        for (int i = 0; i < board.length; i++)
            for (int j = 0; j < board[i].length; j++)
                if (board[i][j].equals(referenceCell))
                    if (lineOrColumn)
                        return i;
                    else
                        return j;
        return -1;
    }

    /**
     * this method returns a cell in a given direction that is a certain number of steps away.
     * @throws OuterWallException in case it reaches outside an external wall in that direction
     */
    public static NewCell getCellInDirection(NewCell[][] board, NewCell referenceCell, int distance, String direction) throws OuterWallException {
        int lineIndex = getLineOrColumnIndex(board, referenceCell, true);
        int columnIndex = getLineOrColumnIndex(board, referenceCell, false);

        NewCell cellToReturn;

        try {
            switch (direction) {
                case "Up":
                    cellToReturn = board[lineIndex - distance][columnIndex];
                    break;
                case "Down":
                    cellToReturn = board[lineIndex + distance][columnIndex];
                    break;
                case "Left":
                    cellToReturn = board[lineIndex][columnIndex - distance];
                    break;
                case "Right":
                    cellToReturn = board[lineIndex][columnIndex + distance];
                    break;
                default:
                    cellToReturn = null;
                    break;
            }
        } catch (IndexOutOfBoundsException e) {
            throw new OuterWallException();
        }

        if (cellToReturn != null && cellToReturn.getCellType() != CellType.OUTSIDEBOARD)
            return cellToReturn;
        else
            throw new OuterWallException();
    }

    // these arrays encode the possible movements in the 4 cardinal directions
    private static final int[] row = {-1, 1, 0, 0};
    private static final int[] col = {0, 0, -1, 1};
    private static final String[] direction = {"Up", "Down", "Left", "Right"};

    /**
     * this method evaluates if a move in the board is valid
     * NOTE: it does not allow movement through an internal wall
     */
    private static boolean isMoveLegal(boolean [][] visited, NewCell previousCell, int row, int col, int directionIndex) {
            return !previousCell.getEdge(directionIndex).equals(CellEdge.WALL) && !visited[row][col];
    }

    /**
     * This methos calculates the distance between startCell and destinationCell with a Lee BFS algorithm
     */
    public static int distanceBetweenCells(NewCell[][] board, NewCell startCell, NewCell arrivalCell){
        int i = getLineOrColumnIndex(board, startCell, true);
        int j = getLineOrColumnIndex(board, startCell, false);

        int x = getLineOrColumnIndex(board, arrivalCell, true);
        int y = getLineOrColumnIndex(board, arrivalCell, false);

        int minimumDist = 100; //  length of longest path from start to finish (arbitrary number)
        boolean[][] alreadyChecked = new boolean[3][4];
        ArrayDeque<EvaluationCell> cellQueue = new ArrayDeque<>();

        alreadyChecked[i][j] = true; // marks as visited the starting cell
        cellQueue.add(new EvaluationCell(0, i, j)); // adds it to the queue

        while (!cellQueue.isEmpty()) {
            EvaluationCell node = cellQueue.poll();//pops the front node from queue and process it

            int dist = node.dist;
            i = node.x;
            j = node.y;

            if (i == x && j == y)
                return dist;

            for (int k = 0; k < 4; k++)
                if (isMoveLegal(alreadyChecked, board[i][j],i + row[k],j + col[k],k)) {
                    alreadyChecked[i+row[k]][j+col[k]] = true;
                    cellQueue.add(new EvaluationCell(dist+1,i + row[k], j + col[k]));
                }
        }
        return minimumDist; //this will never be used
    }
}

class EvaluationCell {
    int dist; //min distance from start
    int x; //coordinate
    int y; //coordinate

    EvaluationCell(int dist, int x, int y) {
        this.dist = dist;
        this.x = x;
        this.y = y;
    }
}

