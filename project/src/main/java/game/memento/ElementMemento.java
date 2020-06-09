package game.memento;

import game.models.PositionModel;

public class ElementMemento {
    private String state;

    public ElementMemento(PositionModel position) { this.state = (position.getX() + ";" + position.getY()); } // X;Y
    public ElementMemento(String state){ this.state = state;}

    public String getState() { return this.state; }
}
