package hu.grdg.projlab.util.commands;

import hu.grdg.projlab.ProtoIO;
import hu.grdg.projlab.util.Command;
import hu.grdg.projlab.util.CommandException;
import hu.grdg.projlab.util.ProtoRuntime;

import java.util.List;

public class PutSnowCommand extends Command {
    @Override
    public void runCommand(ProtoRuntime state, List<String> inputParams) throws CommandException {
        var tName = getStringArg(inputParams);
        var amount = getIntArg(inputParams);
        var tile = state.getTile(tName);
        if(tile == null) {
            ProtoIO.output(ProtoIO.OutputMessages.ERR_TILE_NOT_FOUND);
            return;
        }

        tile.addSnowLayer(amount);
        ProtoIO.output(ProtoIO.OutputMessages.PUTSNOW_OUT);

    }

    @Override
    public int getParamCount() {
        return 1;
    }
}
