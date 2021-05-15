package hu.grdg.projlab.util.commands;

import hu.grdg.projlab.ProtoIO;
import hu.grdg.projlab.model.Tile;
import hu.grdg.projlab.util.Command;
import hu.grdg.projlab.util.CommandException;
import hu.grdg.projlab.util.ProtoRuntime;

import java.util.List;

public class SetNeighbourCommand extends Command {

    @Override
    public void runCommand(ProtoRuntime state, List<String> inputParams) throws CommandException {
        var tile1 = getStringArg(inputParams);
        var dir1 = getIntArg(inputParams);
        var tile2 = getStringArg(inputParams);
        var dir2 = getIntArg(inputParams);

        var t1 = state.getTile(tile1);
        var t2 = state.getTile(tile2);

        if(t1 == null || t2 == null) {
            ProtoIO.output(ProtoIO.OutputMessages.ERR_TILE_NOT_FOUND);
            return;
        }

        Tile n1 = t1.getNeighbour(dir1);
        Tile n2 = t2.getNeighbour(dir2);

        if(n1 != null || n2 != null) {
            ProtoIO.output(ProtoIO.OutputMessages.SETNEIGHBOUR_ERR_USED);
            return;
        }

        t1.setNeighbour(t2, dir1);
        t2.setNeighbour(t1, dir2);

        ProtoIO.output(ProtoIO.OutputMessages.SETNEIGHBOUR_OUT);
    }

    @Override
    public int getParamCount() {
        return 4;
    }
}
