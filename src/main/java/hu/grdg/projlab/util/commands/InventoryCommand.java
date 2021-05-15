package hu.grdg.projlab.util.commands;

import hu.grdg.projlab.ProtoIO;
import hu.grdg.projlab.model.Item;
import hu.grdg.projlab.util.Command;
import hu.grdg.projlab.util.CommandException;
import hu.grdg.projlab.util.ProtoRuntime;

import java.util.List;

public class InventoryCommand extends Command {
    @Override
    public void runCommand(ProtoRuntime state, List<String> inputParams) throws CommandException {
        var pName = getStringArg(inputParams);

        var player = getPlayer(pName, state);
        if(player == null) {
            ProtoIO.output(ProtoIO.OutputMessages.ERR_PLAYER_NOT_FOUND);
            return;
        }

        var out = new StringBuilder();
        List<Item> inventory = player.getInventory();
        for (var i1 = 0; i1 < inventory.size(); i1++) {
            if(!out.toString().isEmpty())
                out.append(",");
            Item i = inventory.get(i1);
            out.append(String.format("%d: %s", i1 + 1, i.getClass().getSimpleName()));
        }

        ProtoIO.outputf(ProtoIO.OutputMessages.INVENTORY_OUT, out.toString());
    }

    @Override
    public int getParamCount() {
        return 1;
    }
}
