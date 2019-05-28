package it.polimi.se2019.controller;

import it.polimi.se2019.model.game.Player;

import java.util.ArrayList;

/**
 * with a certain guncard you can:
 * - use one, two or three effects in a certain order
 * - select one or more targets from a list of players
 * - move (yourself or others) by a given number of cells
 * - inflict damage to players in a certain cell
 * - inflict damage to one or more players in a certain cell
 *
 * Usage of effectsOrder: examples: {"Base Opt1 Opt2", "Base Opt2 Opt1"} or {"Base", "Alternative"} meaning that
 *  you can either use one or the other. Each card has its own, but it must be customized considering which effects
 *  the individual player can pay for with ammo, and whether there are available targets using a certain effect.
 *
 *
 *
 *
 */
public class SingleCardActions{
    private String [] effectsOrder;
    private ArrayList<SingleEffectActions> effectActions;
    private boolean secondTargetCanBeSame;


}
