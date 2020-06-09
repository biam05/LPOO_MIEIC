package game.memento;

import game.models.PositionModel;

public class FarmerMemento {
    private String state;

    public FarmerMemento(PositionModel position, int balance, int fishbait) { this.state = (position.getX() + ";" + position.getY() + ";" + balance + ";" + fishbait); } // X;Y;balance;fishbaits
    public FarmerMemento(String state){ this.state = state;}

    public String getState() {
        return this.state;
    }
}
