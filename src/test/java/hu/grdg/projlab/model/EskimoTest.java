package hu.grdg.projlab.model;

import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

class EskimoTest {

    Eskimo eskimo;
    IceTile tile;

    @BeforeEach
    void setUp() {
        eskimo = new Eskimo(null);
        tile = new IceTile();
        eskimo.setCurrentTile(tile);
        tile.acceptEntity(eskimo);
    }
}