package game.models.elements.plants;

import game.auxiliary.TYPE;

public class PlantFactory {

    private TYPE plant;

    public PlantModel getPlant(TYPE plant) {
        if(plant == TYPE.TREE) return new TreeModel();
        if(plant == TYPE.BUSH) return new BushModel();
        if(plant == TYPE.SEED) return new SeedModel();
        if(plant == TYPE.FLOWER) return new FlowerModel();
        return null;
    }

}
