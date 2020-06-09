package game.models.market;

import game.auxiliary.generator.GenerateFence;
import game.auxiliary.*;
import game.auxiliary.generator.GenerateFlower;
import game.models.FarmModel;
import game.models.elements.lake.DeckModel;
import game.models.elements.FenceModel;
import game.models.elements.lake.WaterModel;

import java.util.List;

public class UpgradeModel {

    private int upgradeNumber;
    private int price;

    public UpgradeModel(int upgradeNumber) {
        this.upgradeNumber = upgradeNumber;
        this.price = upgradePrice();
    }

    public int getUpgradeNumber() { return this.upgradeNumber;}

    public int getPrice() {
        return this.price;
    }

    private int upgradePrice(){
        switch (this.upgradeNumber){
            case 1:
                return 60;
            case 2:
                return 160;
            case 3:
                return 260;
            default:
                return 0;
        }
    }

    static public void upgrade(FarmModel fm, int num) {
        switch (num) {
            case 1:
                List<FenceModel> walls = (new GenerateFence()).generate(fm,1);
                fm.getFences(true).addAll(walls);
                objectManagement.treeUpdate(fm,10,20,5,15);
                movement.organiseAnimals(fm.getAnimals());
                break;
            case 2:
                fm.getFlowers().addAll((new GenerateFlower()).generate(fm,10));
                break;
            case 3:
                List<WaterModel> waters = fm.getLake().getWater();
                List<DeckModel> deck = fm.getLake().getDeck();
                for(int w = 42; w< 42 + 12; w++) for(int h = 16; h< 16+6; h++) waters.add(new WaterModel(w,h));
                deck.add(new DeckModel(fm.getWidth()-19,fm.getHeight()-6));
                fm.getLake().setWater(waters);
                fm.getLake().setDeck(deck);
                objectManagement.treeUpdate(fm,42,54,16,22);
                objectManagement.flowerUpdate(fm,42,54,16,22);
                fm.getFarmer().setFishBait(10);
                break;
        }
    }
}
