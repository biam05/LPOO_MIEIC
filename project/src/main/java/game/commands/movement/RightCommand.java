package game.commands.movement;

import game.commands.Command;
import game.models.FarmModel;

public class RightCommand implements Command {

    private final FarmModel farmModel;

    public RightCommand(FarmModel farmModel) {
        this.farmModel = farmModel;
    }

    @Override
    public void execute() {
        farmModel.moveObject(farmModel.getFarmer().moveRight(), farmModel.getFarmer());
    }
}
