package it.polimi.se2019.model.cards;

import it.polimi.se2019.controller.FictitiousPlayer;
import it.polimi.se2019.enums.CellEdge;
import it.polimi.se2019.view.ChosenActions;
import it.polimi.se2019.controller.SingleEffectsCombinationActions;
import it.polimi.se2019.exceptions.UnavailableEffectCombinationException;

import java.util.ArrayList;

public class FlameThrower extends GunCardAltEff {
    /**
     * hard-coded constructor
     */
    public FlameThrower() {
        super();
        this.ammoCost = new char[1];
        ammoCost[0]= 'r';
        this.description ="basic mode: Choose a square 1 move away and possibly a second square\n" +
                "1 more move away in the same direction. On each square, you may\n" +
                "choose 1 target and give it 1 damage\n"+
                "in barbecue mode: Choose 2 squares as above. Deal 2 damage to\n" +
                "everyone on the first square and 1 damage to everyone on the second\n" +
                "square.";

        this.secondaryEffectCost = new char[2];
        secondaryEffectCost[0] = 'y';
        secondaryEffectCost[1] = 'y';
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
     * Choose a square 1 move away and possibly a second square 1 more move away in the same direction.
     * On each square, you may choose 1 target and give it 1 damage.
     */
    @Override
    void targetsOfBaseEffect(SingleEffectsCombinationActions actions, FictitiousPlayer player) {
        //TODO 8 liste di target? una per direzione?
        //Nota di riflessione: una volta individuata la cella, la si può mostrare e il player poi sceglierà chi colpire
        //dall'elenco dei giocatori ricavato dalla view, in modo da non dover inviare le liste possibili di targets.
        //TODO valutare spostamento scelta dei player da colpire nella view (dai dati salvati lì). Qui si calcolano altre
        //ad esempio le celle che può scegliere e poi si chiede nella view chi colpire al loro interno, riducendo
        // la complessità del calcolo qui
    }

    /**
     *Choose 2 squares as above.
     *Deal 2 damage to everyone on the first square and 1 damage to everyone on the second square.
     */
    @Override
    void targetsOfSecondaryEffect(SingleEffectsCombinationActions actions, FictitiousPlayer player) {
        //TODO come sopra
    }
}