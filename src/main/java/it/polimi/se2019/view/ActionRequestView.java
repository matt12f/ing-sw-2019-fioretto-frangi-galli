package it.polimi.se2019.view;

import it.polimi.se2019.AdrenalineClient;
import it.polimi.se2019.enums.ActionType;
import it.polimi.se2019.enums.CardName;
import it.polimi.se2019.enums.CellEdge;

import java.util.ArrayList;

public class ActionRequestView{
    private ActionType actionToRequest;
    private boolean [] reload;
    private ArrayList<PowerupUse> powerupUse;

    /** this constructor manages both the normal request of a macro action and the end of the turn
     * when the player must decide wether he wants to use a powerup and/or reload
     */
    public ActionRequestView(boolean turnConclusion){
        if(!turnConclusion){
        int choice=1;//TODO richiesta di click a video, a seconda del clic invia il tipo di richiesta
        switch (choice){
            case 1: this.actionToRequest=ActionType.NORMAL1;break;
            case 2: this.actionToRequest=ActionType.NORMAL2;break;
            case 3: this.actionToRequest=ActionType.NORMAL3;break;
            case 4: this.actionToRequest=ActionType.FRENZY1;break;
            case 5: this.actionToRequest=ActionType.FRENZY2;break;
            case 6: this.actionToRequest=ActionType.FRENZY3;break;
            case 7: this.actionToRequest=ActionType.FRENZY4;break;
            case 8: this.actionToRequest=ActionType.FRENZY5;break;
            default:this.actionToRequest=null;break;
        }
        this.powerupUse=powerupManagerView();
        }
        else{
            this.actionToRequest=null;
            this.powerupUse=powerupManagerView();
            this.reload=new boolean[3];
            for(boolean cardToReload:this.reload) {
                cardToReload = true; //TODO richiesta a video se vuole ricaricare, e quali armi, alla fine del turno
            }
        }
    }

    /**
     * this method covers the offer of the powerups newton and teleporter before the macro actions
     */
    private ArrayList<PowerupUse> powerupManagerView() {
        ArrayList<PowerupUse> temp=new ArrayList<>();
        CardTileView[] cardView=AdrenalineClient.getLocalView().getPlayerHand().getPowerups();
        for(int i=0;i<cardView.length;i++)
            if (cardView[i].getCardName().equals(CardName.PWUP_NEWTON)){
             //TODO messaggi a video: vuoi usarla? se la risposta è no
                int idPlayerToMove=3;//chi vuoi spostare? vanno bene tutti (tranne se stesso)
                //TODO check per vedere dove ci si può spostare
                String direction="Up";//in quale direzione? (attivare celle cliccabili)
                int distanceOfMovement=1;//di quante celle?
                temp.add(new PowerupUse(i,idPlayerToMove,distanceOfMovement,direction,null));
            }
            else if(cardView[i].getCardName().equals(CardName.PWUP_TELEPORTER)){
                //TODO messaggi a video: vuoi usarla?
                CellView destCell=new CellView(1,1, CellEdge.WALL,CellEdge.WALL,CellEdge.WALL,CellEdge.WALL);//in quale cella ti vuoi spostare? vanno bene tutte (tranne quella dov'è attualmente)
                temp.add(new PowerupUse(i,AdrenalineClient.getLocalView().getPlayerId(),0,"None",destCell));
            }
        return temp;
    }

    public ActionType getActionToRequest() {
        return actionToRequest;
    }

    public boolean [] isReload() {
        return reload;
    }

    public ArrayList<PowerupUse> getPowerupUse() {
        return powerupUse;
    }
}

