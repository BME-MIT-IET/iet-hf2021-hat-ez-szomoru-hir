package hu.grdg.projlab.util.commands;

import hu.grdg.projlab.ProtoIO;
import hu.grdg.projlab.model.*;
import hu.grdg.projlab.util.Command;
import hu.grdg.projlab.util.CommandException;
import hu.grdg.projlab.util.ProtoRuntime;

import java.util.List;

public class AddCommand extends Command {
    @Override
    public void runCommand(ProtoRuntime state, List<String> inputParams) throws CommandException {
        var pName = getStringArg(inputParams);
        var type = getStringArgOf(inputParams, setOf("Rope", "DivingSuit","Food","Shovel", "BreakableShovel","Tent","RocketPart"));

        var player = getPlayer(pName, state);
        if(player == null) {
            ProtoIO.output(ProtoIO.OutputMessages.ERR_PLAYER_NOT_FOUND);
            return;
        }


        var itm = initializeItem(state, type);
        if(itm != null) {
            int slot = player.addItem(itm);
            itm.setOwner(player);

            ProtoIO.outputf(ProtoIO.OutputMessages.ADD_OUT, slot);
        }

    }



    @Override
    public int getParamCount() {
        return 2;
    }
}
