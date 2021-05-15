package hu.grdg.projlab.model;

public class Direction {
    private Direction() {
    }

    private static int direction;

    public static int getDirection() {
        return direction;
    }

    public static void setDirection(int direction) {
        Direction.direction = direction;
    }
}
