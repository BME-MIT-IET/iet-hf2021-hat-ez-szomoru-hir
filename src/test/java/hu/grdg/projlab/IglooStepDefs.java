package hu.grdg.projlab;

import hu.grdg.projlab.model.*;

import io.cucumber.java.ParameterType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.*;

public class IglooStepDefs {
    private Tile tile;
    private Eskimo eskimo;
    private Boolean success;

    @ParameterType(value = "i|h|u")
    public Tile tile(String value) {
        switch (value){
            case "i":
                return new IceTile();
            case "u":
                return new UnstableIceTile(2);
            case "h":
                return new HoleTile();
        }
        return new IceTile();
    }

    @ParameterType(value = "true|True|TRUE|false|False|FALSE")
    public Boolean booleanValue(String value) {
        return Boolean.valueOf(value);
    }

    @Given("Eskimo is on {tile}")
    public void eskimoIsOn(Tile tile) {
        eskimo = new Eskimo(null);
        this.tile = tile;
        this.tile.acceptEntity(eskimo);
    }

    @When("Eskimo tries to build igloo")
    public void eskimoTriesToBuildIgloo() {
        success = eskimo.specialAbility();
    }


    @Then("tile has igloo is {booleanValue}")
    public void iglooIsOnIs(Boolean result) {
        assertEquals(result, tile.hasIgloo());
    }

    @Given("Eskimo is on {tile} and it has igloo")
    public void eskimoIsOnTileAndItHasIgloo(Tile tile) {
        eskimo = new Eskimo(null);
        this.tile = tile;
        tile.buildIgloo();
        this.tile.acceptEntity(eskimo);
    }

    @Then("it is unsuccessful")
    public void itIsUnsuccessful() {
        assertFalse(success);
    }
}
