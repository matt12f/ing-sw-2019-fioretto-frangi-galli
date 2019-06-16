package it.polimi.se2019.model.cards;

import it.polimi.se2019.controller.ActionManager;
import it.polimi.se2019.controller.FictitiousPlayer;
import it.polimi.se2019.controller.MapManager;
import it.polimi.se2019.model.game.Player;
import it.polimi.se2019.view.ChosenActions;
import it.polimi.se2019.controller.SingleEffectsCombinationActions;
import it.polimi.se2019.exceptions.UnavailableEffectCombinationException;

import java.util.ArrayList;

public class LockRifle extends GunCardAddEff {
    /**
     * hard-coded constructor
     */
    public LockRifle() {
        super();
        ArrayList<String> secondCombination=new ArrayList<>();
        secondCombination.add("Base");
        secondCombination.add("Optional1");
        this.effectsOrder.add(secondCombination);

        this.numberOfOptional = 1;
        this.ammoCost = new char[2];
        ammoCost[0]= 'b';
        ammoCost[1]= 'b';
        this.description ="basic effect: Deal 2 damage and 1 mark to 1 target\n" +
                "you can see.\n" + "with second lock: Deal 1 mark to a different target\n" +
                "you can see.";

        this.secondaryEffectCost = new char[1];
        secondaryEffectCost[0] = 'r';
        this.tertiaryEffectCost =null;

    }

    @Override
    public SingleEffectsCombinationActions buildAvailableActions(ArrayList<String> effectsCombination, FictitiousPlayer player) throws UnavailableEffectCombinationException {
        SingleEffectsCombinationActions actions=new SingleEffectsCombinationActions();
        for(String effect: effectsCombination){

            if (effect.equals("Base"))
                targetsOfBaseEffect(actions, player);
            else if (effect.equals("Optional1"))
                targetsOfSecondaryEffect(actions, player);
            else if(effect.equals("Optional2"))
                targetsOfTertiaryEffect(actions,player);

        }
        actions.validate();
        return actions;
    }

    @Override
    void applyBaseEffect(ChosenActions playersChoice) {
        //TODO scrivere metodo
    }

    @Override
    void applySecondaryEffect(ChosenActions playersChoice) {
        //TODO scrivere metodo
    }

    /**
     * Deal 2 damage and 1 mark to 1 target you can see.
     */
    @Override
    void targetsOfBaseEffect(SingleEffectsCombinationActions actions, FictitiousPlayer player) {
        ArrayList<Player> targets=new ArrayList<>(ActionManager.visibleTargets(player));
        actions.addToTargetList1(targets);
        actions.setMaxNumberOfTargetsList1(1);
    }

    /**
     * Deal 1 mark to a different target you can see.
     */
    @Override
    void targetsOfSecondaryEffect(SingleEffectsCombinationActions actions, FictitiousPlayer player) {
        //TODO basta scegliere di usare l'effetto
        //TODO basta mettere un flag che dica che il target dev'essere diverso
        // Nella view verrà (proponendo la stessa lista di prima - quello già scelto)
    }

    //useless methods
    @Override
    void applyTertiaryEffect(ChosenActions playersChoice) {
        //Does nothing
    }
    @Override
    void targetsOfTertiaryEffect(SingleEffectsCombinationActions actions, FictitiousPlayer player) {
        //does nothing
    }

}