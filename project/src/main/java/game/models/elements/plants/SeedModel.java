package game.models.elements.plants;

import game.models.FarmModel;
import game.models.PositionModel;

public class SeedModel extends PlantModel implements Plantable {

    public SeedModel(int x, int y) {
        super(x, y);
    }
    public SeedModel() {
        super();
    }

    @Override
    public int getPrice() {
        return 3;
    }

    @Override
    public void addElement(FarmModel farmModel, PositionModel position) {
        this.setPos(position);
        farmModel.getSeeds().add(this);
        this.setPlantedAt(farmModel.getTimeCounter());
    }
}
