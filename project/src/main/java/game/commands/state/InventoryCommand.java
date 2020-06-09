package game.commands.state;


import game.commands.Command;
import game.controller.FarmController;
import game.state.GameState;
import game.state.InventoryState;

import java.io.IOException;

public class InventoryCommand implements Command {

    private final FarmController farmController;

    public InventoryCommand(FarmController farmController) {
        this.farmController = farmController;
    }

    @Override
    public void execute() throws IOException {
        if(farmController.getState() instanceof GameState)
            farmController.setState(new InventoryState(farmController));
        else{
            int option = farmController.getNumber();
            if(option == -1) farmController.setState(new GameState(farmController));
        }
    }
    
}
