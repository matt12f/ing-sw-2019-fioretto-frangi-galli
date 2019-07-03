package it.polimi.se2019.view;

import it.polimi.se2019.model.cards.GunCard;
import it.polimi.se2019.model.cards.PowerupCard;

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
    public boolean yesOrNo(String message, String textYesButton, String textNoButton) {
        return false;
    }

    @Override
    public String stringSelector(String message, ArrayList<String> listToChooseFrom) {
        return null;
    }

    @Override
    public void showMessage(String message) {
        System.out.println(message);
    }

    @Override
    public void ammoTileViewer(String content) {
            StringBuilder builder=new StringBuilder("La AmmoTile che hai raccolto contiene: ");
            for (int i = 0; i < content.length(); i++)
                switch (content.charAt(i)){
                    case 'y':builder.append("1 Yellow Ammo");break;
                    case 'b':builder.append("1 Blue Ammo");break;
                    case 'r': builder.append("1 Red Ammo");break;
                    case 'p':builder.append("1 PowerUp");break;
                    default:break;
                }
            System.out.println(builder.toString());
    }

    @Override
    public int[] mapChooser() {
        return new int[0];
    }

    @Override
    public PowerupCard spawnChooser(PowerupCard[] list) {
        return null;
    }
}
