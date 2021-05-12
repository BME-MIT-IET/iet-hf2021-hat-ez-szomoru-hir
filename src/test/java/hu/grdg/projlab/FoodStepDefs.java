package hu.grdg.projlab;

import hu.grdg.projlab.model.*;
import io.cucumber.java.ParameterType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.assertEquals;

public class FoodStepDefs {
    private Player player;

    @ParameterType(value = "eskimo|scientist")
    public Player playerType(String value) {
        switch (value){
            case "eskimo":
                return new Eskimo(null);
            case "scientist":
                return new Scientist(null);
        }
        return new Eskimo(null);
    }


    @Given("{playerType} has food and {int} temp")
    public void playerHasFoodAndTempTemp(Player _player, Integer temp) {
        player = _player;
        player.setCurrentTemp(temp);
        var food = new Food();
        player.addItem(food);
        food.setFuckinOwner(player);
    }

    @When("player eat food")
    public void playerEatFood() {
        player.getInventory().get(0).useItem();
    }

    @Then("its temp is {int}")
    public void itsTempIsTemp(int expected) {
        assertEquals(expected, player.getCurrentTemp());
    }
}
