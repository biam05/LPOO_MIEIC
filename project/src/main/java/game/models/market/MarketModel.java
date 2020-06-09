package game.models.market;

import game.models.elements.*;
import game.models.elements.plants.BushModel;
import game.models.elements.plants.FlowerModel;
import game.models.elements.plants.SeedModel;
import game.models.elements.plants.TreeModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MarketModel {
    private List<StallModel> stalls;
    private int capacity;
    private int upgrade;

    public MarketModel(int upgradeCounter) {
        this.stalls = new ArrayList<>();
        this.capacity = 5;
        this.upgrade = upgradeCounter+1;
        randomGenerate();
    }

    public List<StallModel> getStalls() { return stalls; }
    public int getCapacity() { return capacity;}
    public void updateUpgrade() { this.upgrade++; }

    public void addStall(StallModel stall) { if(this.stalls.size() < this.capacity) stalls.add(stall); }
    public void removeStall(StallModel stall) {stalls.remove(stall);}

    public void randomGenerate() {

        this.stalls.clear();

        Random random = new Random();
        int index, quantity;
        for(int i = 0; i < capacity-1 ; i++) {
            if(upgrade>=3) index = random.nextInt(5);
            else index = random.nextInt(4);
            quantity = random.nextInt(10)+1;
            if(index == 0) {
                StallModel stall = new StallModel(new TreeModel(), quantity);
                addStall(stall);
            }
            else if(index == 1) {
                StallModel stall = new StallModel(new SeedModel(), quantity);
                addStall(stall);
            }
            else if (index == 2){
                StallModel stall = new StallModel(new BushModel(), quantity);
                addStall(stall);
            }
            else if (index == 3){
                StallModel stall = new StallModel(new FenceModel(), quantity);
                addStall(stall);
            }
            else {
                StallModel stall = new StallModel(new FlowerModel(), quantity);
                addStall(stall);
            }
        }
        StallModel stall = new StallModel(new UpgradeModel(this.upgrade));
        addStall(stall);
    }
}
