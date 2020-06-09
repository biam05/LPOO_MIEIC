package game.state;

import game.commands.Command;
import game.commands.state.MenuCommand;
import game.controller.FarmController;
import java.io.IOException;

public class MenuState implements State {

    FarmController farmController;
    Command command;

    public MenuState(FarmController farmController) {
        this.farmController = farmController;
        this.command = new MenuCommand(farmController);
    }

    @Override
    public void doAction(FarmController farmController) throws IOException { command.execute(); }

    @Override
    public String getName() {
        return "Menu";
    }
}
