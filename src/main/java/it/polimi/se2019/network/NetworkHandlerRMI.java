package it.polimi.se2019.network;

import it.polimi.se2019.controller.AvailableActions;
import it.polimi.se2019.view.ActionRequestView;
import it.polimi.se2019.view.LocalView;

import java.rmi.Naming;
import java.rmi.RemoteException;

/**
 * This class represents the RMI network handler, that enables communication between the Remote View obj in the server
 * and the Local View in the client
 *
 * It looks for the RemoteView of the game offered by the Controller
 */

public class NetworkHandlerRMI extends NetworkHandler{

    private RMIInterface server;

    public NetworkHandlerRMI(){
        try {
            //get the server remote object
            RMIInterface server = (RMIInterface) Naming.lookup("rmi://localhost/adrenaline");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public LocalView getLocalView(int playerId) {
        LocalView temp;
        try {
            temp= server.getLocalView(playerId);
        } catch (RemoteException e) {
            return null;
        }
        return temp;
    }

    /**
     * method that builds the request for an action (when? where?)
     * it's called by the view after that's been called by the controller (through the RemoteView object)
     * @param playerId
     * @return
     */
    @Override
    public AvailableActions buildAndSendActionRequest(int playerId){
        ActionRequestView request=new ActionRequestView();
        try {
            return server.askAction(request,playerId);
        } catch (RemoteException e) {
            return null;
        }
    }
}
