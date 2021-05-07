package hu.grdg.projlab.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ScientistTest {

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
    }

    @org.junit.jupiter.api.Test
    void specialAbility() {
    }

    @Test
    void takingDamageReducesBodyTemperature() {
        var scientist = new Scientist(null);
        int originalTemperature = scientist.getTemp();
        scientist.damage(1);

        assertEquals(originalTemperature - 1, scientist.getTemp());
    }
}