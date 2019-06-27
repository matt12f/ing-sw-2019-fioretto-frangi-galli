package it.polimi.se2019.model.game;

import it.polimi.se2019.AdrenalineServer;
import it.polimi.se2019.controller.MapManager;
import it.polimi.se2019.enums.CellEdge;
import it.polimi.se2019.enums.CellType;
import it.polimi.se2019.enums.Color;
import it.polimi.se2019.exceptions.FullException;
import it.polimi.se2019.model.cards.AmmoTileCard;
import it.polimi.se2019.model.cards.GunCard;

import java.util.ArrayList;

public class NewCell {
    private CellType cellType;
    protected Color color;
    protected ArrayList<Player> players;
    protected CellEdge top;
    protected CellEdge bottom;
    protected CellEdge left;
    protected CellEdge right;

    private AmmoTileCard drop;
    private ArrayList<GunCard> weaponCards;

    /**
     * This constructor will serve as a super for its two sub classes
     */
    public NewCell(Color color, CellEdge top, CellEdge bottom, CellEdge left, CellEdge right, CellType cellType) {
        this.color = color;
        this.players = new ArrayList<>();
        this.top = top;
        this.bottom = bottom;
        this.left = left;
        this.right = right;
        this.cellType=cellType;
        if(cellType.equals(CellType.SPAWN)){
            this.drop=null;
            this.weaponCards= new ArrayList<>();
        }
    }

    public Color getColor() {
        return color;
    }

    public CellType getCellType() {
        return cellType;
    }

    public ArrayList<Player> getPlayers(){
        return players;
    }
    public void addPlayers(Player newPlayer){
        this.players.add(newPlayer);
    }
    public void removePlayers(Player player){
        this.players.remove(player);
    }

    /**
     * methods for SpawnCell
     *
     *
     *
     */
    public ArrayList<GunCard> getWeaponCards() {
        return weaponCards;
    }

    /** It fills the first empty slot in weaponCards with the card it receives
     */
    public void setItem(GunCard card)throws FullException{
        if(this.weaponCards.size()<3)
            this.weaponCards.add(card);
        else
            throw new FullException("gun hand already full");
    }

    public boolean needsRefill(boolean emptyDeck){
        if(this.cellType.equals(CellType.SPAWN)){
            if (this.weaponCards.size()<3 && !emptyDeck)
                return true;
        }
        else if(this.cellType.equals(CellType.DROP)){
                if (this.drop==null && !emptyDeck)
                    return true;
        }
        return false;
    }

    /** Method that returns the weapon from the pick-numbered slot
     **/
    public GunCard pickItem(int pick){
        GunCard picked= this.weaponCards.get(pick);
        this.weaponCards.remove(pick);
        return picked;
    }


    /**
     * methods for DropCell
     *
     *
     *
     */
    public AmmoTileCard getDrop(){
        return  drop;
    }

    /**
     * This method puts the card it receives in the slot
     */
    public void setItem(AmmoTileCard card) throws FullException{
        if(this.drop==null)
        {
            this.drop = card;
        }
        else throw new FullException("Ammotile slot already full");
    }

    /**
     * This method picks a drop card and returns it.
     * The slot will be refilled at the end of the turn, otherwise a player could use the move+grab
     * move twice in his turn and pick twice from the same DropCell (non compliant to game rules).
     */
    public AmmoTileCard pickItem(){
        AmmoTileCard temp = this.drop;
        this.drop=null;
        return temp;
    }

    public CellEdge getEdge(int edgeNumber){
        switch (edgeNumber){
            case 0: return this.top;
            case 1: return this.bottom;
            case 2: return this.left;
            case 3: return this.right;
            default:return CellEdge.WALL;
        }
    }
}
