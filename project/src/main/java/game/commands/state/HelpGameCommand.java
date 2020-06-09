package game.commands.state;

import game.commands.Command;
import game.controller.FarmController;
import game.state.GameState;
import game.state.HelpGameState;

import java.io.IOException;

public class HelpGameCommand implements Command {

    FarmController farmController;

    public HelpGameCommand(FarmController farmController) {
        this.farmController = farmController;
    }

    @Override
    public void execute() throws IOException {
        if(farmController.getState() instanceof GameState){
            farmController.setState(new HelpGameState(farmController));
        }
        else{
            int option = farmController.getNumber();
            if(option == -1) farmController.setState(new GameState(farmController));
        }
    }
}