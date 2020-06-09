package game.auxiliary;

import game.auxiliary.generator.*;
import game.models.*;
import game.models.elements.*;
import game.models.elements.lake.LakeModel;
import game.models.elements.plants.BushModel;
import game.models.elements.plants.SeedModel;
import game.models.elements.plants.TreeModel;

import java.util.List;

public class time {

    private static void checkSeeds(FarmModel farmModel){
        int i = 0;
        for(SeedModel seed : farmModel.getSeeds()){
            if(seed.getPlantedAt() + seed.getGrowthTime() == farmModel.getTimeCounter()){
                i = farmModel.getSeeds().indexOf(seed);
                BushModel bush = new BushModel(farmModel.getSeeds().get(i).getPos().getX(), farmModel.getSeeds().get(i).getPos().getY());
                bush.setPlantedAt(farmModel.getTimeCounter());
                farmModel.getSeeds().remove(farmModel.getSeeds().get(i));
                farmModel.getBushes().add(bush);
                return;
            }
        }
    }
    private static void checkBushes(FarmModel farmModel){
        int i = 0;
        for(BushModel bush : farmModel.getBushes()){
            if(bush.getPlantedAt() + bush.getGrowthTime() == farmModel.getTimeCounter()){
                i = farmModel.getBushes().indexOf(bush);
                TreeModel tree = new TreeModel(farmModel.getBushes().get(i).getPos().getX(), farmModel.getBushes().get(i).getPos().getY());
                farmModel.getBushes().remove(farmModel.getBushes().get(i));
                farmModel.getTrees().add(tree);
                return;
            }
        }
    }
    private static void checkIfCanFish(LakeModel lake, int timer){
        if(!lake.isPossibleFishing()){
            if(lake.getLastFished() + lake.getTimeInterval() <= timer){
                lake.setPossibleFishing(true);
            }
        }
    }
    private static void upgradePrizes(FarmModel farmModel) {
        List<GiftModel> gifts = (new GenerateGift()).generate(farmModel,1);
        if(!gifts.isEmpty()) farmModel.getGifts().addAll(gifts);
    }

    public static void timeCheck(FarmModel farmModel) {
        farmModel.setTimeCounter(farmModel.getTimeCounter()+1);
        checkSeeds(farmModel);
        checkBushes(farmModel);
        movement.moveAnimals(farmModel);
        if(farmModel.getTimeCounter()%100 == 0) farmModel.getMarket().randomGenerate();
        if(farmModel.getTimeCounter()%20 == 0) farmModel.getTrees().addAll((new GenerateTree()).generate(farmModel,1));
        upgradePrizes(farmModel);
        checkIfCanFish(farmModel.getLake(), farmModel.getTimeCounter());
    }

}
