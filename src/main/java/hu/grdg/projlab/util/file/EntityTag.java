package hu.grdg.projlab.util.file;

import hu.grdg.projlab.model.*;

public class EntityTag extends Tag<EntityClass> {
    public EntityTag(String textData) {
        super(textData);
    }

    @Override
    public EntityClass getData() throws GameLoadException {
        String[] parts = textData.split(":");

        String name = parts[0];
        String type = parts[1];
        String tile = parts[2];
        String life = parts[3];
        String inv = parts[4];

        Entity e = null;
        if(type.equals("polar")) {
            e = new PolarBear();
        }else {
            Player p = null;
            switch (type) {
                case "scientist":
                    p = new Scientist(null);
                    break;
                case "eskimo":
                    p = new Eskimo(null);
                    break;
                default:
                    throw new GameLoadException("Invalid entity type");
            }
            p.setCurrentTemp(Integer.parseInt(life));

            String[] itms = inv.split(",");
            for(String item : itms) {
                var itm = TileTag.createItem(item);
                if(itm != null)
                    p.addItem(itm);
            }

            e = p;
        }
        var ec = new EntityClass();
        ec.setEntity(e);
        ec.setName(name);
        ec.setTile(tile);


        return ec;
    }

    @Override
    public Tag<EntityClass> createFromData(EntityClass data) {
        return null;
    }
}
