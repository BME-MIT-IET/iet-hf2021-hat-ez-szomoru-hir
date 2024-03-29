package hu.grdg.projlab.model;

import hu.grdg.projlab.ProtoIO;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class Player extends Entity{
    protected int maxTemp;
    private ArrayList<Item> inventory;
    private Controller controller;
    private int currentTemp;
    private boolean isInWater;


    /**
     * Sets maxTemp for player, init other attributes
     * @param maxTemp Maximum temperature
     * @author Geri, Dorina
     */
    protected Player(int maxTemp, Controller controller){
        inventory = new ArrayList<>();
        this.maxTemp = maxTemp;
        currentTemp = maxTemp;
        isInWater = false;
        this.controller = controller;
    }

    /**
     * Returns the Entity's inventory
     * @return the Entity's inventory
     * @author Dorina
     */
    public List<Item> getInventory() {
        return inventory;
    }

    /**
     * Add new item to the inventory
     * @param item new Item
     * @return The index of the added item
     * @author Dorina
     */
    public int addItem(Item item) {
        inventory.add(item);
        return inventory.size();
    }

    //----------------NOT IN DOC-----------
    /**
     * Sets the current temp
     * @param currentTemp The current temp
     */
    public void setCurrentTemp(int currentTemp) {
        this.currentTemp = currentTemp;
    }

    //----------WARNING-------------
    //IMPLEMENTATION HAS TO PRINT THE OUTPUT MESSAGE
    public abstract boolean specialAbility();

    /**
     * Entity eats food
     * @param f the food that the Entity eats
     * @return if the eating was successful
     * @author Dorina
     */
    public boolean eat(Food f){
        if(maxTemp == currentTemp) return false;
        currentTemp++;
        inventory.remove(f);
        return true;
    }

    /**
     * Save player from near holes
     * @return if the saving was successful
     * @author Dorina
     */
    public boolean savingPlayers(){
        var succ = false;
        Collection<Tile> neighbours = currentTile.getNeighbours();
        for (Tile neighbour: neighbours) {
            ArrayList<Entity> entities = new ArrayList<>(neighbour.getEntities());
            for (Entity entity: entities) {
                if(entity.savedFromWater(currentTile)) succ = true;
            }
        }
        return succ;
    }

    /**
     * Entity moves at the given direction
     * @param direction the direction in which the Entity moves
     * @author Dorina
     */
    @Override
    public void move(int direction) {
        if(isInWater) return;
        var newTile = currentTile.getNeighbour(direction);
        currentTile.removeEntity(this);
        newTile.acceptEntity(this);
    }

    /**
     * Decrease the Entity's current temp with the given amount
     * @param i the amount of temp the Entity's currentTemp decrease
     * @author Dorina
     */
    @Override
    public void damage(int i) {
        currentTemp -= i;
        ProtoIO.output(ProtoIO.OutputMessages.SNOWSTORM_OUT_PLAYERDAMAGE);
        if(currentTemp<=0) die();
    }

    /**
     * The player dies
     * @author Dorina
     */
    @Override
    public void die() {
        ProtoIO.output(ProtoIO.OutputMessages.SNOWSTORM_OUT_PLAYERDIE);
        controller.endGame(false);
    }

    /**
     * Sets the isInWater to true;
     * @author Dorina
     */
    @Override
    public void fallInWater() {
        isInWater = true;
    }

    /**
     * Sets the isInWater to false
     * @author Dorina
     */
    public boolean surviveWater(){
        if(isInWater) {
            isInWater = false;
            return true;
        }
        return false;
    }


    /**
     * Save the entity from drowning
     * @param tile the tile where the entity steps
     * @return if the saving was successful
     * @author Dorina
     */
    public boolean savedFromWater(Tile tile){
        if(isInWater){
            currentTile.removeEntity(this);
            tile.acceptEntity(this);
            isInWater = false;
            return true;
        }
        return false;
    }

    public int getCurrentTemp(){
        return currentTemp;
    }



}
