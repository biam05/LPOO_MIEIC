package game.state;

import game.commands.Command;
import game.commands.state.StallsCommand;
import game.controller.FarmController;

import java.io.IOException;

public class MarketState implements State{

    FarmController farmController;
    Command command;

    public MarketState(FarmController farmController) {
        this.farmController = farmController;
        this.command = new StallsCommand(farmController);
    }

    @Override
    public void doAction(FarmController farmController) throws IOException { command.execute(); }

    @Override
    public String getName() {
        return "Market";
    }
}
