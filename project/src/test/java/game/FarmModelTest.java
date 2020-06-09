package game;

import game.auxiliary.TYPE;
import game.auxiliary.generator.GenerateAnimal;
import game.auxiliary.generator.GenerateGift;
import game.auxiliary.time;
import game.commands.memento.SaveCommand;
import game.models.FarmModel;
import game.models.PositionModel;
import game.models.elements.*;
import game.models.elements.plants.*;
import game.models.market.StallModel;
import game.models.market.UpgradeModel;
import org.junit.Test;
import java.util.HashMap;
import java.util.List;

import static game.auxiliary.TYPE.*;
import static game.auxiliary.objectManagement.addOrSell;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.*;

public class FarmModelTest {

    @Test
    public void addElements(){
        FarmModel farmModel = new FarmModel(60,25);

        // adding one fence
        int no_fences = farmModel.getFences(true).size();
        FenceModel fence = new FenceModel(10, 10, true);
        PositionModel fencebeforepos = fence.getPos();
        boolean fencebeforelimit = fence.isLimitFence();
        fence.addElement(farmModel, new PositionModel(11, 11));
        assertNotEquals(fencebeforepos, fence.getPos());
        assertNotEquals(fencebeforelimit, fence.isLimitFence());
        assertEquals(no_fences+1, farmModel.getFences(true).size());

        // adding one gift
        int no_gifts = farmModel.getGifts().size();
        GiftModel gift = new GiftModel();
        TYPE type = gift.getType();
        List<TYPE> types = getTypes(true);
        int quantity = gift.getQuantity();
        assertTrue(types.contains(type));
        assertTrue(quantity >= 2 && quantity <= 11);
        List<GiftModel> farm_gifts = farmModel.getGifts();
        farm_gifts.add(gift);
        assertEquals(no_gifts+1, farmModel.getGifts().size());

    }

    @Test
    public void prices(){
        int price = 0;
        FishModel fish = new FishModel();
        price = fish.getPrice();
        assertEquals(35, price);

        FenceModel fence = new FenceModel();
        price = fence.getPrice();
        assertEquals(15, price);
    }

    @Test
    public void TimeTest() {
        FarmModel farmModel = new FarmModel(60,25);

        //game starts with counter at 0
        assertEquals(0, farmModel.getTimeCounter());

        //after a move or action, timer increments by 1
        farmModel.moveObject(farmModel.getFarmer().moveRight(), farmModel.getFarmer());
        assertEquals(1, farmModel.getTimeCounter());
        farmModel.removeFromInventory(new SeedModel(1,1));
        assertEquals(2, farmModel.getTimeCounter());

        time.timeCheck(farmModel);
        StallModel stall = farmModel.getMarket().getStalls().get(0);
        int num_trees = farmModel.getTrees().size();
        farmModel.setTimeCounter(99);
        time.timeCheck(farmModel);
        StallModel stall_after = farmModel.getMarket().getStalls().get(0);
        assertFalse(stall.getName().equals(stall_after.getName()) && stall.getPrice() == stall_after.getPrice() && stall.getQuantity() == stall_after.getQuantity());
        farmModel.setTimeCounter(19);
        time.timeCheck(farmModel);
        assertEquals(num_trees+2,farmModel.getTrees().size());
    }

    @Test
    public void InvTest() {
        FarmModel farmModel = new FarmModel(60,25);

        //the game starts with 3 seeds in the inventory
        assertEquals(3, farmModel.getInventory().getElements().size());
        assertTrue(farmModel.removeFromInventory(new SeedModel()));
        assertEquals(2, farmModel.getInventory().getElements().size());

        // (and nothing more)
        assertFalse(farmModel.removeFromInventory(new TreeModel()));

        //there aren't any flowers in inventory
        int flowers = farmModel.getInventory().indexInInventory(new FlowerModel());
        assertEquals(-1, flowers);

        farmModel.getInventory().addElement(new TreeModel());
        farmModel.getInventory().addElement(new BushModel());
        farmModel.getInventory().addElement(new FishModel());

        //will return an ordered map containing key(string referring to Element in question) and value (respective index)
        HashMap<String, Integer> invMap = farmModel.getInventory().indexSelection(true);

        int result = invMap.get("BUSH"); // bush has index 0 : is present in inventory + 1st in alphabetical order
        assertEquals(0, result);
        result = invMap.get("TREE"); // tree has index 3 : is present in inventory + 4rd in alphabetical order
        assertEquals(3, result);
        result = invMap.get("FLOWER"); // flower has index -1 : not present in inventory
        assertEquals(-1, result);

        //pickObject
        assertTrue(farmModel.pickObject());
        assertFalse(farmModel.pickObject());

        assertEquals(6, farmModel.getInventory().getElements().size());

        //initial capacity is 20
        assertEquals(20, farmModel.getInventory().getCapacity());
        //update adds 5 to capacity
        farmModel.getInventory().updateCapacity();
        assertEquals(25, farmModel.getInventory().getCapacity());

        assertEquals(20,farmModel.getFarmer().getBalance());

        addOrSell(farmModel, new TreeModel(), 25); //won't go over limit (adds 19 to inv), will sell extras (6)

        assertEquals(25,farmModel.getInventory().getElements().size());
        assertEquals(farmModel.getFarmer().getBalance(), 20+6*(new TreeModel().getPrice()+1));

        // number of all plants
        assertEquals(25, farmModel.getInventory().getPlants().size());
        // cant add more trees cause the limit was reached
        assertFalse(farmModel.getInventory().addElement(new TreeModel()));
        farmModel.getInventory().updateCapacity();
        // add 1 fish -> not a plant
        assertTrue(farmModel.getInventory().addElement(new FishModel()));
        assertEquals(26, farmModel.getInventory().getPlants().size());
    }

    @Test
    public void plantsGrowthTest() {

        FarmModel farmModel = new FarmModel(60,25);
        int seedCounter = 0, bushCounter = 0, treeCounter = 0;

        //3 seeds initially
        List<ElementModel> elements1 = farmModel.getInventory().getElements();
        for (ElementModel element : elements1) {
            if (element.getClass() == SeedModel.class) seedCounter++;
            else if (element.getClass() == BushModel.class) bushCounter++;
            else if (element.getClass() == TreeModel.class) treeCounter++;
        }
        assertEquals(3, seedCounter);

        seedCounter = 0;
        bushCounter = 0;
        treeCounter = 0;

        // planting a seed
        farmModel.removeFromInventory(new SeedModel(1,1));

        // letting it grow
        farmModel.moveObject(farmModel.getFarmer().moveLeft(), farmModel.getFarmer());
        farmModel.moveObject(farmModel.getFarmer().moveLeft(), farmModel.getFarmer());
        farmModel.moveObject(farmModel.getFarmer().moveRight(), farmModel.getFarmer());
        farmModel.moveObject(farmModel.getFarmer().moveRight(), farmModel.getFarmer());

        // harvesting a bush
        assertTrue(farmModel.pickObject());

        //2 seeds and 1 bush at the end
        List<ElementModel> elements2 = farmModel.getInventory().getElements();
        for (ElementModel element : elements2) {
            if (element.getClass() == SeedModel.class) seedCounter++;
            else if (element.getClass() == BushModel.class) bushCounter++;
            else if (element.getClass() == TreeModel.class) treeCounter++;
        }
        assertEquals(2, seedCounter);
        assertEquals(1, bushCounter);

        seedCounter = 0;
        bushCounter = 0;
        treeCounter = 0;

        // planting a bush
        farmModel.removeFromInventory(new BushModel(1,1));

        // letting it grow
        farmModel.moveObject(farmModel.getFarmer().moveLeft(), farmModel.getFarmer());
        farmModel.moveObject(farmModel.getFarmer().moveLeft(), farmModel.getFarmer());
        farmModel.moveObject(farmModel.getFarmer().moveRight(), farmModel.getFarmer());
        farmModel.moveObject(farmModel.getFarmer().moveRight(), farmModel.getFarmer());

        // harvesting a tree
        assertTrue(farmModel.pickObject());

        //2 seeds and 1 bush at the end
        List<ElementModel> elements3 = farmModel.getInventory().getElements();
        for (ElementModel element : elements3) {
            if (element.getClass() == SeedModel.class) seedCounter++;
            else if (element.getClass() == BushModel.class) bushCounter++;
            else if (element.getClass() == TreeModel.class) treeCounter++;
        }
        assertEquals(2, seedCounter);
        assertEquals(1, treeCounter);

        int animalcounter = farmModel.getAnimals().size();
        GenerateAnimal generateAnimal = new GenerateAnimal();
        List<AnimalModel> animalModelList = generateAnimal.generate(farmModel,3);
        farmModel.getAnimals().addAll(animalModelList);
        assertEquals(animalcounter+3, farmModel.getAnimals().size());
    }

    @Test
    public void upgrades() {
        FarmModel farmModel = new FarmModel(60,25);

        //1st upgrade -- fences
        int initialFences = farmModel.getFences(true).size();
        int nonLimitFence = farmModel.getFences(false).size();

        assertEquals(0, farmModel.getUpgradeCounter());
        assertEquals(0, nonLimitFence);

        farmModel.getFarmer().setBalance(100);
        farmModel.buyMarket(5); //upgrade special
        assertTrue((initialFences!= farmModel.getFences(true).size()));
        assertEquals(1, farmModel.getUpgradeCounter());
        nonLimitFence = farmModel.getFences(false).size();
        assertTrue(0 != nonLimitFence);

        //2nd upgrade -- flowers
        int flowers = farmModel.getFlowers().size();

        assertEquals(1, farmModel.getUpgradeCounter());
        assertEquals(0, flowers);

        farmModel.getFarmer().setBalance(160);
        farmModel.buyMarket(5); //upgrade special
        flowers = farmModel.getFlowers().size();
        assertEquals(2, farmModel.getUpgradeCounter());
        assertEquals(10,flowers);

        //3rd upgrade -- fishing
        int fish = farmModel.getInventory().getFishes();

        assertEquals(2, farmModel.getUpgradeCounter());
        assertEquals(0, fish);

        farmModel.getFarmer().setBalance(260);
        farmModel.buyMarket(5); //upgrade special
        assertEquals(10,farmModel.getFarmer().getFishBait());
        farmModel.setTimeCounter(farmModel.getTimeCounter()+200);
        farmModel.fish();
        fish = farmModel.getInventory().getFishes();
        assertEquals(3, farmModel.getUpgradeCounter());
        assertTrue(fish != 0);
        assertEquals(5, farmModel.getFarmer().getFishBait());

        // upgrade correct price
        UpgradeModel testing_upgrade_2 = new UpgradeModel(2);
        assertEquals(2, testing_upgrade_2.getUpgradeNumber());
        assertEquals(160, testing_upgrade_2.getPrice());
        UpgradeModel testing_upgrade_3 = new UpgradeModel(3);
        assertEquals(3, testing_upgrade_3.getUpgradeNumber());
        assertEquals(260, testing_upgrade_3.getPrice());
        UpgradeModel testing_upgrade_0 = new UpgradeModel(30);
        assertEquals(30, testing_upgrade_0.getUpgradeNumber());
        assertEquals(0, testing_upgrade_0.getPrice());
    }

    @Test
    public void plantsTest(){

        PlantFactory plantFactory = new PlantFactory();

        PlantModel tree = plantFactory.getPlant(TREE);
        PlantModel bush = plantFactory.getPlant(BUSH);
        PlantModel seed = plantFactory.getPlant(SEED);
        PlantModel flower = plantFactory.getPlant(FLOWER);
        PlantModel nullO = plantFactory.getPlant(FENCE);

        assertThat(tree, instanceOf(TreeModel.class));
        assertThat(bush, instanceOf(BushModel.class));
        assertThat(seed, instanceOf(SeedModel.class));
        assertThat(flower, instanceOf(FlowerModel.class));
        assertNull(nullO);

        assertEquals(10,flower.getPrice());
        assertEquals(9,tree.getPrice());
        assertEquals(3,seed.getPrice());
        assertEquals(6,bush.getPrice());

        FarmModel fm = new FarmModel(60,25);

        Plantable tree1 = new TreeModel();
        int counter = fm.getTrees().size();
        tree1.addElement(fm,new PositionModel(2,2));
        assertEquals(counter+1,fm.getTrees().size());

        Plantable flower1 = new FlowerModel();
        counter = fm.getFlowers().size();
        flower1.addElement(fm,new PositionModel(2,3));
        assertEquals(counter+1,fm.getFlowers().size());
    }

    @Test
    public void FarmTest() {
        FarmModel fm = new FarmModel(60,25);
        fm.getFarmer().setPos(new PositionModel(20,20));
        fm.moveObject(new PositionModel(20,21),fm.getTrees().get(0));
        fm.moveObject(new PositionModel(20,22),fm.getTrees().get(1));
        fm.moveObject(new PositionModel(21,20),fm.getTrees().get(2));
        fm.moveObject(new PositionModel(22,20),fm.getTrees().get(3));

        int counter = fm.getFences(true).size();
        FenceModel fenceF = new FenceModel(-1,-1,true);
        assertTrue(fm.addElement(fenceF));
        assertTrue(fm.addElement(fenceF));
        assertTrue(fm.addElement(fenceF));
        assertTrue(fm.addElement(fenceF));
        assertEquals(counter+4,fm.getFences(true).size());

        AnimalModel ani = new AnimalModel();
        fm.moveObject(new PositionModel(50,4),ani);
        fm.moveObject(new PositionModel(2,3),ani);
    }

    @Test
    public void gifts(){
        FarmModel fm = new FarmModel(60, 25);

        fm.getFarmer().setBalance(500);

        List<GiftModel> gifts = (new GenerateGift()).generate(fm, 1);

        assertEquals(0, gifts.size());

        fm.buyMarket(5);
        fm.buyMarket(5);

        assertEquals(2, fm.getUpgradeCounter());

        assertEquals(0, fm.getLake().getWater().size());

        fm.buyMarket(5);

        assertEquals(3, fm.getUpgradeCounter());

        assertNotEquals(0, fm.getLake().getWater().size());
        assertNotEquals(0, fm.getTimeCounter() % 200);

        fm.setTimeCounter(200);

        assertEquals(3, fm.getUpgradeCounter());

        assertNotEquals(0, fm.getLake().getWater().size());
        assertEquals(0, fm.getTimeCounter() % 200);

        gifts = (new GenerateGift()).generate(fm, 1);

        assertEquals(1, gifts.size());

    }

}
