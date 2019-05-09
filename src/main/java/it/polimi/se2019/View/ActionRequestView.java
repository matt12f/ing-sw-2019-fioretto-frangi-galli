package it.polimi.se2019.view;

import it.polimi.se2019.enums.ActionType;

public class ActionRequestView{
    private ActionType actionToRequest;
    private boolean reload;

    public ActionRequestView(){
        int choice=1;
        ActionType playerChoice;
        //TODO richiesta a video, a seconda del clic invia il tipo di richiesta
        switch (choice){
            case 1: playerChoice=ActionType.NORMAL1;break;
            case 2: playerChoice=ActionType.NORMAL2;break;
            case 3: playerChoice=ActionType.NORMAL3;break;
            case 4: playerChoice=ActionType.FRENZY1;break;
            case 5: playerChoice=ActionType.FRENZY2;break;
            default:playerChoice=null;break;
        }
        this.actionToRequest=playerChoice;
        }
    }

