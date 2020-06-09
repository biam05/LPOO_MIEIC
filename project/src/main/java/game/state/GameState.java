package game.state;

import game.commands.Command;
import game.controller.FarmController;
import java.io.IOException;

public class GameState implements State {

    FarmController farmController;
    Command command;

    public GameState(FarmController farmController) {
        this.farmController = farmController;
        this.command = null;
    }

    @Override
    public void doAction(FarmController farmController) throws IOException {
        this.command = farmController.getCommand();
        command.execute();
    }

    @Override
    public String getName() {
        return "Game";
    }
}
