package game.models.elements.lake;

import game.models.elements.ElementModel;

import java.util.ArrayList;
import java.util.List;

public class LakeModel extends ElementModel {
    private List<WaterModel> water;
    private List<DeckModel> deck;
    private int timeInterval;
    private int lastFished;
    private boolean possibleFishing;

    public LakeModel() {
        this.water = new ArrayList<>();
        this.deck = new ArrayList<>();
        this.timeInterval = 40;
        this.lastFished = -1;
        this.possibleFishing = true;
    }

    public List<WaterModel> getWater() { return water; }
    public List<DeckModel> getDeck() { return deck; }
    public boolean isPossibleFishing() { return possibleFishing; }
    public int getTimeInterval() { return timeInterval; }
    public int getLastFished() { return lastFished; }

    public void setWater(List<WaterModel> water) {
        this.water = water;
    }
    public void setDeck(List<DeckModel> deck) {
        this.deck = deck;
    }
    public void setPossibleFishing(boolean possibleFishing) {this.possibleFishing = possibleFishing;}

    public boolean canFish(int timer) {
        if(this.lastFished == -1) {
            this.lastFished = timer;
            possibleFishing = false;
            return true;
        }
        else {
            if(this.lastFished+this.timeInterval <= timer) {
                this.lastFished = timer;
                possibleFishing = false;
                return true;
            }
        }
        possibleFishing = false;
        return false;
    }

    public void removeAll() {
        this.deck.clear();
        this.water.clear();
    }
}
