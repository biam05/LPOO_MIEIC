package game.commands.memento;

import game.commands.Command;
import game.models.FarmModel;
import game.memento.FarmMemento;

public class RestoreCommand implements Command {

    FarmMemento farmState;
    FarmModel farmModel;

    public RestoreCommand(FarmMemento farmState, FarmModel farmModel) {
        this.farmState = farmState;
        this.farmModel = farmModel;
    }

    @Override
    public void execute() { farmModel.restore(farmState); }
}
