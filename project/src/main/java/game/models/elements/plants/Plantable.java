package game.models.elements.plants;

import game.models.FarmModel;
import game.models.PositionModel;

public interface Plantable {
    void addElement(FarmModel farmModel, PositionModel position);
}
