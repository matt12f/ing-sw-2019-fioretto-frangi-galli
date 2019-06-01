package it.polimi.se2019.view;

import it.polimi.se2019.AdrenalineClient;
import it.polimi.se2019.enums.ActionType;
import it.polimi.se2019.enums.CardName;

public class ActionRequestView{
    private ActionType actionToRequest;
    private boolean [] reload;
    private PowerupUse powerupUse;

    /** this constructor manages both the normal request of a macro action and the end of the turn
     * when the player must decide wether he wants to use a powerup and/or reload
     */
    public ActionRequestView(boolean turnConclusion){
        if(!turnConclusion){
        int choice=1;//TODO questo va richiesto a video, a seconda del clic invia il tipo di richiesta
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
    private PowerupUse powerupManagerView() {
        for(CardTileView cardView:AdrenalineClient.getLocalView().getPlayerHand().getPowerups())
            if (cardView.getCardName().equals(CardName.PWUP_NEWTON)){
             //TODO messaggi a video: vuoi usarla? se la risposta è no ritorno null
                int idPlayerToMove=3;//chi vuoi spostare?
                String direction="Up";//in quale direzione? (attivare celle cliccabili)
                int distanceOfMovement=1;//di quante celle?
                return new PowerupUse(idPlayerToMove,direction,distanceOfMovement,null);
            }
            else if(cardView.getCardName().equals(CardName.PWUP_TELEPORTER)){
                //TODO messaggi a video: vuoi usarla?
                CellView temp=new CellView();//in quale cella ti vuoi spostare?
                return new PowerupUse(AdrenalineClient.getLocalView().getPlayerId(),"None",0,temp);
            }
            return null;
    }

    public ActionType getActionToRequest() {
        return actionToRequest;
    }

    public boolean [] isReload() {
        return reload;
    }

    public PowerupUse getPowerupUse() {
        return powerupUse;
    }
}

