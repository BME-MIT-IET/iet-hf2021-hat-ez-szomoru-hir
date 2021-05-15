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

        Entity entity;
        if(type.equals("polar")) {
            entity = new PolarBear();
        }else {
            Player player;
            switch (type) {
                case "scientist":
                    player = new Scientist(null);
                    break;
                case "eskimo":
                    player = new Eskimo(null);
                    break;
                default:
                    throw new GameLoadException("Invalid entity type");
            }
            player.setCurrentTemp(Integer.parseInt(life));

            String[] itemNames = inv.split(",");
            for(String itemName : itemNames) {
                var item = TileTag.createItem(itemName);
                if(item != null)
                    player.addItem(item);
            }

            entity = player;
        }
        var entityClass = new EntityClass();
        entityClass.setEntity(entity);
        entityClass.setName(name);
        entityClass.setTile(tile);


        return entityClass;
    }

    @Override
    public Tag<EntityClass> createFromData(EntityClass data) {
        return null;
    }
}
