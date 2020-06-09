package game.state;

import game.controller.FarmController;
import java.io.IOException;

public interface State {
    void doAction(FarmController farmController) throws IOException;
    String getName();
}