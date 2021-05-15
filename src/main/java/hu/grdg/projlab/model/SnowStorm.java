package hu.grdg.projlab.model;

public class SnowStorm implements TurnBasedEvent{
    private static final SnowStorm instance = new SnowStorm();

    public static SnowStorm getInstance() {
        return instance;
    }

    private SnowStorm() { }

    @Override
    public void doEvent(Level lvl, boolean fullTurn) {
        //Do nothing, Snow storm does not create an event
    }

    /**
     * Do the storm on the tile. It adds the snow and damages the players.
     * @param tile Target tile
     * @param amount Amount of snow to add
     * @author Barrow099
     */
    public void doStorm(Tile tile, int amount) {
        tile.addSnowLayer(amount);
        tile.stormDamage();
    }
}
