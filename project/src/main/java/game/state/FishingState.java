package game.state;

import game.commands.Command;
import game.commands.state.FishingCommand;
import game.controller.FarmController;

import java.io.IOException;

public class FishingState implements State{

    FarmController farmController;
    Command command;

    public FishingState(FarmController farmController) {
        this.farmController = farmController;
        this.command = new FishingCommand(farmController);
    }

    @Override
    public void doAction(FarmController farmController) throws IOException {
        command.execute();
    }

    @Override
    public String getName() {
        return "Fishing";
    }
}
