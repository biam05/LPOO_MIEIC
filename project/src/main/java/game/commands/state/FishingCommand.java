package game.commands.state;


import game.commands.Command;
import game.controller.FarmController;
import game.state.FishingState;
import game.state.GameState;

import java.io.IOException;

public class FishingCommand implements Command {

    FarmController farmController;

    public FishingCommand(FarmController farmController) {
        this.farmController = farmController;
    }

    @Override
    public void execute() throws IOException {
        if(farmController.getState() instanceof GameState)
            farmController.setState(new FishingState(farmController));
        else{
            int option = 0;
            option = farmController.getNumber();

            if (option == -1) farmController.setState(new GameState(farmController));
            else if (option == 1) farmController.getFarmModel().fish();
        }
        
    }
}