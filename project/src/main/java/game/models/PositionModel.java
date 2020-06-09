package game.models;

public class PositionModel {
    private int x;
    private int y;

    public PositionModel(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() { return x; }
    public int getY() { return y; }

    public void setX(int x) { this.x = x;}
    public void setY(int y) { this.y = y;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (getClass() != o.getClass()) return false;
        PositionModel p = (PositionModel) o;
        return x == p.getX() && y == p.getY();
    }

}
