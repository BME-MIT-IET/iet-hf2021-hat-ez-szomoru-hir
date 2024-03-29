package hu.grdg.projlab.model;

import hu.grdg.projlab.ProtoIO;

import java.util.ArrayList;

/**
 * The controller controls the whole game. It creates the map, the players, the events.
 * It schedules the turns, events. It checks the win and lose conditions.
 */
public class Controller {
    private ArrayList<Player> players;
    ArrayList<RocketPart> rocketParts;

    public Controller(){
        players = new ArrayList<>();
        rocketParts = new ArrayList<>();
    }

    public void addPlayer(Player p){
        players.add(p);
    }

    public void addRocketPart(RocketPart rp){
        rocketParts.add(rp);
    }

    public void endGame(boolean win){
        if(win) {
            ProtoIO.output("Item used. Game ended with a win.");
        }
        System.exit(0);
    }

    /**
     * Checks if all conditions are true for winning the game
     * @return if all conditions are true for win
     * @author Dorina
     */
    public boolean checkWin(){
        var tile = players.get(0).getCurrentTile();
        for (Player player: players) {
            if(!tile.equals(player.getCurrentTile())) return false;
        }
        for (RocketPart rp: rocketParts) {
            if(rp.getOwner() == null) return false;
        }
        endGame(true);
        return true;
    }

    /**
     * Returns the number of players in the game
     * @return the number of players
     * @author Dorina
     */
    public int getPlayerCount(){
        return players.size();
    }
}
