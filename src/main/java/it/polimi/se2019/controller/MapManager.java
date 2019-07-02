package it.polimi.se2019.controller;

import it.polimi.se2019.AdrenalineServer;
import it.polimi.se2019.enums.CellEdge;
import it.polimi.se2019.enums.CellType;
import it.polimi.se2019.exceptions.FullException;
import it.polimi.se2019.exceptions.OuterWallException;
import it.polimi.se2019.model.game.*;
import it.polimi.se2019.view.CellView;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MapManager {
    private static final Logger LOGGER = Logger.getLogger(MapManager.class.getName());

    public static int getIndexOfMove(String directionOfMove) {
        switch (directionOfMove){
            case "Up":return 0;
            case "Down":return 1;
            case"Left":return 2;
            case "Right": return 3;
        }
        return -1;
    }

    public static String getDirOfMove(int indexOfMove) {
        switch (indexOfMove){
            case 0: return"Up";
            case 1: return "Down";
            case 2: return "Left";
            case 3:return "Right";
        }
        return "None";
    }

    public static Room getRoom(Controller currentController, NewCell cell){
        for(Room room: currentController.getMainGameModel().getCurrentMap().getRooms())
           if(room.getColor().equals(cell.getColor()))
            return room;
        return null;
    }

    public static void refillEmptiedCells(NewCell[][] mapMatrixToFill, Decks decks) {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 4; j++)
                if (mapMatrixToFill[i][j].getCellType().equals(CellType.DROP)) {
                    if (mapMatrixToFill[i][j].needsRefill(false))
                        refillCell(decks, mapMatrixToFill[i][j]);
                } else if (mapMatrixToFill[i][j].getCellType().equals(CellType.SPAWN)) {
                    if (mapMatrixToFill[i][j].needsRefill(decks.getGunDeck().getActiveDeck().isEmpty()))
                        refillCell(decks, mapMatrixToFill[i][j]);
                }
    }

    private static void refillCell(Decks decks,NewCell cell) {
        try {
            if (cell.getCellType().equals(CellType.DROP))
                cell.setItem(decks.getAmmotilesDeck().draw());
            else
                cell.setItem(decks.getGunDeck().draw());
        } catch (FullException e) {
            LOGGER.log(Level.FINE, "MapManager refill", e);
        }
    }

    public static NewCell cellViewToNewCell(Controller currentController, CellView cellView) {
        return currentController.getMainGameModel().getCurrentMap().getBoardMatrix()[cellView.getLineIndex()][cellView.getColumnIndex()];
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
    public static NewCell getCellInDirection(NewCell[][] board, NewCell referenceCell, int distance, int directionIndex) throws OuterWallException {
        int lineIndex = getLineOrColumnIndex(board, referenceCell, true);
        int columnIndex = getLineOrColumnIndex(board, referenceCell, false);

        NewCell cellToReturn;

        try {
            switch (directionIndex) {
                case 0: //Up
                    cellToReturn = board[lineIndex - distance][columnIndex];
                    break;
                case 1: //Down
                    cellToReturn = board[lineIndex + distance][columnIndex];
                    break;
                case 2: //Left
                    cellToReturn = board[lineIndex][columnIndex - distance];
                    break;
                case 3: //Right
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

    // these arrays encode the possible movements in the 4 cardinal directions (Up,Down,Left,Right)
    private static final int[] row = {-1, 1, 0, 0};
    private static final int[] col = {0, 0, -1, 1};

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

    public static ArrayList<NewCell> squaresInRadius2(Controller currentController,FictitiousPlayer player){
        NewCell [][] board= currentController.getMainGameModel().getCurrentMap().getBoardMatrix();
        ArrayList<NewCell> possibleCells=new ArrayList<>(ActionManager.cellsOneMoveAway(currentController, player.getPosition()));
        for (NewCell cell: ActionManager.cellsOneMoveAway(currentController,player.getPosition())){
            for (int i = 0; i < 4; i++) {
                try{
                    NewCell possibleCell=MapManager.getCellInDirection(board,cell,1,i);
                    if(!cell.getEdge(i).equals(CellEdge.WALL) && !possibleCells.contains(possibleCell))
                        possibleCells.add(possibleCell);

                }catch (OuterWallException e){
                    //Won't happen
                }

            }
        }
        return possibleCells;
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

