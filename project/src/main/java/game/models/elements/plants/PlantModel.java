package game.models.elements.plants;

import game.memento.PlantMemento;
import game.models.PositionModel;
import game.models.elements.ElementModel;

public abstract class PlantModel extends ElementModel {
    private int growthTime;
    private int plantedAt;

    public PlantModel(int x, int y) {
        super(x, y);
        this.growthTime = 5;
        this.plantedAt = -1;
    }
    public PlantModel(){
        super(1,1);
        this.growthTime = 5;
        this.plantedAt = -1;
    }

    public abstract int getPrice();
    public int getGrowthTime() {
        return growthTime;
    }
    public int getPlantedAt() {
        return plantedAt;
    }
    public void setPlantedAt(int plantedAt){
        this.plantedAt = plantedAt;
    }

    public PlantMemento save() { return new PlantMemento(getPos(), getGrowthTime(),getPlantedAt());}
    public void restore(PlantMemento plantState) {
        String[] arr = plantState.getState().split(";");

        setPos(new PositionModel(Integer.parseInt(arr[0]),Integer.parseInt(arr[1])));
        this.growthTime = Integer.parseInt(arr[2]);
        this.plantedAt = Integer.parseInt(arr[3]);
    }
}
