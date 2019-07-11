package it.polimi.se2019.view;

import it.polimi.se2019.AdrenalineClient;
import it.polimi.se2019.enums.ActionType;
import it.polimi.se2019.enums.CellEdge;
import it.polimi.se2019.enums.Color;
import it.polimi.se2019.model.cards.PowerupCard;

import java.io.Serializable;
import java.util.ArrayList;

public class ActionRequestView implements Serializable {
    private ActionType actionToRequest;
    private boolean [] reload;
    private ArrayList<PowerupUse> powerupUse;
    private UserInteractionGUI askUser;

    /** this constructor manages both the normal request of a macro action and the end of the turn
     * when the player must decide whether he wants to use a powerup and/or reload
     */
    public ActionRequestView(boolean turnConclusion){
        LocalView localView=AdrenalineClient.getLocalView();
        this.askUser=new UserInteractionGUI();

        if(!turnConclusion){
            String action=this.askUser.actionToRequest(localView.getPersonalPlayerBoardView().getFrenzy(),localView.getPersonalPlayerBoardView().getColor().toString());
            switch (action){
                case "move": this.actionToRequest=ActionType.NORMAL1; break;
                case "grab": this.actionToRequest=ActionType.NORMAL2; break;
                case "shoot": this.actionToRequest=ActionType.NORMAL3; break;
                case "frenzy1": this.actionToRequest=ActionType.FRENZY1; break;
                case "frenzy2": this.actionToRequest=ActionType.FRENZY2; break;
                case "frenzy3": this.actionToRequest=ActionType.FRENZY3; break;
                case "frenzy4": this.actionToRequest=ActionType.FRENZY4; break;
                case "frenzy5": this.actionToRequest=ActionType.FRENZY5; break;
                default: break; //won't happen, the player must chose one of the above
            }
            this.powerupUse=powerupManagerView(localView);
        }else{
            //here we are at the end of the turn
            this.actionToRequest=null;
            this.powerupUse=powerupManagerView(localView);
            this.reload=reloadManager(localView);
        }
    }

    /**
     * this method covers the offer of the powerups newton and teleporter before the macro actions
     * @param localView is the localview
     */
    private ArrayList<PowerupUse> powerupManagerView(LocalView localView) {
        ArrayList<PowerupUse> temp=new ArrayList<>();

        PowerupCard[] cardView=localView.getPlayerHand().getPowerups();

        for(int i=0;i<cardView.length;i++)

            if (cardView[i]!=null && cardView[i].getPowerupType().equals("Newton") &&
                    this.askUser.yesOrNo("Vuoi usare un PowerUp Raggio Cinetico?","Si","No")){
                ArrayList<String> playerColors = getPlayerColors();
                String colorString = this.askUser.stringSelector("Quale player vuoi spostare? scegline il colore", playerColors);

                Color color=colorConverter(colorString);

                CellView cellOfTarget = localView.getMapView().getPlayerPosition(color);

                ArrayList<String> directionsAvailable=getDirection(cellOfTarget);
                ArrayList<Integer> maxDistanceForDirection=getMaxDistance(directionsAvailable,cellOfTarget);

                String direction=this.askUser.stringSelector("In quale direzione vuoi muoverlo?", directionsAvailable);

                int distance=1;
                if(maxDistanceForDirection.get(directionsAvailable.indexOf(direction))==2 && this.askUser.yesOrNo("Di quante celle vuoi muoverlo?","2 celle","1 cella"))
                    distance=2;

                temp.add(new PowerupUse(i,color,distance,direction,-1,-1));
            }
            else if(cardView[i]!=null && cardView[i].getPowerupType().equals("Teleporter") && this.askUser.yesOrNo("vuoi usare un PowerUp Teletrasporto?","Si","No")){

                CellView yourPosition = localView.getPlayerPosition();
                ArrayList<Coordinates> coordinates = localView.getMapView().availableCoordinates(yourPosition);

                ArrayList<String> coordToChooseFrom=new ArrayList<>();
                for(Coordinates coord: coordinates)
                    coordToChooseFrom.add(coord.toString());

                String coordChoosenCell=this.askUser.stringSelector("In quale cella ti vuoi muovere?",coordToChooseFrom);

                Coordinates coordinates1=coordinates.get(coordToChooseFrom.indexOf(coordChoosenCell));

                temp.add(new PowerupUse(i,localView.getPersonalPlayerBoardView().getColor(),0,"None",coordinates1.getX(),coordinates1.getY()));
            }
        return temp;
    }

    private boolean [] reloadManager(LocalView localView){
        boolean [] reloadableCards=new boolean[3];
        //inverts the
        for (int i = 0; i < localView.getPlayerHand().getLoadedGuns().length; i++)
            reloadableCards[i]=!localView.getPlayerHand().getLoadedGuns()[i];
        return this.askUser.cardsToReload(localView.getPlayerHand().getGuns(), reloadableCards);
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

    private Color colorConverter(String color){
        switch (color){
            case "BLUE": return Color.BLUE;
            case "YELLOW": return Color.YELLOW;
            case "VIOLET": return Color.VIOLET;
            case "GREEN": return Color.GREEN;
            case "WHITE": return Color.WHITE;
            default:return Color.RED;
        }
    }

    private ArrayList<String> getPlayerColors(){
        ArrayList<String> colors=new ArrayList<>();
        for(PlayerBoardView playerBoard: AdrenalineClient.getLocalView().getPlayerBoardViews())
            colors.add(playerBoard.getColor().toString());

        colors.remove(AdrenalineClient.getLocalView().getPersonalPlayerBoardView().getColor().toString());
        return colors;
    }

    private ArrayList<String> getDirection(CellView position){
        ArrayList<String> directions=new ArrayList<>();
        String [] dir={"Up","Down","Left","Right"};
        for (int i = 0; i < 4; i++) {
            if(!position.getCorrespondingCell().getEdge(i).equals(CellEdge.WALL))
                directions.add(dir[i]);
        }
        return directions;
    }

    private ArrayList<Integer> getMaxDistance(ArrayList<String> directionsAvailable, CellView positionOfTarget) {
        ArrayList<Integer> distances =new ArrayList<>();

        for(int i=0;i < directionsAvailable.size();i++){
            //Checks if the next cell in the same direction has a wall or if the player can be moved there
            switch (directionsAvailable.get(i)){
                case "Up": {
                    if (!AdrenalineClient.getLocalView().getMapView().getCell(positionOfTarget.getLineIndex() - 1, positionOfTarget.getColumnIndex()).getCorrespondingCell().getEdge(0).equals(CellEdge.WALL))
                        distances.add(2);
                    else
                        distances.add(1);
                }break;
                case "Down": {
                    if (!AdrenalineClient.getLocalView().getMapView().getCell(positionOfTarget.getLineIndex() + 1, positionOfTarget.getColumnIndex()).getCorrespondingCell().getEdge(1).equals(CellEdge.WALL))
                        distances.add(2);
                    else
                        distances.add(1);
                }break;
                case "Left": {
                    if (!AdrenalineClient.getLocalView().getMapView().getCell(positionOfTarget.getLineIndex(), positionOfTarget.getColumnIndex()-1).getCorrespondingCell().getEdge(2).equals(CellEdge.WALL))
                        distances.add(2);
                    else
                        distances.add(1);
                }break;
                case "Right": {
                    if (!AdrenalineClient.getLocalView().getMapView().getCell(positionOfTarget.getLineIndex(), positionOfTarget.getColumnIndex()+1).getCorrespondingCell().getEdge(3).equals(CellEdge.WALL))
                        distances.add(2);
                    else
                        distances.add(1);
                }break;
            }

        }
        return distances;
    }

}

