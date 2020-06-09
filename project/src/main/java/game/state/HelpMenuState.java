package game.state;

import game.commands.Command;
import game.commands.state.HelpMenuCommand;
import game.controller.FarmController;

import java.io.IOException;

public class HelpMenuState implements State{

    FarmController farmController;
    Command command;

    public HelpMenuState(FarmController farmController){
        this.farmController = farmController;
        this.command = new HelpMenuCommand(farmController);
    }

    @Override
    public void doAction(FarmController farmController) throws IOException { command.execute(); }

    @Override
    public String getName() {
        return "Help Menu";
    }
}
