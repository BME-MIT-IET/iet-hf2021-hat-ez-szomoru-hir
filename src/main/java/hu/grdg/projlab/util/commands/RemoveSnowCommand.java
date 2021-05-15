package hu.grdg.projlab.util.commands;

import hu.grdg.projlab.ProtoIO;
import hu.grdg.projlab.model.Player;
import hu.grdg.projlab.model.Tile;
import hu.grdg.projlab.util.Command;
import hu.grdg.projlab.util.CommandException;
import hu.grdg.projlab.util.ProtoRuntime;

import java.util.List;

public class RemoveSnowCommand extends Command {
    @Override
    public void runCommand(ProtoRuntime state, List<String> inputParams) throws CommandException {
        var pName = getStringArg(inputParams);
        var player = getPlayer(pName, state);
        if(player == null) {
            ProtoIO.output(ProtoIO.OutputMessages.ERR_PLAYER_NOT_FOUND);
            return;
        }

        var t = player.getCurrentTile();
        if(t == null) {
            ProtoIO.output(ProtoIO.OutputMessages.ERR_NOT_ON_TILE);
            return;
        }

        if(t.removeSnowLayer(1)) {
            ProtoIO.output(ProtoIO.OutputMessages.REMOVESNOW_OUT);
        }else {
            ProtoIO.output(ProtoIO.OutputMessages.REMOVESNOW_ERR_NOT_ENOUGH);
        }
    }

    @Override
    public int getParamCount() {
        return 1;
    }
}
