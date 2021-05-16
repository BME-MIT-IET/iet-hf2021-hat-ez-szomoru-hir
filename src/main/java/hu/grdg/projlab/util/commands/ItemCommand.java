package hu.grdg.projlab.util.commands;

import hu.grdg.projlab.ProtoIO;
import hu.grdg.projlab.model.*;
import hu.grdg.projlab.util.Command;
import hu.grdg.projlab.util.CommandException;
import hu.grdg.projlab.util.ItemFactory;
import hu.grdg.projlab.util.ProtoRuntime;

import java.util.List;

public class ItemCommand extends Command {
    @Override
    public void runCommand(ProtoRuntime state, List<String> inputParams) throws CommandException {
        var type = getStringArgOf(inputParams, setOf("Rope", "DivingSuit","Food","Shovel", "BreakableShovel","Tent","RocketPart"));
        var fieldName = getStringArg(inputParams);
        Tile t;
        if((t = state.getTile(fieldName)) == null) {
            ProtoIO.output(ProtoIO.OutputMessages.ERR_TILE_NOT_FOUND);
            return;
        }

        var itm = ItemFactory.createItem(state, type);
        t.setFrozenItem(itm);
        ProtoIO.output(ProtoIO.OutputMessages.ITEM_OUT);
    }

    @Override
    public int getParamCount() {
        return 2;
    }
}
