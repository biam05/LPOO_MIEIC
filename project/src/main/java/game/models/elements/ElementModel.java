package game.models.elements;

import game.memento.ElementMemento;
import game.models.PositionModel;

public abstract class ElementModel {
    public PositionModel pos;

    public ElementModel(){
        this.pos = new PositionModel(1,1);
    }
    public ElementModel(int x, int y){ this.pos = new PositionModel(x,y); }

    public PositionModel getPos() { return pos; }
    public void setPos(PositionModel pos) { this.pos = pos; }

    public PositionModel moveRight() {
        return new PositionModel(pos.getX()+1,pos.getY());
    }
    public PositionModel moveLeft() {
        return new PositionModel(pos.getX()-1,pos.getY());
    }
    public PositionModel moveUp() {
        return new PositionModel(pos.getX(),pos.getY()-1);
    }
    public PositionModel moveDown() {
        return new PositionModel(pos.getX(),pos.getY()+1);
    }

    //game.memento pattern functions (originator)
    public void restore(ElementMemento elementState) {
        String[] arr = elementState.getState().split(";");
        this.pos = (new PositionModel(Integer.parseInt(arr[0]),Integer.parseInt(arr[1])));
    }
}
