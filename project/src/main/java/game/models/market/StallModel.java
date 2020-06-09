package game.models.market;

import game.auxiliary.TYPE;
import game.models.elements.*;
import game.models.elements.plants.*;

public class StallModel {

    private PlantModel plant;
    private UpgradeModel upgrade;
    private FenceModel fence;
    private int quantity;
    private TYPE type;

    public StallModel(PlantModel plant, int quantity) {
        this.plant = plant;
        this.upgrade = null;
        this.fence = null;
        this.quantity = quantity;
        if(plant instanceof TreeModel) this.type = TYPE.TREE;
        else if(plant instanceof BushModel) this.type = TYPE.BUSH;
        else if(plant instanceof SeedModel) this.type = TYPE.SEED;
        else if(plant instanceof FlowerModel) this.type = TYPE.FLOWER;
    }
    public StallModel(UpgradeModel upgrade){
        this.plant = null;
        this.fence = null;
        this.upgrade = upgrade;
        this.quantity = 1;
        this.type = TYPE.UPGRADE;
    }
    public StallModel(FenceModel fence, int quantity){
        this.plant = null;
        this.fence = fence;
        this.upgrade = null;
        this.quantity = quantity;
        this.type = TYPE.FENCE;
    }

    public PlantModel getPlant() { return plant; }
    public FenceModel getFence() {return fence;}
    public int getQuantity() { return quantity; }
    public int getPrice() {
        if (this.type == TYPE.FENCE) return fence.getPrice() * this.quantity;
        else if(this.type == TYPE.UPGRADE) return upgrade.getPrice();
        else return plant.getPrice() * this.quantity;
    }
    public TYPE getType() { return type;}

    public String getName(){
        String result = this.type.getName();

        if ("UPGRADE".equals(result)) {
            if (upgrade.getUpgradeNumber() == 1) return "FENCE";
            if (upgrade.getUpgradeNumber() == 2) return "FLOWER";
            if (upgrade.getUpgradeNumber() == 3) return "LAKE";
            if (upgrade.getUpgradeNumber() >= 4) return "COMING SOON";
        }
        return result;
    }
}
