package game.commands.movement;

import game.commands.Command;
import game.models.FarmModel;

public class UpCommand implements Command {

    private final FarmModel farmModel;

    public UpCommand(FarmModel farmModel) {
        this.farmModel = farmModel;
    }

    @Override
    public void execute() {
        farmModel.moveObject(farmModel.getFarmer().moveUp(), farmModel.getFarmer());
    }
}
