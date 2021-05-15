package hu.grdg.projlab.util.file;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.function.Function;
import java.util.regex.Pattern;

/**
 * Reads tags from input
 */
public class TagIO {

    private static HashMap<String, Function<String, Tag<?>>> generators = new HashMap<>();
    private static final String TAG_REGEX = "<([a-zA-Z0-9])+ \\| ([a-zA-Z0-9.:;', ]+)>";

    private TagIO() { }

    /**
     * Register tag handlers for tags
     */
    public static void registerTags() {
        generators.put("tilecount", HeaderTag::new);
        generators.put("conncount", HeaderTag::new);
        generators.put("entitycount", HeaderTag::new);
        generators.put("tile", TileTag::new);
        generators.put("connection", ConnectionTag::new);
        generators.put("entity", EntityTag::new);
    }

    /**
     * Reads a single tag from the input
     * @param reader Input reader
     * @param <T> Type of tag data
     * @return The read tag
     * @throws IOException on IO error
     * @throws GameLoadException on logic error
     */
    public static <T> Tag<T> readTag(BufferedReader reader) throws IOException, GameLoadException {
        String line = reader.readLine();
        var pattern = Pattern.compile(TAG_REGEX);
        var m = pattern.matcher(line);
        if(m.find()) {
            String type = line.split("\\|")[0].replace("<","").trim();
            String value = line.split("\\|")[1].replace(">","").trim();

            if(generators.containsKey(type)) {
                return (Tag<T>) generators.get(type).apply(value);
            }else {
                throw new GameLoadException("Invalid tag type");
            }
        }else {
            throw new GameLoadException("Invalid tag format");
        }
    }
}
