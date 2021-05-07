package hu.grdg.projlab.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ScientistTest {

    Scientist scientist;
    IceTile tile;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        scientist = new Scientist(null);
        tile = new IceTile();
        scientist.setCurrentTile(tile);
        tile.acceptEntity(scientist);
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
    }

    @org.junit.jupiter.api.Test
    void whenNotOnTileSpecialAbilityDoesntWork() {
        assertFalse(scientist.specialAbility());
    }

    @Test
    void takingDamageReducesBodyTemperature() {
        int originalTemperature = scientist.getTemp();
        scientist.damage(1);

        assertEquals(originalTemperature - 1, scientist.getTemp());
    }
}