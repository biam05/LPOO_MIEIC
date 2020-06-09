package game.auxiliary.generator;

import game.auxiliary.collision;
import game.models.*;
import game.models.elements.plants.FlowerModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GenerateFlower implements RandomGenerator {
    @Override
    public List<FlowerModel> generate(FarmModel fm, int num) {
        List<FlowerModel> flowers = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i<num; i++) {
            PositionModel position = new PositionModel(random.nextInt(fm.getWidth() - 2) + 1, random.nextInt(fm.getHeight() - 2) + 1);
            if(collision.marketCollision(fm, position) && !collision.rugCollision(fm,position) && collision.elementCollision(fm, position))
                flowers.add(new FlowerModel(position.getX(), position.getY()));
            else
                i--;
        }
        return flowers;
    }
}