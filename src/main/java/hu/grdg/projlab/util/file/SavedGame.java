package hu.grdg.projlab.util.file;

import hu.grdg.projlab.ProtoIO;
import hu.grdg.projlab.util.ProtoRuntime;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Used for loading and storing the game
 * --------------WARNING-----------------
 * ---------SAVE FORMAT CHANGED----------
 * Only single tags per line are allowed
 * First line is tile count
 * Second line is entity count
 * Third line is connection count
 * Then  tile count tiles
 * Then connection time connections
 * Then N lines of entities
 */
public class SavedGame {
    //TODO SET TO FALSE
    public static final boolean DEBUG_MODE = true;


    private int tileNumber;
    private int connectionCount;
    private int entityCount;
    private ProtoRuntime state;


    public SavedGame(ProtoRuntime state) {
        this.state = state;
    }

    private SavedGame() {
        state = new ProtoRuntime();
    }

    /**
     * Loads a SavedGame from file
     * The game state can be retrieved from the returned instance
     * @param fileName The name of the savegame file
     * @return The loaded game
     * @throws IOException if any IO error occurs
     * @throws GameLoadException if format error, or any other load error occurs
     */
    public static SavedGame load(String fileName) {
        try {
            var ins = new SavedGame();
            ins.checkFile(fileName);

            var reader = new BufferedReader(new FileReader(fileName));

            ins.loadHeader(reader);
            ins.loadContent(reader);

            reader.close();
            ProtoIO.output(ProtoIO.OutputMessages.LOAD_OUT);
            return ins;
        }catch (IOException | GameLoadException e) {
            ProtoIO.outputf(ProtoIO.OutputMessages.LOAD_ERR_FORMAT, e.getMessage());
        }
        return null;
    }

    public ProtoRuntime getState() {
        return state;
    }

    /**
     * Loads the save content
     * @param reader
     */
    private void loadContent(BufferedReader reader) throws IOException, GameLoadException {
        //Load tile contents
        for(var i = 0; i < tileNumber; i++) {
            TileClass tc = (TileClass) TagIO.readTag(reader).getData();
            state.addTile(tc.name, tc.tile);
        }

        //Load connections
        for(var i = 0; i < connectionCount; i++) {
            ConnectionClass cc = (ConnectionClass) TagIO.readTag(reader).getData();
            var t1 = state.getTile(cc.name1);
            var t2 = state.getTile(cc.name2);
            t1.setNeighbour(t2, cc.dir1);
            t2.setNeighbour(t1, cc.dir2);
        }

        for(var i = 0; i < entityCount; i++) {
            EntityClass ec = (EntityClass) TagIO.readTag(reader).getData();
            state.addEntity(ec.name, ec.entity);
            var t = state.getTile(ec.tile);
            t.acceptEntity(ec.entity);
        }



    }

    /**
     * Loads the save header from the file
     * @param reader The file reader
     */
    private void loadHeader(BufferedReader reader) throws IOException, GameLoadException {
        int tilenum = (int) TagIO.readTag(reader).getData();
        int connum = (int) TagIO.readTag(reader).getData();
        int ecount = (int) TagIO.readTag(reader).getData();
        tileNumber = tilenum;
        connectionCount = connum;
        entityCount = ecount;
        if(DEBUG_MODE) {
            ProtoIO.outputf("Map header: %d tiles, %d connections, it has %d entities", tileNumber, connectionCount, entityCount);
        }
    }

    /**
     * Checks if a file exists
     * @param fileName The path of the file
     * @throws IOException If the file does not exists
     */
    private void checkFile(String fileName) throws IOException {
        if(!new File(fileName).exists()) {
            throw new IOException("The savegame file does not exists");
        }
    }
}
