package game.models.elements;

import game.memento.FenceMemento;
import game.models.FarmModel;
import game.models.PositionModel;
import game.models.elements.plants.Plantable;

public class FenceModel extends ElementModel implements Plantable {

    private boolean limitFence;

    public FenceModel(int x, int y, boolean limitFence) {
        super(x,y);
        this.limitFence = limitFence;
    }
    public FenceModel() {
        super(1,1);
        this.limitFence = false;
    }

    public boolean isLimitFence() {
        return limitFence;
    }
    private void setLimitFence(boolean limitFence) {
        this.limitFence = limitFence;
    }
    public int getPrice() { return 15; }

    public void restore(FenceMemento fenceState) {
        String[] arr = fenceState.getState().split(";");

        setPos(new PositionModel(Integer.parseInt(arr[0]), Integer.parseInt(arr[1])));
        if(Integer.parseInt(arr[2]) == 1) setLimitFence(true);
        else setLimitFence(false);
    }

    @Override
    public void addElement(FarmModel farmModel, PositionModel position) {
        this.setPos(position);
        this.setLimitFence(false);
        farmModel.getFences(true).add(this);
    }
}
