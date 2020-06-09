package game.state;

import game.commands.Command;
import game.commands.state.QuitCommand;
import game.controller.FarmController;

import java.io.IOException;

public class QuitState implements State{

    FarmController farmController;
    Command command;

    public QuitState(FarmController farmController) {
        this.farmController = farmController;
        this.command = new QuitCommand(farmController);
    }

    @Override
    public void doAction(FarmController farmController) throws IOException { command.execute(); }

    @Override
    public String getName() {
        return "Quit";
    }

}
