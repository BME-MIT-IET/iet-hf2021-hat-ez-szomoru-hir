package hu.grdg.projlab.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ScientistTest {

    Scientist scientist;
    IceTile tile;

    @BeforeEach
    void setUp() {
        scientist = new Scientist(null);
        tile = new IceTile();
        scientist.setCurrentTile(tile);
        tile.acceptEntity(scientist);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void withoutNeighbourTileScientistSpecialAbilityDoesntWork() {
        assertFalse(scientist.specialAbility());
    }

    @Test
    void whenTheresANeighbourTileScientistCanUseSpecialAbility() {
        var neighbourTileNorth = new IceTile();
        tile.setNeighbour(neighbourTileNorth, 0);
        var neighbourTileEast = new IceTile();
        tile.setNeighbour(neighbourTileEast, 1);
        var neighbourTileSouth = new IceTile();
        tile.setNeighbour(neighbourTileSouth, 2);
        var neighbourTileWest = new IceTile();
        tile.setNeighbour(neighbourTileWest, 3);

        assertTrue(scientist.specialAbility());
    }

    @Test
    void takingDamageReducesBodyTemperature() {
        int originalTemperature = scientist.getTemp();
        scientist.damage(1);

        assertEquals(originalTemperature - 1, scientist.getTemp());
    }
}