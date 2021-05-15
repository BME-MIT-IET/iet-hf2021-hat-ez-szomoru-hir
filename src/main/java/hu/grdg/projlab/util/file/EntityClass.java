package hu.grdg.projlab.util.file;

import hu.grdg.projlab.model.Entity;

public class EntityClass {
    private Entity entity;
    private String name;
    private String tile;

    public Entity getEntity() {
        return entity;
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTile() {
        return tile;
    }

    public void setTile(String tile) {
        this.tile = tile;
    }
}
