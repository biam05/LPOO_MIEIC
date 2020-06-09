package game.commands.movement;

import game.commands.Command;
import game.models.FarmModel;

public class DownCommand implements Command {

    private final FarmModel farmModel;

    public DownCommand(FarmModel farmModel) {
        this.farmModel = farmModel;
    }

    @Override
    public void execute() {
        farmModel.moveObject(farmModel.getFarmer().moveDown(), farmModel.getFarmer());
    }
}
