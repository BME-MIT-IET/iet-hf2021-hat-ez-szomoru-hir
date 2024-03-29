package hu.grdg.projlab.util;

import hu.grdg.projlab.ProtoIO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Provides the command input facilities to the proto
 * @author Barrow099
 */
public class ProtoInputSystem {

    private final HashMap<String, Command> commands = new HashMap<>();


    /**
     * Registets a new command to the command system with the provided name
     * @param name The name of the command. The command will be executed when the user
     *             types in this name
     * @param command The command instance
     */
    public void registerCommand(String name, Command command) {
        this.commands.put(name, command);
    }

    /**
     * Starts command parsing, it returns when EOF occurs on System.in
     */
    public void start() {
        var gameState = new ProtoRuntime();
        try (var reader = new BufferedReader(new InputStreamReader(System.in))) {
            String commandLine;
            while ((commandLine = reader.readLine()) != null) {
                String[] commandParts = commandLine.split(" ");
                String command = commandParts[0];

                if (commands.containsKey(command)) {
                    var c = commands.get(command);
                    if (commandParts.length < c.getParamCount() + 1) {
                        ProtoIO.outputf("Command error: The command requires %d arguments but only %d were provided", c.getParamCount(), commandParts.length - 1);
                    } else {
                        runCommand(gameState, c, commandParts);
                    }
                } else {
                    ProtoIO.output("Command error: Invalid command");
                }
            }
        } catch (IOException e) {
            ProtoIO.output("Error occured :(");
        }
        //The universe blows up
    }

    private void runCommand(ProtoRuntime gameState, Command c, String[] commandParts) {
        List<String> args = new ArrayList<>();
        if(c.getParamCount() > 0) {
            args.addAll(Arrays.asList(commandParts).subList(1, commandParts.length));
        }
        try {
            c.runCommand(gameState, args);
        }catch (CommandException e) {
            ProtoIO.output("Command error: " + e.getMessage());
        }
    }
}
