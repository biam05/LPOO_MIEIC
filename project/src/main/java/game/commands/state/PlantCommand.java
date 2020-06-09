package game.commands.state;

import game.auxiliary.objectManagement;
import game.commands.Command;
import game.controller.FarmController;
import game.state.*;
import java.io.IOException;

public class PlantCommand implements Command {

    private FarmController farmController;

    public PlantCommand(FarmController farmController) {
        this.farmController = farmController;
    }

    @Override
    public void execute() throws IOException {
        if (farmController.getFarmModel().getInventory().getPlants().size() != 0) {
            if (farmController.getState() instanceof GameState) farmController.setState(new PlantingState(farmController));
            else {
                int plant = farmController.getNumber();
                if (plant == -1) {
                    farmController.setState(new GameState(farmController));
                    return;
                }
                else objectManagement.plant(farmController.getFarmModel(), farmController.getFarmModel().getInventory().indexSelection(false), plant);
            }
        }
        else farmController.setState(new GameState(farmController));
    }
}
