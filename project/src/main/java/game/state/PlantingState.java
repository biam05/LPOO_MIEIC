package game.state;

import game.commands.Command;
import game.commands.state.PlantCommand;
import game.controller.FarmController;
import java.io.IOException;

public class PlantingState implements State{

    FarmController farmController;
    Command command;

    public PlantingState(FarmController farmController) {
        this.farmController = farmController;
        this.command = new PlantCommand(farmController);
    }

    @Override
    public void doAction(FarmController farmController) throws IOException { command.execute();}

    @Override
    public String getName() {
        return "Planting";
    }
}
