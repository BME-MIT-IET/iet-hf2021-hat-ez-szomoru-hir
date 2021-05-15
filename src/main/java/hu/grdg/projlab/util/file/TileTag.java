package hu.grdg.projlab.util.file;

import hu.grdg.projlab.model.*;

public class TileTag extends Tag<TileClass> {

    public TileTag(String textData) {
        super(textData);
    }

    @Override
    public TileClass getData() throws GameLoadException {
        String[] data = textData.split(":");
        String type = data[0];
        String limit = data[1].split(",")[0];
        String snow = data[1].split(",")[1];
        String fItem = data[2].split(",")[0];
        String isFrozen = data[2].split(",")[1];
        String hasIgloo = data[3].split(",")[0];
        String hasTent = data[3].split(",")[1];
        String name = data[4];

        Tile t;
        if(type.equals("h")) {
            t = new HoleTile();
        }else if(type.equals("u")) {
            t = new UnstableIceTile(Integer.parseInt(limit));
        }else if(type.equals("i")) {
            t = new IceTile();
        }else {
            throw new GameLoadException("Invalid tile type");
        }

        t.addSnowLayer(Integer.parseInt(snow));
        var itm = createItem(fItem);
        if(itm != null)
            itm.setIsFrozen(Boolean.parseBoolean(isFrozen));
        t.setFrozenItem(itm);

        if(Boolean.parseBoolean(hasIgloo))
            t.buildIgloo();
        if(Boolean.parseBoolean(hasTent))
            t.buildTent();

        var tc = new TileClass();
        tc.name = name;
        tc.tile = t;

        return tc;
    }

    /**
     * Creates a new item from name
     * @param type The item type
     * @return The new item
     * @throws GameLoadException if the item name is invalid
     */
    public static Item createItem(String type) throws GameLoadException {
        return switch (type) {
            case "Rope" -> new Rope();
            case "DivingSuit" -> new DivingSuit();
            case "Food" -> new Food();
            case "Shovel" -> new Shovel();
            case "BreakableShovel" -> new BreakableShovel();
            case "Tent" -> new Tent();
            case "RocketPart" ->
                    //TODO FIX IT
                    new RocketPart(null);
            case "None" -> null;
            default -> throw new GameLoadException("Invalid item type");
        };
    }

    @Override
    public Tag<TileClass> createFromData(TileClass data) {
        return null;
    }
}
