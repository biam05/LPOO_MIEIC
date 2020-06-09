package game.state;

import game.commands.Command;
import game.commands.state.InventoryCommand;
import game.controller.FarmController;

import java.io.IOException;

public class InventoryState implements State {

    FarmController farmController;
    Command command;

    public InventoryState(FarmController farmController) {
        this.farmController = farmController;
        this.command = new InventoryCommand(farmController);
    }

    @Override
    public void doAction(FarmController farmController) throws IOException { command.execute(); }

    @Override
    public String getName() {
        return "Inventory";
    }
}
