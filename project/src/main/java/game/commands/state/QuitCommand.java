package game.commands.state;

import game.commands.Command;
import game.controller.FarmController;
import game.state.QuitState;
import java.io.IOException;

public class QuitCommand implements Command {

    FarmController farmController;

    public QuitCommand(FarmController farmController) {
        this.farmController = farmController;
    }

    @Override
    public void execute() throws IOException {

        farmController.getFarmModel().saveToFile();
        try {
            farmController.getFarmView().getScreen().close();
        } catch (IOException ignored) { }

        farmController.setState(new QuitState(farmController));
    }
}