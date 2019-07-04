package it.polimi.se2019.network;

import it.polimi.se2019.AdrenalineServer;
import it.polimi.se2019.enums.Status;

import java.util.Timer;

//this class monitors the lobby to check if there are the conditions to start the game

public class LobbyMonitor implements Runnable{
    @Override
    public void run(){
        int connectionNumber = 0;
        int prev = 0;
        boolean condition = true;
        boolean newbie = false;
        boolean timerStarted = false;
        Timer timer = new Timer();
        Thread thread;
        long time = 10; //todo settare tempo timer prendendolo da file
        GameStarter gameToStart = new GameStarter();
        while(condition) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(newbie)
                gameToStart = new GameStarter();
            if (!AdrenalineServer.getLobby().isEmpty()) {
                connectionNumber = AdrenalineServer.clientCounter();
                if(connectionNumber != prev){
                    for (ClientHandler client: AdrenalineServer.getLobbyClient()) {
                        client.setStatus(Status.UPDATE);
                    }
                }
                if (connectionNumber >= 5){
                    if(timerStarted){
                        timer.cancel();
                        timerStarted = false;
                    }
                    thread = new Thread(gameToStart);
                    thread.start();
                }else if(connectionNumber == 3 || connectionNumber == 4){
                    if(!timerStarted){
                        timerStarted = true;
                        timer.schedule(gameToStart, time);
                        newbie = true;
                    }
                }else{
                    if(timerStarted){
                        timerStarted = false;
                        timer.cancel();
                    }
                }
                prev = connectionNumber;
            }
        }
    }
}
