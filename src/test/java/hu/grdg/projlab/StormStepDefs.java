package hu.grdg.projlab;

import hu.grdg.projlab.model.*;
import io.cucumber.java.ParameterType;
import io.cucumber.java.an.E;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StormStepDefs {
    private Player player;
    private Tile tile;

    @ParameterType(value = "nothing|igloo|tent")
    public Tile tileWithBuilding(String value) {
        var tile = new IceTile();
        switch (value){
            case "igloo":
                tile.buildIgloo();
                break;
            case "tent":
                tile.buildTent();
                break;
        }
        return tile;
    }

    @Given("tile has {tileWithBuilding}")
    public void tileHas(Tile tile) {
        player = new Eskimo(null);
        player.setCurrentTile(tile);
        this.tile = tile;
        this.tile.acceptEntity(player);
    }

    @When("storm comes")
    public void stormComes() {
        SnowStorm.getInstance().doStorm(tile, 1);
    }

    @Then("player has {int} temp")
    public void playerHasTempTemp(Integer expected) {
        assertEquals(expected, player.getCurrentTemp());
    }
}
