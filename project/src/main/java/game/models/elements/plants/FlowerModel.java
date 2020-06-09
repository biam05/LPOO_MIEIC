package game.models.elements.plants;

import game.models.FarmModel;
import game.models.PositionModel;

public class FlowerModel extends PlantModel implements Plantable {
    public FlowerModel(int x, int y) {
        super(x, y);
    }
    public FlowerModel() {
        super();
    }

    public int getPrice() { return 10; }

    @Override
    public void addElement(FarmModel farmModel, PositionModel position) {
        this.setPos(position);
        farmModel.getFlowers().add(this);
    }
}
