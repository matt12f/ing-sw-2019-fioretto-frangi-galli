package it.polimi.se2019.view;

import it.polimi.se2019.model.cards.GunCard;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class UserInteractionCLI extends UserInteraction {

    @Override
    public String actionToRequest(int frenzy) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<String> available = new ArrayList<>();
        switch (frenzy) {
            case 0: {
                System.out.println("Azioni disponibili: move, grab o shoot");
                available.addAll(Arrays.asList("move", "grab", "shoot"));
            }
            break;
            case 1: {
                System.out.println("Azioni disponibili: frenzy1, frenzy2, frenzy3");
                available.addAll(Arrays.asList("frenzy1", "frenzy2", "frenzy3"));
            }
            break;
            case 2:{
                System.out.println("Azioni disponibili: frenzy4, frenzy5");
                available.addAll(Arrays.asList("frenzy4", "frenzy5"));
        }break;
            default:break;
        }

        System.out.print("Inserire la macro azione da svolgere: ");
        String action = null;

        do {

            try {
                action = reader.readLine().toLowerCase();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }while(!available.contains(action));
        return action;
    }

    @Override
    public boolean[] cardsToReload(GunCard[] cards, boolean[] reloadableCards) {
        return new boolean[0];
    }

    @Override
    public boolean yesOrNo(String message) {
        return false;
    }

    @Override
    public String stringSelector(String message, ArrayList<String> listToChooseFrom) {
        return null;
    }


}
