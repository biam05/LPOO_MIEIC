package game;

import game.controller.FarmController;
import game.models.FarmModel;
import game.views.FarmView;

import java.io.IOException;

public class Application {
    public static void main(String[] args) throws IOException {

        FarmModel farmModel = new FarmModel(60,25);
        FarmView farmView = new FarmView(60,25);
        FarmController farmController = new FarmController(farmModel, farmView);
        farmController.run();
    }
}