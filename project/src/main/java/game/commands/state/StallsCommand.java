package game.commands.state;

import game.commands.Command;
import game.controller.FarmController;
import game.state.*;
import java.io.IOException;

public class StallsCommand implements Command {

    FarmController farmController;

    public StallsCommand(FarmController farmController) {
        this.farmController = farmController;
    }

    @Override
    public void execute() throws IOException {
        if(farmController.getState() instanceof GameState) farmController.setState(new MarketState(farmController));
        else{
            int option = farmController.getNumber();
            if(option == -1) farmController.setState(new GameState(farmController));
            else{
                if(option <= 9 && option >= 5) farmController.getFarmModel().buyMarket(option);
                else farmController.getFarmModel().sellMarket(farmController.getFarmModel().getInventory().indexSelection(true), option);
            }
        }
    }
}
