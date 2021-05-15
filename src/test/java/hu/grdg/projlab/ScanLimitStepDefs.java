package hu.grdg.projlab;

import hu.grdg.projlab.model.*;
import io.cucumber.java.ParameterType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ScanLimitStepDefs {
    private Player player;
    private int result;

    @ParameterType(value = "i|h")
    public Tile tileType(String value) {
        switch (value){
            case "i":
                return new IceTile();
            case "h":
                return new HoleTile();
        }
        return new IceTile();
    }


    @Given("tile is type {tileType}")
    public void tileIsTypeTile_type(Tile tile) {
        player = new Scientist(null);
        var currentTile = new IceTile();
        currentTile.setNeighbour(tile, 1);
        player.setCurrentTile(currentTile);
    }

    @When("scientist scan tile")
    public void scientistScanTile() {
        Direction.setCode(1);
        result = player.getCurrentTile().getNeighbour(1).scanLimit();
    }

    @Then("it should return {int}")
    public void itShouldReturnLimit(Integer expected) {
        assertEquals(expected, result);
    }

    @Given("tile is unstable and has {int} limit")
    public void tileIsUnstableAndHasLimitLimit(Integer limit) {
        player = new Scientist(null);
        var currentTile = new IceTile();
        currentTile.setNeighbour(new UnstableIceTile(limit), 1);
        player.setCurrentTile(currentTile);
    }
}
