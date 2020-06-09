package game.models;

import game.memento.FarmMemento;
import game.memento.FarmerMemento;
import game.memento.InventoryMemento;
import game.auxiliary.TYPE;
import game.auxiliary.generator.*;
import game.auxiliary.*;
import game.models.elements.*;
import game.models.elements.lake.LakeModel;
import game.models.elements.plants.*;
import game.models.market.InventoryModel;
import game.models.market.MarketModel;
import game.models.market.StallModel;
import game.models.market.UpgradeModel;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class FarmModel {
    // dimensions, time, upgrades
    private int width;
    private int height;
    private int timeCounter;
    private int upgradeCounter;
    //object
    private FarmerModel farmer;
    private InventoryModel inventory;
    private MarketModel market;
    private List<TreeModel> trees;
    private List<FenceModel> fences;
    private List<AnimalModel> animals;
    private List<SeedModel> seeds;
    private List<BushModel> bushes;
    private List<FlowerModel> flowers;
    private List<GiftModel> gifts;
    private LakeModel lake;
    //files
    private File file;

    // constructor
    public FarmModel(int width, int height) {
        this.file = new File("farm.txt");
        this.upgradeCounter = 0;
        this.timeCounter = 0;
        this.height = height;
        this.width = width;
        this.farmer = new FarmerModel(width / 2, height / 2);
        this.trees = (new GenerateTree()).generate(this,15);
        this.fences = (new GenerateFence(true)).generate(this,0);
        this.animals = (new GenerateAnimal(true)).generate(this,5);
        this.seeds = new ArrayList<>();
        this.bushes = new ArrayList<>();
        this.flowers = new ArrayList<>();
        this.gifts = new ArrayList<>();
        this.inventory = new InventoryModel();
        this.market = new MarketModel(upgradeCounter);
        this.lake = new LakeModel();
    }

    // getters & setters
    public FarmerModel getFarmer() { return farmer;}
    public int getWidth() { return width; }
    public int getHeight() { return height; }
    public int getTimeCounter() {return timeCounter;}
    public int getUpgradeCounter() { return upgradeCounter; }
    public InventoryModel getInventory() { return inventory;}
    public MarketModel getMarket() { return market; }
    public List<TreeModel> getTrees() { return trees; }
    public List<FenceModel> getFences(boolean all) {
        if(all) return fences;
        List<FenceModel> result = this.fences;
        result.removeIf(FenceModel::isLimitFence);
        return result;
    }
    public LakeModel getLake() { return lake;}
    public List<SeedModel> getSeeds() { return seeds; }
    public List<ElementModel> getAllElements() {
        List<ElementModel> objects = new ArrayList<>();

        objects.addAll(trees);
        objects.addAll(fences);
        objects.addAll(animals);
        objects.addAll(seeds);
        objects.addAll(bushes);
        objects.addAll(lake.getDeck());
        objects.addAll(lake.getWater());
        objects.addAll(flowers);
        objects.addAll(gifts);
        objects.add(farmer);

        return objects;
    }
    public List<AnimalModel> getAnimals() { return animals; }
    public List<FlowerModel> getFlowers() { return flowers;}
    public List<BushModel> getBushes() {return bushes;}
    public List<GiftModel> getGifts() {return gifts;}

    public void setTimeCounter(int timeCounter) {this.timeCounter = timeCounter;}
    private int updateUpgradeCounter() {
        upgradeCounter++;
        this.inventory.updateCapacity();
        this.market.updateUpgrade();
        return upgradeCounter;
    }

    // upgrades
    private void readGift(GiftModel gift) {
        switch (gift.getType()) {
            case MONEY:
                this.farmer.setBalance(this.farmer.getBalance() + gift.getQuantity() * 7);
                return;
            case ANIMAL:
                this.animals.addAll((new GenerateAnimal()).generate(this,gift.getQuantity()));
                return;
            case FISHBAIT:
                this.farmer.setFishBait(this.farmer.getFishBait() + gift.getQuantity());
                return;
            case FENCE:
                objectManagement.addOrSell(this, new FenceModel(), gift.getQuantity());
                return;
        }
        PlantFactory plantFactory = new PlantFactory();
        objectManagement.addOrSell(this, plantFactory.getPlant(gift.getType()), gift.getQuantity());
    }
    public void fish(){
        if(this.lake.canFish(this.timeCounter)) {
            if(this.farmer.getFishBait()>=5) {
                this.farmer.setFishBait(this.farmer.getFishBait()-5);
                Random random = new Random();
                int quantity = random.nextInt(3)+1;
                objectManagement.addOrSell(this, new FishModel(), quantity);
            }
        }
    }

    // movement related functions
    public boolean pickObject(){
        int i = movement.AdjacentElement(this);
        List<ElementModel> objects = getAllElements();
        if(i != -1)
            if(objects.get(i) instanceof GiftModel){
                readGift((GiftModel) objects.get(i));
                gifts.remove(objects.get(i));
                time.timeCheck(this);
                return true;
            }
            else if(objects.get(i) instanceof FenceModel){
                if(!((FenceModel) objects.get(i)).isLimitFence()){
                    fences.remove(objects.get(i));
                    inventory.addElement(objects.get(i));
                }
            }
            else if(inventory.addElement(objects.get(i))){
                if(objects.get(i) instanceof TreeModel) trees.remove(objects.get(i));
                else if(objects.get(i) instanceof SeedModel) seeds.remove(objects.get(i));
                else if(objects.get(i) instanceof BushModel) bushes.remove(objects.get(i));
                else if(objects.get(i) instanceof FlowerModel) flowers.remove(objects.get(i));
                time.timeCheck(this);
                return true;
            }
        return false;
    }
    public void moveObject(PositionModel position, ElementModel element) {
        if(movement.canObjectMove(this,position,false) && collision.marketCollision(this,position)) {
            if (element instanceof FarmerModel){
                element.setPos(position);
                time.timeCheck(this);
            }
            else if(element instanceof AnimalModel)
                if(!collision.rugCollision(this,position))
                    element.setPos(position);
        }
    }

    // object management
    public boolean addElement(Plantable plantable) {
        PositionModel position = movement.nextPossiblePosition(this,true);
        if(position.equals(new PositionModel(-1,-1))) return false;
        else {
            plantable.addElement(this,position);
            time.timeCheck(this);
            return true;
        }
    }
    public boolean removeFromInventory(ElementModel element) {
        int i = inventory.indexInInventory(element);
        if(i != -1) {
            if(addElement((Plantable)element)) {
                inventory.removeElement(i);
                return true;
            }
        }
        return false;
    }

    //market
    public boolean sellMarket(HashMap<String, Integer> map, int pick) {
        if(objectManagement.pickIsValid(map, pick)){
            if(map.get("TREE") == pick) {
                int i = this.inventory.indexInInventory(new TreeModel());
                inventory.removeElement(i);
                this.farmer.setBalance(this.farmer.getBalance()+(new TreeModel().getPrice())+1);
                return true;
            }
            else if(map.get("BUSH") == pick) {
                int i = this.inventory.indexInInventory(new BushModel());
                inventory.removeElement(i);
                this.farmer.setBalance(this.farmer.getBalance()+(new BushModel().getPrice())+1);
                return true;
            }
            else if(map.get("SEED") == pick){
                int i = this.inventory.indexInInventory(new SeedModel());
                inventory.removeElement(i);
                this.farmer.setBalance(this.farmer.getBalance()+(new SeedModel().getPrice())+1);
                return true;
            }
            else if(map.get("FISH") == pick) {
                int i = this.inventory.indexInInventory(new FishModel());
                inventory.removeElement(i);
                this.farmer.setBalance(this.farmer.getBalance()+(new FishModel().getPrice())+1);
                return true;
            }
            else if(map.get("FLOWER") == pick) {
                int i = this.inventory.indexInInventory(new FlowerModel());
                inventory.removeElement(i);
                this.farmer.setBalance(this.farmer.getBalance()+(new FlowerModel().getPrice())+1);
                return true;
            }
            else if(map.get("FENCE") == pick) {
                int i = this.inventory.indexInInventory(new FenceModel());
                inventory.removeElement(i);
                this.farmer.setBalance(this.farmer.getBalance() + (new FenceModel().getPrice()) + 1);
                return true;
            }
        }
        return false;
    }
    public boolean buyMarket(int pick) {

        StallModel stall = this.market.getStalls().get(9-pick);
        if(stall.getType().equals(TYPE.UPGRADE)) {
            if (this.farmer.getBalance() >= stall.getPrice()) {
                this.farmer.setBalance(this.farmer.getBalance() - stall.getPrice());
                this.market.removeStall(stall);
                this.market.addStall(new StallModel(new UpgradeModel(updateUpgradeCounter()+1)));
                UpgradeModel.upgrade(this, this.getUpgradeCounter());
                time.timeCheck(this);
                return true;
            }
        }
        else {
            if (this.farmer.getBalance() >= stall.getPrice()) {
                if ((this.inventory.getElements().size() + stall.getQuantity()) <= this.inventory.getCapacity()) {
                    for(int i = 0; i<stall.getQuantity(); i++) {
                        if(stall.getType().equals(TYPE.FENCE)) this.inventory.addElement(stall.getFence());
                        else this.inventory.addElement(stall.getPlant());
                    }
                    this.farmer.setBalance(this.farmer.getBalance() - stall.getPrice());
                    this.market.removeStall(stall);
                    return true;
                }
            }
        }
        return false;
    }

    //game.memento pattern (originator)
    public FarmMemento save() {
        return new FarmMemento(this.width,this.height,this.timeCounter, this.upgradeCounter,
                this.farmer,this.trees,this.fences,this.inventory
                ,this.animals,this.seeds,this.bushes, this.flowers);
    }
    public void restore(FarmMemento farmState){
        if(farmState.getState().equals("")) return;

        String[] arr = farmState.getState().split("\n::::::::::\n");

        objectManagement.clearAll(this);

        this.width = Integer.parseInt(arr[0]);
        this.height = Integer.parseInt(arr[1]);
        this.timeCounter = Integer.parseInt(arr[2]);
        this.upgradeCounter = Integer.parseInt(arr[3]);

        this.market = new MarketModel(this.upgradeCounter);
        if(this.upgradeCounter >=3) UpgradeModel.upgrade(this, 3);

        this.farmer.restore(new FarmerMemento(arr[4]));

        objectManagement.restoreElement(this,arr[5].split("\n"),"TREES");
        objectManagement.restoreElement(this,arr[6].split("\n"),"FENCES");

        this.inventory.restore(new InventoryMemento(arr[7]));

        objectManagement.restoreElement(this,arr[8].split("\n"),"ANIMALS");
        objectManagement.restoreElement(this,arr[9].split("\n"),"SEEDS");
        objectManagement.restoreElement(this,arr[10].split("\n"),"BUSHES");
        objectManagement.restoreElement(this,arr[11].split("\n"),"FLOWERS");
    }
    public void saveToFile() throws IOException {
        FarmMemento fs = save();

        FileWriter fw = new FileWriter(this.file.getPath());
        fw.write(fs.getState());
        fw.close();
    }
    public String readFile() throws IOException{

        StringBuilder temp = new StringBuilder();
        Scanner sc = new Scanner(this.file);
        while (sc.hasNextLine()) {
            temp.append(sc.nextLine());
            temp.append("\n");
        }
        String data = temp.toString();
        sc.close();
        return data;
    }
}