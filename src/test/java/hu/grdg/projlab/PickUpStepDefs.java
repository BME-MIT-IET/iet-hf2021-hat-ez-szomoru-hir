package hu.grdg.projlab;

import hu.grdg.projlab.model.*;
import io.cucumber.java.ParameterType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.assertEquals;

public class PickUpStepDefs {
    private Player player;
    private Boolean success;
    private Item item;

    @ParameterType(value = "frozen|unfreezed")
    public Boolean isFrozen(String value){
        return value.equals("frozen");
    }

    @ParameterType(value = "reachable|unreachable")
    public Boolean isReachable(String value){
        return value.equals("reachable");
    }

    @ParameterType(value = "successful|unsuccessful")
    public Boolean isSuccessful(String value){
        return value.equals("successful");
    }

    @Given("item is {isFrozen} and {isReachable}")
    public void itemIsFrozenAndReachable(Boolean frozen, Boolean reachable) {
        player = new Eskimo(null);
        Tile tile = new IceTile();
        tile.acceptEntity(player);
        item = new Rope();
        tile.setFrozenItem(item);
        if(!frozen){
            tile.getFrozenItem().setIsFrozen(false);
        }
        if(!reachable){
            tile.addSnowLayer(1);
        }
    }

    @When("player tries to pick up")
    public void playerTriesToPickUp() {
        success = player.getCurrentTile().pickupItem(player);
    }

    @Then("player has the item is {booleanValue}")
    public void playerHasTheItemIsResult(Boolean expected) {
        assertEquals(expected, success);
    }

    @When("player tries to unfreeze it")
    public void playerTriesToUnfreezeIt() {
        var tile = player.getCurrentTile();
        var frozenitem = tile.getFrozenItem();
        if(frozenitem != null){
            success = frozenitem.unfreeze();
        }
        else{
            success = false;
        }
    }

    @Then("the unfreezing was {isSuccessful}")
    public void theItemIsUnfreezedIsResult(Boolean expected) {
        assertEquals(expected, success);
    }
}
