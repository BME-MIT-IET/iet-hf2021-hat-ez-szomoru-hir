package hu.grdg.projlab.model;

public class PolarBearStep implements TurnBasedEvent{

    @Override
    public void doEvent(Level lvl, boolean fullTurn) {
        // Do nothing, Polar bear stepping is not an event that needs side-effects
    }
}
