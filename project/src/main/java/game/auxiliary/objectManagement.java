package game.auxiliary;

import game.models.*;
import game.memento.*;
import game.models.elements.*;
import game.models.elements.plants.*;

import java.util.HashMap;

public class objectManagement {

    static public boolean pickIsValid(HashMap<String, Integer> map, int pick) {
        Integer tree = -1, bush = -1, seed = -1, fish = -1, flower = -1, fence = -1;

        if(map.containsKey("TREE"))     tree = map.get("TREE");
        if(map.containsKey("BUSH"))     bush = map.get("BUSH");
        if(map.containsKey("SEED"))     seed = map.get("SEED");
        if(map.containsKey("FISH"))     fish = map.get("FISH");
        if(map.containsKey("FLOWER"))   flower = map.get("FLOWER");
        if(map.containsKey("FENCE"))    fence = map.get("FENCE");

        if(pick == tree) return true;
        else if (pick == bush) return true;
        else if (pick == seed) return true;
        else if (pick == flower) return true;
        else if (pick == fence) return true;
        else return pick == fish;

    }
    static public void plant(FarmModel farmModel, HashMap<String, Integer> map, int pick) {
        if(pickIsValid(map, pick)){
            if(map.get("TREE") == pick) farmModel.removeFromInventory(new TreeModel());
            else if(map.get("BUSH") == pick) farmModel.removeFromInventory(new BushModel());
            else if(map.get("SEED") == pick) farmModel.removeFromInventory(new SeedModel());
            else if(map.get("FLOWER") == pick) farmModel.removeFromInventory(new FlowerModel());
            else if(map.get("FENCE") == pick) farmModel.removeFromInventory(new FenceModel());
        }
    }
    static public void addOrSell(FarmModel farmModel, ElementModel element, int quantity) {
        for(int i = 0; i< quantity; i++) {
            if(farmModel.getInventory().getElements().size() < farmModel.getInventory().getCapacity())
                farmModel.getInventory().addElement(element);
            else
            if(element instanceof PlantModel) farmModel.getFarmer().setBalance(farmModel.getFarmer().getBalance()+( ((PlantModel) element).getPrice())+1);
            else if(element instanceof FishModel) farmModel.getFarmer().setBalance(farmModel.getFarmer().getBalance()+( ((FishModel) element).getPrice())+1);
            else if(element instanceof FenceModel) farmModel.getFarmer().setBalance(farmModel.getFarmer().getBalance()+( ((FenceModel) element).getPrice())+1);
        }
    }

    static public void treeUpdate(FarmModel farmModel, int w1, int w2, int h1, int h2) {
        int before = farmModel.getTrees().size(), after;
        if(farmModel.getTrees().removeIf(tree -> tree.getPos().getX() >= w1 && tree.getPos().getX() <= w2 && tree.getPos().getY() >= h1 && tree.getPos().getY() <= h2)) {
            after = farmModel.getTrees().size();
            int quantity = before - after;
            addOrSell(farmModel, new TreeModel(), quantity);
        }
    }
    static public void flowerUpdate(FarmModel farmModel, int w1, int w2, int h1, int h2) {
        int before = farmModel.getFlowers().size(), after;
        if(farmModel.getFlowers().removeIf(flower -> flower.getPos().getX() >= w1 && flower.getPos().getX() <= w2 && flower.getPos().getY() >= h1 && flower.getPos().getY() <= h2)) {
            after = farmModel.getFlowers().size();
            int quantity = before - after;
            addOrSell(farmModel, new FlowerModel(), quantity);
        }
    }

    static public void restoreElement(FarmModel farmModel, String[] parts, String string) {
        boolean first = true;
        if (parts[0].equals(string)) {
            for(String a : parts){
                if(first) first = false;
                else{
                    switch (string) {
                        case "TREES":
                            TreeModel t = new TreeModel();
                            t.restore(new PlantMemento(a));
                            farmModel.getTrees().add(t);
                            break;
                        case "FENCES":
                            FenceModel f = new FenceModel();
                            f.restore(new FenceMemento(a));
                            farmModel.getFences(true).add(f);
                            break;
                        case "ANIMALS":
                            AnimalModel an = new AnimalModel();
                            an.restore(new ElementMemento(a));
                            farmModel.getAnimals().add(an);
                            break;
                        case "SEEDS":
                            SeedModel s = new SeedModel();
                            s.restore(new PlantMemento(a));
                            farmModel.getSeeds().add(s);
                            break;
                        case "BUSHES":
                            BushModel b = new BushModel();
                            b.restore(new PlantMemento(a));
                            farmModel.getBushes().add(b);
                            break;
                        case "FLOWERS":
                            FlowerModel fl = new FlowerModel();
                            fl.restore(new PlantMemento(a));
                            farmModel.getFlowers().add(fl);
                            break;
                    }
                }
            }
        }
    }
    static public void clearAll(FarmModel farmModel) {
        farmModel.getTrees().clear();
        farmModel.getFences(true).clear();
        farmModel.getAnimals().clear();
        farmModel.getSeeds().clear();
        farmModel.getBushes().clear();
        farmModel.getFlowers().clear();
        farmModel.getLake().removeAll();
    }
}
