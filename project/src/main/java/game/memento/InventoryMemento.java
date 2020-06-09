package game.memento;

import game.models.elements.*;
import game.models.elements.plants.BushModel;
import game.models.elements.plants.FlowerModel;
import game.models.elements.plants.SeedModel;
import game.models.elements.plants.TreeModel;

import java.util.List;

public class InventoryMemento {
    private String state;

    public InventoryMemento(int capacity, List<ElementModel> elements) {
        int seeds = 0, trees = 0, bushes = 0, fishes = 0, flowers = 0, fences = 0;
        String s1 = (capacity + "\n");

        for (ElementModel element : elements) {
            if (element.getClass() == SeedModel.class) seeds++;
            else if (element.getClass() == BushModel.class) bushes++;
            else if (element.getClass() == TreeModel.class) trees++;
            else if (element.getClass() == FlowerModel.class) flowers++;
            else if (element.getClass() == FishModel.class) fishes++;
            else if (element.getClass() == FenceModel.class) fences++;
        }

        String s2 = (bushes + " BUSH\n" + seeds + " SEED\n" + trees + " TREE\n" + flowers + " FLOWER\n" + fishes + " FISH\n" + fences + " FENCE");
        this.state = s1+s2;
    }
    public InventoryMemento(String state) { this.state = state;}

    public String getState() {
        return this.state;
    }
}
