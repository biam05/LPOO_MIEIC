package game.commands.state;

import game.commands.Command;
import game.controller.FarmController;
import game.memento.FarmMemento;
import game.state.*;
import java.io.IOException;

public class MenuCommand implements Command {

    FarmController farmController;

    public MenuCommand(FarmController farmController) {
        this.farmController = farmController;
    }

    @Override
    public void execute() throws IOException {

        int option = farmController.getNumber();
        switch (option){
            case 1:
                farmController.setState(new GameState(farmController));
                break;
            case 2:
                FarmMemento fs = new FarmMemento(farmController.getFarmModel().readFile());
                farmController.getFarmModel().restore(fs);
                farmController.setState(new GameState(farmController));
                break;
            case 3:
                farmController.setState(new HelpMenuState(farmController));
                break;
            default:
                break;
        }
    }
}
