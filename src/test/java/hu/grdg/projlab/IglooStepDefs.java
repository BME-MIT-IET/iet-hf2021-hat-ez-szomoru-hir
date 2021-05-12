package hu.grdg.projlab;

import hu.grdg.projlab.model.Tile;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class IglooStepDefs {
    private Tile tile;

    @Given("Eskimo is on {string}")
    public void eskimoIsOn(String tile) {
    }

    @When("Eskimo tries to build igloo")
    public void eskimoTriesToBuildIgloo() {
    }


    @Then("igloo is on {string} is {string}")
    public void iglooIsOnIs(String arg0, String arg1) {
    }
}
