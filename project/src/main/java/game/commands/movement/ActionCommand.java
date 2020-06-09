package game.commands.movement;

import game.commands.Command;
import game.models.FarmModel;

public class ActionCommand implements Command {

    private final FarmModel farmModel;

    public ActionCommand(FarmModel farmModel) {
        this.farmModel = farmModel;
    }

    @Override
    public void execute(){
        farmModel.pickObject();
    }
}