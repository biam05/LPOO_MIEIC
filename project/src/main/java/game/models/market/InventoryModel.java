package game.models.market;

import game.memento.InventoryMemento;
import game.models.elements.*;
import game.models.elements.plants.BushModel;
import game.models.elements.plants.FlowerModel;
import game.models.elements.plants.SeedModel;
import game.models.elements.plants.TreeModel;

import java.util.*;

public class InventoryModel {
    public int capacity;
    public List<ElementModel> elements;

    public InventoryModel(){
        this.capacity = 20;
        this.elements = initializeElements();
    }

    public List<ElementModel> initializeElements(){
        List<ElementModel> element = new ArrayList<>();
        element.add(new SeedModel());
        element.add(new SeedModel());
        element.add(new SeedModel());
        return element;
    }

    public int getTrees() {
        int treeCounter = 0;
        for (ElementModel element : elements) {
            if (element.getClass() == TreeModel.class) treeCounter++;
        }
        return treeCounter;
    }
    public int getBushes() {
        int bushCounter = 0;
        for (ElementModel element : elements) {
            if (element.getClass() == BushModel.class) bushCounter++;
        }
        return bushCounter;
    }
    public int getSeeds() {
        int seedCounter = 0;
        for (ElementModel element : elements) {
            if (element.getClass() == SeedModel.class) seedCounter++;
        }
        return seedCounter;
    }
    public int getFishes() {
        int fishCounter = 0;
        for (ElementModel element : elements) {
            if (element.getClass() == FishModel.class) fishCounter++;
        }
        return fishCounter;
    }
    public int getFlowers(){
        int flowerCounter = 0;
        for (ElementModel element : elements) {
            if (element.getClass() == FlowerModel.class) flowerCounter++;
        }
        return flowerCounter;
    }
    public int getFences(){
        int fenceCounter = 0;
        for (ElementModel element : elements) {
            if (element.getClass() == FenceModel.class) fenceCounter++;
        }
        return fenceCounter;
    }

    public int getCapacity() { return capacity;}
    public void updateCapacity() { this.capacity+=5 ; }

    public List<ElementModel> getElements() {
        return elements;
    }
    public List<ElementModel> getPlants() {
        List<ElementModel> result = new ArrayList<>();
        for (ElementModel element: elements) {
            if(!element.equals(new FishModel())) result.add(element);
        }
        return result;
    }

    public boolean addElement(ElementModel element) {
        if(this.elements.size() < getCapacity()){
            this.elements.add(element);
            return true;
        }
        return false;
    }
    public void removeElement(int i) {
        this.elements.remove(i);
    }

    private List<String> sortedElementClasses(){
        List<String> classes = new ArrayList<>();

        if(getBushes()>0)   classes.add("BUSH");
        if(getFences()>0)   classes.add("FENCE");
        if(getFishes()>0)   classes.add("FISH");
        if(getFlowers()>0)  classes.add("FLOWER");
        if(getSeeds()>0)    classes.add("SEED");
        if(getTrees()>0)    classes.add("TREE");

        return classes;
    }
    public int indexInInventory(ElementModel element) {
        for(ElementModel el : elements) if(el.getClass() == element.getClass()) return elements.indexOf(el);
        return -1;
    }
    public HashMap<String, Integer> indexSelection(boolean all) {
        HashMap<String, Integer> result = new HashMap<>();
        List<String> classes = sortedElementClasses();
        if(!all) classes.remove("FISH");

        for(int i = 0; i < classes.size(); i++)
            result.put(classes.get(i), i);

        if(!classes.contains("BUSH"))   result.put("BUSH", -1);
        if(!classes.contains("FENCE"))  result.put("FENCE", -1);
        if(!classes.contains("FISH"))   result.put("FISH", -1);
        if(!classes.contains("FLOWER")) result.put("FLOWER", -1);
        if(!classes.contains("TREE"))   result.put("TREE",-1);
        if(!classes.contains("SEED"))   result.put("SEED", -1);

        return result;
    }

    //game.memento pattern functions (originator)
    public InventoryMemento save() {
        return new InventoryMemento(getCapacity(), getElements());
    }
    public void restore(InventoryMemento inventoryState) {
        String[] arr = inventoryState.getState().split("\n");

        this.capacity = Integer.parseInt(arr[0]);
        this.elements.removeAll(getElements());

        for(int i = 1; i<arr.length; i++) {
            String[] parts = arr[i].trim().split("\\s");
            switch (parts[1]) {
                case "TREE":
                    for (int j = 0; j < Integer.parseInt(parts[0]); j++) this.elements.add(new TreeModel());
                    break;
                case "BUSH":
                    for (int j = 0; j < Integer.parseInt(parts[0]); j++) this.elements.add(new BushModel());
                    break;
                case "SEED":
                    for (int j = 0; j < Integer.parseInt(parts[0]); j++) this.elements.add(new SeedModel());
                    break;
                case "FISH":
                    for (int j = 0; j < Integer.parseInt(parts[0]); j++) this.elements.add(new FishModel());
                    break;
                case "FLOWER":
                    for (int j = 0; j < Integer.parseInt(parts[0]); j++) this.elements.add(new FlowerModel());
                    break;
                case "FENCE":
                    for (int j = 0; j < Integer.parseInt(parts[0]); j++) this.elements.add(new FenceModel());
                    break;
            }
        }
    }

}
