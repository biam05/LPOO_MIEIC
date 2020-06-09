package game.auxiliary;

import game.models.*;
import game.models.elements.*;
import game.models.elements.plants.PlantModel;

import java.util.List;
import java.util.Random;

public class movement {

    static public boolean canObjectMove(FarmModel fm, PositionModel position, boolean plant){
        if (position.getX() >= fm.getWidth()-1 || position.getX() < 1 || position.getY() >= fm.getHeight()-1 || position.getY() < 1) return false;
        if(plant) if(collision.rugCollision(fm,position) || !collision.marketCollision(fm,position)) return false;
        return collision.elementCollision(fm,position);
    }
    static public int AdjacentElement(FarmModel fm){
        List<ElementModel> elements = fm.getAllElements();
        for (ElementModel element : elements) {
            if(element instanceof PlantModel || element instanceof GiftModel || (element instanceof FenceModel && !((FenceModel) element).isLimitFence())) {
                if(fm.getFarmer().getPos().getX() == element.getPos().getX()-1 && fm.getFarmer().getPos().getY() == element.getPos().getY()||
                        fm.getFarmer().getPos().getX() == element.getPos().getX()+1 && fm.getFarmer().getPos().getY() == element.getPos().getY() ||
                        fm.getFarmer().getPos().getY() == element.getPos().getY()-1 && fm.getFarmer().getPos().getX() == element.getPos().getX() ||
                        fm.getFarmer().getPos().getY() == element.getPos().getY()+1 && fm.getFarmer().getPos().getX() == element.getPos().getX())
                    return elements.indexOf(element);
            }
        }
        return -1;
    }
    static public PositionModel nextPossiblePosition(FarmModel fm, boolean plant) {
        if(canObjectMove(fm,fm.getFarmer().moveRight(),plant)) return fm.getFarmer().moveRight();
        else if(canObjectMove(fm, fm.getFarmer().moveUp(),plant)) return fm.getFarmer().moveUp();
        else if(canObjectMove(fm, fm.getFarmer().moveLeft(),plant)) return fm.getFarmer().moveLeft();
        else if(canObjectMove(fm,fm.getFarmer().moveDown(),plant)) return fm.getFarmer().moveDown();
        return new PositionModel(-1,-1);
    }

    static public void moveAnimals(FarmModel fm){
        for(AnimalModel animal : fm.getAnimals()) fm.moveObject(animal.move(), animal);
    }
    static public void organiseAnimals(List<AnimalModel> animals) {
        for(AnimalModel animal : animals) {
            Random random = new Random();
            PositionModel pos = new PositionModel(random.nextInt(19 - 11) + 11, random.nextInt(14 - 6) + 6);
            animal.setPos(pos);
        }
    }
}
