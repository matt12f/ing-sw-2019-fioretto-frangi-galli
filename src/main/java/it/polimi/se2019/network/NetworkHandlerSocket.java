package it.polimi.se2019.network;

import it.polimi.se2019.view.LocalView;

public class NetworkHandlerSocket extends NetworkHandler {

    @Override
    public LocalView getLocalView(int playerId) {
        return null;
    }

    @Override
    public int[] buildAndSendActionRequest(int playerId) {
        return new int[0];
    }
}