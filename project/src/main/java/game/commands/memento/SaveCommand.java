package game.commands.memento;

import game.commands.Command;
import game.controller.FarmController;
import game.memento.FarmMemento;

public class SaveCommand implements Command {

    FarmController farmController;

    public SaveCommand(FarmController farmController) { this.farmController = farmController; }

    @Override
    public void execute(){
        FarmMemento fm = farmController.getFarmModel().save();
        farmController.setFarmState(fm);
    }
}
