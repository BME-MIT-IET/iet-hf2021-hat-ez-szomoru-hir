package hu.grdg.projlab.util.commands;

import hu.grdg.projlab.ProtoIO;
import hu.grdg.projlab.model.PolarBear;
import hu.grdg.projlab.model.Tile;
import hu.grdg.projlab.util.Command;
import hu.grdg.projlab.util.CommandException;
import hu.grdg.projlab.util.ProtoRuntime;

import java.util.List;

public class PolarBearCommand extends Command {
    @Override
    public void runCommand(ProtoRuntime state, List<String> inputParams) throws CommandException {
        var name = getStringArg(inputParams);
        var fieldName = getStringArg(inputParams);

        if(state.getEntity(name) != null) {
            ProtoIO.output(ProtoIO.OutputMessages.ERR_NAME_USED);
            return;
        }

        Tile t = null;
        if((t = state.getTile(fieldName)) == null) {
            ProtoIO.output(ProtoIO.OutputMessages.ERR_TILE_NOT_FOUND);
            return;
        }

        var npb = new PolarBear();
        state.addEntity(name, npb);
        ProtoIO.output(ProtoIO.OutputMessages.POLARBEAR_OUT);

        ProtoIO.mute();
        t.acceptEntity(npb);
        ProtoIO.unmute();
    }

    @Override
    public int getParamCount() {
        return 2;
    }
}
