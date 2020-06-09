package game.auxiliary.generator;

import game.auxiliary.collision;
import game.models.*;
import game.models.elements.AnimalModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GenerateAnimal implements RandomGenerator {

    private boolean first;

    public GenerateAnimal(boolean first) {
        this.first = first;
    }
    public GenerateAnimal() {this.first = false;}

    @Override
    public List<AnimalModel> generate(FarmModel fm, int num) {
        List<AnimalModel> animals = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i<num; i++) {
            PositionModel position;
            if(this.first) position = new PositionModel(random.nextInt(fm.getWidth() - 2) + 1, random.nextInt(fm.getHeight() - 2) + 1);
            else position = new PositionModel(random.nextInt(19 - 11) + 11, random.nextInt(14 - 6) + 6);
                if(collision.marketCollision(fm, position) && !collision.rugCollision(fm,position))
                animals.add(new AnimalModel(position.getX(), position.getY()));
                else
                    i--;
        }
        return animals;
    }
}
