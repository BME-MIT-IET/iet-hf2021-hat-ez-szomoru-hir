package hu.grdg.projlab.model;

public class PolarBear extends Entity{

    /**
     * Steps in given direction
     * @param direction Direction of step
     * @author Geri
     */
    @Override
    public void move(int direction) {
        var stepTile = currentTile.getNeighbour(direction);
        currentTile.removeEntity(this);

        stepTile.acceptEntity(this);
        stepTile.bearAttack();
    }

    /**
     * Does nothing, can't be damaged
     * @param i Amount of damage
     * @author Dorina, Geri
     */
    @Override
    public void damage(int i) {
        // Do nothing, Polar bear cannot be damaged
    }

    /**
     * Does nothing, cannot die
     * @author Dorina, Geri
     */
    @Override
    public void die() {
        // Do nothing, Polar bear cannot die.
    }

    /**
     * Does nothing, cannot suffocate
     * @author Dorina, Geri
     */
    @Override
    public void fallInWater() {
        // Do nothing, Polar bear cannot suffocate
    }

    /**
     * Does nothing, cannot be saved
     * @param t Not relevant
     * @return false
     * @author Dorina, Geri
     */
    @Override
    public boolean savedFromWater(Tile t) {
        return false;
    }
}
