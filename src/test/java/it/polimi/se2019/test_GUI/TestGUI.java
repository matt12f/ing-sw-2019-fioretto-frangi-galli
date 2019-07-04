package it.polimi.se2019.test_GUI;


import it.polimi.se2019.view.UserInteractionGUI;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;


class TestGUI {

    @Test
     void testErrorMessage(){
        UserInteractionGUI userInteractionGUI = new UserInteractionGUI();

        userInteractionGUI.errorDisplay("nick");
    }

    @Test
    public void testGUIUpdate(){
        assertTrue(true);
        //test per vedere se si aggiorna la GUI della board
        //mette un giocatore in una cella, lo sposta e poi aggiorna
        //check se è tutto a posto
    }

}
