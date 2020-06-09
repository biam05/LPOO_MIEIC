package game.memento;

import game.models.PositionModel;

public class FenceMemento {
    private String state;

    public FenceMemento(PositionModel position, boolean isLimit) {
        // X;Y;bool [1 == true]
        ElementMemento elementState = new ElementMemento(position);
        if(isLimit) this.state = elementState.getState() + ";1";
        else this.state = elementState.getState() + ";0";
    }

    public FenceMemento(String state){ this.state = state;}

    public String getState() {
        return this.state;
    }
}
