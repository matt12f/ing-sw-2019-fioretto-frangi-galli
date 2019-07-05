package it.polimi.se2019.test_GUI;


import it.polimi.se2019.model.cards.CyberBlade;
import it.polimi.se2019.model.cards.GunCard;
import it.polimi.se2019.view.UserInteraction;
import it.polimi.se2019.view.UserInteractionCLI;
import it.polimi.se2019.view.UserInteractionGUI;

import java.util.ArrayList;
import java.util.Arrays;

//TODO classe da rimuovere
public class TestUserInteraction {
    private static UserInteraction askUser;

    public static void main(String[] args) {

        askUser=new UserInteractionGUI();
        //askUser=new UserInteractionCLI();

       // testGunReload();
        //testActionSelection();
        //testYesOrNo();
        //testShowMessage();
        //testSelectionFromList();

        testMap();
    }

    private static void testSelectionFromList(){
        ArrayList<String> list= new ArrayList<>(Arrays.asList("mele","pere","meloni","anguria"));

        System.out.println(askUser.stringSelector("prova la frutta",list));
    }

    private static void testShowMessage(){
        askUser.showMessage("<html>provolone<br>affumicato</html>");
    }

    private static void testYesOrNo(){
        boolean pereOMeloni = askUser.yesOrNo("scegli se vuoi le pere o i meloni","pere","meloni");
        if(pereOMeloni)
            System.out.println("Pere");
        else
            System.out.println("Meloni");
    }

    private static void testActionSelection() {
        System.out.println(askUser.actionToRequest(0));
        System.out.println(askUser.actionToRequest(1));
        System.out.println(askUser.actionToRequest(2));
    }

    private static void testGunReload(){
        GunCard[] cards=new GunCard[3];
        cards[0]=new CyberBlade();
        cards[1]=new CyberBlade();
        cards[2]=new CyberBlade();

        boolean [] reloadableCards=new boolean[3];
        reloadableCards[0]=true;
        reloadableCards[1]=true;
        reloadableCards[2]=true;

        boolean[] result=askUser.cardsToReload(cards,reloadableCards);

        System.out.println(result[0]);
        System.out.println(result[1]);
        System.out.println(result[2]);
    }

    private static void testMap(){
        int [] temp=askUser.mapChooser();
        System.out.println(temp[0]);
        System.out.println(temp[1]);
    }
}