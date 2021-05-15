package hu.grdg.projlab.util;

import hu.grdg.projlab.model.*;

public class ItemFactory {
    private ItemFactory() { }

    protected static Item initializeItem(ProtoRuntime state, String type) {
        Item itm = null;
        switch (type) {
            case "Rope":
                itm = new Rope();
                break;
            case "DivingSuit":
                itm = new DivingSuit();
                break;
            case "Food":
                itm = new Food();
                break;
            case "Shovel":
                itm = new Shovel();
                break;
            case "BreakableShovel":
                itm = new BreakableShovel();
                break;
            case "Tent":
                itm = new Tent();
                break;
            case "RocketPart":
                itm = new RocketPart(state.getController());
                break;
            default:
                break;
        }
        return itm;
    }
}
