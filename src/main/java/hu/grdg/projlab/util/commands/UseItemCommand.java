package hu.grdg.projlab.util.commands;

import hu.grdg.projlab.ProtoIO;
import hu.grdg.projlab.model.Player;
import hu.grdg.projlab.util.Command;
import hu.grdg.projlab.util.CommandException;
import hu.grdg.projlab.util.ProtoRuntime;

import java.util.List;

//FIXME Direction is not used for DivingSuit
public class UseItemCommand extends Command {
    @Override
    public void runCommand(ProtoRuntime state, List<String> inputParams) throws CommandException {
        var pName = getStringArg(inputParams);
        var slot = getIntArg(inputParams);

        Player p;
        if((p = getPlayer(pName,state)) == null) {
            ProtoIO.output(ProtoIO.OutputMessages.ERR_PLAYER_NOT_FOUND);
            return;
        }

        try {
            var item = p.getInventory().get(slot - 1);
            boolean succ = item.useItem();
            if(succ)
                ProtoIO.output(ProtoIO.OutputMessages.USEITEM_OUT);
            else
                ProtoIO.output(ProtoIO.OutputMessages.USEITEM_ERR_ITEMFAIL);
        } catch (Exception e) {
            ProtoIO.output(ProtoIO.OutputMessages.USEITEM_ERR_NOITEM);
        }

    }

    @Override
    public int getParamCount() {
        return 2;
    }
}
