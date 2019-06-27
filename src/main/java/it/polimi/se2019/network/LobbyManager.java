package it.polimi.se2019.network;

import it.polimi.se2019.AdrenalineServer;

//this class monitors the lobby to check if there are the conditions to start the game

public class LobbyManager implements Runnable{
    @Override
    public void run(){ //todo pensare se vada sostituito con qualche condizione
        int connectioNumber;
        boolean condition = true;
        while(condition) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (!AdrenalineServer.getLobby().isEmpty()) {
                connectioNumber = AdrenalineServer.clientCounter();
                if (connectioNumber >= 5){
                    //todo avvia GameHandler
                    //todo pulire lobby di AdrenalineServer per restartarle (se + di 5 prendi solo i primi 5 e gli altri rimangono in coda)
                }else if(connectioNumber == 3 || connectioNumber == 4){
                    //todo se il timer non è ancora partito fallo partire
                }else{
                    //todo controlla se il timer è partito, nel caso cacellalo
                }
            }
        }
    }
}
