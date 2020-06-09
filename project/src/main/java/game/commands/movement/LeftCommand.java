package game.commands.movement;

import game.commands.Command;
import game.models.FarmModel;

public class LeftCommand implements Command {

    private final FarmModel farmModel;

    public LeftCommand(FarmModel farmModel) {
        this.farmModel = farmModel;
    }

    @Override
    public void execute() {
        farmModel.moveObject(farmModel.getFarmer().moveLeft(), farmModel.getFarmer());
    }
}
