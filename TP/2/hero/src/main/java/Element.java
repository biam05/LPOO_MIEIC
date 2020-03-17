import com.googlecode.lanterna.graphics.TextGraphics;

abstract class Element {

    private Position position;

    public Element(int x, int y)
    {
        this.position = new Position(x, y);
    }
    public Position getPosition()
    {
        return position;
    }
    public void setPosition(Position position_new){
        position.setx(position_new.getx());
        position.sety(position_new.gety());
    }
    public Position moveUp() {
        return new Position(position.getx(), position.gety() - 1);
    }
    public Position moveDown() {
        return new Position(position.getx(), position.gety() + 1);
    }
    public Position moveLeft() {
        return new Position(position.getx() - 1, position.gety());
    }
    public Position moveRight() {
        return new Position(position.getx() + 1, position.gety());
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null) return false;

        if (getClass() != o.getClass()) return false;

        Position p = (Position) o;
        return position.getx() == p.getx() && position.gety() == p.gety();
    }
    public abstract void draw(TextGraphics graphics);
}
