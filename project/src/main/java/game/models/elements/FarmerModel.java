package game.models.elements;

import game.memento.FarmerMemento;
import game.models.PositionModel;

public class FarmerModel extends ElementModel {

    private int balance;
    private int fishBait;

    public FarmerModel(int x, int y) {
        super(x,y);
        this.balance = 20;
        this.fishBait = 0;
    }
    public FarmerMemento save() {
        return new FarmerMemento(getPos(), getBalance(), getFishBait());
    }

    public int getBalance() { return this.balance;}
    public void setBalance(int balance) {this.balance = balance;}

    public int getFishBait() { return this.fishBait;}
    public void setFishBait(int fishBait) {this.fishBait = fishBait;}

    public void restore(FarmerMemento farmerState) {
        String[] arr = farmerState.getState().split(";");

        setPos(new PositionModel(Integer.parseInt(arr[0]), Integer.parseInt(arr[1])));
        this.balance = Integer.parseInt(arr[2]);
        this.fishBait = Integer.parseInt(arr[3]);
    }

}
