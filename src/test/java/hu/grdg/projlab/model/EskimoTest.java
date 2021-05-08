package hu.grdg.projlab.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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

    @Test
    void takingDamageLowersEskimosBodyTemperature() {
        int originalTemperature = eskimo.getTemp();

        eskimo.damage(2);

        assertEquals(originalTemperature - 2, eskimo.getTemp());
    }

    @Test
    void whenUsingSpecialAbilityOnTileWithoutIglooItSucceeds() {
        assertTrue(eskimo.specialAbility());
    }

    @Test
    void whenUsingSpecialAbilityOnTileWithIglooItFails() {
        tile.buildIgloo();
        assertFalse(eskimo.specialAbility());
    }
}