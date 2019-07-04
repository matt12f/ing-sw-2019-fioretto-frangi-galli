package it.polimi.se2019.test_GUI;


import it.polimi.se2019.view.UserInteractionGUI;
import org.junit.jupiter.api.Test;


class TestGUI {

    @Test
     void testErrorMessage(){
        UserInteractionGUI userInteractionGUI = new UserInteractionGUI();

        userInteractionGUI.errorDisplay("nick");
    }

}
