package game.commands.state;


import game.commands.Command;
import game.controller.FarmController;
import game.state.HelpGameState;
import game.state.MenuState;

import java.io.IOException;

public class HelpMenuCommand implements Command {

    FarmController farmController;

    public HelpMenuCommand(FarmController farmController) {
        this.farmController = farmController;
    }

    @Override
    public void execute() throws IOException {
        if(farmController.getState() instanceof MenuState){
            farmController.setState(new HelpGameState(farmController));
        }
        else{
            int option = farmController.getNumber();
            if(option == -1) farmController.setState(new MenuState(farmController));
        }

    }
}
