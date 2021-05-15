package hu.grdg.projlab;

import hu.grdg.projlab.model.*;
import io.cucumber.java.ParameterType;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.sl.In;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RemoveSnowStepDefs {
    private Player player;

    @ParameterType(value = "usable|broken")
    public Boolean isUsable(String value){
        return value.equals("usable");
    }

    @Given("tile has {int} snow layers")
    public void tileHasLayer_nSnowLayers(Integer num) {
        player = new Eskimo(null);
        Tile tile = new IceTile();
        tile.acceptEntity(player);
        tile.addSnowLayer(num);
    }

    @When("player remove {int} times")
    public void playerRemoveNumberTimes(Integer num) {
        for(int i = 0; i < num; i++){
            player.getCurrentTile().removeSnowLayer(1);
        }
    }

    @Then("tile has {int} snow layers remaining")
    public void tileHasResultSnowLayersRemaining(Integer expected) {
        assertEquals(expected, player.getCurrentTile().getSnowLayers());
    }

    @When("player remove {int} times with item")
    public void playerRemoveNumberTimesWithItem(Integer num) {
        var item = player.getInventory().get(0);
        if(item != null){
            for(int i = 0; i < num; i++){
                item.useItem();
            }
        }

    }

    @And("player has shovel")
    public void playerHasShovel() {
        var shovel = new Shovel();
        shovel.setOwner(player);
        player.addItem(shovel);
    }

    @And("player has breakable shovel")
    public void playerHasBreakableShovel() {
        var shovel = new BreakableShovel();
        shovel.setOwner(player);
        player.addItem(shovel);
    }

    @And("shovel is {isUsable}")
    public void shovelIsBroken(Boolean expected) {
        var shovel = player.getInventory().get(0);
        var tile = new IceTile();
        tile.addSnowLayer(1);
        player.setCurrentTile(tile);
        var usable = shovel.useItem();
        assertEquals(expected, usable);
    }
}
