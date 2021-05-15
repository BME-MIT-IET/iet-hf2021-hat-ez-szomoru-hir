package hu.grdg.projlab.model;

public class Direction {
    private Direction() {
    }

    private static int code;

    public static int getCode() {
        return code;
    }

    public static void setCode(int code) {
        Direction.code = code;
    }
}
