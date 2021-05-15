package hu.grdg.projlab;

import hu.grdg.projlab.model.Controller;
import hu.grdg.projlab.model.IceTile;
import hu.grdg.projlab.model.RocketPart;
import hu.grdg.projlab.model.Scientist;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.assertFalse;

public class RocketStepDefs {
    private RocketPart part = null;
    private boolean success;


    @Given("Scientist has {int} parts")
    public void scientistHasNumberParts(Integer number) {
        Controller c = new Controller();
        Scientist scientist = new Scientist(c);
        new IceTile().acceptEntity(scientist);
        c.addPlayer(scientist);
        for(int i = 0; i < number; i++){
            part = new RocketPart(c);
            scientist.addItem(part);
            part.setOwner(scientist);
        }
        for(int i = number; i < 3; i++){
            part = new RocketPart(c);
        }
    }

    @When("tries to build rocket")
    public void triesToBuildRocket() {
        success = part.useItem();
    }

    @Then("it is not successful")
    public void itIsSuccessful() {
        assertFalse(success);
    }
}
