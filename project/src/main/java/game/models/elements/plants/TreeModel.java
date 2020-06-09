package game.models.elements.plants;

import game.models.FarmModel;
import game.models.PositionModel;

public class TreeModel extends PlantModel implements Plantable{

    public TreeModel(int x, int y) {
        super(x,y);
    }
    public TreeModel() {
        super();
    }

    @Override
    public int getPrice() { return 9; }

    @Override
    public void addElement(FarmModel farmModel, PositionModel position) {
        this.setPos(position);
        farmModel.getTrees().add(this);
    }
}
