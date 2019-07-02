package it.polimi.se2019.model.game;

public class ActionTileFrenzy extends ActionTile{
    /**
     * This constructor, depending on the player's number in the list sets its number of actions
     * It's important to remember that depending on the actionCounter value you have two different sets
     * of available moves:
     * if you have 2 actions you can either choose from:
     *      move up to 1 square, reload if you want, then shoot;
     *      move up to 4 square;
     *      move up to 2 squares and grab something there.
     * if you have 1 action you can either choose from:
     *      move up to 2 square, reload if you want, then shoot;
     *      move up to 3 square and grab something.
     */
    public ActionTileFrenzy(int actions){

        this.actionCounter = actions;
    }

    @Override
    public int getActionCounter() {
        return super.getActionCounter();
    }

    @Override
    public ActionTileFrenzy clone(){
        return new ActionTileFrenzy(this.actionCounter);
    }
}
