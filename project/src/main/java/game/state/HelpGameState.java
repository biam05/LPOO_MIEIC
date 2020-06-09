package game.state;

import game.commands.Command;
import game.commands.state.HelpGameCommand;
import game.controller.FarmController;
import java.io.IOException;

public class HelpGameState implements State{

    FarmController farmController;
    Command command;

    public HelpGameState(FarmController farmController){
        this.farmController = farmController;
        this.command = new HelpGameCommand(farmController);
    }

    @Override
    public void doAction(FarmController farmController) throws IOException {
        command.execute();
    }

    @Override
    public String getName() {
        return "Help Game";
    }
}
