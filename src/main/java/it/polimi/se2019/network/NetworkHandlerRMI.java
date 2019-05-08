package it.polimi.se2019.network;

import it.polimi.se2019.view.LocalView;

import java.rmi.Naming;
import java.rmi.RemoteException;

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

    public LocalView getLocalView(int playerId){
        LocalView temp=null;
        try {
            temp= server.getLocalView(playerId);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        return temp;
    }
}
