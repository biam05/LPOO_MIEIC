package game.models.elements.plants;

import game.models.FarmModel;
import game.models.PositionModel;

public class BushModel extends PlantModel implements Plantable{

    public BushModel(int x, int y) {
        super(x, y);
    }
    public BushModel() {
        super();
    }

    @Override
    public int getPrice() {
        return 6;
    }

    @Override
    public void addElement(FarmModel farmModel, PositionModel position) {
        this.setPos(position);
        farmModel.getBushes().add(this);
        this.setPlantedAt(farmModel.getTimeCounter());
    }
}
