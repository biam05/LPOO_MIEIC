package game.memento;

import game.models.elements.*;
import game.models.elements.plants.BushModel;
import game.models.elements.plants.FlowerModel;
import game.models.elements.plants.SeedModel;
import game.models.elements.plants.TreeModel;
import game.models.market.InventoryModel;

import java.util.List;

public class FarmMemento {
    private String state;

    public FarmMemento(int width, int height, int timeCounter, int upgradeCounter, FarmerModel farmer,
                       List<TreeModel> trees, List<FenceModel> fences, InventoryModel inventory,
                       List<AnimalModel> animals, List<SeedModel> seeds,
                       List<BushModel> bushes, List<FlowerModel> flowers) {

        ElementMemento elementState;
        PlantMemento plantState;
        FarmerMemento farmerState;
        FenceMemento fenceState;
        // width, height, timeCounter, upgradeCounter
        String s1 =
                (width + "\n::::::::::\n" + height + "\n::::::::::\n" + timeCounter + "\n::::::::::\n" + upgradeCounter + "\n::::::::::\n");

        // farmer (displayed as x;y;balance;fishbait)
        farmerState = new FarmerMemento(farmer.getPos(), farmer.getBalance(), farmer.getFishBait());
        String s2 = farmerState.getState() + "\n::::::::::\n";

        // trees
        StringBuilder s3 = new StringBuilder("TREES");
        for (TreeModel tree : trees) {
            plantState = new PlantMemento(tree.getPos(),tree.getGrowthTime(),tree.getPlantedAt());
            s3.append("\n").append(plantState.getState());
        }

        // fences
        StringBuilder s4 = new StringBuilder("\n::::::::::\nFENCES");
        for (FenceModel fence : fences) {
            fenceState = new FenceMemento(fence.getPos(),fence.isLimitFence());
            s4.append("\n").append(fenceState.getState());
        }

        // inventory
        InventoryMemento inventoryState = new InventoryMemento(inventory.getCapacity(),inventory.getElements());
        String s5 = "\n::::::::::\n" + inventoryState.getState();

        // animals
        StringBuilder s7 = new StringBuilder("\n::::::::::\nANIMALS");
        for (AnimalModel animal : animals) {
            elementState = new ElementMemento(animal.getPos());
            s7.append("\n").append(elementState.getState());
        }

        // seeds
        StringBuilder s8 = new StringBuilder("\n::::::::::\nSEEDS");
        for (SeedModel seed : seeds) {
            plantState = new PlantMemento(seed.getPos(), seed.getGrowthTime(), seed.getPlantedAt());
            s8.append("\n").append(plantState.getState());
        }

        // bushes
        StringBuilder s9 = new StringBuilder("\n::::::::::\nBUSHES");
        for (BushModel bush : bushes) {
            plantState = new PlantMemento(bush.getPos(), bush.getGrowthTime(), bush.getPlantedAt());
            s9.append("\n").append(plantState.getState());
        }

        // flowers
        StringBuilder s10 = new StringBuilder("\n::::::::::\nFLOWERS");
        for (FlowerModel flower : flowers) {
            plantState = new PlantMemento(flower.getPos(), flower.getGrowthTime(), flower.getPlantedAt());
            s10.append("\n").append(plantState.getState());
        }

        this.state = s1+s2+s3.toString()+s4.toString()+s5+s7.toString()+s8.toString()+s9.toString()+s10.toString();
    }

    public FarmMemento(String state) {this.state=state;}

    public String getState() { return this.state; }

}
