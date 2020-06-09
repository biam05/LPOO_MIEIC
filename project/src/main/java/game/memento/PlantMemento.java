package game.memento;

import game.models.PositionModel;

public class PlantMemento {
    private String state;

    public PlantMemento(PositionModel position, int growthTime, int plantedAt) {
        // X;Y;growthTime;plantedAt
        ElementMemento elementState = new ElementMemento(position);
        this.state = elementState.getState() + ";" + (growthTime + ";" + plantedAt);
    }

    public PlantMemento(String state){ this.state = state;}

    public String getState() {
        return this.state;
    }
}
