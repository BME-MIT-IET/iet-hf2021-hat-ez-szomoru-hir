package hu.grdg.projlab.util;

import hu.grdg.projlab.model.*;

public class ItemFactory {
    private ItemFactory() { }

    public static Item createItem(ProtoRuntime state, String type) {
        Item item = null;
        switch (type) {
            case "Rope":
                item = new Rope();
                break;
            case "DivingSuit":
                item = new DivingSuit();
                break;
            case "Food":
                item = new Food();
                break;
            case "Shovel":
                item = new Shovel();
                break;
            case "BreakableShovel":
                item = new BreakableShovel();
                break;
            case "Tent":
                item = new Tent();
                break;
            case "RocketPart":
                item = new RocketPart(state.getController());
                break;
            default:
                break;
        }
        return item;
    }
}
